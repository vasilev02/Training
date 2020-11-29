package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.library.models.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findAuthorById(int id);

    Author findAuthorByFirstNameAndLastName(String first, String last);

}
