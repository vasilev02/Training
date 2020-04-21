package implementations;

import interfaces.AbstractBinarySearchTree;
import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {

    private Node<E> root;
    private Node<E> left;
    private Node<E> right;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node<E> root) {
        this.copy(root);
    }

    @Override
    public void insert(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.getRoot() == null) {
            this.root = newNode;
        } else {

            Node<E> current = this.root;
            Node<E> prev = current;

            while (current != null) {

                prev = current;
                if (element.compareTo(current.value) > 0) {
                    current = current.rightChild;
                } else if (element.compareTo(current.value) < 0) {
                    current = current.leftChild;
                } else {
                    return;
                }

            }


            if (element.compareTo(prev.value) > 0) {
                prev.rightChild = newNode;
            } else if (element.compareTo(prev.value) < 0) {
                prev.leftChild = newNode;
            }

        }
    }

    @Override
    public boolean contains(E element) {

        Node<E> current = this.root;

        while (current != null) {

            if (element.compareTo(current.value) > 0) {
                current = current.rightChild;
            } else if (element.compareTo(current.value) < 0) {
                current = current.leftChild;
            } else if (element.compareTo(current.value) == 0) {
                return true;
            }

        }


        return false;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {

        Node<E> node = this.returnNode(element);

        if (node == null) {
            return new BinarySearchTree<>(null);
        }

        return new BinarySearchTree<>(node);
    }

    private void copy(Node<E> node) {

        if (node != null) {
            this.insert(node.value);
            this.copy(node.leftChild);
            this.copy(node.rightChild);
        }

    }


    public Node<E> returnNode(E element) {

        Node<E> current = this.root;

        while (current != null) {

            if (element.compareTo(current.value) > 0) {
                current = current.rightChild;
            } else if (element.compareTo(current.value) < 0) {
                current = current.leftChild;
            } else if (element.compareTo(current.value) == 0) {
                return current;
            }

        }
        return null;
    }

    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.left;
    }

    @Override
    public Node<E> getRight() {
        return this.right;
    }

    @Override
    public E getValue() {
        return this.root.value;
    }
}
