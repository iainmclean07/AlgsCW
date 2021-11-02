package lzw;

import java.io.FileReader;
import java.io.IOException;

public class LZW {

    int compressedSize;
    double ratio;
    int uncompressedFilesize;
    private int dictSize = 128;
    private int codewordlen = 8;
    private Trie trie;
    private StringBuilder cur;

    public LZW(String path) throws IOException {
        this.compressedSize = 0;
        this.trie = new Trie();
        this.cur = new StringBuilder();

        //initiate trie with all characters in ascii
        for (int i = 0; i < 128; ++i){
            this.trie.insert(Character.toString((char)i));
        }

        FileReader reader = new FileReader(path);
        this.uncompressedFilesize = 0;
        int c;
        //for every character in the text
        while ((c = reader.read()) != -1){
            build((char)c);
            uncompressedFilesize ++;
        }
        codeNode lastWord = this.trie.codewordLenFind(cur.toString());
        if (lastWord.isFound()){
            compressedSize += lastWord.getCodewordlen();
        }
        this.ratio = (float)compressedSize/(uncompressedFilesize*8);
        reader.close();
    }

    private void build(char text){
        //current string += next character
        cur.append(text);
        //if the current string doesn't appear in the trie
        if(!(trie.search(cur.toString()))){
            //insert new string into trie, increase the dictionary size, and reset the string
            trie.insert(cur.toString());
            this.dictSize++;
            cur.delete(0, cur.length());
            cur.append(text);
            this.compressedSize += codewordlen;
            //if the length of the number of bits used to represent the new codeword length, then increase the codeword length
            if (countBits(dictSize) > codewordlen){
                codewordlen++;
            }

        }
    }

    private int countBits(int number)
    {
        // log function in base 2
        // take only integer part
        return (int)(Math.log(number) /
                Math.log(2) + 1);
    }
}
