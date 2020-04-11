package dragonArmy;

public class Dragon {

    private String type;
    private String name;
    private int damage;
    private int health;
    private int armor;

    public Dragon(String type, String name, int damage, int health, int armor) {
        this.type = type;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.armor = armor;
    }

    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getHealth() {
        return this.health;
    }

    public int getArmor() {
        return this.armor;
    }
}
