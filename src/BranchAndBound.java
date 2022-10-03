import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class BranchAndBound {

    public static void run(int capacity, int numItems, ArrayList<Item> itemsIn) {
        //DoubleEnded q = new DoubleEnded();
        PriorityQueue<Node> q = new PriorityQueue<>();
        //MaxHeap q = new MaxHeap();
        ArrayList<Item> items = new ArrayList<>(itemsIn);


        Collections.sort(items);
        //Collections.reverse(items);

        int max = Greedy.runInt(capacity, numItems, itemsIn)-1;

        //capacity += 1;
//        for(Item i:items){
//            double v = i.getValue();
//            double w = i.getWeight();
//            System.out.println(i.getIndex() + "\t" + (v/w) + "\t" +v);
//        }

        Item temp = items.get(0);
        Node start = new Node( (int)Math.ceil(((double)capacity*temp.getValue())/(double) temp.getWeight()));
        q.add(start);


        //int max = -1;
        //int max = 0;

        //int max = 4091;
        Node maxNode = null;
        while(!q.isEmpty()) {

           // System.out.println(q.size());
            //System.out.println(max);
            //System.out.println(q.getMax().getBound() + " " +q.getMin().getBound() );
            //Node current = q.extractMax();
            //Node current = q.getMax();
            Node current = q.poll();
            //current.getDetails(capacity, items);
            //q.deleteMax();
            //q1.remove(current);

            //q.remove(current);
           if(current.getBound() >= max) {


            Node with = new Node(current, true, items, capacity, numItems);
            Node without = new Node(current, false, items, capacity, numItems);

            if (with.getWeight() <= capacity) {        //if with is feasible
                if (with.getKnown() == numItems) {
                    if (with.getValue() > max ) {
                        max = with.getValue();
                        maxNode = with;
                    }
                } else {
                    if (with.getBound() >= max) {
                        q.add(with);
                        //q1.add(with);
                    }
                }
            }

            if(without.getWeight() <= capacity) {        //if without is feasible (it should be)
                if (without.getKnown() == numItems) {
                    if (without.getValue() > max) {
                        max = without.getValue();
                        maxNode = without;

                    }
                } else {
                    if (without.getBound() >= max) {
                        q.add(without);
                        //q1.add(without);
                    }
                }
        }

            }

        }
        System.out.println(max);
        int w = 0;
        int v = 0;
        boolean[] result = new boolean[numItems];
        while(maxNode.getKnown() > 0){
            if(maxNode.isIncluding()) {
                //System.out.println(items.get(maxNode.getKnown() - 1).getIndex());
                result[items.get(maxNode.getKnown() - 1).getIndex()-1] = true;
                w+=items.get(maxNode.getKnown() - 1).getWeight();
                v+=items.get(maxNode.getKnown() - 1).getValue();
            }
            maxNode = maxNode.getParent();

        }

        System.out.print(  "Using Branch And Bound the best feasible solution found: \tValue "+ v);
        System.out.print( ", Weight "+ w);
        for(int i = 0; i < numItems; i++){
            if(result[i]) System.out.print(" " + (i+1));
        }
        System.out.println(max);

    }



}
