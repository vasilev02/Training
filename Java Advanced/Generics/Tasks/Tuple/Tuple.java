package tuple;

public class Tuple<T,K> {
    private final T item1;
    private final K item2;

    public Tuple(T element1,K element2){
        this.item1=element1;
        this.item2=element2;
    }

    @Override
    public String toString() {
        return this.item1+" -> "+this.item2;
    }
}
