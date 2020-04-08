package santasWorkshop.core;

public interface Controller {
    String addDwarf(String type, String dwarfName);

    String addInstrumentToDwarf(String dwarfName, int power);

    String addPresent(String presentName, int energyRequired);

    String craftPresent(String presentName);

    String report();
}
