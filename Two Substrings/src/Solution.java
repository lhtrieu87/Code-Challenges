import java.util.Scanner;

public class Solution {
    public static String solve(String S) {
        S = " " + S + " ";

        int numberOfABs = S.split("AB").length - 1;
        int numberOfBAs = S.split("BA").length - 1;
        int numberOfOverlaping = S.split("ABA").length + S.split("BAB").length
                - 2;
        int numberOfABABs = S.split("ABAB").length - 1;
        int numberOfBABAs = S.split("BABA").length - 1;

        if (numberOfABs == 0 || numberOfBAs == 0)
            return "NO";
        if (numberOfABs == 1 && numberOfBAs == 1 && numberOfOverlaping == 1)
            return "NO";
        if (numberOfBABAs == 1 && numberOfABs == 1 && numberOfBAs == 2)
            return "NO";
        if (numberOfABABs == 1 && numberOfBAs == 1 && numberOfABs == 2)
            return "NO";

        return "YES";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.next();
        System.out.println(solve(S));
        scanner.close();
    }
}
