package heroRepository;

public class Hero {

    private String name;
    private int level;
    private Item item;

    public Hero(String name,int level,Item item){
        this.name=name;
        this.level=level;
        this.item=item;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public Item getItem() {
        return this.item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Hero: %s – %d",getName(),getLevel()));
        sb.append(System.lineSeparator());

        sb.append(String.format("  *  Strength: %d",this.item.getStrength()));
        sb.append(System.lineSeparator());

        sb.append(String.format("  *  Agility: %d",this.item.getAgility()));
        sb.append(System.lineSeparator());

        sb.append(String.format("  *  Intelligence: %d",this.item.getIntelligence()));
        sb.append(System.lineSeparator());

        return sb.toString().trim();
    }
}
