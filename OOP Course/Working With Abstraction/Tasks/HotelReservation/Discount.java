package hotelReservation;

public enum Discount {

    VIP(20),
    SecondVisit(10),
    None(0);

    private int discountType;

    Discount(int discountType) {
        this.discountType = discountType;
    }

    public int getDiscountType() {
        return discountType;
    }
}
