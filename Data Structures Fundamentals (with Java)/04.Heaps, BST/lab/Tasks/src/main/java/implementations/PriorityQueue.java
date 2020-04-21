package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private List<E> elements = new ArrayList<>();

    public PriorityQueue() {
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

        while (index >= 0 && isLess(index, getParentIndex(index))) {
            Collections.swap(this.elements, index, getParentIndex(index));
            index = getParentIndex(index);
        }

    }

    private boolean isLess(int index, int parentIndex) {
        return this.elements.get(index).compareTo(this.elements.get(parentIndex)) > 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private void ensureNonEmpty() {
        if (this.elements.size() == 0) {
            throw new IllegalStateException();
        }
    }

    @Override
    public E peek() {

        this.ensureNonEmpty();

        return this.elements.get(0);
    }

    @Override
    public E poll() {

        this.ensureNonEmpty();

        E returnedValue = this.elements.get(0);

        Collections.swap(this.elements, 0, this.elements.size() - 1);

        this.elements.remove(this.elements.size() - 1);

        this.heapifyDown(0);

        return returnedValue;
    }

    private void heapifyDown(int index) {

        while (index < this.elements.size()) {

            int leftChildIndex = this.getLeftChildIndex(index);

            int rightChildIndex = this.getRightChildIndex(index);

            if (rightChildIndex < this.size() && ((this.elements.get(leftChildIndex)).compareTo(this.elements.get(rightChildIndex)) < 0)) {

                Collections.swap(this.elements,rightChildIndex,index);
                index = rightChildIndex;

            }else if(rightChildIndex < this.size() && ((this.elements.get(leftChildIndex)).compareTo(this.elements.get(rightChildIndex)) > 0)){

                Collections.swap(this.elements,leftChildIndex,index);
                index = leftChildIndex;

            }else{
                return;
            }


        }

    }

    private E getLeftChild(int index) {
        return this.elements.get(this.getLeftChildIndex(index));
    }

    private E getRightChild(int index) {
        return this.elements.get(this.getRightChildIndex(index));
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

}
