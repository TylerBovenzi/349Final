import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.util.Comparator.comparing;

public class main {

    public static void main(String args[]) throws IOException {

        String filename = "src/easy20.txt";
        int ni[] = {0};
        int c[] = {0};
        ArrayList<Item> items = fileHelper.loadItems(filename, ni, c);
        //Collections.sort(items);

        int numItems = ni[0];
        int capacity = c[0];

        //BFhelper(capacity, numItems, items);
        DynamicTable(capacity, numItems, items);
        BranchAndBound.run(capacity, numItems, items);
        //greedy(capacity, numItems, items);
    }


    public static int safeGet(int[] table, int index){
        if(index<0) return 0;
        return table[index];
    }
    public static void DynamicTable(int capacity, int numItems, ArrayList<Item> items){
       int[] table = new int[capacity+1];
       ArrayList<Item> stack = new ArrayList<>();
       for( int i =1; i<numItems+1; i++)
           for(int w =capacity; w>=0; w--) {
               Item current = items.get(i - 1);
               if (current.getWeight() <= w) {
                   if (table[w] < table[w - current.getWeight()] + current.getValue()){
                       table[w] = table[w - current.getWeight()] + current.getValue();
                       //stack.add(current);
                   }
                       //table[w] = Math.max(table[w], table[w - items.get(i - 1).getWeight()] + items.get(i - 1).getValue());
               }
           }
       int n = capacity;
       int v = table[n];
        int weight = 0;
        int val = 0;
       ArrayList<Item> tempItems = new ArrayList<Item>(items);
       while(n > 0){
           //System.out.println(table[n]);
           //System.out.println(v + " : " + n);
           boolean found = false;
           for(Item i: tempItems) {
               if(n-i.getWeight() >= 0)

                   if (table[n] - i.getValue() == safeGet(table, n-i.getWeight())) {
                       n = n - i.getWeight();
                        v = v - i.getValue();
                       stack.add(i);
                       found=true;
                       //tempItems.remove(i);
                       break;
                   }
           }
           if(!found) n--;
       }
        weight = 0;
        val = 0;
        for(Item i:stack){
            weight+= i.getWeight();
            val+= i.getValue();
            System.out.println(i.getIndex());
        }
        System.out.println(  "Dy Capacity Used: "+ weight);
        System.out.println(  "Value Gained : "+ val);
        //System.out.println(table[capacity]);
    }

    public static void BFhelper(int capacity, int numItems, ArrayList<Item> items){
        int brute[] = bruteForce(capacity, numItems, items);

        int weight =0;
        int val =0;
        System.out.println(  Arrays.toString(brute));
        for(int i =1; i<brute.length; i++){
            weight+= brute[i]*items.get(i-1).getWeight();
            val+= brute[i]*items.get(i-1).getValue();
        }
        System.out.println(  "Capacity Used: "+ weight);
        System.out.println(  "Value Gained : "+ val);
    }
    public static int[] bruteForce(int capacity, int numItems, ArrayList<Item> items){

        if(capacity <= 0 || numItems==0){
            int result[] = new int[1+items.size()];
            result[0]=0;
            return result;
        }

        Item last = items.get(numItems-1);

        if(last.getWeight() > capacity) {
            return bruteForce(capacity, numItems - 1, items);
        }

        int with[] = bruteForce(capacity-last.getWeight(), numItems-1, items);
        int without[] = bruteForce(capacity, numItems-1, items);
        with[0] += last.getValue();
        if(with[0]>=without[0]){
            with[last.getIndex()] = 1;
            return with;
        } else {
            without[last.getIndex()] = 0;
            return without;
        }


    }

    public static void greedy(int capacity, int numItems, ArrayList<Item> items){
        int roomLeft = capacity;
        ArrayList<Item> sorted = new ArrayList<>(items);
        ArrayList<Item> stack = new ArrayList<>();
        Collections.sort(sorted);
        while(sorted.size() > 0){

            Item first = sorted.get(0);
            if(first.getWeight() <= roomLeft){
                roomLeft-=first.getWeight();
                stack.add(first);
            }
            sorted.remove(0);
        }
        int weight =0;
        int val =0;
        for(Item i: stack){
            weight+= i.getWeight();
            val+= i.getValue();
        }
        System.out.println(  "Capacity Used: "+ weight);
        System.out.println(  "Value Gained : "+ val);
    }



}
