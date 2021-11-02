package lzw;

public class codeNode {
    private boolean found;
    private int codewordlen;

    public codeNode(boolean found, int codewordlen){
        this.found = found;
        this.codewordlen = codewordlen;
    }

    public int getCodewordlen() {
        return codewordlen;
    }

    public void setCodewordlen(int codewordlen) {
        this.codewordlen = codewordlen;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
