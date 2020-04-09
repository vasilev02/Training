package lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        String line = scanner.nextLine();

        for (int i = 0; i <line.length() ; i++) {
            String current = line.charAt(i)+"";
            if(current.equals("(")){
                stack.push(i);
            }else if(current.equals(")")){
                int startIndex = stack.pop();
                StringBuilder builder = new StringBuilder();
                for (int j = startIndex; j <=i ; j++) {
                    builder.append(line.charAt(j));
                }
                System.out.println(builder);
                builder = new StringBuilder();
            }
        }


    }
}
