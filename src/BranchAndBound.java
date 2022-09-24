import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

class Node implements Comparable{

    //private Node parent;
    private int value;
    private int weight;
    private int bound;
    private final int known;
    private boolean including; //0 for no, 1 for yes

     public Node(Node parent, boolean including, ArrayList<Item> items, int capacity, int numItems){
        //this.parent = parent;
        this.value = parent.value;
        this.known = parent.known + 1;
        if(including) value += items.get(known-1).getValue();
        this.weight = parent.weight;
        if(including) weight += items.get(known-1).getWeight();
        this.including = including;
        this.bound = 0;
        if(known != numItems)   this.bound = this.getBound(this.known, capacity, items, including);

    }

    public Node(int bound){
        //this.parent = null;
        this.value = 0;
        this.weight = 0;
        this.known = 0;
        this.including = false;
        this.bound = bound;
    }

    public void getDetails(int capacity, ArrayList<Item> items){
         System.out.println("level "+known);
         System.out.println("value "+value);
         System.out.println("weight "+weight);
         System.out.println("including "+including);
         System.out.println("bound "+bound);
         Item next = items.get(known);
         System.out.println("added "+(int)Math.ceil( (
                 (double)next.getValue()/(double)next.getWeight())));
         System.out.println("value "+ next.getValue());
         System.out.println("weight "+ next.getWeight());

         System.out.println("weight left "+ (capacity-this.weight));
         System.out.println(" ");
    }

    private int getBound(int index, int capacity, ArrayList<Item> items, boolean including){
         Item next = items.get(index);
         return this.value +(int)Math.ceil( (
                 (double)next.getValue()/(double)next.getWeight())
                 * (double)(capacity-this.weight));
    }


    //public Node getParent() {
    //    return parent;
    //}

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

    public static void run(int capacity, int numItems, ArrayList<Item> itemsIn) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        ArrayList<Item> items = new ArrayList<>(itemsIn);
        capacity += 1;
        Collections.sort(items);

//        for(Item i:items){
//            double v = i.getValue();
//            double w = i.getWeight();
//            System.out.println(i.getIndex() + "\t" + (v/w) + "\t" +v);
//        }

        Item temp = items.get(0);
        Node start = new Node( (int)Math.ceil(((double)capacity*temp.getValue())/(double) temp.getWeight()));
        q.add(start);
        //int max = -1;
        int max = Greedy.runInt(capacity, numItems, items);
        Node maxNode = null;
        while(!q.isEmpty()){
            Node current = q.poll();
            if(current.getBound() < max) {
                current = null;
                break;
            }
            Node with = new Node(current, true, items, capacity, numItems);
            Node without = new Node(current, false, items, capacity, numItems);

            if(with.getWeight() < capacity){        //if with is feasible
                if(with.getKnown() == numItems){
                    if(with.getValue() > max){
                        max =with.getValue();
                        maxNode = with;
                    }
                } else {
                    if(with.getBound() > max)
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
                    if(without.getBound() > max)
                        q.add(without);
                }
            }



        }

        int w = 0;
        int v = 0;
        boolean[] result = new boolean[numItems];
//        while(maxNode.getKnown() > 0){
//            if(maxNode.isIncluding()) {
//                //System.out.println(items.get(maxNode.getKnown() - 1).getIndex());
//                result[items.get(maxNode.getKnown() - 1).getIndex()-1] = true;
//                w+=items.get(maxNode.getKnown() - 1).getWeight();
//                v+=items.get(maxNode.getKnown() - 1).getValue();
//            }
//            //maxNode = maxNode.getParent();
//
//        }

        System.out.print(  "Using Branch And Bound the best feasible solution found: \tValue "+ v);
        System.out.print( ", Weight "+ w);
        for(int i = 0; i < numItems; i++){
            if(result[i]) System.out.print(" " + (i+1));
        }
        System.out.println(max);

    }



}
