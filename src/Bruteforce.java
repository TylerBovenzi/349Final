import java.util.ArrayList;
import java.util.Arrays;

public class Bruteforce {

    public static void run(int capacity, int numItems, ArrayList<Item> items){
        int brute[] = bruteForce(capacity, numItems, items);
        System.out.print(  "Using Bruteforce the best feasible solution found: \t\t\tValue "+ brute[0]);
        System.out.print( ", Weight "+ brute[numItems+1]);
        for(int i = 0; i < numItems+1; i++){
            if(brute[i] == 1) System.out.print(" " + (i));
        }
        System.out.println();
    }
    private static int[] bruteForce(int capacity, int numItems, ArrayList<Item> items){

        if(capacity <= 0 || numItems==0){
            int result[] = new int[2+items.size()];
            result[0]=0;
            return result;
        }

        Item last = items.get(numItems-1);

        if(last.getWeight() > capacity)  return bruteForce(capacity, numItems - 1, items);

        int[] with = bruteForce(capacity-last.getWeight(), numItems-1, items);
        int[] without = bruteForce(capacity, numItems-1, items);
        with[0] += last.getValue();
        with[1+items.size()] += last.getWeight();
        if(with[0]>=without[0]){
            with[last.getIndex()] = 1;
            return with;
        } else {
            without[last.getIndex()] = 0;
            return without;
        }


    }

}
