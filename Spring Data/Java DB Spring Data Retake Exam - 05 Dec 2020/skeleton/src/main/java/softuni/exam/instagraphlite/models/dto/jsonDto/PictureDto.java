package softuni.exam.instagraphlite.models.dto.jsonDto;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PictureDto implements Serializable {

    @Expose
    private String path;

    @Expose
    private double size;

    public PictureDto() {
    }

    @Column(name = "path", unique = true)
    @NotNull
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "size")
    @NotNull
    @Min(value = 500)
    @Max(value = 60000)
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
