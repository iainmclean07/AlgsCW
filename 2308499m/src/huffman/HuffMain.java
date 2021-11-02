package huffman;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
//import

/** program to find compression ratio using Huffman coding
 */
public class HuffMain {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		//reads in whole file and converts to String
		byte[] chars = Files.readAllBytes(Paths.get(args[0]));
		String str = new String(chars);
		HuffTree temp = new HuffTree(str);

		// output the results here
		System.out.println("Original file length in bits = "+str.length()*8);
        System.out.println("Compressed file length in bits = "+temp.wpl);
        System.out.printf("Compression ratio: %.4f\n", temp.ratio);

        // end timer and print elapsed time as last line of output
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}
}
