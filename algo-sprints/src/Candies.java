import java.util.Arrays;
import java.util.Scanner;

/**
 * HackerRank problem - candies
 * Simple Greedy problem
 * @author mkumar11
 *
 */
public class Candies {

	private int[] ranking;
	private int n;

	Candies(int n, int[] ranking) {
		this.n = n;
		this.ranking = ranking;
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int nCandies = s.nextInt();
		int[] ranking = new int[nCandies];
		for (int i = 0; i < nCandies; i++) {
			ranking[i] = s.nextInt();
		}
		Candies c = new Candies(nCandies, ranking);
		System.out.println(c.solve());

	}

	private long solve() {
		int[] candies = new int[n];
		candies[0] = 1;
		for (int i = 1; i < n; i++) {
			if (ranking[i] > ranking[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			} else if (ranking[i] == ranking[i - 1]) {
				candies[i] = 1;
			} else {
				int len = 0, j = i;
				while (j < n && ranking[j] < ranking[j - 1]){
					len++;
					j++;
				}
				candies[i - 1] = Math.max(len + 1, candies[i - 1]);
				int p = i;
				for (; p < i + len; p++) {
					candies[p] = len--;
				}
				i = p - 1;
			}

		}
		//System.out.println(Arrays.toString(candies));
		long sum = 0;
		for (int i = 0; i < n; i++)
			sum += candies[i];
		return sum;
	}

}
