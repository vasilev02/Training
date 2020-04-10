package heroRepository;

public class Item {

    private int strength;
    private int agility;
    private int intelligence;

    public Item(int strength,int agility,int intelligence){
        this.strength=strength;
        this.agility=agility;
        this.intelligence=intelligence;
    }

    public int getStrength(){
        return this.strength;
    }

    public int getAgility(){
        return this.agility;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Item:");
        sb.append(System.lineSeparator());

        sb.append(String.format("  *  Strength: %d",getStrength()));
        sb.append(System.lineSeparator());

        sb.append(String.format("  *  Agility: %d",getAgility()));
        sb.append(System.lineSeparator());

        sb.append(String.format("  *  Intelligence: %d",getIntelligence()));
        sb.append(System.lineSeparator());

        return sb.toString().trim();
    }
}
