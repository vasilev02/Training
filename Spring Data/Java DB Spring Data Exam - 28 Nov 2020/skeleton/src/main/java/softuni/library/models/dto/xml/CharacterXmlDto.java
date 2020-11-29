package softuni.library.models.dto.xml;

import org.hibernate.validator.constraints.Length;
import softuni.library.adaptors.LocalDateAdaptor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "character")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterXmlDto implements Serializable {

    @XmlElement(name = "first-name")
    private String firstName;

    @XmlElement(name = "middle-name")
    private String middleName;

    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement(name = "age")
    private int age;

    @XmlElement(name = "role")
    private String role;

    @XmlElement(name = "birthday")
    @XmlJavaTypeAdapter(LocalDateAdaptor.class)
    private LocalDate birthday;

    @XmlElement(name = "book")
    private BookXmlDto book;

    public CharacterXmlDto() {
    }

    @Column(name = "first_name")
    @NotNull
    @Length(min = 3)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "middle_name")
    @NotNull
    @Length(min = 1, max = 1)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "last_name")
    @NotNull
    @Length(min = 3)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    @NotNull
    @Min(value = 10)
    @Max(value = 66)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "role")
    @NotNull
    @Length(min = 5)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "birthday")
    @NotNull
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthDay(LocalDate birthday) {
        this.birthday = birthday;
    }

    public BookXmlDto getBook() {
        return book;
    }

    public void setBook(BookXmlDto book) {
        this.book = book;
    }
}
