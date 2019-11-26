import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.io.*;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/matrix-rotation-algo/problem

public class MatrixLayerRotation {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("matLayer.in"));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);
        
//        rotateLayer(matrix, 0, n-1, m-1);
//        rotateLayer(matrix, 1, n-3, m-3);
//        rotateLayer(matrix, 0, n-1, m-1);
//        rotateLayer(matrix, 1, n-3, m-3);

        bufferedReader.close();
    }
	
    static void matrixRotation(List<List<Integer>> matrix, int r) {
    	int numOfRows = matrix.size();
    	int numOfCols = matrix.get(0).size();
    	int numOflayers = Math.min(numOfRows, numOfCols) / 2;
    	

		for (int layer = 0; layer < numOflayers; layer++) {
			int countForOneFullRotation = (numOfCols + numOfRows) * 2 - 4 - 8 * layer; 
			int thisLayersRotation = r % countForOneFullRotation;
	    	for (int i = 0; i < thisLayersRotation; i++) {
				rotateLayer(matrix, layer, numOfCols-1-2*layer, numOfRows-1-2*layer);
	    	}
		}
    	
    	for (int row = 0; row < numOfRows; row++) {
    		for (int col = 0; col < numOfCols; col++) {
    			String str = (col > 0 ? " " : "") + matrix.get(row).get(col);
    			System.out.print(str);
    		}
    		if (row < numOfRows-1) {
    			System.out.println();
    		}
    	}
    }
	
    static void rotateLayer(List<List<Integer>> matrix, int layer, int horizontal, int vertical) {

    	int[] times = {horizontal, vertical, horizontal, vertical};
    	int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    	
    	int temp = matrix.get(layer).get(layer);
    	int row = layer;
    	int col = layer;
    	for (int i=0; i<4; i++) {
    		for (int t=0; t<times[i]; t++) {
    			int element = matrix.get(row+dir[i][0]).get(col+dir[i][1]); 
    			matrix.get(row).set(col, element);
    			row += dir[i][0];
    			col += dir[i][1];
    		}
    	}
    	matrix.get(layer+1).set(layer, temp);
    }
}
