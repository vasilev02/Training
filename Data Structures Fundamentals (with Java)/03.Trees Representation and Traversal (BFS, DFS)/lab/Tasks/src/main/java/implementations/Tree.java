package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E element;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E element, Tree<E>... subtrees) {
        this.element = element;
        this.children = new ArrayList<>();

        for (Tree<E> subtree : subtrees) {
            this.children.add(subtree);
            subtree.parent = this;
        }

    }

    @Override
    public List<E> orderBfs() {

        List<E> result = new ArrayList<>();

        if(this.element == null){
            return result;
        }

        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {

            Tree<E> current = queue.poll();

            result.add(current.element);

            for (Tree<E> child : current.children) {
                queue.offer(child);
            }

        }

        return result;
    }

    @Override
    public List<E> orderDfs() {

        List<E> result = new ArrayList<>();
        this.dfs(this, result);

        return result;
    }

    private void dfs(Tree<E> tree, List<E> result) {

        for (Tree<E> child : tree.children) {
            dfs(child, result);
        }
        result.add(tree.element);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {

        Tree<E> resultedTree = this.findTreeWithSomeKey(parentKey);

        if (resultedTree == null) {
            throw new IllegalStateException("No such tree");
        } else {

            resultedTree.children.add(child);
            child.parent = resultedTree;
        }

    }

    private Tree<E> findTreeWithSomeKey(E parentKey) {

        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.add(this); //Adding current Tree (root)

        while (!queue.isEmpty()) {

            Tree<E> current = queue.poll();

            if (current.element.equals(parentKey)) {
                return current;
            }

            for (Tree<E> tree : current.children) {

                if (tree.element.equals(parentKey)) {
                    return tree;
                }
                queue.offer(tree);
            }

        }
        return null;
    }

    @Override
    public void removeNode(E nodeKey) {

        Tree<E> resultedTree = this.findTreeWithSomeKey(nodeKey);

        if(resultedTree == null){
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : resultedTree.children) {

            child.parent = null;

        }

        resultedTree.children.clear();

        Tree<E> parent = resultedTree.parent;

        if(parent != null){

            parent.children.remove(resultedTree);

        }

        resultedTree.element = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {

        Tree<E> firstTree = this.findTreeWithSomeKey(firstKey);
        Tree<E> secondTree = this.findTreeWithSomeKey(secondKey);

        if(firstTree == null || secondTree == null){
            throw new NullPointerException("Missing tree !");
        }

        Tree<E> firstParent = firstTree.parent;
        Tree<E> secondParent = secondTree.parent;

        if(firstParent == null){
            this.element = secondTree.element;
            this.parent = null;
            this.children = secondTree.children;
            secondTree.parent = null;
            return;

        }else if(secondParent == null){
            this.element = firstTree.element;
            this.parent = null;
            this.children = firstTree.children;
            firstTree.parent = null;
            return;
        }

        firstTree.parent = secondParent;
        secondTree.parent = firstParent;

        int indexOne = firstParent.children.indexOf(firstTree);
        int indexTwo = secondParent.children.indexOf(secondTree);

        firstParent.children.set(indexOne,secondTree);
        secondParent.children.set(indexTwo,firstTree);

    }
}



