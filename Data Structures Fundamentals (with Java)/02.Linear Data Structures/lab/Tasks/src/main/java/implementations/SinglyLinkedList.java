package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private Node<E> head;
    private Node<E> tale;
    private int size;

    private static class Node<E> {

        private E element;
        private Node<E> next;
        private Node<E> prev;

        private Node(E element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }

    }

    public SinglyLinkedList() {
        this.head = null;
        this.tale = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {

        Node<E> newNode = new Node<>(element);

        if (this.size == 0) {
            this.head = newNode;
            this.tale = newNode;
        } else {

            newNode.next = this.head;

            this.head.prev = newNode;

            this.head = newNode;

        }

        this.size++;
    }

    @Override
    public void addLast(E element) {

        Node<E> newNode = new Node<>(element);

        if (this.size == 0) {
            this.head = newNode;
            this.tale = newNode;
        } else {

            this.tale.next = newNode;

            newNode.prev = this.tale;

            this.tale = newNode;

        }

        this.size++;

    }

    @Override
    public E removeFirst() {

        validateEmpty();

        E elementToReturn = this.head.element;

        this.head = this.head.next;

        this.size--;

        return elementToReturn;
    }


    @Override
    public E removeLast() {

        validateEmpty();

        Node<E> beforeNode = tale.prev;

        E elementToReturn = tale.element;

        beforeNode.next = null;

        this.tale = beforeNode;

        this.size--;

        return elementToReturn;
    }

    @Override
    public E getFirst() {
        return this.head.element;
    }

    @Override
    public E getLast() {
        return this.tale.element;
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
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }


    //Helping methods

    private void validateEmpty() {

        if (isEmpty()) {
            throw new IllegalStateException("Empty SinglyLinkedList !");
        }

    }

}
