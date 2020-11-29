package softuni.library.models.dto.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import softuni.library.adaptors.LocalDateAdaptor;
import softuni.library.models.entities.Author;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

public class BookDto implements Serializable {

    @Expose
    private String name;

    @Expose
    private int edition;

    @Expose
    private LocalDate written;

    @Expose
    private String description;

    @Expose
    private int author;

    public BookDto() {
    }

    @Column(name = "name")
    @NotNull
    @Length(min = 5)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "edition")
    @Min(value = 1)
    @Max(value = 5)
    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @Column(name = "written")
    @NotNull
    public LocalDate getWritten() {
        return written;
    }

    public void setWritten(LocalDate written) {
        this.written = written;
    }

    @Column(name = "description")
    @Length(min = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "author")
    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
