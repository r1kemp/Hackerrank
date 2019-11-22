
public class Lexicographical {

	public static void main(String[] args) {
		System.out.print(biggerIsGreater("dkhc"));
	}
	
    // Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {
    	String res = nextLexWord(w);
    	
    	if (res.compareTo(w) == 0) {
    		return "no answer";
    	}
    	
    	return res;
    }
    
    static String nextLexWord(String w) {
    	char[] wa = w.toCharArray();
    	
    	int last = w.length()-1;
    	int i = last - 1;
    	for (; (i >= 0) && (wa[last] < wa[i]); i--) {
    		;
    	}
    	 
    	if (i == -1) {
    		return w;
    	}
    	
    	swap(wa, i, last);
    	
    	reverseSubArray(wa, i+1, last);

    	return new String(wa);
    }
	
    static void swap(char[] A, int i, int j) {
    	char t = A[i];
    	A[i] = A[j];
    	A[j] = t;
    }
    
    static void reverseSubArray(char[] A, int l, int r) {
    	while (l < r) {
    		swap(A, l, r);
    		l++;
    		r--;
    	}
    }

}
