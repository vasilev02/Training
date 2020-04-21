package implementations;

import interfaces.AbstractBinarySearchTree;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private List<E> elements = new ArrayList<>();

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);

        heapify(this.elements.size() - 1);

    }

    private void heapify(int index) {

        while (index >= 0 && isLess(index,getParentIndex(index))) {
            Collections.swap(this.elements,index,getParentIndex(index));
            index = getParentIndex(index);
        }

    }

    private boolean isLess(int index, int parentIndex) {
        return this.elements.get(index).compareTo(this.elements.get(parentIndex)) > 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }


    @Override
    public E peek() {

        if (this.elements.size() == 0) {
            throw new IllegalStateException("No elements !");
        }

        return this.elements.get(0);
    }
}
