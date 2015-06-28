import java.util.Scanner;

public class Solution {
	private static int clockwise(int i, int N) {
		return (i + 1) % N;
	}

	private static int counterClockwise(int i, int N) {
		return (i - 1 + N) % N;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int[] gears = new int[N];
		for (int i = 0; i < N; i++) {
			gears[i] = scanner.nextInt();
		}

		boolean can = true;
		for (int i = 0; i < N - 1; i++) {
			if ((gears[i] + gears[i + 1] - i - i - 1) % N != 0) {
				can = false;
				break;
			}
		}

		if (can)
			System.out.println("Yes");
		else
			System.out.println("No");

		scanner.close();
	}
}
