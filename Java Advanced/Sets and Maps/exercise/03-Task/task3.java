package exercise;

import java.util.*;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> compounds = new TreeSet<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <n ; i++) {
            String[] data = scanner.nextLine().split("\\s+");
            compounds.addAll(Arrays.asList(data));
        }

        for (String s : compounds) {
            System.out.print(s+" ");
        }
    }
}
