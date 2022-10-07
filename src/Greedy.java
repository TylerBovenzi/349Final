import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Greedy {

    public static void main(String[] args) throws IOException {
        String filename = "src/data/easy4.txt";
        int ni[] = {0};int c[] = {0};
        ArrayList<Item> items = fileHelper.loadItems(filename, ni, c);
        Collections.sort(items);
        int numItems = ni[0];
        int capacity = c[0];
        Greedy.run(capacity, numItems, items);
    }

    public static void run(int capacity, int numItems, ArrayList<Item> items){

        int roomLeft = capacity;
        ArrayList<Item> sorted = new ArrayList<>(items);
        //Collections.reverse(sorted);
        long startTime = System.nanoTime();
        boolean[] result = new boolean[numItems];
        int totalWeight = 0;
        int totalValue = 0;
        Collections.sort(sorted);
        while(sorted.size() > 0){

            Item first = sorted.get(0);
            if(first.getWeight() <= roomLeft){
                roomLeft-=first.getWeight();

                result[first.getIndex()-1] = true;
                totalWeight += first.getWeight();
                totalValue  += first.getValue();
            }
            sorted.remove(0);
        }

        System.out.print(  "Using Greedy the best feasible solution found: \t\t\t\tValue "+ totalValue);
        System.out.print( ", Weight "+ totalWeight);
        for(int i = 0; i < numItems; i++){
            if(result[i]) System.out.print(" " + (i+1));
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("\tTime:" + totalTime/1000 + " us");
    }

    public static int runInt(int capacity, int numItems, ArrayList<Item> itemsIn) {
        int roomLeft = capacity;
        ArrayList<Item> sorted = new ArrayList<>(itemsIn);

        boolean[] result = new boolean[numItems];
        int totalWeight = 0;
        int totalValue = 0;
        Collections.sort(sorted);
        while (sorted.size() > 0) {

            Item first = sorted.get(0);
            if (first.getWeight() <= roomLeft) {
                roomLeft -= first.getWeight();

                result[first.getIndex() - 1] = true;
                totalWeight += first.getWeight();
                totalValue += first.getValue();
            }
            sorted.remove(0);
        }

        return totalValue;

    }

    public static int intTooGreedy(int capacity, int numItems, ArrayList<Item> itemsIn, int start) {
        int roomLeft = capacity;
        ArrayList<Item> sorted = new ArrayList<>(itemsIn);
        boolean[] result = new boolean[numItems];
        int totalWeight = 0;
        int totalValue = 0;
        Collections.sort(sorted);
        for(int i = start; i>0;i--){
            sorted.remove(0);
        }
        while (totalWeight < capacity && sorted.size()>0)  {
            Item first = sorted.get(0);
            result[first.getIndex() - 1] = true;
            totalWeight += first.getWeight();
            totalValue += first.getValue();
            sorted.remove(0);
        }

        return totalValue;

    }

}
