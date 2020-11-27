package softuni.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureRootDto implements Serializable {

    @XmlElement(name = "picture")
    private Set<PictureDto> pictureDtos;

    public PictureRootDto() {
        pictureDtos = new LinkedHashSet<>();
    }

    public Set<PictureDto> getPictureDtos() {
        return pictureDtos;
    }

    public void setPictureDtos(Set<PictureDto> pictureDtos) {
        this.pictureDtos = pictureDtos;
    }
}
