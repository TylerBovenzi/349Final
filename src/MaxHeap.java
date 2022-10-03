import java.util.ArrayList;

public class MaxHeap {
        public ArrayList<Node> Heap;
        private int size;
        private int maxsize;

        // Constructor to initialize an
        // empty max heap with given maximum
        // capacity
        public MaxHeap()
        {
            // This keyword refers to current instance itself
            this.size = 0;
            Heap = new ArrayList<>();
        }

        public int getSize(){
            return size;
        }

        private int parent(int pos) { return (pos - 1) / 2; }

        private int leftChild(int pos) { return (2 * pos) + 1; }

        private int rightChild(int pos)
        {
            return (2 * pos) + 2;
        }

        private boolean isLeaf(int pos)
        {
            if (pos >= (Math.floorDiv(size, 2)) && pos <= size) {
                return true;
            }
            return false;
        }
        private void swap(int fpos, int spos)
        {
            Node tmp;
            tmp = Heap.get(fpos);
            Heap.set(fpos, Heap.get(spos));
            Heap.set(spos, tmp);
        }

        private void maxHeapify(int pos)
        {
            if (isLeaf(pos))
                return;

            int l = -1;
            int r = -1;

            //if(leftChild(pos) < size + 2)
            l = Heap.get(leftChild(pos)).getBound();
            if(rightChild(pos) < size) r = Heap.get(rightChild(pos)).getBound();

            if(Heap.get(pos).getBound() >= Math.max(l,r))
                return;


            if(r > -1 && r > l){
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            } else {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }
//
//            if (Heap.get(pos).getBound() < Heap.get(leftChild(pos)).getBound()
//                    || Heap.get(pos).getBound() < Heap.get(rightChild(pos)).getBound()) {
//
//                if (Heap.get(leftChild(pos)).getBound()
//                        > Heap.get(rightChild(pos)).getBound()) {
//                    swap(pos, leftChild(pos));
//                    maxHeapify(leftChild(pos));
//                }
//                else {
//                    swap(pos, rightChild(pos));
//                    maxHeapify(rightChild(pos));
//                }
//            }
        }

        public void prune(int max, int iteration){


            if(size > 2 && iteration < 1000) {
                if (Heap.get(size - 1).getBound() < max && Heap.get(size - 1).getValue() < max) {
                    Heap.remove(size - 1);
                    size--;
                    prune(max, iteration+1);
                }
            } else {
                swap(Math.floorDiv(size,2)+1 + (int)Math.floor(size-(Math.floorDiv(size,2)+1)* Math.random()), size-1);
            }

        }

        public void add(Node element)
        {

            Heap.add(element);

            // Traverse up and fix violated property
            int current = size;
            while (Heap.get(current).getBound() > Heap.get(parent(current)).getBound()) {
                swap(current, parent(current));
                current = parent(current);
            }
            size++;
        }

//        public void print()
//        {
//
//            for (int i = 0; i < size / 2; i++) {
//
//                System.out.print("Parent Node : " + Heap[i]);
//
//                if (leftChild(i)
//                        < size) // if the child is out of the bound
//                    // of the array
//                    System.out.print(" Left Child Node: "
//                            + Heap[leftChild(i)]);
//
//                if (rightChild(i)
//                        < size) // if the right child index must not
//                    // be out of the index of the array
//                    System.out.print(" Right Child Node: "
//                            + Heap[rightChild(i)]);
//
//                System.out.println(); // for new line
//            }
//        }

        public Node extractMax()
        {
            Node popped = Heap.get(0);
            //if(Heap.size() == 1){
            //    size--;
            //}else {
            size--;
                Heap.set(0, Heap.get(size));
                Heap.remove(size);
                maxHeapify(0);
            //}

            return popped;
        }

//        public static void main(String[] arg)
//        {
//            // Display message for better readability
//            System.out.println("The Max Heap is ");
//
//            MaxHeap maxHeap = new MaxHeap(15);
//
//            // Inserting nodes
//            // Custom inputs
//            maxHeap.insert(5);
//            maxHeap.insert(3);
//            maxHeap.insert(17);
//            maxHeap.insert(10);
//            maxHeap.insert(84);
//            maxHeap.insert(19);
//            maxHeap.insert(6);
//            maxHeap.insert(22);
//            maxHeap.insert(9);
//
//            // Calling maxHeap() as defined above
//            maxHeap.print();
//
//            // Print and display the maximum value in heap
//            System.out.println("The max val is "
//                    + maxHeap.extractMax());
//        }
    }