package softuni.library.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "characters")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterRootDto implements Serializable {

    @XmlElement(name = "character")
    private List<CharacterXmlDto> characterXmlDtos;

    public CharacterRootDto() {
    }

    public List<CharacterXmlDto> getCharacterXmlDtos() {
        return characterXmlDtos;
    }

    public void setCharacterXmlDtos(List<CharacterXmlDto> characterXmlDtos) {
        this.characterXmlDtos = characterXmlDtos;
    }
}
