package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E element;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E element) {
        this.element = element;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.element;
    }

    @Override
    public String getAsString() {
        int index = 0;

        StringBuilder builder = new StringBuilder();
        recursiveMethod(index, this, builder);

        return builder.toString().trim();
    }

    private void recursiveMethod(int index, Tree<E> tree, StringBuilder builder) {

        builder.append(padding(index));
        builder.append(tree.element);
        builder.append(System.lineSeparator());

        for (Tree<E> child : tree.children) {
            recursiveMethod(index + 2, child, builder);
        }

    }

    private String padding(int index) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < index; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public List<E> getLeafKeys() {

        List<E> elements = new ArrayList<>();

        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {

            Tree<E> current = queue.poll();

            if (current.children.isEmpty()) {
                elements.add(current.element);
            }

            for (Tree<E> child : current.children) {
                queue.offer(child);
            }

        }

        return elements;
    }

    @Override
    public List<E> getMiddleKeys() {

        List<E> elements = new ArrayList<>();

        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {

            Tree<E> current = queue.poll();

            if (current.children.size() > 0 && current.parent != null) {
                elements.add(current.element);
            }

            for (Tree<E> child : current.children) {
                queue.offer(child);
            }

        }

        return elements;
    }

    public List<Tree<E>> getLeafTreesWithBFS() {

        List<Tree<E>> elements = new ArrayList<>();

        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {

            Tree<E> current = queue.poll();

            if (current.children.isEmpty()) {
                elements.add(current);
            }

            for (Tree<E> child : current.children) {
                queue.offer(child);
            }

        }

        return elements;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {

        List<Tree<E>> leaves = this.getLeafTreesWithBFS();

        int max = 0;
        Tree<E> deepestTree = this;

        for (Tree<E> leaf : leaves) {

            int currentLength = 0;
            Tree<E> currentTree = leaf;

            while (leaf.parent != null) {

                currentLength++;
                leaf = leaf.parent;

            }

            if (currentLength > max) {

                max = currentLength;
                deepestTree = currentTree;

            }

        }


        return deepestTree;
    }

    @Override
    public List<E> getLongestPath() {

        Tree<E> tree = this.getDeepestLeftmostNode();

        List<E> elementsDirection = new ArrayList<>();

        while (tree.parent != null) {

            elementsDirection.add(0, tree.element);
            tree = tree.parent;

        }
        elementsDirection.add(0, this.element);

        return elementsDirection;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {

        List<List<E>> combinations = new ArrayList<>();

        this.recursiveMethodToTrackSum(sum, this, combinations);

        return combinations;
    }

    private void recursiveMethodToTrackSum(int sum, Tree<E> tree, List<List<E>> combinations) {

        if (tree.children.isEmpty()) {

            List<Integer> sumOfNumbers = new ArrayList<>();

            while (tree.parent != null) {
                sumOfNumbers.add(0, (Integer) tree.element);
                tree = tree.parent;
            }
            sumOfNumbers.add(0, (Integer) tree.element);

            int num = 0;
            for (Integer current : sumOfNumbers) {
                num += current;
            }
            if (num == sum) {
                combinations.add((List<E>) sumOfNumbers);
            }
            return;
        }


        for (Tree<E> child : tree.children) {

            recursiveMethodToTrackSum(sum, child, combinations);

        }

    }
    
}



