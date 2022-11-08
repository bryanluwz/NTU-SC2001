import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		System.out.print("\033[H\033[2J");
		System.out.flush();

		int capacity = 14;

		// Test case 1
		int values[] = { 7, 6, 9 };
		int weights[] = { 4, 6, 8 };

		System.out.print("\n");
		System.out.println("\u001B[36m========== Test Case 1 ==========\u001B[0m");

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

		// Test case 2
		System.out.print("\n");
		System.out.println("\u001B[36m========== Test Case 2 ==========\u001B[0m");

		weights[0] = 5;

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

		System.out.print("\n");
	}
}
