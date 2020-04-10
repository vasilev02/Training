package rabbits;

public class Rabbit {

    private String name;
    private String species;
    private boolean available = true;

    public Rabbit(String name , String species){
        this.name = name;
        this.species=species;
    }

    public String getName() {
        return this.name;
    }

    public String getSpecies() {
        return this.species;
    }

    public boolean isAvailable(){
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Rabbit (%s): %s",getSpecies(),getName()));
        sb.append(System.lineSeparator());
        return sb.toString().trim();
    }
}
