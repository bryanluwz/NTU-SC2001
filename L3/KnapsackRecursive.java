/**
 * Thanks
 * https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/
 */

public class KnapsackRecursive {
    // A utility function that returns maximum of two integers
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int unboundedKnapsack(int C, int wt[], int val[], int idx) {
        // Base Case if we are at idx 0.
        if (idx == 0) {
            return (C / wt[0]) * val[0];
        }

        // There are two cases either take element or not take. If not take then
        int notTake = 0 + unboundedKnapsack(C, wt, val, idx - 1);

        // If take then weight = capacity - wt[idx] and index will remain same.
        int take = Integer.MIN_VALUE;
        if (wt[idx] <= C) {
            take = val[idx] + unboundedKnapsack(C - wt[idx], wt, val, idx);
        }

        return max(take, notTake);
    }

    // Driver code
    public static void main(String args[]) {
        int capacity = 100;
        int values[] = { 10, 30, 20 };
        int weights[] = { 5, 10, 15 };
        int n = values.length;
        System.out.println(unboundedKnapsack(capacity, weights, values, n - 1));
    }
}
