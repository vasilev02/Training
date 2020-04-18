import implementations.ArrayList;
import implementations.Queue;
import implementations.SinglyLinkedList;
import implementations.Stack;

public class Main {
    public static void main(String[] args) {

        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addLast(5);
        list.addLast(15);
        list.addLast(25);

        list.removeLast();

    }
}
