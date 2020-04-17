package representation;

public class NodeStorage {

    private Node node;

    private class Node {

        private int element;
        private Node next;

        private Node(int element) {
            this.element = element;
        }

    }


    public NodeStorage() {
        this.node = new Node(0);
    }

    public boolean add(int element) {
        this.node.next = new Node(element);
        return true;
    }

}
