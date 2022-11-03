import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int capacity = 14;
        int values[] = { 7, 6, 9 };
        int weights[] = { 4, 6, 8 };

        System.out.println("Knapsack of Capacity\t: " + capacity);

        // I don't know what's going here I just copied the code from the internet
        System.out.println("Weights of each item\t: " + String.join(", ", Arrays.stream(weights)
                .mapToObj(String::valueOf)
                .toArray(String[]::new)));
        System.out.println("Values of each item\t: " + String.join(", ", Arrays.stream(values)
                .mapToObj(String::valueOf)
                .toArray(String[]::new)));

        System.out.print("Knapsack Recursive value: ");
        System.out.println(KnapsackRecursive.unboundedKnapsack(capacity, weights, values, values.length - 1));

        System.out.print("Knapsack Dynamic value\t: ");
        System.out.println(KnapsackDynamic.unboundedKnapsack(capacity, values, weights, values.length));
    }
}
