package softuni.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamRootDto implements Serializable {

    @XmlElement(name = "team")
    private Set<TeamDto> teamDtos;

    public TeamRootDto() {
        this.teamDtos = new LinkedHashSet<>();
    }

    public Set<TeamDto> getTeamDtos() {
        return teamDtos;
    }

    public void setTeamDtos(Set<TeamDto> teamDtos) {
        this.teamDtos = teamDtos;
    }
}
