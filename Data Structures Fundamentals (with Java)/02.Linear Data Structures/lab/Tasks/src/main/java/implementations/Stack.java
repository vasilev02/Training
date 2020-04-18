package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

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

    public Stack() {
        this.head = new Node<>(null);
        this.size = 0;
    }


    @Override
    public void push(E element) {

        Node<E> newNode = new Node<>(element);

        newNode.next = this.head;

        this.head = newNode;

        this.size++;
    }

    @Override
    public E pop() {

        validateEmpty();

        E elementToRemove = this.head.element;

        this.head = this.head.next;

        this.size--;

        return elementToRemove;
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
                E value = this.current.element;
                this.current = this.current.next;
                return value;
            }
        };
    }

    //Methods for more help

    private void validateEmpty() {

        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty !");
        }

    }

}
