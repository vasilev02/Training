package lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> queue = new ArrayDeque<>();

        String input = scanner.nextLine();
        while (!input.equals("print")){

            if(input.equals("cancel")){
                if(queue.size()>=1){
                    String current = queue.remove();
                    System.out.println("Canceled "+ current);
                }else{
                    System.out.println("Printer is on standby");
                }
            }else{
                queue.add(input);
            }

            input = scanner.nextLine();
        }
        for (String s : queue) {
            System.out.println(s);
        }

    }
}
