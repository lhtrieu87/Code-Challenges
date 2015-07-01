import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		long w = scanner.nextLong();

		long[] cups = new long[2 * n];
		for (int i = 0; i < cups.length; i++) {
			cups[i] = scanner.nextLong();
		}

		Arrays.sort(cups);
		if (cups[n] / 2.0 > cups[0]) {
			if (cups[0] * n * 3 > w) {
				System.out.println(w);
			} else
				System.out.println(cups[0] * n * 3);
		} else {
			if (cups[n] / 2.0 * n * 3 > w)
				System.out.println(w);
			else
				System.out.println(cups[n] / 2.0 * n * 3);
		}

		scanner.close();
	}
}
