package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private Node<E> head;
    private int size;

    private static class Node<E> {

        private E element;
        private Node<E> next;

        private Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    public Queue() {
        this.head = new Node<>(null);
        this.size = 0;
    }

    @Override
    public void offer(E element) {

        Node<E> newNode = new Node<>(element);

        if (this.size == 0) {

            this.head = newNode;

        } else {

            Node<E> current = this.head;

            while (current.next != null) {

                current = current.next;
            }

            current.next = newNode;

        }

        this.size++;
    }

    @Override
    public E poll() {

        validateEmpty();

        E elementToReturn = null;

        Node<E> current = this.head;

        elementToReturn = current.element;

        this.head = current.next;

        this.size--;

        return elementToReturn;
    }


    @Override
    public E peek() {

        validateEmpty();

        E elementToPeek = this.head.element;

        return elementToPeek;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return this.current.next != null;
            }

            @Override
            public E next() {

                E elementToReturn = this.current.element;

                this.current = this.current.next;

                return elementToReturn;
            }
        };
    }

    //Helping methods

    private void validateEmpty() {

        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty !");
        }

    }
}
