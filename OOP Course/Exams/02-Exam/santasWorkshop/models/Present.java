package santasWorkshop.models;

public interface Present {
    String getName();

    int getEnergyRequired();

    boolean isDone();

    void getCrafted();
}
