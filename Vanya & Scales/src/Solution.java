import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int w = scanner.nextInt();
		int m = scanner.nextInt();

		while (m > 1) {
			if (m % w == 0)
				m = m / w;
			else if ((m + 1) % w == 0)
				m = (m + 1) / w;
			else if ((m - 1) % w == 0)
				m = (m - 1) / w;
			else
				break;
		}

		if (m > 1)
			System.out.println("NO");
		else
			System.out.println("YES");

		scanner.close();
	}
}
