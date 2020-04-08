package santasWorkshop.repositories;

import santasWorkshop.models.Dwarf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DwarfRepository implements Repository<Dwarf> {

    private Collection<Dwarf> dwarfs;

    public DwarfRepository() {
        this.dwarfs = new ArrayList<>();
    }

    @Override
    public Collection<Dwarf> getModels() {
        return Collections.unmodifiableCollection(this.dwarfs);
    }

    @Override
    public void add(Dwarf model) {
        this.dwarfs.add(model);
    }

    @Override
    public boolean remove(Dwarf model) {
        return this.dwarfs.remove(model);
    }

    @Override
    public Dwarf findByName(String name) {

        return this.dwarfs.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
    }
}
