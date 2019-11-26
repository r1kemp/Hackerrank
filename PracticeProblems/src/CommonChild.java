
public class CommonChild {

	public static void main(String[] args) {
		System.out.println(commonChild("ART", "ALT"));
		System.out.println(commonChild("AA", "BB"));
		System.out.println(commonChild("HARRY", "SALLY"));
		System.out.println(commonChild("SHINCHAN", "NOHARAAA"));
		System.out.println(commonChild("ABCDEF", "FBDAMN"));
	}

    static int commonChild(String s1, String s2) {
    	int N = s1.length();
    	int[][] M = new int[N+1][N+1];

    	char[] L = s1.toCharArray();
    	char[] R = s2.toCharArray();
    	
    	for (int i = 0; i <= N; i++) {
    		M[0][i] = M[i][0] = 0;
    	}

    	for (int r = 1; r <= N; r++) {
    		for (int l = 1; l <= N; l++) {
    			if (L[l-1] == R[r-1]) 
    				M[l][r] = M[l-1][r-1] + 1;
    			else 
    				M[l][r] = Math.max(M[l-1][r], M[l][r-1]);   		
			}
    	}
    	
    	return M[N][N];
    }
}
