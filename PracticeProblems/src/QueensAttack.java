import java.util.Arrays;


public class QueensAttack {

	public static void main(String[] args) {
		int[][] obstacles = {{5,5},{4,2},{2,3}};
		
		System.out.print(queensAttack(5, 3, 4, 3, obstacles));
	}

    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        class pair  implements Comparable<pair>
        {
        	public int r, c;
        	pair(int[] rc) { r = rc[0]; c = rc[1]; }
        	pair() { r = c = 0; }
        	
			@Override
			public int compareTo(pair o) {
				return (r != o.r) ? r - o.r : c - o.c;
			}
        }

    	pair[] pairs = new pair[k];
    	for (int i=0; i<k; i++) {
    		pairs[i] = new pair(obstacles[i]); 
    	}

    	Arrays.sort(pairs);
    	
        int count = 0;

        pair pTemp = new pair();
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0},{1,-1},{1,1},{-1,-1},{-1,1}};
        
        for (int d=0; d<dir.length; d++) {
        	int rInc = dir[d][0];
        	int cInc = dir[d][1];
        	
        	for (int r=r_q+rInc, c=c_q+cInc; 
        			r >= 1 && r <= n && c >= 1 && c <= n;
        			r += rInc, c += cInc) 
        	{
            	pTemp.r = r;
            	pTemp.c = c;
            	
            	int index = Arrays.binarySearch(pairs, pTemp);
            	
            	if (index < 0) {
            		++count;
            	}
            	else {
            		break;
            	}
        	}
        }
        
        return count;
    }
    
}


/*
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        class pair  implements Comparable<pair>
        {
        	public int r, c;
        	pair(int _r, int _c) { r = _r; c = _c; }
        	pair() { r = c = 0; }
        	
			@Override
			public int compareTo(pair o) {
				return (r != o.r) ? r - o.r : c - o.c;
			}
        }

        --r_q;
        --c_q;
        
    	pair[] pairs = new pair[k];
    	for (int i=0; i<k; i++) {
    		pairs[i] = new pair(obstacles[i][0]-1, obstacles[i][1]-1); 
    	}

    	Arrays.sort(pairs);
    	
        int count = 0;

        pair pTemp = new pair();
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0},{1,-1},{1,1},{-1,-1},{-1,1}};
        
        for (int d=0; d<dir.length; d++) {
        	int rInc = dir[d][0];
        	int cInc = dir[d][1];
        	
        	for (int r=r_q+rInc, c=c_q+cInc; 
        			r >= 0 && r < n && c >= 0 && c < n;
        			r += rInc, c += cInc) 
        	{
            	pTemp.r = r;
            	pTemp.c = c;
            	
            	int index = Arrays.binarySearch(pairs, pTemp);
            	
            	if (index < 0) {
            		++count;
            	}
            	else {
            		break;
            	}
        	}
        }
        
        return count;
    }
    
}

*/