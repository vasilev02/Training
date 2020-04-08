package hotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] info = scanner.nextLine().split("\\s+");

        double price = Double.parseDouble(info[0]);
        int numberOfDays = Integer.parseInt(info[1]);
        String nameOfSeason = info[2];
        String type = info[3];

        Reservation reservation = new Reservation(price,numberOfDays,Season.valueOf(nameOfSeason),Discount.valueOf(type));

        System.out.printf("%.2f",PriceCalculator.getPrice(reservation));

    }
}
