package google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parents {
    private List<String> parentsList;

    public Parents(){
        this.parentsList = new ArrayList<>();
    }
    public void add(String current){
        parentsList.add(current);
    }

    public List<String> getParentsList() {
        return Collections.unmodifiableList(parentsList);
    }
}
