package cardsSuits;


public class Main {
    public static void main(String[] args) {


        System.out.println("Card Ranks:");

        Cards[] allCards = Cards.values();
        for (Cards current : allCards) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",current.ordinal(),current.name());
        }

    }
}
