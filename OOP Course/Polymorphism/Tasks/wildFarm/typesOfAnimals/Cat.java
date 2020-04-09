package typesOfAnimals;

import wildFarm.Food;

import java.text.DecimalFormat;

public class Cat extends Felime{

     private String breed;

    public Cat(String animalName, String animalType, double animalWeight, String livingRegion,String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    public String getBreed() {
        return this.breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        this.setFoodEaten(this.getFoodEaten()+food.getQuantity());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        DecimalFormat format = new DecimalFormat("#.##");

        sb.append(String.format("%s[%s, %s, " + format.format(getAnimalWeight()),getAnimalName(),getAnimalType(),getBreed()));

        sb.append(String.format(", %s, %d] ",getLivingRegion(),getFoodEaten()));

        return sb.toString().trim();

    }
}
