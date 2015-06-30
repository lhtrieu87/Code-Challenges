import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		ArrayList<ArrayList<Integer>> dolls = new ArrayList<ArrayList<Integer>>();
		int doll1Chain = -1;
		for (int i = 0; i < k; i++) {
			int dollCount = scanner.nextInt();
			ArrayList<Integer> chain = new ArrayList<Integer>();
			dolls.add(chain);
			for (int j = 0; j < dollCount; j++) {
				int doll = scanner.nextInt();
				chain.add(doll);

				if (doll == 1) {
					doll1Chain = i;
				}
			}
		}

		int seconds = 0;

		for (int c = 0; c < k; c++) {
			ArrayList<Integer> chain = dolls.get(c);
			if (c == doll1Chain) {
				for (int i = 1; i < chain.size(); i++) {
					if (chain.get(i) != chain.get(i - 1) + 1) {
						seconds += 2 * (chain.size() - i);
						break;
					}
				}
			} else {
				seconds += 2 * chain.size() - 1;
			}
		}

		System.out.println(seconds);

		scanner.close();
	}
}
