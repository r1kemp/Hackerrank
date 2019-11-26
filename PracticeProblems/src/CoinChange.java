import java.util.*;

// https://www.hackerrank.com/challenges/coin-change/problem
public class CoinChange {

	public static void main(String[] args) {
		ArrayList<Long> coin = new ArrayList<>();
		coin.add(1L);
		coin.add(2L);
		coin.add(3L);
		
		long ans = getWays(4, coin);
		System.out.println(ans);
		
		coin.clear();
		coin.add(2L);
		coin.add(5L);
		coin.add(3L);
		coin.add(6L);
		
		ans = getWays(10, coin);
		System.out.println(ans);
		
		// 166 23
		// 5 37 8 39 33 17 22 32 13 7 10 35 40 2 43 49 46 19 41 1 12 11 28
		//
		// 96190959
		
		int[] ar = {5, 37, 8, 39, 33, 17, 22, 32, 13, 7, 10, 35, 40, 2, 43, 49, 46, 19, 41, 1, 12, 11, 28};
		coin.clear();
		for (int n : ar) {
			coin.add((long)n);
		}
		ans = getWays(166, coin);
		System.out.println(ans);

	}

    public static long getWays(int N, List<Long> C) {
    	
    	coin = new int[C.size()];
    	for (int i=0; i<coin.length; i++) {
    		coin[i] = C.get(i).intValue();
    	}
    	Arrays.sort(coin);
    	
    	M = new long[coin.length][N+1];
    	for (int i=0; i < M.length; i++) {
    		Arrays.fill(M[i], -1);
    		M[i][0] = 0;
    	}
    		
    	return CC(N, coin.length-1);
    }
    
    static int[] coin;
    static long[][] M;
    
    static long CC(int amt, int i) {
    	if (M[i][amt] != -1)
    		return M[i][amt];
    	
    	// base case
		if (i == 0) {
			M[i][amt] = (amt % coin[0] == 0) ? 1 : 0; 
			return M[i][amt];
		}
		
    	long count = 0;
    	for (int currAmt = amt; currAmt > 0; currAmt -= coin[i]) {
    		count += CC(currAmt, i-1);
    	}
    	if (amt % coin[i] == 0)
    		count++;
    	
    	M[i][amt] = count;
    	return M[i][amt]; 
    }
}

/*
 *     static long CC(int amt, int i) {
    	if (M[i][amt] == -1) 
    	{    	
    		if (i == 0) {
    			M[i][amt] = (amt % coin[0] == 0) ? 1 : 0; 
    			return M[i][amt];
    		}
    		
	    	long count = 0;
	    	
	    	for (int currAmt = amt; currAmt > 0; currAmt -= coin[i]) {
	    		count += CC(currAmt, i-1);
	    	}
	    	if (amt % coin[i] == 0)
	    		count++;
	    	
	    	M[i][amt] = count;
    	}
    	return M[i][amt]; 
    }
*/
