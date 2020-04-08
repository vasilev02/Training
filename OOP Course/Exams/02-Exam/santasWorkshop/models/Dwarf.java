package santasWorkshop.models;

import java.util.Collection;

public interface Dwarf {
    void work();

    void addInstrument(Instrument instrument);

    boolean canWork();

    String getName();

    int getEnergy();

    Collection<Instrument> getInstruments();
}
