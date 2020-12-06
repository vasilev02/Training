package softuni.exam.instagraphlite.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity{

    private String path;
    private double size;
    private Set<User> users;
    private Set<Post> posts;

    public Picture() {
    }

    @Column(name = "path", unique = true, nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "size", nullable = false)
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @OneToMany(mappedBy = "profilePicture", targetEntity = User.class)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @OneToMany(mappedBy = "picture", targetEntity = Post.class)
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
