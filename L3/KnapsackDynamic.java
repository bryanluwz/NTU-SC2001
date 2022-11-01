/**
 * Thanks
 * https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/
 */

public class KnapsackDynamic {
    private static int max(int i, int j) {
        return (i > j) ? i : j;
    }

    // Returns the maximum value with knapsack of W capacity
    private static int unboundedKnapsack(int C, int n, int[] val, int[] wt) {

        // dp[i] is going to store maximum with knapsack capacity i.
        int dp[] = new int[C + 1];

        // Fill dp[] using above recursive formula
        for (int i = 0; i <= C; i++) {
            for (int j = 0; j < n; j++) {
                if (wt[j] <= i) {
                    dp[i] = max(dp[i], dp[i - wt[j]] + val[j]);
                }
            }
        }
        return dp[C];
    }

    // Driver program
    public static void main(String[] args) {
        int capacity = 100;
        int values[] = { 10, 30, 20 };
        int weights[] = { 5, 10, 15 };
        int n = values.length;
        System.out.println(unboundedKnapsack(capacity, n, values, weights));
    }
}
