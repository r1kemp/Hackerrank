import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/*
 * 	https://www.hackerrank.com/challenges/bigger-is-greater/problem
 * 
 */

public class Lexicographical {

	public static void main(String[] args) throws IOException {
		//System.out.println(biggerIsGreater("eye"));
		
		BufferedReader f = new BufferedReader(new FileReader("input01.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output01.txt")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			out.println(biggerIsGreater(f.readLine()));
		}
		
		f.close();
		out.close();
	}
	
    // Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {
    	String res = nextLexicallyBiggerWord(w);
    	
    	if (res.compareTo(w) == 0) {
    		return "no answer";
    	}
    	
    	return res;
    }
    
    static String nextLexicallyBiggerWord(String w) {
    	char[] wa = w.toCharArray();
    	
    	int last = w.length()-1;
    	int i = last - 1;
    	for (; (i >= 0) && (wa[i] >= wa[i+1]); i--) {
    		;
    	}
    	 
    	if (i == -1) {
    		return w;
    	}
    	
    	int j = last;
    	while (wa[j] <= wa[i])
    		j--;
    	
    	swap(wa, i, j);
    	
    	reverseSubArray(wa, i+1, w.length()-1);

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

