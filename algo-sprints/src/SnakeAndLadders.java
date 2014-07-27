import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * HackerRank - Simple BFS problem
 * 
 * @author mkumar11
 *
 */
public class SnakeAndLadders {

	class Node {
		int pos;
		int d;
		List<Node> neighbors;
		int parent;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + pos;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (pos != other.pos)
				return false;
			return true;
		}

		public Node(int pos, int d) {
			this.pos = pos;
			this.d = d;
		}

		public List<Node> neighbors() {
			if (neighbors == null) {
				neighbors = new ArrayList<Node>();
				for (int i = 1; i <= 6; i++) {
					int next = pos + i;
					if (next <= 100) {
						if (snakes.containsKey(next))
							neighbors.add(nodes[snakes.get(next)]);
						else if (ladders.containsKey(next))
							neighbors.add(nodes[ladders.get(next)]);
						else
							neighbors.add(nodes[next]);
					}
				}
			}
			return neighbors;

		}

		private SnakeAndLadders getOuterType() {
			return SnakeAndLadders.this;
		}
	}

	Node[] nodes;
	Map<Integer, Integer> snakes;
	Map<Integer, Integer> ladders;

	public SnakeAndLadders(Map<Integer, Integer> snakes,
			Map<Integer, Integer> ladders) {
		this.nodes = new Node[101];
		for (int i = 1; i <= 100; i++)
			nodes[i] = new Node(i, Integer.MAX_VALUE);
		nodes[1].d = 0;
		this.snakes = snakes;
		this.ladders = ladders;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int nCases = s.nextInt();
		for (int i = 0; i < nCases; i++) {
			String[] parts = s.next().split(",");
			int nLadders = Integer.parseInt(parts[0]);
			int nSnakes = Integer.parseInt(parts[1]);
			Map<Integer, Integer> ladders = new HashMap<Integer, Integer>();
			for (int j = 0; j < nLadders; j++) {
				parts = s.next().split(",");
				int start = Integer.parseInt(parts[0]);
				int end = Integer.parseInt(parts[1]);
				ladders.put(start, end);
			}
			Map<Integer, Integer> snakes = new HashMap<Integer, Integer>();
			for (int j = 0; j < nSnakes; j++) {
				parts = s.next().split(",");
				int start = Integer.parseInt(parts[0]);
				int end = Integer.parseInt(parts[1]);
				snakes.put(start, end);
			}

			SnakeAndLadders game = new SnakeAndLadders(snakes, ladders);
			System.out.println(game.solve());

		}

	}

	private int solve() {
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(nodes[1]);
		
		boolean visited[] = new boolean[101];
		Arrays.fill(visited, false);
		visited[1] = true;
		while (!queue.isEmpty()) {
			Node cur = queue.remove();
			if (cur.pos == 100)
				break;
			for (Node next : cur.neighbors()) {
				if (!visited[next.pos]) {
					visited[next.pos] = true;
					next.d = cur.d + 1;
					next.parent = cur.pos;
					queue.add(next);
				}
			}
		}
		//printPath();
		return nodes[100].d;
	}

	private void printPath() {
		System.out.println(snakes);
		System.out.println(ladders);
		int cur = 100;
		LinkedList<Integer> path = new LinkedList<Integer>();
		while (cur != 1) {
			path.addFirst(cur);
			cur = nodes[cur].parent;
		}
		System.out.println(path);
	}

}
