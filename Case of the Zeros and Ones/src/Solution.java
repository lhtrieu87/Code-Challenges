import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		String s = scanner.next();

		char[] chars = s.toCharArray();
		int zeroCount = 0;
		int oneCount = 0;
		for (int i = 0; i < n; i++) {
			if (chars[i] == '0')
				zeroCount++;
			else
				oneCount++;
		}

		System.out.println(Math.abs(oneCount - zeroCount));
		scanner.close();
	}
}
