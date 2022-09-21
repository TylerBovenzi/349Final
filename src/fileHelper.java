import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class fileHelper {

    public static ArrayList<Item> loadItems(String filename, int[] numItems, int[] capacity) throws IOException {
        ArrayList<Item> result = new ArrayList<>();
        FileInputStream file = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        numItems[0] = parseInt(br.readLine().replaceAll("\t", ""));
        for(int i =0; i<numItems[0]; i++){
            String line = br.readLine().replaceAll("\t", " ");
            ArrayList<String> itemString = new ArrayList<>(Arrays.asList(line.split(" ")));

            //Remove Spaces
            for(int s=itemString.size()-1; s >=0 ; s--) {
                itemString.set(s, itemString.get(s).replaceAll(" ", ""));
                if (itemString.get(s).equals("")){
                    itemString.remove(s);
                }
            }

            //System.out.println(Arrays.deepToString(itemString.toArray()));

            result.add(new Item(
                    parseInt(itemString.get(0)),
                    parseInt(itemString.get(1)),
                    parseInt(itemString.get(2))
            ));
        }
        capacity[0] = parseInt(br.readLine().replaceAll("\t", ""));
        return result;
    }


}
