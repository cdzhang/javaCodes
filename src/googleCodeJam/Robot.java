package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import tools.Tools;
/*
 * because x^x = x^x, x^y=y^x
 * a^b^c^d = K <=>
 * a^b^c^d^c^d = K^c^d
 * a^b ^ c^d = K^c^d
 */
public class Robot {
	int[] A,B,C,D;
	int N,K;
	public static void main(String[] args) throws IOException {
		Robot t = new Robot();
		t.solve();
		Tools.println(1^0);
		Tools.println(2147483646^2140000001);
		Tools.println(-2147483646^7483647);
		Tools.println((-2147483646)^(-7483647));
		Tools.println(Integer.MAX_VALUE);
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] NK = Tools.intArray(in.readLine(), " ");
			N = NK[0];
			K = NK[1];
			A = Tools.intArray(in.readLine()," ");
			B = Tools.intArray(in.readLine(), " ");
			C = Tools.intArray(in.readLine()," ");
			D = Tools.intArray(in.readLine()," ");
			String output = "Case #"+caseN + ": " +getNumber2();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long getNumber(){
		long count=0;
		for(int ia=0;ia<N;ia++)
			for(int ib=0;ib<N;ib++)
				for(int ic=0;ic<N;ic++)
					for(int id=0;id<N;id++)
						if((A[ia]^B[ib]^C[ic]^D[id]) ==K )
							count++;
		return count;
	}
	long getNumber2(){
		long startTime = System.currentTimeMillis();
		Map<Long,Long> mAB = new HashMap<Long,Long>();
		Map<Long,Long> mCD = new HashMap<Long,Long>();
		for(int i1=0;i1<N;i1++){
			for(int i2=0;i2<N;i2++){
				long ab = A[i1]^B[i2];
				//long cd = C[i1]^D[i2];
				if(mAB.containsKey(ab)){
					mAB.put(ab, mAB.get(ab)+1);
				}else{
					mAB.put(ab, 1L);
				}
				/*if(mCD.containsKey(cd)){
					mCD.put(cd, mCD.get(cd)+1);
				}else{
					mCD.put(cd, 1L);
				}*/
			}
		}
		long count = 0;
		for(int i1=0;i1<N;i1++){
			for(int i2=0;i2<N;i2++){
				long cd = C[i1]^D[i2]^K;
				Long ccd = mAB.get(cd);
				if(ccd!=null)
					count +=  ccd;
			}
		}
		Tools.println(System.currentTimeMillis()-startTime);
		
		/*for (Map.Entry<Long,Long> eAB : mAB.entrySet()) {
			long ab = eAB.getKey();
		    long countAB = eAB.getValue();
		    for (Map.Entry<Long,Long> eCD : mCD.entrySet()) {
			    long cd = eCD.getKey();
			    long countCD = eCD.getValue();
			    if((ab^cd)==K)
			    	count += countAB*countCD;
		    }
		}*/
		Tools.println(System.currentTimeMillis()-startTime);
		
		return count;
	}

}
