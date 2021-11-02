package lzw;

public class Node {
	
	private char letter; // label on incoming branch
	private boolean isWord; // true when node represents a word
	private Node sibling; // next sibling (when it exists)
	private Node child; // first child (when it exists)
	
	/** create a new node with letter c */
    Node(char c){
		letter = c;
		isWord = false;
		sibling = null;
		child = null;
	}
	
	// include accessors and mutators for
	// the various components of the class


	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	Node getSibling() {
		return sibling;
	}

	void setSibling(Node sibling) {
		this.sibling = sibling;
	}

	public Node getChild() {
		return child;
	}

	public void setChild(Node child) {
		this.child = child;
	}

	boolean getIsWord(){
		return isWord;
	}

	void setIsWord(boolean word) {
		isWord = word;
	}
}
