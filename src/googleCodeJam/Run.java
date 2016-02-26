package googleCodeJam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JFileChooser;

public class Run {
	public static void main(String[] args) throws IOException {
		String reguex = "\b|\t|(\b)+";
		String s = "a b  c	d";
		String[] as = s.split(" +|\t");
		for(String bs:as){
			System.out.println(bs);
		}
	}

}
