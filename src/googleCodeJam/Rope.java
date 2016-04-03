package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class Rope {
   public static int count;
   public static void main(String[] arg) throws IOException{
	   Rope r = new Rope();
	   r.solve();
   }
   public void solve() throws IOException {
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN = 1;caseN <= T;caseN++){
			count = 0;
			int N = Integer.parseInt(in.readLine());
			int[][] AB = new int[N][2];
			for(int n=1;n<=N;n++){
				String[] s = in.readLine().split(" ");
				AB[n-1][0] = Integer.parseInt(s[0]);
				AB[n-1][1] = Integer.parseInt(s[1]);
			}
			sortOnFirstColumn(AB);
			sortOnSecondColumn(AB);
			String output = "Case #"+caseN + ": " +count;
			System.out.println(output);
			out.println(output);
			/*for(int i=0;i<N;i++){
				System.out.println(AB[i][0] +","+ AB[i][1]);
			}*/
		}
		in.close();
		out.close();
   }
   public void sortOnFirstColumn(int[][] array){
	   int switchTimes = 0;
	   for(int i=0;i<array.length-1;i++){
		   int j = i+1;
		   if(array[i][0] > array[j][0] ){
			   int[] a = new int[2];
			   a[0] = array[i][0];
			   a[1] = array[i][1];
			   array[i][0] = array[j][0];
			   array[i][1] = array[j][1];
			   array[j][0] = a[0];
			   array[j][1] = a[1];
			   switchTimes++;
		   }
	   }
	   if(switchTimes==0){
		   return;
	   }else{
		   sortOnFirstColumn(array);
	   }
   }
   public void sortOnSecondColumn(int[][] array){
	   int switchTimes = 0;
	   for(int i=0;i<array.length-1;i++){
		   int j = i+1;
		   if(array[i][1] > array[j][1] ){
			   int[] a = new int[2];
			   a[0] = array[i][0];
			   a[1] = array[i][1];
			   array[i][0] = array[j][0];
			   array[i][1] = array[j][1];
			   array[j][0] = a[0];
			   array[j][1] = a[1];
			   switchTimes++;
			   count++;
		   }
	   }
	   if(switchTimes==0){
		   return;
	   }else{
		   sortOnSecondColumn(array);
	   }
   }

}
