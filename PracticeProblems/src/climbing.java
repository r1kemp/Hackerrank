import java.io.*;
import java.util.*;

//import sun.jvm.hotspot.prims.JvmtiExport;

public class climbing {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
    	int length = 1;
    	int prevScore = scores[0]; 
    	for (int i=1; i<scores.length; i++) {
    		if (scores[i] != prevScore) {
    			scores[length++] = scores[i]; 
    			prevScore = scores[i];
    		}
    	}
    	
    	int[] results = new int[alice.length];
    	for (int j=0; j<alice.length; j++) {
    		int index = binSearch(scores, 0, length, alice[j]);
			results[j] = index + 1;
    	}
    	
    	return results;
    }
    
    static int binSearch(int[] A, int b, int e, int key) {
    	int res = -1;
    	
    	if (e - b < 5) {
    		for (res = b; res < e; res++) {
    			if (key >= A[res]) 
    				return res;
    		}
    		return res;
    	}
    	
    	int m = (b+e) / 2;
    	if (A[m] == key) {
    		return m;
    	}
    	if (key < A[m]) {
    		return binSearch(A, m+1, e, key);
    	}
    	else {
    		return binSearch(A, b, m, key);
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
    	//// test binSearch
    	//int[] A = {100, 50, 40, 20, 10};
    	//int res = binSearch(A, 0, A.length, 120);
    	//System.out.println("res = " + (res+1));
    	
    	//// end test binSearch
    	
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("todelete.txt"));

    	/*
        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }
		*/
    	
        int[] scores = {100, 100, 50, 40, 40, 20, 10};
        int[] alice = {5, 25, 50, 120};
        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
