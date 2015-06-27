import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		long K = scanner.nextLong();

		long[] toys = new long[N];
		for (int i = 0; i < N; i++) {
			toys[i] = scanner.nextLong();
		}

		Arrays.sort(toys);
		long total = 0;
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (total + toys[i] > K)
				break;
			total += toys[i];
			count++;
		}

		System.out.println(count);
	}
}
