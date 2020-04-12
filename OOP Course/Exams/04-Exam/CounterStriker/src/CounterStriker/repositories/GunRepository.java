package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GunRepository implements Repository<Gun> {

    private Collection<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Gun model) {

        if(model==null){
            throw new NullPointerException("Cannot add null in Gun Repository");
        }
        this.models.add(model);
    }

    @Override
    public boolean remove(Gun model) {

        for (Gun gun : models) {
            if(gun.getName().equals(model.getName())){
                this.models.remove(gun);
                return true;
            }
        }

        return false;
    }

    @Override
    public Gun findByName(String name) {
        Gun gun = this.models.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
        return gun;
    }
}
