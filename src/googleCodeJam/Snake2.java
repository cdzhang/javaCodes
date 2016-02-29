package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Snake2 {
	//take care if the snake is on one line, next food?
	//if after the turn has done
	int heading = 2;//0,1,2,3: left, up, right, down respectively
	int S, R, C;
	int[][] turns;
	int time = 0;
	int maxTime = 1000000000;
	LinkedList<Body> snake = new LinkedList<Body>();
	public static void main(String[] args) throws IOException{
		Snake2 s = new Snake2();
		//System.out.println(s.maxTime);
		s.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			snake.clear();
			snake.add(new Body(0,0));
			heading = 2;
			time = 0;
			String[] SRC = in.readLine().split(" ");
			S = Integer.parseInt(SRC[0]);
			R = Integer.parseInt(SRC[1]);
			C = Integer.parseInt(SRC[2]);
			turns = new int[S][2];
			for(int s=0;s<S;s++){
				String[] tT = in.readLine().split(" ");
				int t = Integer.parseInt(tT[0]);
				char turn = tT[1].charAt(0);
				turns[s][0] = t;
				turns[s][1] = (turn == 'L'? 3:1);//0 -> turn left, 1-> turn right
			}
			///solving how snakes moves
			int nI = 0;//nextIndex
			int nT = turns[nI][0];
			boolean dead = false;
			while(true){
				if(time == nT){
					int turn = turns[nI][1];
					heading = (heading + turn) % 4;
					nI++;
					if(nI == S )
						break;
					nT = turns[nI][0];
				}
				if(!move(1)){
					dead = true;
					break;
				}
				//printSnake();
			}// do all the turns in turns
			if(!dead && time < maxTime){
				for(int i=0;i<=snake.size() || i <= R || i <= C;i++){
					if(!move(1)){
						dead = true;
						break;
					}
				}
			}
			System.out.println("time="+time);
			String output = "Case #" + caseN + ": " + snake.size();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}

	public boolean hasFood(int r,int c,int t){//if there is food here
		//if it is on the track of the snake head, then there is no food
		if((r+c)%2==0)
			return false;
		int facing = 2;//initially to right
		int tt = 0;
		int hr = 0;
		int hc = 0;
		for(int s=0;s<=S;s++){
			int ti=0;
			if(s==S)
				ti = t;
			else
			    ti = turns[s][0];
			int dt = Math.min(ti,t) - tt;
			if(r==hr){
				if(facing==0){
					int dist = hc - c;
					if(dist < 0)
						dist = dist + C;
					if(dist <= dt)
						return false;
				}else if(facing==2){
					int dist = c - hc;
					if(dist < 0)
						dist = dist + C;
					if(dist <= dt)
						return false;
				}
			}else if(c==hc){
				if(facing==1){
					int dist = hr - r;
					if(dist < 0)
						dist = dist + R;
					if(dist <= dt)
						return false;
				}else if(facing==3){
					int dist = r - hr;
					if(dist < 0)
						dist = dist + R;
					if(dist <= dt)
						return false;
				}
			}
			tt = ti;
			if(tt > t)
				return true;
            int[] mv = getFacing(facing);
            if(s < S){
	            hr = hr + mv[0]*dt;
	            hr = ((hr%R)+R)%R;
	            hc = hc + mv[1]*dt;
	            hc = ((hc%C+C)%C);
				facing = (facing + turns[s][1])%4;
            }
		}
		return true;
	}
	int[] getFacing(int facing){
		if(facing==0){
			return new int[]{0,-1};
		}
		if(facing==1){
			return new int[]{-1,0};
		}
		if(facing==2)
			return new int[]{0,1};
		if(facing==3)
			return new int[]{1,0};
		return null;
	}
	public boolean move(int step){//move "step" steps of the snake
		int L = snake.size();
		int r = 0;
		int c = 0;
		Body h = snake.getLast();
		/*if(heading == 0){//left
			r = h.r;
			c = h.c - step;
		}else if(heading == 1){//up
			r = h.r - step;
			c = h.c;
		}else if(heading == 2){//right
			r = h.r;
			c = h.c + step;
		}else if(heading == 3){//down
			r = h.r + step;
			c = h.c;
		}
        r = r%R;
        r = (r + R) % R;
        c = c%C;
        c = (c + C)% C;
        */
        int[] mv = getFacing(heading);
        r = h.r + mv[0]*step;
        r = ((r%R)+R)%R;
        c = h.c + mv[1]*step;
        c = ((c%C+C)%C);
        boolean hf = hasFood(r,c,time);
		if(hf){//has food
			//System.out.println("[r,c]=["+r+","+c+"],time="+time+":"+hf);
			snake.add(new Body(r,c));
		}else{
			for(int l=0;l<L-1;l++){
				snake.get(l).set(snake.get(l+1));
			}
			snake.getLast().set(r,c);
		}
		time += step;
		if(time >= maxTime || isDead()){
			return false;
		}
		return true;
	}
    public boolean isDead(){
    	int L = snake.size();
    	if(L == 1)
    		return false;
    	Body head = snake.getLast();
    	for(int l=0; l<L-1; l++){
    		if(head.equals(snake.get(l))){
    			return true;
    		}
    	}
    	return false;
    }
}
/*class Body{//snake body
	int r;
	int c;
	Body(){
	}
	Body(int r1,int c1){
		r = r1;
		c = c1;
	}
	public boolean equals(Body b){
		return (r==b.r && c==b.c);
	}
	void set(Body b){
		r = b.r;
		c = b.c;
	}
	void set(int r1,int c1){
		r = r1;
		c = c1;
	}
}*/
