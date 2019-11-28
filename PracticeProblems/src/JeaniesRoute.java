import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

// https://www.hackerrank.com/challenges/jeanies-route/problem
public class JeaniesRoute {

	//private static final Scanner scanner = new Scanner(System.in);
	
    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    	BufferedReader f = new BufferedReader(new FileReader("JeaniesRoute.in"));
    	Scanner scanner = new Scanner(f);
    	
        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0].trim());

        int k = Integer.parseInt(nk[1].trim());

        int[] city = new int[k];

        String[] cityItems = scanner.nextLine().split(" ");

        for (int cityItr = 0; cityItr < k; cityItr++) {
            int cityItem = Integer.parseInt(cityItems[cityItr].trim());
            city[cityItr] = cityItem;
        }

        int[][] roads = new int[n-1][3];

        for (int roadsRowItr = 0; roadsRowItr < n-1; roadsRowItr++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");

            for (int roadsColumnItr = 0; roadsColumnItr < 3; roadsColumnItr++) {
                int roadsItem = Integer.parseInt(roadsRowItems[roadsColumnItr].trim());
                roads[roadsRowItr][roadsColumnItr] = roadsItem;
            }
        }

        int result = jeanisRoute(city, roads);
        scanner.close();

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();
    }
	
	public static void main2(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("JeaniesRoute.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("JeaniesRoute.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] kCities = new int[K];
		st = new StringTokenizer(f.readLine());
		for (int i=0; i<K; i++) {
			kCities[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		int[][] roads = new int[N][N];
		for (int i=0; i<N; i++) {
			Arrays.fill(roads[i], 0);
		}
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			
			roads[u][v] = d;
			roads[v][u] = d;
		}
		
		System.out.print(jeanisRoute(kCities, roads));
		
		f.close();
		out.close();
	}

	
	static int N;
	static int s;
	static int[][] G;
	static int[][] distTo;
	static IndexMinPQ<Integer> pq;
	
    static int jeanisRoute(int[] k, int[][] roads) 
    {
    	G = roads;
    	N = roads.length;
    	distTo = new int[N][N];
    	pq = new IndexMinPQ<>(N);
    	
    	for (int i=0; i<N; i++) 
    	{
    		Arrays.fill(distTo[i], -1);
    		distTo[i][i] = 0;
    	}
    	
    	for (s=0; s<N; s++) 
    	{
    		// clear pq
    		while (!pq.isEmpty()) 
    		{
    			pq.delMin();
    		}

    		pq.insert(s, s);
    		distTo[s][s] = 0;
    		
    		while (!pq.isEmpty()) 
    		{
    			// relax
    			int v = pq.delMin();

    			for (int w=0; w<N; w++)
    			{
    				if (G[v][w] != 0 && distTo[s][v] != -1)
    				{
    					if (distTo[s][w] > distTo[s][v] + G[v][w])
    					{
    						distTo[s][w] = distTo[s][v] + G[v][w];
    						
    						if (pq.contains(w)) 
    							pq.changeKey(w, distTo[s][w]);
    						else 
    							pq.insert(w, distTo[s][w]);
    					}			
    				}
    			}
    		}
    	}
    	
    	int minDistToTarvel = 0;
    	// TODO: Run a min spanning tree algorithm
    	int K = k.length;
    	int[][] G2 = new int[K][K];
    	for (int i=0; i<K; i++) {
    		Arrays.fill(G2[i], -1);
    	}
    	
    	for (int i=0; i<K; i++) {
    		for (int j=0; j<K; j++) {
    			G2[i][j] = distTo[k[i]][k[j]];
    		}
    	}
    	
    	int[] parent = new int[K];
    	int[] distTo2 = new int[K];
    	boolean[] marked = new boolean[K];
    	IndexMinPQ<Integer> pq2 = new IndexMinPQ<Integer>(K);
    	
		pq2.insert(0, 0);
		while (!pq2.isEmpty())
		{
			int v = pq2.delMin();
			marked[v] = true;
			
			for (int w=0; w<K; w++)
			{
				if (marked[w]) continue;
				
				if (G2[v][w] < distTo2[w]) 
				{
					parent[w] = v;
					distTo2[w] = G2[v][w];
					
					if (pq2.contains(w)) pq.changeKey(w, distTo2[w]);
					else				 pq.insert(w, distTo2[w]);
				}
			}
		}
		
		for (int i=0; i<K; i++)
			minDistToTarvel += distTo2[i];
    	
    	return minDistToTarvel;
    }
	
	static class IndexMinPQ<Key extends Comparable<Key>> 
	{
	    private int maxN;        
	    private int N;           
	    private int[] pq;        
	    private int[] qp;        
	    private Key[] keys;      

	    @SuppressWarnings("unchecked")
		public IndexMinPQ(int maxN) {
	        if (maxN < 0) throw new IllegalArgumentException();
	        this.maxN = maxN;
	        keys = (Key[]) new Comparable[maxN + 1];    
	        pq   = new int[maxN + 1];
	        qp   = new int[maxN + 1];                   
	        for (int i = 0; i <= maxN; i++)
	            qp[i] = -1;
	    }

	    public boolean isEmpty() {
	        return N == 0;
	    }

	    public boolean contains(int i) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        return qp[i] != -1;
	    }

	    public int size() {
	        return N;
	    }

	    public void insert(int i, Key key) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
	        N++;
	        qp[i] = N;
	        pq[N] = i;
	        keys[i] = key;
	        swim(N);
	    }

	    public int minIndex() { 
	        if (N == 0) throw new RuntimeException("Priority queue underflow");
	        return pq[1];        
	    }

	    public Key minKey() { 
	        if (N == 0) throw new RuntimeException("Priority queue underflow");
	        return keys[pq[1]];        
	    }

	    public int delMin() { 
	        if (N == 0) throw new RuntimeException("Priority queue underflow");
	        int min = pq[1];        
	        exch(1, N--); 
	        sink(1);
	        qp[min] = -1;            // delete
	        keys[pq[N+1]] = null;    // to help with garbage collection
	        pq[N+1] = -1;            // not needed
	        return min; 
	    }

	    public Key keyOf(int i) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new RuntimeException("index is not in the priority queue");
	        else return keys[i];
	    }

	    public void changeKey(int i, Key key) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new RuntimeException("index is not in the priority queue");
	        keys[i] = key;
	        swim(qp[i]);
	        sink(qp[i]);
	    }

	    public void decreaseKey(int i, Key key) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new RuntimeException("index is not in the priority queue");
	        if (keys[i].compareTo(key) <= 0)
	            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
	        keys[i] = key;
	        swim(qp[i]);
	    }

	    public void increaseKey(int i, Key key) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new RuntimeException("index is not in the priority queue");
	        if (keys[i].compareTo(key) >= 0)
	            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
	        keys[i] = key;
	        sink(qp[i]);
	    }

	    public void delete(int i) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new RuntimeException("index is not in the priority queue");
	        int index = qp[i];
	        exch(index, N--);
	        swim(index);
	        sink(index);
	        keys[i] = null;
	        qp[i] = -1;
	    }


	   /***************************************************************************
	    * General helper functions.
	    ***************************************************************************/
	    private boolean greater(int i, int j) {
	        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	    }

	    private void exch(int i, int j) {
	        int swap = pq[i];
	        pq[i] = pq[j];
	        pq[j] = swap;
	        qp[pq[i]] = i;
	        qp[pq[j]] = j;
	    }


	   /***************************************************************************
	    * Heap helper functions.
	    ***************************************************************************/
	    private void swim(int k)  {
	        while (k > 1 && greater(k/2, k)) {
	            exch(k, k/2);
	            k = k/2;
	        }
	    }

	    private void sink(int k) {
	        while (2*k <= N) {
	            int j = 2*k;
	            if (j < N && greater(j, j+1)) j++;
	            if (!greater(k, j)) break;
	            exch(k, j);
	            k = j;
	        }
	    }
	}
    
}
