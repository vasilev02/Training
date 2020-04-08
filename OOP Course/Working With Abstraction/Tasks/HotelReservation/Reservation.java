package hotelReservation;

public class Reservation {

    private double price;
    private int days;
    private Season season;
    private Discount type;

    public Reservation(double price, int days, Season season, Discount type) {
        this.price = price;
        this.days = days;
        this.season = season;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public int getDays() {
        return days;
    }

    public int getSeason() {
        return season.getSeasonValue();
    }

    public int getType() {
        return type.getDiscountType();
    }
}
