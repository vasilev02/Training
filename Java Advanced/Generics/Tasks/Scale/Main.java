package scale;

public class Main {
    public static void main(String[] args) {

        Scale<String> stringScale = new Scale<>("a","c");
        System.out.println(stringScale.getHeavier());


    }
}
