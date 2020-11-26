package hiberspring.service.impl;

import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.xml.EmployeeDto;
import hiberspring.domain.dto.xml.EmployeeRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository employeeCardRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, BranchRepository branchRepository, EmployeeCardRepository employeeCardRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        Path path = Paths.get(GlobalConstants.EMPLOYEES_FILE_PATH);
        return Files.readAllLines(path).toString();
    }

    @Override
    public String importEmployees() throws JAXBException, FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        EmployeeRootDto data = this.xmlParser.parseXml(EmployeeRootDto.class, GlobalConstants.EMPLOYEES_FILE_PATH);

        for (EmployeeDto current : data.getEmployeeDtos()) {

            Employee findEmployee = this.employeeRepository.findEmployeeByCard_Number(current.getCard());

            if (validationUtil.isValid(current) && findEmployee == null) {

                Employee employee = this.modelMapper.map(current, Employee.class);

                Branch branch = this.branchRepository.findBranchByName(current.getBranch());

                if (branch == null) {
                    sb.append(GlobalConstants.INCORRECT_DATA_MESSAGE);
                    sb.append(System.lineSeparator());
                    continue;
                }

                EmployeeCard card = this.employeeCardRepository.findEmployeeCardByNumber(current.getCard());

                employee.setCard(card);
                employee.setBranch(branch);

                this.employeeRepository.saveAndFlush(employee);

                String fullName = employee.getFirstName() + " " + employee.getLastName();
                sb.append(String.format(GlobalConstants.SUCCESSFUL_IMPORT_MESSAGE, "Employee", fullName));

            } else {
                sb.append(GlobalConstants.INCORRECT_DATA_MESSAGE);
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String exportProductiveEmployees() {

        StringBuilder sb = new StringBuilder();

        Set<Employee> data = this.employeeRepository.employeesInfo();

        for (Employee current : data) {

            sb.append(String.format("Name: %s", current.getFirstName() + " " + current.getLastName()));
            sb.append(System.lineSeparator());

            sb.append(String.format("Position: %s", current.getPosition()));
            sb.append(System.lineSeparator());

            sb.append(String.format("Card Number: %s", current.getCard().getNumber()));
            sb.append(System.lineSeparator());

            sb.append("-----------------------");
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}
