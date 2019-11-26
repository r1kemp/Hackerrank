import java.util.*;


// https://www.hackerrank.com/challenges/red-knights-shortest-path/problem
public class RedKnightsShortestPath {

	public static void main(String[] args) {
		
		printShortestPath(30, 25, 2, 23, 29);
//		printShortestPath(7, 6, 6, 0, 1); // Sample input 0
//		printShortestPath(6, 5, 1, 0, 5); // Sample input 1
//		printShortestPath(7, 0, 3, 4, 3); // Sample input 2
	}

	// Print the distance along with the sequence of moves.
    static void printShortestPath(int N, int startAtRow, int startAtCol, int endAtRow, int endAtCol) {
    	if (!possible(startAtRow, startAtCol, endAtRow,endAtCol)) {
    		System.out.println("Impossible");
    		return;
    	}
    	

    	class pair {
    		public int row, col;
    		pair(int r, int c) { row = r; col = c; }
    	}

    	
    	//boolean[][] visited = new boolean[N][N];
    	String[][] path = new String[N][N]; 
    	Queue<pair> q = new LinkedList<pair>();
    	
    	//visited[startAtRow][startAtCol] = true;
    	path[startAtRow][startAtCol] = "self";
    	q.add(new pair(startAtRow, startAtCol));
    	
    	boolean reachedTarget = false;
    	
    	String[] dirStr =    {"UL", "UR", "R", "LR", "LL", "L"};
    	String[] dirStrRev = {"LR", "LL", "L", "UL", "UR", "R"};
    	int[][] dir = {{-2, -1}, {-2, +1}, {0, +2}, {+2, +1}, {+2, -1}, {0, -2}};
    	int[][] dirRev = {{+2, +1}, {+2, -1}, {0, -2}, {-2, -1}, {-2, +1}, {0, +2}};

    	while (!q.isEmpty()) 
    	{
    		pair at = q.poll();

        	for (int d=0; d < dirStr.length; d++) {
        		int row = at.row + dir[d][0];
        		int col = at.col + dir[d][1];
        		
            	if (row >= 0 && row < N && col >= 0 && col < N) 
            	{
            		if (path[row][col] == null) 
            		{
            			path[row][col] = dirStrRev[d];
            			
            			if (row == endAtRow && col == endAtCol) {
            				reachedTarget = true;
            				break;
            			}
            			
            			q.add(new pair(row, col));
            		}
        		}
        	}
    		
        	if (reachedTarget) {
        		break;
        	}
    	}
    	
    	Stack<String> pathToPrint = new Stack<>();
    	int row = endAtRow;
    	int col = endAtCol;
    	while (path[row][col].compareTo("self") != 0) {
    		for (int d=0; d < dirStr.length; d++) {
        		if (path[row][col].compareTo(dirStrRev[d]) == 0) {
        			pathToPrint.push(dirStr[d]);
        			row += dirRev[d][0];
        			col += dirRev[d][1];
        			break;
        		}
    		}
    	}
    	
    	System.out.println(pathToPrint.size());
		System.out.print(pathToPrint.pop());
    	while (!pathToPrint.isEmpty()) {
    		System.out.print(" " + pathToPrint.pop());
    	}
    	System.out.println();
    }
    
    static boolean possible(int startRow, int startCol, int endRow, int endCol) {
    	if (startRow % 2 != endRow % 2) 
    	{
        	return false;
    	}
    		
		if (Math.abs(startRow - endRow) % 4 == 0) 
		{
			return (Math.abs(startCol - endCol) % 2 == 0);
		}
		return (Math.abs(startCol - endCol) % 2 == 1);
    }
    
}
