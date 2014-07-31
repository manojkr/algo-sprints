/* Sample program illustrating input/output methods */
import java.util.*;

/**
 * @author mkumar11
 * 
 * 
 * Hackerrank - Flowers problem
 * 
 * Simple problem - Greedy strategy 
 *  - Sort the cost of flowers and buy the most expensive ones at the least cost
 *  - Then remaining ones buy at double cost
 *  - Still remaining ones buy at triple cost. 
 *  - The cheapest flowers are bought at least cost
 *  - O(n log(N))
 *
 */
class Flowers {

	public static long solve(int N, int K, int C[]) {
		Arrays.sort(C);
		
		long cost = 0;
		long flowerCount = 0;
		for(int i = C.length - 1; i >= 0; i--, flowerCount++){
			cost += ((flowerCount / K) + 1) * C[i];
		}
		return cost;
	}
	
	public static void main(String args[]) {

		// helpers for input/output

		Scanner in = new Scanner(System.in);

		int N, K;
		N = in.nextInt();
		K = in.nextInt();

		int C[] = new int[N];
		for (int i = 0; i < N; i++) {
			C[i] = in.nextInt();
		}

		long result = solve(N,K,C);
		System.out.println(result);

	}
}
