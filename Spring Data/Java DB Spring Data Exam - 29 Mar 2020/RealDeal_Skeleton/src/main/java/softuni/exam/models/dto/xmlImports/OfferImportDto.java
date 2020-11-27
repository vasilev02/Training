package softuni.exam.models.dto.xmlImports;

import org.hibernate.validator.constraints.Length;
import softuni.exam.adaptors.LocalDateTimeAdaptor;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Seller;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportDto implements Serializable {

    @XmlElement(name = "price")
    @Min(value = 0)
    private BigDecimal price;

    @XmlElement(name = "description")
    @Length(min = 5)
    private String description;

    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;

    @XmlElement(name = "added-on")
    @XmlJavaTypeAdapter(LocalDateTimeAdaptor.class)
    private LocalDateTime addedOn;

    @XmlElement(name = "car")
    private CarImportDto car;

    @XmlElement(name = "seller")
    private SellerImportDto seller;

    public OfferImportDto() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public CarImportDto getCar() {
        return car;
    }

    public void setCar(CarImportDto car) {
        this.car = car;
    }

    public SellerImportDto getSeller() {
        return seller;
    }

    public void setSeller(SellerImportDto seller) {
        this.seller = seller;
    }
}
