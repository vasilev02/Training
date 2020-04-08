package christmas;

public class Present {
    private String name;
    private double magic;

    public Present(String name, double magic){
        this.setName(name);
        this.setMagic(magic);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setMagic(double magic) {
        this.magic = magic;
    }

    public String getName() {
        return name;
    }

    public double getMagic() {
        return magic;
    }
}
