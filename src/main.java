import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.util.Comparator.comparing;

public class main {

    public static void main(String args[]) throws IOException {
        long startTime = System.nanoTime();
        String filename = "src/easy50.txt";
        int ni[] = {0};
        int c[] = {0};

        ArrayList<Item> items = fileHelper.loadItems(filename, ni, c);

        int numItems = ni[0];
        int capacity = c[0];

        //Bruteforce.run(capacity, numItems, items);
        //Greedy.run(capacity, numItems, items);
        //System.out.println(Greedy.runInt(capacity, numItems, items));
        //Dynamic.run(capacity, numItems, items);
        BranchAndBound.run(capacity, numItems, items);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000 + " us");
    }

}
