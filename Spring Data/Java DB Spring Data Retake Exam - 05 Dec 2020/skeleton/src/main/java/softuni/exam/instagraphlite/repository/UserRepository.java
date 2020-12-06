package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entities.User;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUsernameEquals(String username);

    @Query(value = "SELECT u FROM User AS u ORDER BY u.posts.size DESC, u.id ASC")
    Set<User> getUsersExport();
}
