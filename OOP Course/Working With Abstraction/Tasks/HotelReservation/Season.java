package hotelReservation;

public enum Season {

    Autumn(1),
    Spring(2),
    Winter(3),
    Summer(4);

    private int seasonValue;

    Season(int seasonValue) {
        this.seasonValue = seasonValue;
    }

    public int getSeasonValue() {
        return this.seasonValue;
    }
}
