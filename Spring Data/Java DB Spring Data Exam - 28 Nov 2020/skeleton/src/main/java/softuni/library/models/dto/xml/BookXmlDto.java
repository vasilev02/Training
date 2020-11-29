package softuni.library.models.dto.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookXmlDto implements Serializable {

    @XmlElement(name = "id")
    private int id;

    public BookXmlDto() {
    }

    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
