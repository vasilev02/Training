package customList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomList<T extends Comparable<T>> {

    private List<T> list;

    public CustomList() {
        this.list = new ArrayList<>();
    }

    public void add(T element) {

        this.list.add(element);

    }

    public void remove(int index){
        this.list.remove(index);
    }

    public void contains(T element){

        if (this.list.contains(element)) {
            System.out.println(true);
        }else{
            System.out.println(false);
        }

    }

    public void sort(){
        Collections.sort(this.list);
    }


    public void swap(int a,int b ){
        Collections.swap(this.list,a,b);
    }

    public void countGreaterThan(T element){
        int counter=0;
        for (T t : list) {
            if(t.compareTo(element)>0){
                counter++;
            }
        }
        System.out.println(counter);
    }

    public void getMax(){

        T element = this.list.get(0);

        for (int i = 1; i <this.list.size() ; i++) {
            if(element.compareTo(this.list.get(i))<0){
                element = this.list.get(i);
            }
        }
        System.out.println(element);
    }

    public void getMin(){

        T element = this.list.get(0);

        for (int i = 1; i <this.list.size() ; i++) {
            if(element.compareTo(this.list.get(i))>0){
                element = this.list.get(i);
            }
        }
        System.out.println(element);
    }

    public void Print(){

        StringBuilder sb = new StringBuilder();

        for (T t : list) {
            sb.append(t);
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString().trim());
    }

}
