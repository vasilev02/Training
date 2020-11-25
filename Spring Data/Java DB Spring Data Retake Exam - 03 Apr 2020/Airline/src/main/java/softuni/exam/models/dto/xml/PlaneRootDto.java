package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneRootDto implements Serializable {

    @XmlElement(name = "plane")
    private List<PlaneDto> planeDtos;

    public PlaneRootDto() {
    }

    public List<PlaneDto> getPlaneDtos() {
        return planeDtos;
    }

    public void setPlaneDtos(List<PlaneDto> planeDtos) {
        this.planeDtos = planeDtos;
    }
}
