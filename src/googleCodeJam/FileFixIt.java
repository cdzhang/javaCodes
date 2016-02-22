package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class FileFixIt {
	String name; //the name of this directory
	LinkedList<FileFixIt> subDirs = new LinkedList<FileFixIt>();
	public static int countNew = 0;
	
	FileFixIt(){
		
	}
	FileFixIt(String name){
		this.name = name;
	}
	FileFixIt(String name,LinkedList<FileFixIt> ll){
		this.name = name;
		this.subDirs = ll;
	}
	public static void main(String[] arg) throws IOException{
		FileFixIt f = new FileFixIt();
		f.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int totalCasesNumber = Integer.parseInt(in.readLine());//first line
		for(int caseIndex=1;caseIndex<=totalCasesNumber;caseIndex++){
			//for each case, first line is "N M"
			String[] NM = in.readLine().split(" ");
			int N = Integer.parseInt(NM[0]);//number of lines of already existing dirs
			int M = Integer.parseInt(NM[1]);//number of lines of dirs to be created
			name = "/";
			subDirs = new LinkedList<FileFixIt>();
			int count = 0;
			for(int n=1;n<=N;n++){
				String eDir = in.readLine();
				this.createSubDir(eDir, false);//create existing dirs
			}
			for(int m=1;m<=M;m++){
				String nDir = in.readLine();
				count+=this.createSubDir(nDir,true);
			}
			out.println("Case #"+caseIndex+": " + count);
		}
		in.close();
		out.close();
	}
	public static LinkedList<String> convert(String dirs){
		String[] s = dirs.split("/");
		LinkedList<String>  cs = new LinkedList<String> ();
		for(int i=0;i<s.length;i++)
			if(s[i].length()!=0)
				cs.add(s[i]);
		return cs;
	}
	int createSubDir(String dirs, boolean count){
		LinkedList<String>  cs = convert(dirs);
		return createSubDir(cs,count);
	}
	int createSubDir(LinkedList<String> s,boolean count){
		if(s.size() == 0){
			return 0;
		}
		int r = 0;
		String name = s.get(0);//first directory
		FileFixIt sub = isSubDir(name);
		if(sub == null){
			sub = new FileFixIt(name);
			subDirs.add(sub);
			r = 1;
			if(count)
				countNew++;
		}
		s.remove(name);
		return r + sub.createSubDir(s, count);
	}
	public void print(){
		System.out.println(name);
		if(subDirs != null){
			for(FileFixIt f:subDirs){
				f.print();
			}
		}
	}
	FileFixIt isSubDir(String name){
		if(subDirs == null)
			return null;
		for(FileFixIt f:subDirs){
			if(f.name.equals(name))
				return f;
		}
		return null;
	}
}
