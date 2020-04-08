package christmas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class PresentBag {
    private Collection<Present> data;

    public PresentBag() {
        this.data = new ArrayList<>();
    }

    public int getCount() {
        return this.data.size();
    }

    public String create(Present present) {
//        if (present == null) {
//            throw new NullPointerException("Present is null");
//        }
        if (this.data.stream().anyMatch(p -> p.getName().equals(present.getName()))) {
            throw new IllegalArgumentException(String.format("Present with name %s already exists", present.getName()));
        }
        this.data.add(present);
        return String.format("Successfully added present %s with magic %.2f", present.getName(), present.getMagic());
    }

    public boolean remove(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException("Name cannot be null");
        }

        boolean isRemoved = this.data.removeIf(p -> p.getName().equals(name));
        return isRemoved;
    }
    public Present getPresentWithLeastMagic() {
        Present present = this.data
                .stream()
                .min(Comparator.comparingDouble(Present::getMagic))
                .orElse(null);

        return present;
    }
    public Present getPresent(String name) {
        Present present = this.data
                .stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);

        return present;
    }
    public Collection<Present> getPresents() {
        return Collections.unmodifiableCollection(this.data);
    }

}
