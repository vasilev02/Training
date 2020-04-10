package healthyHeaven;

public class Vegetable {

    private String name;
    private int calories;


    public Vegetable(String name,int calories){
        this.name=name;
        this.calories=calories;
    }

    public String getName() {
        return this.name;
    }

    public int getCalories(){
        return this.calories;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" - %s have %d calories",getName(),getCalories()));
        sb.append(System.lineSeparator());
        return sb.toString().trim();
    }
}