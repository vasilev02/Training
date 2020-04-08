package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;

public class SpeedMotorcycle extends MotorcycleImpl {


    public SpeedMotorcycle(String model, int horsePower) {
        super(model, horsePower, 125);
    }


    @Override
    protected void setHorsePower(int horsePower) {

        if(horsePower<50 || horsePower>69){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower));
        }

        super.setHorsePower(horsePower);
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public int getHorsePower() {
        return super.getHorsePower();
    }

    @Override
    public double getCubicCentimeters() {
        return super.getCubicCentimeters();
    }

    @Override
    public double calculateRacePoints(int laps) {
        return super.calculateRacePoints(laps);
    }
}
