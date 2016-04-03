package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.Tools;

public class Fairland {
	int N, D;
	Employee[] ems;
	Employee ceo;
	LinkedList<Employee> sorted = new LinkedList<Employee>();
	public static void main(String[] args) throws IOException {
		Fairland t = new Fairland();
		t.solve();
		Tools.println(Integer.MAX_VALUE);
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			sorted.clear();
			int[] nd = Tools.intArray(in.readLine(), " ");
			N = nd[0];
			D = nd[1];
			long[] line1 = Tools.longArray(in.readLine(), " ");
			long S0 = line1[0];
			long As = line1[1];
			long Cs = line1[2];
			long Rs = line1[3];
			long[] line2 = Tools.longArray(in.readLine(), " ");
			long M0 = line2[0];
			long Am = line2[1];
			long Cm = line2[2];
			long Rm = line2[3];
			ems = new Employee[N];
			ceo = new Employee(0,S0);
			sorted.add(ceo);
			ems[0] = ceo;
			long Si = S0;
			long Mi = M0;
			for(int i=1;i<N;i++){
				Si = (Si*As+Cs)%Rs;
				Mi = (Mi*Am+Cm)%Rm;
				int mID = (int)(Mi%i);
				Employee aE = new Employee(i,Si,ems[mID]);
				ems[i] = aE;
				sorted.add(aE);
			}
			Collections.sort(sorted);
			ceo.countAll();
			if(caseN==48)
				printMap(ceo);
			String output = "Case #"+caseN + ": "+countFinal();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	void printMap(Employee E){
		Tools.print(E.id+"->");
		for(Employee Es:E.sbor){
			Tools.print(Es.id+" ");
		}
		Tools.println();
		for(Employee Es:E.sbor){
			printMap(Es);
		}
	}
	int countFinal(){
		layoffIllegal(ceo);
		Employee Emax = sorted.getLast();
		Employee Emin = sorted.getFirst();
		long max = Emax.salary;
		long min = Emin.salary;
		while(Math.abs(max-min)>D){
			if(Emax.allSbo >= Emin.allSbo){
				layoff(Emin,true);
				Emin = sorted.getFirst();
				min = Emin.salary;
			}else{
				layoff(Emax,true);
				Emax = sorted.getLast();
				max = Emax.salary;
			}
		}
		
		return sorted.size();
	}
	void layoffIllegal(Employee E){
		if(isIllegal(E))
			layoff(E,true);
		else{
			for(Employee Es:E.sbor){
				layoffIllegal(Es);
			}
		}
	}
	boolean isIllegal(Employee E){
		if(E.left||E.id==0)
			return false;
		Employee manager = E.manager;
		while(manager !=null){
			if(Math.abs(manager.salary-E.salary)>D){
				return true;
			}
			manager = manager.manager;
		}
		return false;
	}
	public void layoff(Employee E,boolean first){
		if(first){
			Employee manager = E.manager;
			int allS = E.allSbo;
			while(manager!=null){
				manager.allSbo = manager.allSbo - allS;
				manager = manager.manager;
			}
		}
		E.left = true;
		sorted.remove(E);
		for(Employee Es:E.sbor){
			layoff(Es,false);
		}
	}
}
class Employee implements Comparable<Employee>{
	int id;
	long salary;
	Employee manager = null;
	LinkedList<Employee> sbor = new LinkedList<Employee>();
	boolean left;
	int allSbo = -1;
	Employee(){}
	Employee(int id, long salary){
		this.id = id;
		this.salary = salary;
	}
	Employee(int id, long salary,Employee manager){
		this.id = id;
		this.salary = salary;
		this.manager = manager;
		manager.sbor.add(this);
	}
	int countAll(){
		if(allSbo!=-1)
			return allSbo;
		int count = 1;
		for(Employee E:sbor){
			count += E.countAll();
		}
		allSbo = count;
		return count;
	}
	public int compareTo(Employee E) {
		// TODO Auto-generated method stub
		if(salary>E.salary)
			return 1;
		else if(salary < E.salary)
			return -1;
		return 0;
	}
}