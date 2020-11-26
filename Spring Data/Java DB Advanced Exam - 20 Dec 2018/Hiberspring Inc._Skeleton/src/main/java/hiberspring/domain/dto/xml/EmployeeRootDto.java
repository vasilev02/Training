package hiberspring.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRootDto implements Serializable {

    @XmlElement(name = "employee")
    private List<EmployeeDto> employeeDtos;

    public EmployeeRootDto() {
    }

    public List<EmployeeDto> getEmployeeDtos() {
        return employeeDtos;
    }

    public void setEmployeeDtos(List<EmployeeDto> employeeDtos) {
        this.employeeDtos = employeeDtos;
    }
}
