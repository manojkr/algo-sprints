import java.util.Scanner;

/**
 * Hacker Rank
 * Dynamic Programming Problem 
 * 
 * @author mkumar11
 *
 */
public class PlayGame {

	private int[] brickValues;
	private long[] maxValues;
	private int[] maxMoves;

	public PlayGame(int[] brickValues) {
		this.brickValues = brickValues;
	}

	private long playGame() {
		maxValues = new long[brickValues.length + 1];
		maxMoves = new int[brickValues.length + 1];
		maxValues[brickValues.length] = 0;
		maxMoves[brickValues.length] = 0;

		maxValues[brickValues.length - 1] = brickValues[brickValues.length - 1];
		maxMoves[brickValues.length - 1] = 1;

		for (int i = brickValues.length - 1; i >= 0; i--) {
			long max = Long.MIN_VALUE;
			// one brick
			long curMaxValue;
			int curMaxMove = 0;
			curMaxValue = getNewMax(i, 1);
			if (curMaxValue > max) {
				max = curMaxValue;
				curMaxMove = 1;
			}

			curMaxValue = getNewMax(i, 2);
			if (curMaxValue > max) {
				max = curMaxValue;
				curMaxMove = 2;
			}

			curMaxValue = getNewMax(i, 3);
			if (curMaxValue > max) {
				max = curMaxValue;
				curMaxMove = 3;
			}

			maxValues[i] = max;
			maxMoves[i] = curMaxMove;
		}

		return maxValues[0];
	}

	private long getNewMax(int start, int nBricks) {
		long sum = Long.MIN_VALUE;
		if (start + nBricks <= brickValues.length) {
			sum = 0;
			for (int i = start; i < start + nBricks; i++) {
				sum += brickValues[i];
			}
			if (start + nBricks < maxMoves.length
					&& start + nBricks + maxMoves[start + nBricks] < maxValues.length)
				sum += maxValues[start + nBricks + maxMoves[start + nBricks]];
		}
		return sum;
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int ncases = s.nextInt();
		for (int i = 0; i < ncases; i++) {
			int nBricks = s.nextInt();
			int[] brickValues = new int[nBricks];
			for (int j = 0; j < nBricks; j++) {
				brickValues[j] = s.nextInt();
			}
			PlayGame game = new PlayGame(brickValues);
			System.out.println(game.playGame());
		}

	}

}
