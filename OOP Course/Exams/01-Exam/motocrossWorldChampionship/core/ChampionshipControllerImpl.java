package motocrossWorldChampionship.core;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.common.OutputMessages;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.RaceImpl;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ChampionshipControllerImpl implements ChampionshipController {

    private Repository<Rider> riderRepository;
    private Repository<Motorcycle> motorcycleRepository;
    private Repository<Race> raceRepository;

    public ChampionshipControllerImpl(Repository<Rider> riderRepository,Repository<Motorcycle> motorcycleRepository, Repository<Race> raceRepository) {
        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createRider(String riderName) {

        for (Rider rider : riderRepository.getAll()) {

            if(rider.getName().equals(riderName)){
                throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_EXISTS,riderName));
            }
        }
        Rider rider = new RiderImpl(riderName);
        this.riderRepository.add(rider);

        return String.format(OutputMessages.RIDER_CREATED,riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {

        if(this.riderRepository.getByName(model)!=null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_EXISTS,model));
        }


        Motorcycle motorcycle = null;

        String message = "";

        if(type.equals("Speed")){
            motorcycle = new SpeedMotorcycle(model,horsePower);
            message = "SpeedMotorcycle "+ model +" is created.";
        }else if(type.equals("Power")){
            message = "PowerMotorcycle "+ model +" is created.";
            motorcycle = new PowerMotorcycle(model,horsePower);
        }
        
        this.motorcycleRepository.add(motorcycle);

        return message;
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {

        Motorcycle motorcycle = null;
        Rider rider = null;

        for (Motorcycle current : motorcycleRepository.getAll()) {
            if(current.getModel().equals(motorcycleModel)){
                motorcycle = current;
                break;
            }
        }

        for (Rider current : riderRepository.getAll()) {
            if(current.getName().equals(riderName)){
                rider = current;
                break;
            }
        }

        if(motorcycle==null){
            throw new NullPointerException(String.format(ExceptionMessages.MOTORCYCLE_NOT_FOUND,motorcycleModel));
        }
        if(rider==null){
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND,riderName));
        }

        rider.addMotorcycle(motorcycle);

        return String.format(OutputMessages.MOTORCYCLE_ADDED,riderName,motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {

        Race race = null;
        Rider rider = null;

        for (Race current : raceRepository.getAll()) {
            if(current.getName().equals(raceName)){
                race = current;
            }
        }

        for (Rider current : riderRepository.getAll()) {
            if(current.getName().equals(riderName)){
                rider = current;
            }
        }

        if (race==null){
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }
        if (rider==null){
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND,riderName));
        }

        race.addRider(rider);

        return String.format(OutputMessages.RIDER_ADDED,riderName,raceName);
    }

    @Override
    public String startRace(String raceName) {

        Race race = null;

        for (Race current : this.raceRepository.getAll()) {
            if(current.getName().equals(raceName)){
                race = current;
                break;
            }
        }

        if(race == null){
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }

        int labs = race.getLaps();

        Collection<Rider> riders = race.getRiders();

        if(riders.size()<3){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID,raceName,3));
        }

        List<Rider> winners = riders.stream().sorted((a, b) -> Double.compare(b.getMotorcycle().calculateRacePoints(labs)
                , a.getMotorcycle().calculateRacePoints(labs))).limit(3).collect(Collectors.toList());

        winners.get(0).winRace();

        StringBuilder sb= new StringBuilder();

        sb.append(String.format("Rider %s wins %s race.",winners.get(0).getName(),raceName));
        sb.append(System.lineSeparator());
        sb.append(String.format("Rider %s is second in %s race.",winners.get(1).getName(),raceName));
        sb.append(System.lineSeparator());
        sb.append(String.format("Rider %s is third in %s race.",winners.get(2).getName(),raceName));
        sb.append(System.lineSeparator());

        this.raceRepository.remove(race);

        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {

        Race race = new RaceImpl(name,laps);

        for (Race current : raceRepository.getAll()) {
            if(current.getName().equals(name)){
                throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS,name));
            }
        }
        this.raceRepository.add(race);

        return String.format(OutputMessages.RACE_CREATED,name);
    }
}
