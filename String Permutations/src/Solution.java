import java.util.Scanner;

public class Solution {
	private static void printSortedPermutations(char[] chars,
			boolean[] visited, String path) {
		if (path.length() == chars.length) {
			System.out.println(path);
			return;
		}

		for (int i = 0; i < chars.length; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				printSortedPermutations(chars, visited, path + chars[i]);
				visited[i] = false;
			}
		}
	}

	private static void printSortedCombinations(int[] f, String path) {
		boolean end = true;
		for (int i = 0; i < f.length; i++) {
			if (f[i] > 0) {
				end = false;

				char c = (char) (i + 'a');

				f[i]--;
				printSortedCombinations(f, path + c);
				f[i]++;
			}
		}

		if (end) {
			System.out.println(path);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		char[] chars = s.toCharArray();
		// Arrays.sort(chars);
		// boolean[] visited = new boolean[chars.length];
		// printSortedPermutations(chars, visited, "");
		int[] f = new int[26];
		for (char c : chars) {
			int index = c - 'a';
			f[index]++;
		}
		printSortedCombinations(f, "");
		scanner.close();
	}
}
