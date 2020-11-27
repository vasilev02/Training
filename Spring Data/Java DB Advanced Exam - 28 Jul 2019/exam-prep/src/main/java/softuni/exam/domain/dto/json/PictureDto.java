package softuni.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PictureDto implements Serializable {

    @Expose
    private String url;

    public PictureDto() {
    }

    @NotNull
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
