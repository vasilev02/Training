package softuni.exam.instagraphlite.models.dto.jsonDto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserDto implements Serializable {

    @Expose
    private String username;

    @Expose
    private String password;

    @Expose
    private String profilePicture;

    public UserDto() {
    }

    @Column(name = "username", unique = true)
    @NotNull
    @Length(min = 2, max = 18)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    @NotNull
    @Length(min = 4)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "profile_picture")
    @NotNull
    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
