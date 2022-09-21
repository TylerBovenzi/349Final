public class Item implements Comparable{
    private int value;
    private int weight;

    private int index;

    public Item(int index, int value, int weight){
        this.index = index;
        this.value = value;
        this.weight = weight;

    }


    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public int getIndex() {
        return index;
    }

    public int compare(Item s1, Item s2) {
        return s1.getWeight() -s2.getWeight();
    }

    @Override
    public int compareTo(Object o) {
        Item other = (Item)o;
        return -(this.getValue()/ this.getWeight()) + (other.getValue()/ other.getWeight());
    }
}

