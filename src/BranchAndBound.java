import java.util.ArrayList;
import java.util.PriorityQueue;

class Node implements Comparable{

    private Node parent;
    private int value;
    private int weight;
    private int bound;
    private final int known;
    private boolean including; //0 for no, 1 for yes

     public Node(Node parent, boolean including, ArrayList<Item> items, int capacity, int numItems){
        this.parent = parent;
        this.value = parent.value;
        this.known = parent.known + 1;
        if(including) value += items.get(known-1).getValue();
        this.weight = parent.weight;
        if(including) weight += items.get(known-1).getWeight();
        this.including = including;
        this.bound = 0;
        if(known != numItems)   this.bound = this.getBound(this.known, this.weight, capacity, items);
    }

    public Node(int bound){
        this.parent = null;
        this.value = 0;
        this.weight = 0;
        this.known = 0;
        this.including = false;
        this.bound = bound;
    }

    private int getBound(int known, int weight, int capacity, ArrayList<Item> items){
        return this.value + (int)Math.ceil( ((double)items.get(known).getValue()/(double)items.get(known).getWeight()) * (double)(capacity-weight));
    }


    public Node getParent() {
        return parent;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public int getBound() {
        return bound;
    }

    public int getKnown() {
        return known;
    }

    public boolean isIncluding() {
        return including;
    }

    @Override
    public int compareTo(Object o) {
        Node other = (Node)o;
        return -(this.getBound()) + (other.getBound());
    }
}

public class BranchAndBound {

    public static void run(int capacity, int numItems, ArrayList<Item> items) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        Item temp = items.get(0);
        Node start = new Node( (int)Math.ceil(((double)capacity*temp.getValue())/(double) temp.getWeight()));
        q.add(start);
        int max = -1;
        Node maxNode = null;
        while(!q.isEmpty()){
            Node current = q.poll();
            Node with = new Node(current, true, items, capacity, numItems);
            Node without = new Node(current, false, items, capacity, numItems);

            if(with.getWeight() < capacity){        //if with is feasible
                if(with.getKnown() == numItems){
                    if(with.getValue() > max){
                        max =with.getValue();
                        maxNode = with;
                    }
                } else {
                    q.add(with);
                }
            }

            if(without.getWeight() < capacity){        //if without is feasible (it should be)
                if(without.getKnown() == numItems){
                    if(without.getValue() > max){
                        max =without.getValue();
                        maxNode = without;
                    }
                } else {
                    q.add(without);
                }
            }



        }


        while(maxNode.getKnown() > 0){
            if(maxNode.isIncluding())
                System.out.println(items.get(maxNode.getKnown()-1).getIndex());
            maxNode = maxNode.getParent();
        }

    }



}
