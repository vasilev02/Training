package softuni.library.models.dto.xml;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryXmlDto implements Serializable {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "location")
    private String location;

    @XmlElement(name = "rating")
    private int rating;

    @XmlElement(name = "book")
    private BookXmlDto book;

    public LibraryXmlDto() {
    }

    @Length(min = 3)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 5)
    @NotNull
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Min(value = 1)
    @Max(value = 10)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public BookXmlDto getBook() {
        return book;
    }

    public void setBook(BookXmlDto book) {
        this.book = book;
    }
}
