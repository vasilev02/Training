package softuni.library.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "libraries")
public class Library extends BaseEntity{

    private String name;
    private String location;
    private int rating;
    private Set<Book> books;

    public Library() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "location", nullable = false)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "libraries_books",
    joinColumns = @JoinColumn(name = "library_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
