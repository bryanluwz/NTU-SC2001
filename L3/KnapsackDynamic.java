/**
 * Thanks
 * https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/
 */

public class KnapsackDynamic {
    public static int count = 0;

    private static int max(int i, int j) {
        return (i > j) ? i : j;
    }

    // Returns the maximum value with knapsack of W capacity
    public static int unboundedKnapsack(int C, int[] val, int[] wt, int n) {

        // dp[i] is going to store maximum with knapsack capacity i.
        int dp[] = new int[C + 1];

        // Fill dp[] using above recursive formula
        for (int i = 0; i <= C; i++) {
            for (int j = 0; j < n; j++) {
                count++;
                if (wt[j] <= i) {
                    dp[i] = max(dp[i], dp[i - wt[j]] + val[j]);
                }
            }
        }
        for (int i : dp) {
            System.out.printf("%d ", i);
        }

        return dp[C];
    }

    // Driver program
    public static void main(String[] args) {
        int capacity = 14;
        int values[] = { 7, 6, 9 };
        int weights[] = { 4, 6, 8 };
        int n = values.length;
        System.out.println(unboundedKnapsack(capacity, values, weights, n));
        System.out.println(count);
    }
}
