package christmas;

import java.io.Console;

public class Main {
    public static void main(String[] args) {

        //Initialize the repository (Bag)
        Bag bag = new Bag("Blue", 20);
//Initialize entity
        Present present = new Present("Train", 0.4, "Boy");
//Print Present
         // Present Train for a Boy

//Add Present
        bag.add(present);
        System.out.println(bag.count());
//Remove Present
        bag.remove("Doll"); //false

        Present secondPresent = new Present("Doll", 0.7, "Girl");

//Ad
// Blue bag contains:
// Present Train for a Boy
// Present Doll for a Girl


    }
}
