package guild;

public class Player {

    private String name;
    private String clazz;
    private String rank;
    private String description;

    public Player(String name, String clazz) {
        this.setName(name);
        this.setClazz(clazz);
        this.setRank("Trial");
        this.setDescription("n/a");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Player %s: %s",this.getName(),this.getClazz()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Rank: %s",this.getRank()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Description: %s",this.getDescription()));
        sb.append(System.lineSeparator());

        return sb.toString().trim();
    }
}
