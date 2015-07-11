import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Solution {
	private static class Bridge implements Comparable<Bridge> {
		public long length;
		public int index;

		public Bridge(long length, int index) {
			this.length = length;
			this.index = index;
		}

		@Override
		public int compareTo(Bridge that) {
			if(this.length > that.length) return 1;
			if(this.length < that.length) return -1;
			return Integer.compare(this.index, that.index);
		}
	}

	private static class Interval implements Comparable<Interval> {
		public long min;
		public long max;
		public int index;

		public Interval(long min, long max, int index) {
			this.min = min;
			this.max = max;
			this.index = index;
		}

		@Override
		public int compareTo(Interval that) {
			return Long.compare(this.max, that.max);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		long[][] islands = new long[n][2];
		for (int i = 0; i < n; i++) {
			islands[i][0] = scanner.nextLong();
			islands[i][1] = scanner.nextLong();
		}

		scanner.nextLine();
		String[] tokens = scanner.nextLine().split(" ");
		Bridge[] bridges = new Bridge[m];
		for (int i = 0; i < m; i++) {
			bridges[i] = new Bridge(Long.parseLong(tokens[i]), i + 1);
		}

		Arrays.sort(bridges);

		Interval[] intervals = new Interval[n - 1];
		for (int i = 0; i < n - 1; i++) {
			intervals[i] = new Interval(islands[i + 1][0] - islands[i][1],
					islands[i + 1][1] - islands[i][0], i);
		}

		Arrays.sort(intervals);

		TreeSet<Bridge> set = new TreeSet<>();
		int j = 0;
		boolean possible = true;
		int[] selectedBridges = new int[n - 1];
		for (int i = 0; i < n - 1; i++) {
			while (j < bridges.length && bridges[j].length <= intervals[i].max) {
				set.add(bridges[j]);
				j++;
			}

			Bridge b = set.higher(new Bridge(intervals[i].min, -1));
			if (b == null) {
				possible = false;
				break;
			}

			selectedBridges[intervals[i].index] = b.index;
			set.remove(b);
		}

		if (possible) {
			System.out.println("Yes");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n - 1; i++) {
				sb.append(selectedBridges[i]);
				if(i < n - 1)
					sb.append(" ");
			}
			System.out.println(sb);
		} else {
			System.out.println("No");
		}

		scanner.close();
	}
}
