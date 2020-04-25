package implementations;

import java.util.Iterator;

public class ReversedList<E> implements Iterable<E> {

    private Object[] array;
    private int size;
    private int capacity;

    public ReversedList() {
        this.array = new Object[2];
        this.size = 0;
        this.capacity = 2;
    }

    public void add(E element) {

        if (this.size == this.capacity) {
            grow();
        }
        this.array[size] = element;

        this.size++;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

    public E get(int index) {

        index = Math.abs(this.size - index - 1);

        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index !");
        }

        return (E) this.array[index];
    }

    private void grow() {

        this.capacity *= 2;

        Object[] newArray = new Object[this.capacity];

        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }

        this.array = newArray;
    }

    public void removeAt(int index) {

        index = Math.abs(this.size - index - 1);

        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index !");
        }

        this.array[index] = null;

        shifting();

        this.size--;

    }

    private void shifting() {

        int counter = 0;

        Object[] newArray = new Object[capacity];

        for (int i = 0; i < this.array.length; i++) {

            if (this.array[i] != null) {
                newArray[counter] = this.array[i];
                counter++;
            }

        }
        this.array = newArray;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private int index = size - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {

                return (E) array[index--];
            }
        };
    }

}
