package huffman;

public class Node {

    private char letter;
    private int weight;
    private Node leftChild;
    private Node rightChild;
//    private Node parent;

    public Node (char c, int i){
        letter = c;
        weight = i;
        leftChild = null;
        rightChild = null;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getWeight(){
        return weight;
    }

    public void increaseWeight(){
        this.weight += 1;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
