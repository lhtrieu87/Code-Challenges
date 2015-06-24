import java.util.Scanner;

public class Solution {
    private static int solve(String[] phoneNos, int l, int r, int k) {
        int count = 0;
        String called = phoneNos[k];
        for (int i = l; i <= r; i++) {
            String callee = phoneNos[i];
            int index = callee.indexOf(called, 0);
            while (index >= 0) {
                count++;
                index = callee.indexOf(called, index + 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        String[] phoneNos = new String[n];
        for (int i = 0; i < n; i++) {
            phoneNos[i] = scanner.next();
        }

        for (int i = 0; i < q; i++) {
            int l = scanner.nextInt() - 1;
            int r = scanner.nextInt() - 1;
            int k = scanner.nextInt() - 1;
            System.out.println(solve(phoneNos, l, r, k));
        }

        scanner.close();
    }
}
