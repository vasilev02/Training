package arrayCreator;

public class Main {
    public static void main(String[] args) {

        String[] strings = ArrayCreator.create(10,"Array");
        Integer[] integers = ArrayCreator.create(Integer.class,10,123);
        System.out.println(integers[0]);
    }
}
