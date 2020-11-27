package softuni.exam.models.dto.jsonImports;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PictureImportDto implements Serializable {

    @Expose
    @Length(min = 2, max = 20)
    private String name;

    @Expose
    private LocalDateTime dateAndTime;

    @Expose
    private int car;

    public PictureImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String make) {
        this.name = make;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }
}
