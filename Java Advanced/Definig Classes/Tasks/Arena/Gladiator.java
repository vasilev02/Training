package arena;

public class Gladiator {

    private String name;
    private Stat stat;
    private Weapon weapon;

    public Gladiator(String name,Stat stat,Weapon weapon){
        this.name=name;
        this.stat=stat;
        this.weapon=weapon;
    }

    public String getName() {
        return this.name;
    }

    public int getStatPower(){

        int counter = 0;
        counter+=this.stat.getStrength();
        counter+=this.stat.getFlexibility();
        counter+=this.stat.getAgility();
        counter+=this.stat.getSkills();
        counter+=this.stat.getIntelligence();

        return counter;
    }

    public int getWeaponPower(){

        int counter = 0;
        counter+=this.weapon.getSharpness();
        counter+=this.weapon.getSolidity();
        counter+=this.weapon.getSize();

        return counter;
    }

    public int getTotalPower(){

        return getStatPower()+getWeaponPower();

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s – %d",getName(),getTotalPower()));
        sb.append(System.lineSeparator());

        sb.append(String.format("  Weapon Power: %d",getWeaponPower()));
        sb.append(System.lineSeparator());

        sb.append(String.format("  Stat Power: %d",getStatPower()));
        sb.append(System.lineSeparator());

        return sb.toString().trim();
    }
}
