package genericBox;

public class Box<T> {

    private String mess;

    public Box(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return String.format("java.lang.String: %s",mess);
    }
}
