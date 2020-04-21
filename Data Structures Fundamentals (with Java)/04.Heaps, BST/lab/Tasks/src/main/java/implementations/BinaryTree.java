package implementations;

import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {

    private E key;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree(E key, BinaryTree<E> left, BinaryTree<E> right) {
        this.setKey(key);
        this.left = left;
        this.right = right;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {

        StringBuilder builder = new StringBuilder();

        builder.append(padding(indent));
        builder.append(this.key);

        if (this.getLeft() != null) {
            builder.append(System.lineSeparator());
            builder.append(this.getLeft().asIndentedPreOrder(indent + 2));
        }
        if (this.getRight() != null) {
            builder.append(System.lineSeparator());
            builder.append(this.getRight().asIndentedPreOrder(indent + 2));
        }

        return builder.toString();
    }


    private String padding(int indent) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < indent; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {

        List<AbstractBinaryTree<E>> trees = new ArrayList<>();

        BinaryTree<E> current = this;

        trees.add(current);

        if (current.getLeft() != null) {
            trees.addAll(current.getLeft().preOrder());
        }
        if (current.getRight() != null) {
            trees.addAll(current.getRight().preOrder());
        }

        return trees;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {

        List<AbstractBinaryTree<E>> trees = new ArrayList<>();

        BinaryTree<E> current = this;

        if (current.getLeft() != null) {
            trees.addAll(current.getLeft().inOrder());
        }

        trees.add(current);

        if (current.getRight() != null) {
            trees.addAll(current.getRight().inOrder());
        }

        return trees;

    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {

        List<AbstractBinaryTree<E>> trees = new ArrayList<>();

        BinaryTree<E> current = this;

        if (current.getLeft() != null) {
            trees.addAll(current.getLeft().postOrder());
        }

        if (current.getRight() != null) {
            trees.addAll(current.getRight().postOrder());
        }

        trees.add(current);

        return trees;

    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {

        if (this.getLeft() != null) {
            this.getLeft().forEachInOrder(consumer);
        }

        consumer.accept(this.getKey());

        if (this.getRight() != null) {
            this.getRight().forEachInOrder(consumer);
        }

    }
}
