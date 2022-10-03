import java.util.ArrayList;

public class Node implements Comparable{

        private Node parent;
        private int value;
        private int weight;
        private int bound;
        private final int known;
        private boolean including; //0 for no, 1 for yes

        public Node(Node parent, boolean including, ArrayList<Item> items, int capacity, int numItems){
            this.parent = parent;
            this.value = parent.value;
            this.known = parent.known + 1;
            if(including) value += items.get(known-1).getValue();
            this.weight = parent.weight;
            if(including) weight += items.get(known-1).getWeight();
            this.including = including;
            this.bound = 0;
            if(known != numItems) {this.bound = this.getBound(this.known, capacity, items, including);
            } else this.bound=value;

        }

        public Node(int bound){
            this.parent = null;
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


        public Node getParent() {
            return parent;
        }

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
            //return (-this.getBound()) + (other.getBound());
            return -this.getBound() + other.getBound();
        }
    }

