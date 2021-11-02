package lzw;

import java.util.ArrayList;

public class Trie {
	
	// create root of the trie
	private Node root;
    private ArrayList<String> words = new ArrayList<String>();
	
	Trie() {
		// null character in the root
		root = new Node(Character.MIN_VALUE);
	}        
	
	// possible outcomes of a search
	private enum Outcomes {PRESENT, ABSENT, UNKNOWN}
	
	/** search trie for word w */
	public boolean search(String w) {
		
		// initially outcome unknown
		Outcomes outcome = Outcomes.UNKNOWN;
		
		// position in word so far searched (start at beginning)
		int i = 0;
		
		// start with first child of the root
		Node current = root.getChild();
		
		// start search
		while (outcome == Outcomes.UNKNOWN) {
			
			if (current == null) outcome = Outcomes.ABSENT; // dead-end
			else if (current.getLetter() == w.charAt(i)) { // positions match				
				if (i == w.length() - 1) {
					outcome = Outcomes.PRESENT; // matched word
				}
				else { // descend one level...
					current = current.getChild(); // in trie
					i++; // in word being searched
				}
			}	
			else { // positions not matched so try next sibling
				current = current.getSibling();
			}
		}
		// return answer
		if (outcome != Outcomes.PRESENT) return false;
		else return current.getIsWord();
	}
	
	/** insert word w into trie */
    void insert(String w){
		
		int i = 0; // position in word (start at beginning)
		Node current = root; // current node in trie (start at root)
		Node next = current.getChild(); // first child of current node we are testing
		
		while (i < w.length()) { // not reached end of word
			if (next !=null && next.getLetter() == w.charAt(i)) { // chars match: decend a level
				current = next; // update node to the child node
				next = current.getChild(); // update child node
				i++; // next position in the word
			} 
			else if (next != null) { // try next sibling
				next = next.getSibling();
			}
			else { // no more siblings: need new node
				Node x = new Node(w.charAt(i)); // label with ith element of the word
				x.setSibling(current.getChild()); // sibling: first child of current node
				current.setChild(x); // make first child of current node
				current = x; // move to the new node
				next = current.getChild(); // update child node
				i++; // next position in word
			}
		}
		current.setIsWord(true); // current represents word w
		
	}
	
	// delete method to be added
	public codeNode codewordLenFind (String w){
		// initially outcome unknown
		Outcomes outcome = Outcomes.UNKNOWN;

		// position in word so far searched (start at beginning)
		int i = 0;
		int codewordlen = 8;
		// start with first child of the root
		Node current = root.getChild();

		// start search
		while (outcome == Outcomes.UNKNOWN) {

			if (current == null) outcome = Outcomes.ABSENT; // dead-end
			else if (current.getLetter() == w.charAt(i)) { // positions match
				if (i == w.length() - 1) {
					outcome = Outcomes.PRESENT; // matched word
				}
				else { // descend one level...
					current = current.getChild(); // in trie
					i++; // in word being searched
					codewordlen++;
				}
			}
			else { // positions not matched so try next sibling
				current = current.getSibling();
			}
		}
		// return answer

		if (outcome != Outcomes.PRESENT){
			codeNode node = new codeNode(false, 0);
			return node;
		}else{
			codeNode node = new codeNode(true, codewordlen);
			return node;
		}
	}

	// traverse method (extracting words) to be added
    private void traverse(Node t, String s){
        if (t != null){ // not at a leaf

            if (t.getIsWord()) words.add(s + t.getLetter()); // if node represents a word: add word to list

            // continue the traversal
            traverse(t.getChild(), s + t.getLetter()); // first look at children (note string changes)
            traverse(t.getSibling(), s); // then look at siblings
        }
    }

    ArrayList<String> extract(){
        words.clear(); // clear the array list
        String s = ""; // path to root yields empty string
        traverse(root.getChild(), s); // start traversal
        return words;
    }
}
