import java.util.Scanner;

/**
 * Hackerrank 
 * Simple DP Problem 
 *  - If there is a higher price in future, then buy
 *  - else sell
 *  
 * @author mkumar11
 *
 */
public class StockMaximize {
	private int[] prices;
	private int N;

	public StockMaximize(int[] prices, int N) {
		this.prices = prices;
		this.N = N;
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int nCases = s.nextInt();
		for (int i = 0; i < nCases; i++) {
			int N = s.nextInt();
			int[] prices = new int[N];
			for (int j = 0; j < N; j++)
				prices[j] = s.nextInt();
			StockMaximize sm = new StockMaximize(prices, N);
			System.out.println(sm.calculateMaxProfit());
		}

	}

	private long calculateMaxProfit() {
		// Calculate max future price
		int[] maxPriceFromTodayTillEnd = new int[N];
		maxPriceFromTodayTillEnd[N - 1] = prices[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			if (prices[i] >= maxPriceFromTodayTillEnd[i + 1])
				maxPriceFromTodayTillEnd[i] = prices[i];
			else
				maxPriceFromTodayTillEnd[i] = maxPriceFromTodayTillEnd[i + 1];
		}

		long sum = 0;
		long count = 0;
		for (int i = 0; i < N-1; i++) {
			if (prices[i] < maxPriceFromTodayTillEnd[i + 1]) {
				// buy
				sum -= prices[i];
				count++;
			} else if (prices[i] > maxPriceFromTodayTillEnd[i + 1]) {
				sum += count * prices[i];
				count = 0;
			}
		}
		sum += count * prices[N-1];
		return sum;
	}

}
