import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 *  https://www.hackerrank.com/challenges/3d-surface-area/problem
 */

public class SurfaceArea3D {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("SurfaceArea3D.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("SurfaceArea3D.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[][] G = new int[R][C];
		
		for (int r=0; r<R; r++) {
			st = new StringTokenizer(f.readLine());
			
			for (int c=0; c<C; c++) {
				G[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.print(surfaceArea(G));
		
		f.close();
		out.close();
	}

    static int surfaceArea(int[][] A) {
    	int area = 0;

    	int R = A.length;
    	int C = A[0].length;
    	
		for (int r=0; r<R; r++) {
			for (int c=0; c<C; c++) {
				int currArea = 2; // top and bottom
				
				// North
				if (r == 0) {
					currArea += A[r][c];
				}
				else {
					int diff = A[r][c] - A[r-1][c];
					if (diff > 0) {
						currArea += diff;
					}
				}
				
				// South
				if (r == R-1) {
					currArea += A[r][c];
				}
				else {
					int diff = A[r][c] - A[r+1][c];
					if (diff > 0) {
						currArea += diff;
					}
				}
				
				// East
				if (c == 0) {
					currArea += A[r][c];
				}
				else {
					int diff = A[r][c] - A[r][c-1];
					if (diff > 0) {
						currArea += diff;
					}
				}
									
				// West
				if (c == C-1) {
					currArea += A[r][c];
				}
				else {
					int diff = A[r][c] - A[r][c+1];
					if (diff > 0) {
						currArea += diff;
					}
				}

				area += currArea;
			}
		}
    	
    	return area;
    }
	
}
