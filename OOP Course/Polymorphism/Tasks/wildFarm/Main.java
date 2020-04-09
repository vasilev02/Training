package wildFarm;

import typesOfAnimals.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {


        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        List<Animal> animals = new ArrayList<>();


        String[] data = buff.readLine().split("\\s+");
        while (!data[0].equals("End")) {

            String[] foodData = buff.readLine().split("\\s+");

            Food food = makeFood(foodData[0], Integer.parseInt(foodData[1]));

            switch (data[0]) {

                case "Cat":
                    Cat cat = new Cat(data[0], data[1], Double.parseDouble(data[2]), data[3], data[4]);

                    animals.add(cat);
                    cat.makeSound();
                    cat.eat(food);

                    break;

                case "Tiger":

                    Tiger tiger = new Tiger(data[0], data[1], Double.parseDouble(data[2]), data[3]);

                    animals.add(tiger);
                    tiger.makeSound();
                    tiger.eat(food);



                    break;

                case "Zebra":

                    Zebra zebra = new Zebra(data[0], data[1], Double.parseDouble(data[2]), data[3]);

                    animals.add(zebra);
                    zebra.makeSound();
                    zebra.eat(food);


                    break;

                case "Mouse":

                    Mouse mouse = new Mouse(data[0], data[1], Double.parseDouble(data[2]), data[3]);

                    animals.add(mouse);
                    mouse.makeSound();
                    mouse.eat(food);

                    break;

            }

            data = buff.readLine().split("\\s+");
        }

        for (Animal current : animals) {
            System.out.println(current.toString());

        }

    }


    private static Food makeFood(String foodData, int i) {

        if (foodData.equals("Vegetable")) {
            return new Vegetable(i);
        } else if (foodData.equals("Meat")) {
            return new Meat(i);
        }
        return null;
    }
}
