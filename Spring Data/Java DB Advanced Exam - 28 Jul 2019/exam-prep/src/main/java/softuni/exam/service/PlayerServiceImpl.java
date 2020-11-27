package softuni.exam.service;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.json.PlayerDto;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Position;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.util.ValidationUtil;


import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;


@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final static String PLAYERS_PATH = "src/main/resources/files/json/players.json";

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public String importPlayers() throws IOException {

        StringBuilder sb = new StringBuilder();

        PlayerDto[] data = this.gson.fromJson(this.readPlayersJsonFile(),PlayerDto[].class);

        for (PlayerDto current : data) {

            Position position;
            try {
                position = Position.valueOf(current.getPosition().name());
            }catch (Exception e){
                sb.append("Invalid player");
                sb.append(System.lineSeparator());
                continue;
            }

            if(validationUtil.isValid(current)){

                Player player = this.modelMapper.map(current,Player.class);

                player.setPosition(position);

                sb.append(String.format("Successfully imported player: %s %s",player.getFirstName(),player.getLastName()));

                this.playerRepository.saveAndFlush(player);

            }else{

                sb.append("Invalid player");

            }
            sb.append(System.lineSeparator());


        }

        return sb.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PLAYERS_PATH)));
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {


        StringBuilder sb = new StringBuilder();

        Set<Player> data = this.playerRepository.getPlayers();

        for (Player current : data) {

            sb.append(String.format("Player name: %s %s",current.getFirstName(),current.getLastName()));

            sb.append(System.lineSeparator());

            sb.append(String.format("\tNumber: %s",current.getNumber()));
            sb.append(System.lineSeparator());

            sb.append(String.format("\tSalary: %s",current.getSalary()));
            sb.append(System.lineSeparator());

            sb.append(String.format("\tTeam: %s",current.getTeam().getName()));
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();


    }

    @Override
    public String exportPlayersInATeam() {

        StringBuilder sb = new StringBuilder();

        Set<Player> data = this.playerRepository.getPlayersFromFromNorthHub();

        sb.append("Team: North Hub").append(System.lineSeparator());

        for (Player current : data) {

            sb.append(String.format("\tPlayer name: %s %s - %s",current.getFirstName(),current.getLastName(),current.getPosition()));

            sb.append(System.lineSeparator());

            sb.append(String.format("\tNumber: %s",current.getNumber()));

            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }


}
