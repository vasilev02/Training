package softuni.library.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "libraries")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryRootDto implements Serializable {

    @XmlElement(name = "library")
    private List<LibraryXmlDto> libraryXmlDtos;

    public LibraryRootDto() {
    }

    public List<LibraryXmlDto> getLibraryXmlDtos() {
        return libraryXmlDtos;
    }

    public void setLibraryXmlDtos(List<LibraryXmlDto> libraryXmlDtos) {
        this.libraryXmlDtos = libraryXmlDtos;
    }
}
