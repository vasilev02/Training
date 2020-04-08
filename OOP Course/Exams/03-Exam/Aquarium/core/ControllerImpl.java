package aquarium.core;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.models.aquariums.Aquarium;
import aquarium.models.aquariums.FreshwaterAquarium;
import aquarium.models.aquariums.SaltwaterAquarium;
import aquarium.models.decorations.Decoration;
import aquarium.models.decorations.Ornament;
import aquarium.models.decorations.Plant;
import aquarium.models.fish.Fish;
import aquarium.models.fish.FreshwaterFish;
import aquarium.models.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {

        Aquarium aquarium = null;

        if(aquariumType.equals("FreshwaterAquarium")){
            aquarium = new FreshwaterAquarium(aquariumName);
        }else if(aquariumType.equals("SaltwaterAquarium")){
            aquarium = new SaltwaterAquarium(aquariumName);
        }else{
            throw new IllegalArgumentException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }

        this.aquariums.add(aquarium);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE,aquariumType);
    }

    @Override
    public String addDecoration(String type) {

        Decoration decoration = null;

        if(type.equals("Ornament")){
            decoration = new Ornament();
        }else if(type.equals("Plant")){
            decoration = new Plant();
        }else{
            throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }

        this.decorations.add(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE,type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {

        Aquarium aquarium = null;
        for (Aquarium current : aquariums) {
            if(current.getName().equals(aquariumName)){
                aquarium = current;
                break;
            }
        }

        Decoration decoration = null;

        decoration = decorations.findByType(decorationType);

        if(decoration==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND,decorationType));
        }

        aquarium.addDecoration(decoration);
        this.decorations.remove(decoration);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM,decorationType,aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {

        Fish fish = null;

        if(fishType.equals("FreshwaterFish")){
            fish = new FreshwaterFish(fishName,fishSpecies,price);
        }else if(fishType.equals("SaltwaterFish")){
            fish = new SaltwaterFish(fishName,fishSpecies,price);
        }else{
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }

        Aquarium aquarium = null;

        for (Aquarium current : aquariums) {
            if(current.getName().equals(aquariumName)){
                aquarium = current;
            }
        }

        if(aquarium.getClass().getSimpleName().equals("FreshwaterAquarium") && fish.getClass().getSimpleName().equals("FreshwaterFish")){
            aquarium.addFish(fish);

        }else if(aquarium.getClass().getSimpleName().equals("SaltwaterAquarium")&& fish.getClass().getSimpleName().equals("SaltwaterFish")){
            aquarium.addFish(fish);
        }else{
            return String.format(ConstantMessages.WATER_NOT_SUITABLE);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM,fishName,aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {

        int counter=0;

        Aquarium aquarium = aquariums.stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);

        if(aquarium==null){
            return String.format(ConstantMessages.FISH_FED,counter);
        }else{

            for (Aquarium current : aquariums) {
                current.feed();
                counter++;
            }
        }

        return String.format(ConstantMessages.FISH_FED,counter);
    }

    @Override
    public String calculateValue(String aquariumName) {

        double price = 0.0;

        Aquarium aquarium = this.aquariums.stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);

        for (Decoration decoration : aquarium.getDecorations()) {
            price+=decoration.getPrice();
        }
        for (Fish fish : aquarium.getFish()) {
            price+=fish.getPrice();
        }

        return String.format("The value of Aquarium %s is %.2f.",aquariumName,price);
    }

    @Override
    public String report() {

        StringBuilder sb= new StringBuilder();

        for (Aquarium aquarium : aquariums) {
            sb.append(aquarium.getInfo());
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
