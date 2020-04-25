import implementations.ReversedList;

public class Main {
    public static void main(String[] args) {

        ReversedList<Integer> list = new ReversedList<>();

        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println(list.get(1));

    }
}
