package lzw;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/** program to find compression ratio using LZW compression
 */
public class LZWMain {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		LZW temp = new LZW(args[0]);

		// write out the results here
        System.out.println("Original file length in bits = "+temp.uncompressedFilesize*8);
        System.out.println("Compressed file length in bits = "+temp.compressedSize);
        System.out.printf("Compression ratio: %.4f\n", temp.ratio);

		long end = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (end - start) + " milliseconds");

	}

}
