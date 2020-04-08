package aquarium.models.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseAquarium implements Aquarium {

    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if(name==null || name.trim().isEmpty()){
            throw new NullPointerException("Aquarium name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {

        int comfort = 0;

        for (Decoration decoration : decorations) {
            comfort+=decoration.getComfort();
        }

        return comfort;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {

        if(this.fish.size()==this.capacity){
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {

        for (Fish current : fish) {
            current.eat();
        }

    }

    @Override
    public String getInfo() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%s):",this.name,Aquarium.class.getSimpleName()));
        sb.append(System.lineSeparator());

        sb.append("Fish: ");

        if(this.fish.size()==0){
            sb.append("none");

        }else{
            for (Fish current : fish) {
                sb.append(current.getName()+" ");
            }
        }

        sb.append(System.lineSeparator());

        sb.append("Decorations: "+this.decorations.size());
        sb.append(System.lineSeparator());

        sb.append("Comfort: "+this.calculateComfort());

        sb.append(System.lineSeparator());
        return sb.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return Collections.unmodifiableCollection(this.fish);
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(this.decorations);
    }
}
