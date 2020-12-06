package softuni.exam.instagraphlite.models.dto.xmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostRootXmlDto implements Serializable {

    @XmlElement(name = "post")
    private List<PostXmlDto> postXmlDtos;

    public List<PostXmlDto> getPostXmlDtos() {
        return postXmlDtos;
    }

    public void setPostXmlDtos(List<PostXmlDto> postXmlDtos) {
        this.postXmlDtos = postXmlDtos;
    }
}
