package santasWorkshop.models;

public class Sleepy extends BaseDwarf {

    private static final int INITIAL_ENERGY_SLEEPY = 50;

    public Sleepy(String name) {
        super(name,INITIAL_ENERGY_SLEEPY );
    }

    @Override
    public void work() {

        int value = this.getEnergy()-15;

        if(value<0){
            this.setEnergy(0);
        }else{
            this.setEnergy(value);
        }
    }

}
