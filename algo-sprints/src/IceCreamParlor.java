import java.util.*;

/**
 * 
 * @author mkumar11
 * 
 * Ice Cream Parlor - Hacker rank 
 * Simple problem - 
 *  - Two solutions
 *  - O(n log(n)) using sorting
 *  - O(n) via Hashmap - to save space use bitset to represent HashMaps
 *  - For each number, check if the counterpart is there
 *
 */

public class IceCreamParlor {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();

		for (int t = 0; t < T; t++) {
			int M = s.nextInt();
			int N = s.nextInt();
			int[] cost = new int[N];
			BitSet isPresent = new BitSet(10001);
			BitSet isRepeated = new BitSet(10001);
			for (int i = 0; i < N; i++) {
				cost[i] = s.nextInt();
				if (!isPresent.get(cost[i]))
					isPresent.set(cost[i]);
				else {
					isRepeated.set(cost[i]);
				}
			}
			for (int i = 0; i < N; i++) {
				int cur = cost[i];
				int required = M - cur;
				if (required > 0) {
					if (cur != required) {
						if (isPresent.get(required)) {
							System.out.format("%d %d\n", i + 1,
									findIndexOf(required, cost, i + 1) + 1);
							break;
						}
					} else {
						if (isRepeated.get(required)) {
							System.out.format("%d %d\n", i + 1,
									findIndexOf(required, cost, i + 1) + 1);
							break;
						}
					}
				}

			}
		}

	}

	private static int findIndexOf(int required, int[] cost, int start) {
		for (int i = start; i < cost.length; i++)
			if (cost[i] == required)
				return i;
		return -1;
	}

}
