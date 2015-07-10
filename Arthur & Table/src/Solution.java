import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {
	private static class Leg {
		public int length;
		public int energy;
		public boolean removed = false;

		public Leg(int length, int energy) {
			this.length = length;
			this.energy = energy;
		}

		public String toString() {
			return "(" + this.length + " " + this.energy + ")";
		}
	}

	private static class SortByLength implements Comparator<Leg> {

		@Override
		public int compare(Leg l1, Leg l2) {
			if (l1.length < l2.length)
				return 1;
			if (l1.length > l2.length)
				return -1;
			return 0;
		}

	}

	private static class SortByEnergy implements Comparator<Leg> {

		@Override
		public int compare(Leg l1, Leg l2) {
			if (l1.energy < l2.energy)
				return 1;
			if (l1.energy > l2.energy)
				return -1;
			return 0;
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		scanner.nextLine();
		String[] line0 = scanner.nextLine().split(" ");
		String[] line1 = scanner.nextLine().split(" ");

		Leg[] legs = new Leg[n];
		for (int i = 0; i < n; i++) {
			legs[i] = new Leg(Integer.parseInt(line0[i]),
					Integer.parseInt(line1[i]));
		}

		Leg[] sortedByLength = legs;
		Leg[] sortedByEnergy = legs.clone();
		Arrays.sort(sortedByLength, new SortByLength());
		Arrays.sort(sortedByEnergy, new SortByEnergy());

		int totalEnergy = 0;
		for (Leg l : sortedByEnergy) {
			totalEnergy += l.energy;
		}

		System.out
				.println(solve(sortedByLength, sortedByEnergy, totalEnergy, 0));

		// 183 186 195 25 132
		// 16 19 5 19 19

		// String[] lengths = scanner.nextLine().split(" ");
		// String[] tokens = scanner.nextLine().split(" ");
		// int[] values = new int[tokens.length];
		// for (int i = 0; i < tokens.length; i++) {
		// values[i] = Integer.parseInt(tokens[i]);
		// }
		// boolean[] visited = new boolean[tokens.length];
		// findSum(387, values, 0, visited, lengths);

		scanner.close();
	}

	private static void findSum(int sum, int[] values, int current,
			boolean[] visited, String[] ls) {
		if (sum == 0) {
			for (int i = 0; i < values.length; i++) {
				if (!visited[i])
					System.out.print(values[i] + " ");
			}

			System.out.println();
			for (int i = 0; i < values.length; i++) {
				if (!visited[i])
					System.out.print(ls[i] + " ");
			}

			System.out.println();
			return;
		}

		if (sum < 0)
			return;

		if (current >= values.length)
			return;

		visited[current] = true;
		findSum(sum - values[current], values, current + 1, visited, ls);
		visited[current] = false;

		findSum(sum, values, current + 1, visited, ls);
	}

	private static int solve(Leg[] sortedByLength, Leg[] sortedByEnergy,
			int totalEnergy, int currentLengthIdx) {
		if (currentLengthIdx >= sortedByLength.length)
			return 0;

		Leg currentLeg = sortedByLength[currentLengthIdx];
		int l = currentLeg.length;
		int eToRemoveAllL = currentLeg.energy;
		int countL = 1;
		while (++currentLengthIdx < sortedByLength.length
				&& sortedByLength[currentLengthIdx].length == l) {
			eToRemoveAllL += sortedByLength[currentLengthIdx].energy;
			countL++;
			sortedByLength[currentLengthIdx].removed = true;
		}

		// keep l
		int c = 0;
		int eByKeepingL = totalEnergy - eToRemoveAllL;
		for (int i = 0; i < sortedByEnergy.length && c < countL - 1; i++) {
			Leg leg = sortedByEnergy[i];
			if (!leg.removed && leg.length < currentLeg.length) {
				eByKeepingL -= leg.energy;
				c++;
			}
		}

		// do not keep l
		int eByRemovingL = eToRemoveAllL
				+ solve(sortedByLength, sortedByEnergy, totalEnergy
						- eToRemoveAllL, currentLengthIdx);

		return Math.min(eByKeepingL, eByRemovingL);
	}
}
