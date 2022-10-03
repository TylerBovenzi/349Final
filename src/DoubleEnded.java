import java.util.*;

public class DoubleEnded {

            Set<Node> s;
            DoubleEnded()
            {
                s= new HashSet<Node>();
            }
            // Returns size of the queue. Works in
            // O(1) time
            int size()
            {
                return s.size();
            }

            // Returns true if queue is empty. Works
            // in O(1) time
            boolean isEmpty()
            {
                return (s.size() == 0);
            }

            // Inserts an element. Works in O(Log n)
            // time
            void add(Node x)
            {
                s.add(x);

            }

            // Returns minimum element. Works in O(1)
            // time
            Node getMin()
            {
                return Collections.min(s);
            }

            // Returns maximum element. Works in O(1)
            // time
            Node getMax()
            {
                return Collections.max(s);
            }

            // Deletes minimum element. Works in O(Log n)
            // time
            void deleteMin()
            {
                if (s.size() == 0)
                    return ;
                s.remove(Collections.min(s,null));

            }

            // Deletes maximum element. Works in O(Log n)
            // time
            void deleteMax()
            {
                if (s.size() == 0)
                    return ;
                s.remove(Collections.max(s,null));

            }
        };



