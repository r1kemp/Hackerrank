import java.util.Arrays;

//  https://www.hackerrank.com/challenges/almost-sorted/editorial

public class AlmostSorted {
	public static void main(String[] args) {
		int[][] A = {{1,2,7,4,5,6,3,8,9}, 
//				     {1, 5, 4, 3, 2, 6},
//					 {3,1,2}, 
//					 {1,2,3,4,5},
//					 {1,2,3,5,4},
//					 {1,2,3,6,5,4},
//					 {1,2,3,4,7,6,5,8,9},
//					 {1,2,4,3,5,6,8,7,9},
					 {3,4,1,2}};
				
		for (int i=0; i<A.length; i++) {
			System.out.println("Input = " + Arrays.toString(A[i]));
			almostSorted(A[i]);
			System.out.println("------------------------------");
		}
	}
	
    static void almostSorted(int[] A) {
    	int[] B = Arrays.copyOf(A, A.length);

    	Arrays.sort(B);
    	
    	int leftmostMismatch = -1, rightmostMismatch = -1;
    	
    	for (int i = 0; i<A.length; i++) { 
    		if (A[i] != B[i]) {
    			leftmostMismatch = i;
    			break;
    		}
    	}
    	
    	if (leftmostMismatch == -1) {
			System.out.println("yes");
			return;
    	}

    	for (int i = A.length-1; i >=0; i--) { 
    		if (A[i] != B[i]) {
    			rightmostMismatch = i;
    			break;
    		}
    	}
    	
    	// Check if swapping elements at leftmostMismatch & rightmostMismatch sorts the array
		boolean swappingWorks = true;
		for (int i=leftmostMismatch+1; i < rightmostMismatch; i++) {
			if (A[i] != B[i]) {
				swappingWorks = false;
				break;
			}
		}
		
		if (!swappingWorks) {
			// Check if mismatch sub-array is in decreasing order
			for (int i=leftmostMismatch; i<rightmostMismatch; i++) {
				if (A[i] < A[i+1]) {
					System.out.println("no");
					return;
				}
			}
		}
		
		System.out.println("yes");
		String res = swappingWorks ? "swap " : "reverse ";
		res += (leftmostMismatch+1) + " " + (rightmostMismatch+1);
		System.out.println(res);
    }
	
}
