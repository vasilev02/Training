package google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Children {
    private List<String> childrenList;

    public Children() {
        this.childrenList = new ArrayList<>();
    }

    public void add(String current){
        childrenList.add(current);
    }

    public List<String> getChildrenList() {
        return Collections.unmodifiableList(childrenList);
    }
}
