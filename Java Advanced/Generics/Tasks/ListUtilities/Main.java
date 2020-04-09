package listUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers,1);

        Integer max = ListUtils.getMin(integers);
        System.out.println(max);

    }
}
