/*
There are 3 doors. Behind one door there is a car. 
There two goats behind each of the other two doors
You select one door, the instructor will show you one door 
with a goat from the remaining doors. 
The questions, if you want a car, do you switch your choice or not?
 * 
 */
package solutionForFun;

import java.util.Random;

public class CarAndGoat {
	int[] doors = {0,0,0};
	public static void main(String[] args) {
		CarAndGoat c = new CarAndGoat();
		for(int n=1;n <= 100;n++){
			System.out.println(c.noSwitchExperiment());
		}
		
	}
	CarAndGoat(){
		int i = new Random().nextInt(3);
		doors[i] = 1;//1: car,0: goat
	}
	int noSwitchExperiment(){
		int choice = new Random().nextInt(3);
		int show = new Random().nextInt(3);
		while(show == choice || doors[show] == 1){
			show = new Random().nextInt(3);
		}
		return doors[choice];
	}
}
