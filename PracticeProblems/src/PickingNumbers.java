import java.util.*;

public class PickingNumbers {

	public static void main(String[] args) {
		int[] temp = {1, 1, 2, 2, 3, 7, 7, 7, 7, 7, 8, 8};  // {1,3,4,4,5,1,2,2,5,5}; 
		ArrayList<Integer> A = new ArrayList<Integer>();
		for (int n : temp) {
			A.add(n);
		}
		
		System.out.print(pickingNumbers(A));
	}
	
    /*
     * Complete the 'pickingNumbers' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */
    public static int pickingNumbers(List<Integer> A) {
    	if (A.size() == 0) {
    		return 0;
    	}
    	
    	Collections.sort(A);

    	int p = -1, a = -1, b = 0;
    	int index;
    	int maxSetSize = 0;
    	
    	while (true) {
        	for (index = b + 1; (index < A.size()) && (A.get(index) == A.get(b)); index++) {
        		;
        	}
        	b = index - 1;
        	
        	int currSetSize = (a >= 0) && (Math.abs(A.get(a) - A.get(b)) == 1) ? b-p : b-a;
        	maxSetSize = Math.max(maxSetSize, currSetSize);
        	
        	if (index == A.size())
        		break;
        	
        	p = a;
        	a = b;
        	b = b+1;
    	}
    	
    	return maxSetSize;
    }

}
