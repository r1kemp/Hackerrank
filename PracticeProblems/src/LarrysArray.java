
// https://www.hackerrank.com/challenges/larrys-array/problem

public class LarrysArray {

	public static void main(String[] args) 
	{
		int A[] = {1,3,4,2};
		System.out.println(larrysArray(A));

		int B[] = {3,1,2};
		System.out.println(larrysArray(B));
		
		int C[] = {1,2,3,5,4};
		System.out.println(larrysArray(C));
		
		int D[] = {5,4,3,2,1};
		System.out.print(larrysArray(D));
	}

	static String larrysArray(int[] A) { 
		int swapsNeeded = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i+1; j < A.length; j++) {
				if (A[i] > A[j]) {
					swapsNeeded++;
				}
			}
		}
		
		return swapsNeeded % 2 == 0 ? "YES" : "NO";
	}
	/**
	 * Array A is filled up with integers from 1 to N. The size of the array is N.
	 * We need to try and sort the array by using the rotateBy1() and rotateBy2() methods.
	 * 
	 * Algorithm: proceeding from index 0 to N-3, ensure integer 'n' is at array index 'n-1'.
	 * Finally check if integer 'N-1' is at index 'N-2'. 
	 * 
	 * @param A
	 * @return "YES" if array can be sorted, "NO" otherwise.
	 * 
	 */
    static String larrysArray2(int[] A) {
    	int N = A.length;

    	for (int index=0; index <= N-3; index++) {
    		int num = index+1;
    		int loc = find(num, A);
			while (loc != index) {
				if (loc == index + 1) {
					rotateBy1(A,loc-1);
					loc -= 1;
				} 
				else {
					rotateBy2(A,loc-2);
					loc -= 2;
				}
			}
    	}
    	
    	if (A[N-2] == N-1) // && A[N-1] == N) 
    	{
    		return "YES";
    	}
    	
    	return "NO";
    }
    
    static int find(int num, int[] A) {
    	for (int loc = num-1; loc<A.length; loc++) {
    		if (A[loc] == num)
    			return loc;
    	}
    	
    	return -1;
    }
    
    static void rotateBy1(int[] A, int i) {
    	int temp = A[i];
    	A[i] = A[i+1];
    	A[i+1] = A[i+2];
    	A[i+2] = temp;
    }

    static void rotateBy2(int[] A, int i) {
    	int temp = A[i+2];
    	A[i+2] = A[i+1];
    	A[i+1] = A[i];
    	A[i] = temp;
    }
    
}

/*
    static String larrysArray(int[] A) {
    	int N = A.length;

    	for (int index=0; index <= N-3; index++) {
    		int num = index+1;
    		for (int loc = find(num, A); loc < N; loc++) {
    			while (loc != index) {
    				if (loc == index + 1) {
    					rotateBy1(A,loc-1);
    					loc -= 1;
    				} 
    				else {
    					rotateBy2(A,loc-2);
    					loc -= 2;
    				}
    			}
    			if (loc == index) {
    				break;
    			}
    		}
    	}
    	
    	if (A[N-2] == N-1) // && A[N-1] == N) 
    	{
    		return "YES";
    	}
    	
    	return "NO";
    }

*/