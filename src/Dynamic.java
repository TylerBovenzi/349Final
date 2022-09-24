import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Dynamic {

    public static void run(int capacity, int numItems, ArrayList<Item> items){
        boolean[] result = new boolean[numItems];
        int totalWeight = 0;
        int totalValue = 0;

        int[] table = new int[capacity+1];

        for( int i =1; i<numItems+1; i++)
            for(int w =capacity; w>=0; w--) {
                Item current = items.get(i - 1);
                if (current.getWeight() <= w) {
                    if (table[w] < table[w - current.getWeight()] + current.getValue()){
                        table[w] = table[w - current.getWeight()] + current.getValue();
                    }
                }
            }
        int n = capacity;
        int v = table[n];

        Collections.sort(items, new Comparator<Item>() {
            public int compare(Item o1, Item o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });
        int weight = 0;
        int val = 0;
        ArrayList<Item> tempItems = new ArrayList<Item>(items);
        while(n > 0){
            boolean found = false;
            for(Item i: tempItems) {
                if(n-i.getWeight() >= 0) {
                    if(!result[i.getIndex() - 1])
                        if (table[n] - i.getValue() == safeGet(table, n - i.getWeight())) {
                            n = n - i.getWeight();
                            v = v - i.getValue();
                            result[i.getIndex() - 1] = true;
                            totalValue += i.getValue();
                            totalWeight += i.getWeight();
                            found = true;
                            break;
                        }

                }
            }
            if(!found) n--;
        }

        System.out.print(  "Using Dynamic the best feasible solution found: \t\t\tValue "+ totalValue);
        System.out.print( ", Weight "+ totalWeight);
        for(int i = 0; i < numItems; i++){
            if(result[i]) System.out.print(" " + (i+1));
        }
        System.out.println();

    }

    private static int safeGet(int[] table, int index){
        if(index<0) return 0;
        return table[index];
    }

}
