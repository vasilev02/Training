package ferrari;

public class Ferrari implements Car {

    private String driverName;
    private String model = "488-Spider";

    public Ferrari(String driverName) {
        setDriverName(driverName);
    }

    private void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String brakes() {
        return "Brakes!";
    }

    @Override
    public String gas() {
        return "brum-brum-brum-brrrrr";
    }

    @Override
    public String toString() {

        String text = "%s/%s/%s/%s";

        return String.format(text, this.model,
                this.brakes(), this.gas(), this.driverName);
    }
}
