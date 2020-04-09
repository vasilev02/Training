package lab;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> vipPeople = new TreeSet<>();
        Set<String> regularPeople = new TreeSet<>();

        String input = scanner.nextLine();
        while (!input.equals("PARTY")){
            char symbol = input.charAt(0);
            if(Character.isDigit(symbol)){
                vipPeople.add(input);
            }else{
                regularPeople.add(input);
            }

            input = scanner.nextLine();
        }
        String secondInput = scanner.nextLine();
        while (!secondInput.equals("END")){

            char symbol = secondInput.charAt(0);
            if(Character.isDigit(symbol)){
                vipPeople.remove(secondInput);
            }else{
                regularPeople.remove(secondInput);
            }

            secondInput = scanner.nextLine();
        }

        int total = vipPeople.size()+regularPeople.size();
        System.out.println(total);
        for (String current : vipPeople) {
            System.out.println(current);
        }
        for (String current : regularPeople) {
            System.out.println(current);
        }

    }
}
