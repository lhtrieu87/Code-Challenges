import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		for (int t = 0; t < T; t++) {
			int N = scanner.nextInt();
			long K = scanner.nextLong();
			long[] A = new long[N];
			long[] B = new long[N];
			for (int i = 0; i < N; i++) {
				A[i] = scanner.nextLong();
			}

			for (int i = 0; i < N; i++) {
				B[i] = scanner.nextLong();
			}

			Arrays.sort(A);
			Arrays.sort(B);

			boolean can = true;
			for (int i = 0; i < N; i++) {
				if (A[i] + B[N - 1 - i] < K) {
					can = false;
					break;
				}
			}

			if (can)
				System.out.println("YES");
			else
				System.out.println("NO");
		}

		scanner.close();
	}
}
