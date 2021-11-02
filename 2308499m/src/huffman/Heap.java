package huffman;

public class Heap {

    int size;
    Node[] items;

    public Heap(int n){
        size = 0;
        items = new Node[n];
    }

    public Heap(int n, Node[] arr){
        size = arr.length;
        items = new Node[n];
        for (int i = 0; i < size    ; i++){
            items[i] = arr[i];
        }
        build();
    }

    private void build(){
        for (int i = (size-2)/2; i >= 0; i--){
            impose(i);
        }
    }

    public void insert(Node x){
        size++;
        int i = size-1;

        while (i > 0 && items[(i-1)/2].getWeight() > x.getWeight()){
            items[i] = items[(i-1)/2];
            i = (i-1)/2;
        }
        items[i] = x;
    }

    public Node deleteMin(){
        Node x = items[0];
        items[0] = items[size-1];
        size--;
        impose(0);
        return x;
    }

    private void impose(int i){
        Node temp = items[i]; //Node at position i
        int current = i; //current position to change or "bad value", good value not implemented
        boolean finished = false;

        while (2*current < size && !finished){
            //find larger child
            int next = 2*current+1;
            if (next+1 < size && items[next+1].getWeight() < items[next].getWeight()){
                next++; //change if right child exists and is larger
            }
            //originally had next, changed to next-1 for testing
            if(temp.getWeight() > items[next-1].getWeight()){//bad node(current node) if less than larger child
                items[current] = items[next]; //child becomes parent
                current = next; //new position bad node moved to child node
            }
            else finished = true; //not bad node so finished
        }
        items[current] = temp;
    }
}
