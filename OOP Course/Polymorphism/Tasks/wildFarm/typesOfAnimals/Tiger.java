package typesOfAnimals;

import wildFarm.Food;

import java.text.DecimalFormat;

public class Tiger extends Felime {

    private String livingRegion;

    public Tiger(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {

        if (food.getClass().getSimpleName().equals("Meat")){
            this.setFoodEaten(this.getFoodEaten()+food.getQuantity());
        }else{
            System.out.println("Tigers are not eating that type of food!");
        }

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        DecimalFormat format = new DecimalFormat("#.##");

        sb.append(String.format("%s[%s, " + format.format(getAnimalWeight()),getAnimalName(),getAnimalType()));

        sb.append(String.format(", %s, %d] ",getLivingRegion(),getFoodEaten()));

        return sb.toString().trim();

    }
}
