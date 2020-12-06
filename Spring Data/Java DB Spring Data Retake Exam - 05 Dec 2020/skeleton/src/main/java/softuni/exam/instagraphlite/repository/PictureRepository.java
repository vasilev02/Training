package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entities.Picture;

import java.util.Set;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Picture findPictureByPathEquals(String path);

    @Query(value = "SELECT p FROM Picture AS p WHERE p.size > 30000 ORDER BY p.size ASC")
    Set<Picture> getPicturesExport();
}
