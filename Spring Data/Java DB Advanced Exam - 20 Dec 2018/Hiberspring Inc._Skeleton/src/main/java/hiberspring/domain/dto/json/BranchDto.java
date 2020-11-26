package hiberspring.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BranchDto implements Serializable {

    @Expose
    private String name;

    @Expose
    private String town;

    public BranchDto() {
    }

    @Column(name = "name", nullable = false)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "town", nullable = false)
    @NotNull
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
