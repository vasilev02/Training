package pointInRectangle;

public class Rectangle {
    private int bottomLeftX;
    private int bottomLeftY;
    private int topRightX;
    private int topRightY;

    public Rectangle(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.topRightX = topRightX;
        this.topRightY = topRightY;
    }

    public boolean contains(Point point){

        boolean checkX = point.getCoordinateX()>=this.bottomLeftX &&
                point.getCoordinateX()<=this.topRightX;

        boolean checkY = point.getCoordinateY()>=this.bottomLeftY &&
                point.getCoordinateY()<=this.topRightY;

        return checkX && checkY;
    }

}
