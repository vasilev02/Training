package shapes;

public abstract class Shape {

    private double perimeter;
    private double area;

    public double getPerimeter() {
        return this.perimeter;
    }

    public double getArea() {
        return this.area;
    }

    private void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    private void setArea(double area) {
        this.area = area;
    }

    protected abstract double calculatePerimeter();

    protected abstract double calculateArea();

}
