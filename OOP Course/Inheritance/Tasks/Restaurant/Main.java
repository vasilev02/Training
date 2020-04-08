package restaurant;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Salmon salmon = new Salmon("Fishcho",BigDecimal.valueOf(1.20));
        System.out.println(salmon.getGrams());

    }
}
