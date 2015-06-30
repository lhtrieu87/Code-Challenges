import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		for (int t = 0; t < T; t++) {
			int S = scanner.nextInt();
			char[][] matrix = new char[S][S];
			for (int s = 0; s < S; s++) {
				matrix[s] = scanner.next().toCharArray();
			}

			int[][] countTable = new int[S][26];
			for (int r = 0; r < S; r++) {
				for (int c = 0; c < S; c++) {
					countTable[r][matrix[r][c] - 'a']++;
				}
			}

			boolean stop = false;
			for (int r = 0; r < S - 1 && stop == false; r++) {
				int acc = 0;
				for (int i = 0; i < 26; i++) {
					if (countTable[r + 1][i] > countTable[r][i] + acc) {
						stop = true;
						break;
					} else {
						acc = countTable[r][i] + acc - countTable[r + 1][i];
					}
				}
			}
			
			if(stop)
				System.out.println("NO");
			else
				System.out.println("YES");
		}

		scanner.close();
	}
}
