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
        //return (int)((-1*(double)this.getValue()/ (double)this.getWeight()) + ((double)other.getValue()/ (double)other.getWeight()));
        double v1 = this.getValue();
        double v2 = ((Item) o).getValue();
        double w1 = this.getWeight();
        double w2 = ((Item) o).getWeight();
        if(v1/w1  == v2/w2){
            return v1>v2?-1:1;
        }
        if(v1/w1  > v2/w2) return -1;
        return 1;

        }



}

