package softuni.exam.models.dto.xmlImports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferRootDto implements Serializable {

    @XmlElement(name = "offer")
    private Set<OfferImportDto> offerImportDtos;

    public OfferRootDto() {
    }

    public Set<OfferImportDto> getOfferImportDtos() {
        return offerImportDtos;
    }

    public void setOfferImportDtos(Set<OfferImportDto> offerImportDtos) {
        this.offerImportDtos = offerImportDtos;
    }
}
