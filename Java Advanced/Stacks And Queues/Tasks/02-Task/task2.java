package lab;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();

        String[] data = scanner.nextLine().split("\\s+");

        for (int i = data.length-1; i >=0 ; i--) {
            stack.push(data[i]);
        }

        while (stack.size()>1){
            int first = Integer.parseInt(stack.poll());
            String operand = stack.poll();
            int second = Integer.parseInt(stack.poll());

            switch (operand){
                case"+":
                    int result = first+second;
                    stack.push(result+"");
                    break;
                case"-":
                    int resultt = first-second;
                    stack.push(resultt+"");
                    break;
            }

        }
        System.out.println(stack.peek());

    }
}
