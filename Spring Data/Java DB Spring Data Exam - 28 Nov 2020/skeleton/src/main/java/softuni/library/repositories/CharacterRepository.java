package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.library.models.entities.Character;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Integer> {

    @Query(value = "SELECT c FROM Character AS c WHERE c.age >= 32 ORDER BY c.book.name, c.lastName DESC, c.age")
    List<Character> getCharacters();

}
