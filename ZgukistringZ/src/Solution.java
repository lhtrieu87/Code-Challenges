import java.util.Scanner;

public class Solution {
    private static String solve(String a, String b, String c) {
        int[] xa = new int[26];
        int[] xb = new int[26];
        int[] xc = new int[26];

        for (int i = 0; i < a.length(); i++)
            xa[a.charAt(i) - 'a']++;
        for (int i = 0; i < b.length(); i++)
            xb[b.charAt(i) - 'a']++;
        for (int i = 0; i < c.length(); i++)
            xc[c.charAt(i) - 'a']++;

        StringBuilder sb = new StringBuilder();

        int maxNumberOfBs = 0;
        int maxNumberOfCs = 0;
        boolean stop = false;
        for (int numberOfBs = 0; numberOfBs <= a.length() && !stop; numberOfBs++) {
            int[] ta = xa.clone();
            for (int i = 0; i < 26; i++) {
                ta[i] -= numberOfBs * xb[i];
                if (ta[i] < 0) {
                    stop = true;
                    break;
                }
            }

            if (!stop) {
                int numberOfCs = Integer.MAX_VALUE;
                for (int i = 0; i < 26; i++) {
                    numberOfCs = Math.min(numberOfCs,
                            xc[i] == 0 ? Integer.MAX_VALUE : ta[i] / xc[i]);
                }

                if (numberOfBs + numberOfCs > maxNumberOfBs + maxNumberOfCs) {
                    maxNumberOfBs = numberOfBs;
                    maxNumberOfCs = numberOfCs;

                }
            }
        }

        for (int i = 0; i < maxNumberOfBs; i++)
            sb.append(b);

        for (int i = 0; i < maxNumberOfCs; i++)
            sb.append(c);

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < xa[i] - maxNumberOfBs * xb[i] - maxNumberOfCs
                    * xc[i]; j++)
                sb.append((char) (i + 'a'));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        String b = scanner.next();
        String c = scanner.next();

        System.out.println(solve(a, b, c));

        scanner.close();
    }
}
