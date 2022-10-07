import java.io.IOException;
import java.util.*;

public class BranchAndBound {

    public static void main(String[] args) throws IOException {
        String filename = "src/data/easy4.txt";
        int ni[] = {0};int c[] = {0};
        ArrayList<Item> items = fileHelper.loadItems(filename, ni, c);
        Collections.sort(items);
        int numItems = ni[0];
        int capacity = c[0];
        BranchAndBound.run(capacity, numItems, items);
    }

    public static void run(int capacity, int numItems, ArrayList<Item> itemsIn) {
        long startTime = System.nanoTime();
        PriorityQueue<Node> q = new PriorityQueue<>();
        ArrayList<Item> items = new ArrayList<>(itemsIn);
        Collections.sort(items);

        int max = Greedy.runInt(capacity, numItems, itemsIn) - 1;

        int table[][] = new int[numItems + 1][capacity + 1];

        for (int i = 0; i < numItems + 1; i++) {
            for (int j = 0; j < capacity + 1; j++)
                table[i][j] = Greedy.intTooGreedy(j, numItems, items, i);
        }


        Item temp = items.get(0);
        Node start = new Node((int) Math.ceil(((double) capacity * temp.getValue()) / (double) temp.getWeight()));
        q.add(start);


        Node maxNode = null;
        while (!q.isEmpty()) {

            Node current = q.poll();

            if (current.getBound() >= max) {


                Node with = new Node(current, true, items, capacity, numItems, table);
                Node without = new Node(current, false, items, capacity, numItems, table);

                if (with.getWeight() <= capacity) {        //if with is feasible
                    if (with.getKnown() == numItems) {
                        if (with.getValue() > max) {
                            max = with.getValue();
                            maxNode = with;
                        }
                    } else {
                        if (with.getBound() >= max) {
                            q.add(with);
                        }
                    }
                }

                if (without.getWeight() <= capacity) {        //if without is feasible (it should be)
                    if (without.getKnown() == numItems) {
                        if (without.getValue() > max) {
                            max = without.getValue();
                            maxNode = without;

                        }
                    } else {
                        if (without.getBound() >= max) {
                            q.add(without);
                        }
                    }
                }

            }

        }
        int w = 0;
        int v = 0;
        boolean[] result = new boolean[numItems];
        while (maxNode.getKnown() > 0) {
            if (maxNode.isIncluding()) {
                result[items.get(maxNode.getKnown() - 1).getIndex() - 1] = true;
                w += items.get(maxNode.getKnown() - 1).getWeight();
                v += items.get(maxNode.getKnown() - 1).getValue();
            }
            maxNode = maxNode.getParent();

        }

        System.out.print("Using Branch And Bound the best feasible solution found: \tValue " + v);
        System.out.print(", Weight " + w);
        for (int i = 0; i < numItems; i++) {
            if (result[i]) System.out.print(" " + (i + 1));
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("\t\tTime:" + totalTime/1000 + " us");

    }


}
