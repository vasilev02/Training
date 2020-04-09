package lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<String> stackForward = new ArrayDeque<>();

        String input = scanner.nextLine();

        while (!input.equals("Home")){

            if(input.equals("back")){
                if(stack.size()>1){
                    stack.poll();
                    System.out.println(stack.peek());
                }else{
                    System.out.println("no previous URLs");
                }
            }else{
                stack.push(input);
                System.out.println(stack.peek());
            }

            input = scanner.nextLine();
        }

    }
}
