import java.util.*;

public class PowerSum {

	public static void main(String[] args) {
		powerSum(100, 2);
	}

	static int[] powersOfN;
    static int powerSum(int X, int N) {
    	powersOfN = new int[N+1];
    	
    	for (int n=1; n <= X; n++) {
    		powersOfN[n] = pow(n, N);
    		if (powersOfN[n] > X) {
    			powersOfN[n] = 0;
    			break;
    		}
    	}
    	
    	int count = numOfSums(1);
    	return 0;
    }
    
    static int numOfSums(int start) {
    	
    	int count = 0;
    	fot (int i=start; i<)
    }
    
    static int[] power;
    static int pow(int base, int exp) {
    	if (exp == 1) return base;
    	if (exp == 2) return base * base;
    	int result = pow(pow(base, exp/2), 2);
    	if (exp % 2 == 1) {
    		result *= base;
    	}
    	return result;
    }
}
