package exercise;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> usernames = new LinkedHashSet<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i <n ; i++) {
            String name = scanner.nextLine();

            usernames.add(name);

        }

        for (String s : usernames) {
            System.out.println(s);
        }

    }
}
