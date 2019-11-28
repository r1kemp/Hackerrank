import java.util.Arrays;
import java.util.Comparator;

import JeaniesRoute.IndexMinPQ;

public class Dijkstra {

	public static void main(String[] args) {
		

	}

	void dijkstra(int V, int[][] E, int s, int[] dist) {
		class MyComp implements Comparator<int[]> 		{
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]; 
			}
		}
		
		Arrays.sort(E, new MyComp());
		int[] eStartForV = new int[V];
		Arrays.fill(eStartForV, -1);
		for (int e=0; e<E.length; e++) {
			if (eStartForV[E[e][0]] == -1) {
				eStartForV[E[e][0]] = e;
			}
		}
		
		int[] prev = new int[V];
		Arrays.fill(prev, -1);

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s] = 0;
		
		IndexMinPQ<Integer> pq = new IndexMinPQ<>(V);
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
