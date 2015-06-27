import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long[] ppl = new long[n];
		for (int i = 0; i < n; i++) {
			ppl[i] = scanner.nextLong();
		}

		// randomTestData(10, "example1.txt");
		// System.out.println(bruteForce(ppl));
		// System.out.println(insertionSort(ppl));

		System.out.println(solve(ppl));

		scanner.close();
	}

	private static String solve(long[] ppl) {
		int n = ppl.length;

		List<Long> list = new LinkedList<Long>();
		for (int i = 0; i < n; i++) {
			list.add(ppl[i] + i);
		}
		PriorityQueue<Long> queue = new PriorityQueue<Long>(list);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			long v = queue.poll();
			ppl[i] = v - i;

			if (i > 0 && ppl[i - 1] > ppl[i] || ppl[i] < 0)
				return ":(";

			sb.append(ppl[i]);
			if (i != n - 1)
				sb.append(" ");
		}

		return sb.toString();
	}

	private static void randomTestData(int n, String fileName)
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.println(n);
		for (int i = 0; i < n; i++) {
			writer.print((int) Math.floor(100 * Math.random()) + " ");
		}
		writer.close();
	}

	// Pre-condition(s): i > j & ppl[j] > ppl[i]
	private static void swap(long[] ppl, int i, int j) {
		long tmp = ppl[i];
		ppl[i] = ppl[j] - 1;
		ppl[j] = tmp + 1;
	}

	private static String insertionSort(long[] ppl) {
		int n = ppl.length;
		for (int i = 1; i < n; i++) {
			for (int j = i; j >= 1; j--) {
				if (ppl[j] < ppl[j - 1]) {
					swap(ppl, j, j - 1);
				} else {
					break;
				}
			}
		}

		return validate(ppl);
	}

	private static String validate(long[] ppl) {
		int n = ppl.length;
		boolean can = true;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (i < n - 1 && ppl[i] == ppl[i + 1] + 1) {
				can = false;
				break;
			}
			sb.append(ppl[i]);
			if (i < n - 1)
				sb.append(" ");
		}

		if (can)
			return sb.toString();
		else
			return ":(";
	}

	private static String bruteForce(long[] ppl) {
		int n = ppl.length;
		long[] result = new long[n];
		boolean[] visited = new boolean[n];
		for (int i = n - 1; i >= 0; i--) {
			long max = 0;
			int maxId = 0;
			for (int j = 0; j < n; j++) {
				if (visited[j] == false && ppl[j] + j - i > max) {
					max = ppl[j] - i + j;
					maxId = j;
				}
			}

			visited[maxId] = true;
			result[i] = max;
		}

		return validate(result);
	}
}
