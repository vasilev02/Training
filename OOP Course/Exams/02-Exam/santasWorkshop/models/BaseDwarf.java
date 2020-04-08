package santasWorkshop.models;

import santasWorkshop.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseDwarf implements Dwarf{

    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    protected BaseDwarf(String name, int energy) {
        setName(name);
        setEnergy(energy);
        this.instruments = new ArrayList<>();
    }


    @Override
    public void work() {
        this.energy-=10;
        if(this.energy<0){
            this.energy=0;
        }
    }


    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {

        if(this.energy>0){
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return Collections.unmodifiableCollection(this.instruments);
    }

    private void setName(String name) {

        if(name==null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.DWARF_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setEnergy(int energy) {

        if(energy<0){
            throw new IllegalArgumentException(ExceptionMessages.DWARF_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }
}
