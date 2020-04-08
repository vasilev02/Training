package box;

public class Box {

    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    private void setLength(double length) {
        validator(length,"Length");
        this.length = length;
    }

    private void setWidth(double width) {
        validator(width,"Width");
        this.width = width;
    }

    private void setHeight(double height) {
        validator(height,"Height");
        this.height = height;
    }

    private void validator(double number, String side){
        if (number<=0){
            throw new IllegalArgumentException(String.format
                    ("%s cannot be zero or negative.",side));
        }
    }

    public double calculateSurfaceArea(){
        double sum = 0;
        sum += 2*this.length*this.width;
        sum += 2*this.length*this.height;
        sum += 2*this.width*this.height;
        return sum;
    }

    public double calculateLateralSurfaceArea(){
        double sum = 0;
        sum += 2*this.length*this.height;
        sum += 2*this.width*this.height;
        return sum;
    }

    public double calculateVolume(){
        return this.height*this.width*this.length;
    }


}
