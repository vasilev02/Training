package aquarium.models.fish;

public class FreshwaterFish extends BaseFish {
    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(3);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize()+3);
    }
}
