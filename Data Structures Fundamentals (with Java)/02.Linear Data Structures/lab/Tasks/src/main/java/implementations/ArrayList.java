package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private static final int INITIAL_SIZE = 4;

    private Object[] elements;
    private int size;
    private int capacity;

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        this.size = 0;
        this.capacity = INITIAL_SIZE;
    }

    @Override
    public boolean add(E element) {

        if (this.size == this.capacity) {
            resizeList();
            this.capacity = this.elements.length;
        }

        this.elements[size] = element;

        this.size++;

        return true;
    }


    @Override
    public boolean add(int index, E element) {

        validationOfIndex(index);

        if (this.size == this.capacity) {
            resizeList();
            this.capacity = this.elements.length;
        }

        for (int i = this.size - 1; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }

        this.elements[index] = element;

        size++;

        return true;
    }


    @Override
    public E get(int index) {

        validationOfIndex(index);

        return getElement(index);
    }


    @Override
    public E set(int index, E element) {

        validationOfIndex(index);

        E item = getElement(index);

        this.elements[index] = element;

        return item;
    }

    @Override
    public E remove(int index) {

        validationOfIndex(index);

        E lastItem = getElement(index);

        for (int i = index; i < this.size - 1; i++) {

            this.elements[i] = this.elements[i + 1];

        }

        this.elements[this.size - 1] = null;

        this.size--;

        return lastItem;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {

        for (int i = 0; i < this.size; i++) {

            if (this.elements[i].equals(element)) {
                return i;
            }

        }

        return -1;
    }

    @Override
    public boolean contains(E element) {

        for (int i = 0; i < this.size; i++) {

            if (this.elements[i].equals(element)) {
                return true;
            }

        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return getElement(index++);
            }
        };
    }

    private void resizeList() {

        Object[] newArray = new Object[this.elements.length * 2];

        for (int i = 0; i < this.elements.length; i++) {
            newArray[i] = this.elements[i];
        }

        this.elements = newArray;

    }


    private void validationOfIndex(int index) {

        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index !");
        }

    }

    @SuppressWarnings("unchecked")
    private E getElement(int index) {

        return (E) this.elements[index];

    }


}
