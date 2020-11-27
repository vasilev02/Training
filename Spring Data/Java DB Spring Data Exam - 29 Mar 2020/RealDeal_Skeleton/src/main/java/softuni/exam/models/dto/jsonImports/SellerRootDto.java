package softuni.exam.models.dto.jsonImports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerRootDto implements Serializable {

    @XmlElement(name = "seller")
    private Set<SellerImportDto> sellerImportDtos;

    public SellerRootDto() {
    }

    public Set<SellerImportDto> getSellerImportDtos() {
        return sellerImportDtos;
    }

    public void setSellerImportDtos(Set<SellerImportDto> sellerImportDtos) {
        this.sellerImportDtos = sellerImportDtos;
    }
}
