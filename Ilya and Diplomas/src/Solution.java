import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		long n = scanner.nextLong();
		long min1 = scanner.nextLong();
		long max1 = scanner.nextLong();

		long min2 = scanner.nextLong();
		long max2 = scanner.nextLong();

		long min3 = scanner.nextLong();
		long max3 = scanner.nextLong();

		long o1 = Math.min(max1, n - min2 - min3);
		long remaining = n - o1;
		long o2 = Math.min(max2, remaining - min3);
		long o3 = n - o1 - o2;

		System.out.println(o1 + " " + o2 + " " + o3);

		scanner.close();
	}
}
