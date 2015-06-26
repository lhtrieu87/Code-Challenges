import java.util.Scanner;

public class Solution {
	// Evaluate the value of an expression segment [l, r)
	public static long eval(char[] expression, int l, int r, long subtitution) {
		long result = 0;
		long lastNumber = 0;
		boolean isLastOperatorMultiply = false;
		for (int i = l; i < r; i++) {
			char c = expression[i];
			switch (c) {
			case '+':
				result += lastNumber;
				isLastOperatorMultiply = false;
				break;
			case '*':
				isLastOperatorMultiply = true;
				break;
			default:
				long value = c - '0';
				if (c == 's')
					value = subtitution;
				if (isLastOperatorMultiply)
					lastNumber *= value;
				else
					lastNumber = value;
				break;
			}
		}

		return result += lastNumber;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[] expression = scanner.next().toCharArray();
		long max = 0;
		for (int l = 0; l < expression.length; l += 2) {
			if (l > 0 && expression[l - 1] == '+')
				continue;
			for (int r = l + 1; r <= expression.length; r += 2) {
				if (r < expression.length && expression[r] == '+')
					continue;

				char[] newExpression = new char[l + 1 + expression.length - r];

				System.arraycopy(expression, 0, newExpression, 0, l);
				newExpression[l] = 's';
				System.arraycopy(expression, r, newExpression, l + 1,
						expression.length - r);

				max = Math.max(
						max,
						eval(newExpression, 0, newExpression.length,
								eval(expression, l, r, 1)));
			}
		}

		System.out.println(max);
	}
}