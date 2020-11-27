package softuni.exam.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.xml.TeamDto;
import softuni.exam.domain.dto.xml.TeamRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;


import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final static String TEAMS_PATH = "src/main/resources/files/xml/teams.xml";

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final PictureRepository pictureRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, PictureRepository pictureRepository) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.pictureRepository = pictureRepository;
    }

    @Override
    
    public String importTeams() throws JAXBException, FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        TeamRootDto data = this.xmlParser.importFromXml(TeamRootDto.class, TEAMS_PATH);

        for (TeamDto teamDto : data.getTeamDtos()) {

            Picture picture = this.pictureRepository.findByUrl(teamDto.getPictureDto().getUrl());

            Team findTeam = this.teamRepository.findTeamByName(teamDto.getName());

            if(validationUtil.isValid(teamDto) && picture != null && findTeam == null){

                Team team = this.modelMapper.map(teamDto,Team.class);

                team.setPicture(picture);

                sb.append(String.format("Successfully imported - %s",team.getName()));

                this.teamRepository.saveAndFlush(team);

            }else{

                sb.append("Invalid team");

            }
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TEAMS_PATH)));
    }

}
