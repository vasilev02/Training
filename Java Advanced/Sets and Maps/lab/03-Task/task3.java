package lab;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Integer> firstPlayer = new LinkedHashSet<>();
        Set<Integer> secondPlayer = new LinkedHashSet<>();

        String[] dataOne = scanner.nextLine().split("\\s+");
        for (int i = 0; i <dataOne.length ; i++) {
            int curr = Integer.parseInt(dataOne[i]);
            firstPlayer.add(curr);
        }

        String[] dataTwo = scanner.nextLine().split("\\s+");
        for (int i = 0; i <dataTwo.length ; i++) {
            secondPlayer.add(Integer.parseInt(dataTwo[i]));
        }
        //TODO Battle

        for (int i = 1; i <=50 ; i++) {

            int first = firstPlayer.iterator().next();
            firstPlayer.remove(first);

            int second = secondPlayer.iterator().next();
            secondPlayer.remove(second);

            if(!(firstPlayer.size()>0)){
                System.out.println("Second player win!");
                break;
            }
            if(!(secondPlayer.size()>0)){
                System.out.println("First player win!");
                break;
            }

            if(first>second){
                firstPlayer.add(first);
                firstPlayer.add(second);
            }else if(second>first){
                secondPlayer.add(first);
                secondPlayer.add(second);
            }else{
                continue;
            }


        }
        if(firstPlayer.size()==secondPlayer.size()){
            System.out.println("draw");
        }else if(firstPlayer.size()>secondPlayer.size()){
            System.out.println("First player win!");
        }else{
            System.out.println("Second player win!");
        }

    }
}
