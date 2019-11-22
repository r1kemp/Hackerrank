
public class magicSquare {

	public static void main(String[] args) {
		int[][] s = {{4,9,2}, {3,5,7}, {8,1,5}};
		System.out.println(formingMagicSquare(s));

		int[][] s2 = {{4,8,2}, {4,5,7}, {6,1,6}};
		System.out.println(formingMagicSquare(s2));
	}

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
    	int[][] m1 = {{8,3,4}, {1,5,9}, {6,7,2}};
    	int[][] m2 = rotate90(m1);
    	int[][] m3 = rotate90(m2);
    	int[][] m4 = rotate90(m3);

    	int[][] n1 = {{8,1,6}, {3,5,7}, {4,9,2}};
    	int[][] n2 = rotate90(n1);
    	int[][] n3 = rotate90(n2);
    	int[][] n4 = rotate90(n3);
    	
    	int minCost = 81;

    	minCost = Math.min(minCost, cost(s, m1));
    	minCost = Math.min(minCost, cost(s, m2));
    	minCost = Math.min(minCost, cost(s, m3));
    	minCost = Math.min(minCost, cost(s, m4));

    	minCost = Math.min(minCost, cost(s, n1));
    	minCost = Math.min(minCost, cost(s, n2));
    	minCost = Math.min(minCost, cost(s, n3));
    	minCost = Math.min(minCost, cost(s, n4));
    	
    	return minCost;
    }
    
    static int cost(int[][] s, int[][] m) {
    	int cost = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				cost += Math.abs(s[i][j] - m[i][j]);
			}
		}
		return cost;
    }
    
    static int N = 3;
	static int[][] rotate90(int[][] m) {
		int[][] n = new int[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				n[j][N-1-i] = m[i][j];
			}
		}
		
		return n;
	}
    
	
}
