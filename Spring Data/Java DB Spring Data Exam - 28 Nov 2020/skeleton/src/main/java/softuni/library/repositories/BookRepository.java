package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.library.models.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Book findBookById(int id);

}
