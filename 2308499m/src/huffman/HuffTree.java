package huffman;

import java.util.*;


class HuffTree {

    int wpl;
    float ratio;

    HuffTree(String t){

        //calls build method, calculates the compressed file size
        this.wpl = build(t);
        this.ratio = (float) wpl/(t.length()*8);
    }

    private int build(String t){

        //converts full text to a character array
        char[] charArr = t.toCharArray();

        //maps characters to frequency they appear in the text
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (char c : charArr){
            Integer value = charMap.get(c);
            if (!(charMap.get(c) == null)){
                charMap.put(c, (value+1));
            }
            else {
                //if character doesn't appear in the text, set its frequency to 1
                charMap.put(c, 1);
            }
        }

        //for every character in the character map, create a parent-less node and assign it to its character frequency
        Node[] nodeArr = new Node[charMap.size()];
        int i = 0;
        for (Character c : charMap.keySet()){
            nodeArr[i] = new Node(c, charMap.get(c));
            nodeArr[i].setLeftChild(null);
            nodeArr[i].setRightChild(null);
            i++;
        }

        PriorityQueue<Node> queue = new PriorityQueue<Node>(nodeArr.length, new HuffComparator());

        queue.addAll(Arrays.asList(nodeArr));

        Node root = null;
        int wpl = 0;

        //for every node in the node queue
        while (queue.size() > 1){
            //pop the 2 minimum nodes in the queue
            Node temp1 = queue.peek();
            queue.poll();

            Node temp2 = queue.peek();
            queue.poll();

            //create a parent for the two nodes
            Node parent = new Node('-', temp1.getWeight() + temp2.getWeight());
            parent.setLeftChild(temp1);
            parent.setRightChild(temp2);

            //append parents weight to the total Weighted Path Length, in order to find compressed file size
            wpl += parent.getWeight();
            queue.add(parent);
        }
        return wpl;

    }
}
