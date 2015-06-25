import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long m = scanner.nextInt();
        long h1 = scanner.nextInt();
        long a1 = scanner.nextInt();
        long x1 = scanner.nextInt();
        long y1 = scanner.nextInt();

        long h2 = scanner.nextInt();
        long a2 = scanner.nextInt();
        long x2 = scanner.nextInt();
        long y2 = scanner.nextInt();

        // Find the first time h1 == a1
        long time_a = 0;
        while (h1 != a1) {
            if (time_a >= m) {
                System.out.println(-1);
                return;
            }
            h1 = (h1 * x1 + y1) % m;
            h2 = (h2 * x2 + y2) % m;
            time_a++;
        }

        if (h2 == a2) {
            System.out.println(time_a);
            return;
        }

        // Find cycle length of h1
        long time_b = 0;
        long X2 = 1;
        long Y2 = 0;
        while (h1 != a1 || time_b == 0) {
            if (time_b >= m) {
                System.out.println(-1);
                return;
            }
            h1 = (h1 * x1 + y1) % m;
            X2 = (x2 * X2) % m;
            Y2 = (x2 * Y2 + y2) % m;
            time_b++;
        }

        long time_c = 0;
        while (h2 != a2) {
            if (time_c >= m) {
                System.out.println(-1);
                return;
            }
            h2 = (h2 * X2 + Y2) % m;
            time_c++;
        }
        System.out.println(time_a + time_b * time_c);
    }
}
