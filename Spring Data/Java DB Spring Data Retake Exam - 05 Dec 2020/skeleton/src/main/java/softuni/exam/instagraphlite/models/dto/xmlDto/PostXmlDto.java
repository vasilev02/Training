package softuni.exam.instagraphlite.models.dto.xmlDto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostXmlDto implements Serializable {

    @XmlElement(name = "caption")
    private String caption;

    @XmlElement(name = "user")
    private UserXmlDto user;

    @XmlElement(name = "picture")
    private PictureXmlDto picture;

    public PostXmlDto() {
    }

    @NotNull
    @Length(min = 21)
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public UserXmlDto getUser() {
        return user;
    }

    public void setUser(UserXmlDto user) {
        this.user = user;
    }

    public PictureXmlDto getPicture() {
        return picture;
    }

    public void setPicture(PictureXmlDto picture) {
        this.picture = picture;
    }
}
