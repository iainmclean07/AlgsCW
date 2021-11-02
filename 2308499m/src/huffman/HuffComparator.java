package huffman;

import java.util.Comparator;

public class HuffComparator implements Comparator<Node> {
    //Used for priority queue
    public int compare(Node left, Node right)
    {
        return left.getWeight() - right.getWeight();
    }
}
