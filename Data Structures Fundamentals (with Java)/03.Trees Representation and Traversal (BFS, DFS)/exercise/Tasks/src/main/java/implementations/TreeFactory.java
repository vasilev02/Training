package implementations;

import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {

        for (int i = 0; i < input.length; i++) {

            String[] current = input[i].split("\\s+");

            int parentKey = Integer.parseInt(current[0]);
            int childKey = Integer.parseInt(current[1]);

            this.addEdge(parentKey, childKey);

        }

        return getRoot();
    }

    private Tree<Integer> getRoot() {

        for (Tree<Integer> current : nodesByKeys.values()) {
            if (current.getParent() == null) {

                return current;

            }
        }

        return null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        this.nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return this.nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {

        Tree<Integer> parentNode = this.createNodeByKey(parent);
        Tree<Integer> childNode = this.createNodeByKey(child);

        parentNode.addChild(childNode);
        childNode.setParent(parentNode);

    }
}



