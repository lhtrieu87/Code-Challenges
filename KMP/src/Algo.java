import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Algo {
	private static int[] buildFailureTable(String b) {
		char[] chars = b.toCharArray();

		int[] fTable = new int[chars.length];
		fTable[0] = 0;
		int k = 0;
		for (int i = 1; i < chars.length; i++) {
			while (k > 0 && chars[k] != chars[i]) {
				k = fTable[k - 1];
			}

			if (chars[k] == chars[i]) {
				k++;
			}

			fTable[i] = k;
		}

		return fTable;
	}

	private static List<Integer> search(String a, String b, int[] fTable) {
		char[] aChars = a.toCharArray();
		char[] bChars = b.toCharArray();

		List<Integer> foundPositions = new LinkedList<>();

		int k = 0;
		for (int i = 0; i < aChars.length; i++) {
			while (k > 0 && aChars[i] != bChars[k]) {
				k = fTable[k - 1];
			}

			if (aChars[i] == bChars[k]) {
				k++;
			}

			if (k == b.length()) {
				k = fTable[k - 1];
				foundPositions.add(i - b.length() + 1);
			}
		}

		return foundPositions;
	}

	private static List<Integer> bruteForce(String a, String b) {
		List<Integer> foundPositions = new LinkedList<Integer>();
		for (int i = 0; i <= a.length() - b.length(); i++) {
			for (int j = 0; j < b.length(); j++) {
				if (a.charAt(i + j) != b.charAt(j)) {
					break;
				}

				if (j == b.length() - 1) {
					foundPositions.add(i);
				}
			}
		}

		return foundPositions;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String a = scanner.nextLine();
		String b = scanner.nextLine();

		int[] fTable = buildFailureTable(b);
		// List<Integer> foundPositions = search(a, b, fTable);
		List<Integer> foundPositions = bruteForce(a, b);

		if (foundPositions.size() == 0) {
			System.out.println(-1);
		} else
			for (int i = 0; i < foundPositions.size(); i++) {
				System.out.println(foundPositions.get(i));
			}

		scanner.close();
	}
}
