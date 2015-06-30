import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LeHongTrieu_Solution {
	private static Result search(int[][] map, int row, int col, Result[][] mem) {
		if (mem[row][col] != null)
			return mem[row][col];

		Result bestResult = new Result(1, 0);
		List<Location> adjacents = getAdjacents(map, row, col);
		for (Location next : adjacents) {
			Result temp = search(map, next.row, next.col, mem);
			Result result = new Result(temp.length + 1, temp.steepness
					- next.height + map[row][col]);
			if (result.compareTo(bestResult) > 0) {
				bestResult = result;
			}
		}

		mem[row][col] = bestResult;
		return bestResult;
	}

	private static List<Location> findPeaks(int[][] map) {
		int rowCount = map.length;
		int colCount = map[0].length;
		List<Location> peaks = new LinkedList<Location>();
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < colCount; c++) {
				int height = map[r][c];
				if ((r == 0 || height >= map[r - 1][c])
						&& (r == rowCount - 1 || height >= map[r + 1][c])
						&& (c == 0 || height >= map[r][c - 1])
						&& (c == colCount - 1 || height >= map[r][c + 1])) {
					peaks.add(new Location(r, c, map[r][c]));
				}
			}
		}

		return peaks;
	}

	private static List<Location> getAdjacents(int[][] map, int row, int col) {
		int rowCount = map.length;
		int colCount = map[0].length;

		int height = map[row][col];

		List<Location> adjacents = new LinkedList<>();

		if (row > 0 && map[row - 1][col] < height)
			adjacents.add(new Location(row - 1, col, map[row - 1][col]));
		if (col > 0 && map[row][col - 1] < height)
			adjacents.add(new Location(row, col - 1, map[row][col - 1]));
		if (row < rowCount - 1 && map[row + 1][col] < height)
			adjacents.add(new Location(row + 1, col, map[row + 1][col]));
		if (col < colCount - 1 && map[row][col + 1] < height)
			adjacents.add(new Location(row, col + 1, map[row][col + 1]));

		return adjacents;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int rowCount = scanner.nextInt();
		int colCount = scanner.nextInt();

		int[][] map = new int[rowCount][colCount];
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < colCount; c++) {
				map[r][c] = scanner.nextInt();
			}
		}

		List<Location> peaks = findPeaks(map);

		Result[][] mem = new Result[rowCount][colCount];
		Result bestResult = new Result(1, 0);
		for (Location start : peaks) {
			Result result = search(map, start.row, start.col, mem);
			if (result.compareTo(bestResult) > 0) {
				bestResult = result;
			}
		}

		System.out.println(bestResult);

		scanner.close();
	}

	private static class Location {
		public final int row;
		public final int col;
		public final int height;

		public Location(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}

		public String toString() {
			return "(Row: " + row + ", Col: " + col + ")";
		}
	}

	private static class Result implements Comparable<Result> {
		public final int length;
		public final int steepness;

		public Result(int length, int steepness) {
			this.length = length;
			this.steepness = steepness;
		}

		public String toString() {
			return "(Length: " + length + ", Steepness: " + steepness + ")";
		}

		@Override
		public int compareTo(Result that) {
			if (this.length > that.length)
				return 1;
			if (this.length < that.length)
				return -1;
			if (this.steepness > that.steepness)
				return 1;
			if (this.steepness < that.steepness)
				return -1;
			return 0;
		}
	}
}
