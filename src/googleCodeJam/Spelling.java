package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Spelling {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        Spelling sp = new Spelling();
        sp.convertSpelling();
	}
	private void convertSpelling() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
    	BufferedReader in = new BufferedReader(new FileReader(inputFile));
    	PrintWriter out = new PrintWriter(outputFile);
    	int L = Integer.parseInt(in.readLine());//first line
    	for(int i=1;i<=L;i++){
    		String line = in.readLine();
    		String numberCode = translateString(line);
    		out.println("Case #" + i + ": " + numberCode);
    	}
    	in.close();
    	out.close();
	}
	private int convertChar(char c){//{number to press, last number}
		int i;
		c = Character.toLowerCase(c);
		switch (c){
		case 'a': i = 2; break;
		case 'b': i = 22; break;
		case 'c': i = 222; break;
		case 'd': i = 3; break;
		case 'e': i = 33; break;
		case 'f': i = 333; break;
		case 'g': i = 4; break;
		case 'h': i = 44; break;
		case 'i': i = 444; break;
		case 'j': i = 5; break;
		case 'k': i = 55; break;
		case 'l': i = 555; break;
		case 'm': i = 6; break;
		case 'n': i = 66; break;
		case 'o': i = 666; break;
		case 'p': i = 7; break;
		case 'q': i = 77; break;
		case 'r': i = 777; break;
		case 's': i = 7777; break;
		case 't': i = 8; break;
		case 'u': i = 88; break;
		case 'v': i = 888; break;
		case 'w': i = 9; break;
		case 'x': i = 99; break;
		case 'y': i = 999; break;
		case 'z': i = 9999; break;
		case ' ': i = 0; break;
		default: return i=-1;
		}
		return i;
	}
	private String translateString(String line){
		int L = line.length();
		String number = "";
		int lastDigit = -1;
		for(int i=0;i<L;i++){
			char c = line.charAt(i);
			int j = convertChar(c);
			int digit = j % 10;
			if(digit==lastDigit){
				number = number + " " + j;
			}else{
				number = number + "" + j;
			}
			lastDigit = digit;
		}
		return number;
	}
}
