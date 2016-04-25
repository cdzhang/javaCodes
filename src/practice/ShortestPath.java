package practice;

import java.util.LinkedList;
import java.util.Queue;

import tools.TT;

public class ShortestPath {
	public static void main(String[] arg){
		String[] s = {
				"#S######.#",
				"......#..#",
				".#.##.##.#",
				".#........",
				"##.##.####",
				"....#....#",
				".#######.#",
				"....#.....",
				".####.###.",
				"....#...G#",};
		int R = s.length;
		int C = s.length;
		char[][] map = new char[R][C];
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				map[i][j] = s[i].charAt(j);
			}
		}
		ShortestPath sh = new ShortestPath();
		TT.println(s);
		TT.println(sh.shortestPath(map));
	}
	int shortestPath(char[][] map){
		int[] dx = {1,0,-1,0};
		int[] dy = {0,1,0,-1};
		int INF = 10000;
		int R = map.length;
		int C = map[0].length;
		int sx = 0,sy = 0,gx = 0,gy = 0;
		int[][] p = new int[R][C];
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				p[i][j] = INF;
				if(map[i][j]=='S'){
					sx = i;
					sy = j;
				}else if(map[i][j]=='G'){
					gx = i;
					gy = j;
				}
			}
		}
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[]{sx,sy});
		p[sx][sy] = 0;
		while(q.size()>0){
			int[] axis = q.poll();
			int cx = axis[0];
			int cy = axis[1];
			if(cx==gx&&cy==gy){
				break;
			}
			for(int di=0;di<4;di++){
				int m = cx+dx[di];
				int n = cy+dy[di];
				if(m>=0&&m<R&&n>=0&&n<C&&map[m][n]!='#'&&p[m][n]==INF){
					p[m][n] = p[cx][cy]+1;
					q.add(new int[]{m,n});
			    }
			}
		}
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				System.out.printf("%5d ",p[i][j]);
			}
			TT.println();
		}
		return p[gx][gy];
	}

}
