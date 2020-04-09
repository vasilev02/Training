package typesOfAnimals;

import wildFarm.Food;

import java.text.DecimalFormat;

public class Zebra extends Mammal {
    public Zebra(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public void eat(Food food) {

        if (food.getClass().getSimpleName().equals("Vegetable")){
            this.setFoodEaten(this.getFoodEaten()+food.getQuantity());
        }else{
            System.out.println("Zebras are not eating that type of food!");
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
