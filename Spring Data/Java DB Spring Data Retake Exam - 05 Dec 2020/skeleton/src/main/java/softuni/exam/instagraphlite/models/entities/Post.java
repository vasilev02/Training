package softuni.exam.instagraphlite.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity{

    private String caption;
    private User user;
    private Picture picture;

    public Post() {
    }

    @Column(name = "caption", nullable = false)
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "picture_id", referencedColumnName = "id")
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
