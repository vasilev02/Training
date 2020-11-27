package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Player;

import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {

    @Query(value = "SELECT p FROM Player AS p WHERE p.team.name = 'North Hub' ORDER BY p.id ASC")
    Set<Player> getPlayersFromFromNorthHub();

    @Query(value = "SELECT p FROM Player AS p WHERE p.salary > 100000 ORDER BY p.salary DESC")
    Set<Player> getPlayers();

}
