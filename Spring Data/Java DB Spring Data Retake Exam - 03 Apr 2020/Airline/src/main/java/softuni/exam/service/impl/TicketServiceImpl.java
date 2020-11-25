package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.TicketDto;
import softuni.exam.models.dto.xml.TicketRootDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {

    private final static String TICKETS_PATH = "src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;
    private final TownRepository townRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, ValidationUtil validationUtil, XmlParser xmlParser, ModelMapper modelMapper, PassengerRepository passengerRepository, PlaneRepository planeRepository, TownRepository townRepository) {
        this.ticketRepository = ticketRepository;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        Path path = Paths.get(TICKETS_PATH);
        return Files.readAllLines(path).toString();
    }


    @Override
    public String importTickets() throws JAXBException {

        StringBuilder sb = new StringBuilder();

        TicketRootDto data = this.xmlParser.parseXml(TicketRootDto.class, TICKETS_PATH);



        for (TicketDto current : data.getTicketDtos()) {

            Ticket findTicket = this.ticketRepository.findTicketBySerialNumber(current.getSerialNumber());

            if(validationUtil.isValid(current) && findTicket == null){

                Ticket ticket = this.modelMapper.map(current, Ticket.class);

                Town toTown = this.townRepository.findByName(current.getToTownDto().getName());
                Town fromTown = this.townRepository.findByName(current.getFromTownDto().getName());
                Passenger passenger = this.passengerRepository.findPassengerByEmail(current.getPassengerDto().getEmail());
                Plane plane = this.planeRepository.findPlaneByRegisterNumber(current.getPlaneDto().getRegisterNumber());

                ticket.setToTown(toTown);
                ticket.setFromTown(fromTown);
                ticket.setPassenger(passenger);
                ticket.setPlane(plane);


                sb.append(String.format("Successfully imported Ticket - %s %s",ticket.getFromTown().getName(),ticket.getToTown().getName()));

                this.ticketRepository.saveAndFlush(ticket);

            }else{

                sb.append("Invalid ticket");

            }
            sb.append(System.lineSeparator());

        }

        return sb.toString();
    }
}
