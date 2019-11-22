import java.util.*;

public class Encryption {

	public static void main(String[] args) {
		System.out.println(encryption("chillout"));
		System.out.println(encryption("feedthedog"));
		System.out.println(encryption("haveaniceday"));
		System.out.println(encryption("if man was meant to stay on the ground god would have given us roots"));

	}

    // Complete the encryption function below.
    static String encryption(String s) {
    	s = removeAllWhiteSpace(s);
    	
    	int numCols = (int) Math.ceil(Math.sqrt(s.length()));
    	
    	StringBuilder sb  = new StringBuilder();
    	for (int c=0; c<numCols; c++) {
    		for (int r=c; r<s.length(); r+=numCols ) {
    			sb.append(s.charAt(r));
    		}
    		sb.append(' ');
    	}
    	
    	return sb.toString();
    }
	
    static String removeAllWhiteSpace(String S) {
    	StringBuilder sb = new StringBuilder();
    	for (int i=0; i<S.length(); i++) {
    		char c = S.charAt(i);
    		if (c >= 'a' && c <= 'z') {
    			sb.append(c);
    		}
    	}
    	return sb.toString();
    }
}
