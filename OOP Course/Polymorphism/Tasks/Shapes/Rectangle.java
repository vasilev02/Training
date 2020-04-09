package shapes;

public class Rectangle extends Shape{

    private double height;
    private double width;

    public Rectangle(double height,double width) {
        this.height = height;
        this.width = width;
    }


    @Override
    protected double calculatePerimeter() {
        return 2*(width+height);
    }

    @Override
    protected double calculateArea() {
        return width*height;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }
}
