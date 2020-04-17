package representation;

public class ArrayStorage {
    private final int INITIAL_CAPACITY = 4;

    private int[] elements;
    private int index;

    public ArrayStorage() {
        this.elements = new int[INITIAL_CAPACITY];
        this.index = -1;
    }

    public boolean add(int element) {
        this.add(element, ++index);
        return true;
    }

    private void add(int element, int index) {
        if (index == this.elements.length) {
            this.grow();
        }
        this.elements[index] = element;
    }

    private void grow(){

        int[] newElements = new int[this.elements.length + INITIAL_CAPACITY];

        for (int i = 0; i <this.elements.length ; i++) {
            newElements[i] = this.elements[i];
        }

        this.elements = newElements;

    }

}
