package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.json.BranchDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final TownRepository townRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, TownRepository townRepository) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        Path path = Paths.get(GlobalConstants.BRANCHES_FILE_PATH);
        return Files.readAllLines(path).toString();
    }

    @Override
    public String importBranches(String branchesFileContent) throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        BranchDto[] data = this.gson.fromJson(new FileReader(GlobalConstants.BRANCHES_FILE_PATH), BranchDto[].class);

        for (BranchDto current : data) {

            if (validationUtil.isValid(current)) {

                Branch branch = this.modelMapper.map(current,Branch.class);

                Town town = this.townRepository.findTownByName(current.getTown());

                branch.setTown(town);

                this.branchRepository.saveAndFlush(branch);

                sb.append(String.format(GlobalConstants.SUCCESSFUL_IMPORT_MESSAGE,town.getName(), branch.getName()));

            } else {
                sb.append(GlobalConstants.INCORRECT_DATA_MESSAGE);
            }
            sb.append(System.lineSeparator());


        }

        return sb.toString();
    }
}
