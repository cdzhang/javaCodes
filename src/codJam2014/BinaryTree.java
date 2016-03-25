package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BinaryTree {
	Node[] nodes;
	int N;
	public static void main(String[] args) throws IOException {
		BinaryTree t = new BinaryTree();
		t.solve();
	}
	void run(){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(null, 1);
		Tools.println(map.get(null));
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			N = Integer.parseInt(in.readLine());
			nodes = new Node[N+1];
			for(int n=1;n<=N;n++){
				nodes[n] = new Node();
			}
			for(int n=1;n<=N-1;n++){
				int[] l = Tools.intArray(in.readLine(), " ");
				Node n1 = nodes[l[0]];
				Node n2 = nodes[l[1]];
				n1.connected.add(n2);
				n2.connected.add(n1);
			}
			int maxN = 1;
			for(int n=1;n<=N;n++){
				int count = nodes[n].countNodes(null);
				if(count > maxN)
					maxN = count;
			}
			int cut = N - maxN;
			String output = "Case #"+caseN + ": "+cut;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	int countNodes(Node node,Node parent){
		
		return 0;
	}
}
class Node{
	LinkedList<Node> connected = new LinkedList<Node>();
	Map<Node,Integer> map = new HashMap<Node,Integer>();
	int countNodes(Node parent){//this node is root, the number of full nodes
		Integer c = map.get(parent);
		if(c!=null){
			return c;
		}
		int cc = 0;
		for(Node n:connected){
			if(n!=parent)
				cc++;
		}
		int count;
		if(cc<=1)
			count = 1;
		else{
			int max1 = -1;
			int max2 = -1;
			for(Node n:connected){
			   if(n==parent)
				   continue;
			   int cn = n.countNodes(this);
			   if(max1<0 || cn>max1){
				   max2 = max1;
				   max1 = cn;
			   }else if(max2<0 || cn>max2){
				   max2 = cn;
			   }
			}
			count = max1 + max2 + 1;
		}
		map.put(parent, count);
		return count;
	}
}
