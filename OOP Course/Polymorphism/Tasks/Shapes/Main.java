package shapes;

public class Main {
    public static void main(String[] args) {

        Shape circle = new Circle(3);
        System.out.println(circle.calculateArea());

        System.out.println(circle.getArea());
    }
}
