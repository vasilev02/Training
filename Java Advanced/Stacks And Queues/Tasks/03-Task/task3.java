package lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int number = Integer.parseInt(scanner.nextLine());

        if(number==0){
            System.out.println("0");
        }else{
            while (number>0){

                if(number%2==0){
                    number/=2;
                    stack.push(0);

                }else{
                    number/=2;
                    stack.push(1);
                }

                if(number==1){
                    stack.push(1);
                    break;
                }

            }
            while(stack.size()>0) {
                int current = stack.poll();
                System.out.print(current);
            }
        }



    }
}
