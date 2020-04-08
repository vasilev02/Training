package pointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] data = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        Rectangle rectangle = new Rectangle(data[0],data[1],data[2],data[3]);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <n ; i++) {

            int[] pointsData = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();

            Point point = new Point(pointsData[0],pointsData[1]);

            System.out.println(rectangle.contains(point));

        }

    }
}
