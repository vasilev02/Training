package softuni.exam.models.dto.xml;

import org.hibernate.validator.constraints.Length;
import softuni.exam.adaptors.LocalDateTimeAdaptor;


import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketDto implements Serializable {

    @Column(unique = true)
    @XmlElement(name = "serial-number")
    @Length(min = 2)
    private String serialNumber;

    @XmlElement(name = "price")
    @Min(value = 0)
    private BigDecimal price;

    @XmlElement(name = "take-off")
    @XmlJavaTypeAdapter(LocalDateTimeAdaptor.class)
    private LocalDateTime takeOff;

    @XmlElement(name = "from-town")
    private FromTownDto fromTownDto;

    @XmlElement(name = "to-town")
    private ToTownDto toTownDto;

    @XmlElement(name = "passenger")
    private PassengerDto passengerDto;

    @XmlElement(name = "plane")
    private PlaneDto planeDto;

    public TicketDto() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public FromTownDto getFromTownDto() {
        return fromTownDto;
    }

    public void setFromTownDto(FromTownDto fromTownDto) {
        this.fromTownDto = fromTownDto;
    }

    public ToTownDto getToTownDto() {
        return toTownDto;
    }

    public void setToTownDto(ToTownDto toTownDto) {
        this.toTownDto = toTownDto;
    }

    public PassengerDto getPassengerDto() {
        return passengerDto;
    }

    public void setPassengerDto(PassengerDto passengerDto) {
        this.passengerDto = passengerDto;
    }

    public PlaneDto getPlaneDto() {
        return planeDto;
    }

    public void setPlaneDto(PlaneDto planeDto) {
        this.planeDto = planeDto;
    }

    public LocalDateTime getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(LocalDateTime takeOff) {
        this.takeOff = takeOff;
    }
}
