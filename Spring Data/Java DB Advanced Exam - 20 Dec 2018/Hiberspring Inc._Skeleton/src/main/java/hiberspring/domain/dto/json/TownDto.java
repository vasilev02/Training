package hiberspring.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TownDto implements Serializable {

    @Expose
    private String name;

    @Expose
    private int population;

    public TownDto() {
    }

    @Column(name = "name", nullable = false)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "population", nullable = false)
    @NotNull
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
