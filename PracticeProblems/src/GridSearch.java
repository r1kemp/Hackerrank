import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GridSearch {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("GridSearch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("GridSearch.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		for (int n=0; n<N; n++) {
			st = new StringTokenizer(f.readLine());
			int rows = Integer.parseInt(st.nextToken());
			int cols = Integer.parseInt(st.nextToken());
			
			String[] grid  = new String[rows];
			for (int r = 0; r < rows; r++) {
				grid[r] = f.readLine();
			}

			st = new StringTokenizer(f.readLine());
			int sRows = Integer.parseInt(st.nextToken());
			int sCols = Integer.parseInt(st.nextToken());
			
			String[] srch  = new String[sRows];
			for (int r = 0; r < sRows; r++) {
				srch[r] = f.readLine();
			}
			
			out.println(gridSearch(grid, srch));
			System.out.println(gridSearch(grid, srch));
		}
		
		f.close();
		out.close();
	}

    static String gridSearch(String[] G, String[] P) {
    	//int width = G[0].length() - P[0].length();
    	int depth = G.length - P.length;
    	
    	for (int d = 0; d <= depth; d++) {
    		int startAt = 0;
    		int index = -1;
    		while ((index = G[d].indexOf(P[0], startAt)) != -1) {
    			startAt = index + 1;
    			
    			boolean foundMatch = true;
    			
    			for (int p = 1; p < P.length; p++) {
    				String ss = G[d+p].substring(index, index + P[0].length());
    				if (ss.compareTo(P[p]) != 0) {
    					foundMatch = false;
    					break;
    				}
    			}
    			
    			if (foundMatch) {
    				return "YES";
    			}
    		}
    	}

    	return "NO";
    }
}
