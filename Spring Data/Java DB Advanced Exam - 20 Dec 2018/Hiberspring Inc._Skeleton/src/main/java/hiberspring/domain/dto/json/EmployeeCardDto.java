package hiberspring.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EmployeeCardDto implements Serializable {

    @Expose
    private String number;

    public EmployeeCardDto() {
    }

    @Column(name = "number", unique = true)
    @NotNull
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
