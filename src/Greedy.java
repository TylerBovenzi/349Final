import java.util.ArrayList;
import java.util.Collections;

public class Greedy {

    public static void run(int capacity, int numItems, ArrayList<Item> items){
        int roomLeft = capacity;
        ArrayList<Item> sorted = new ArrayList<>(items);
        //Collections.reverse(sorted);
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
        System.out.println();
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

}
