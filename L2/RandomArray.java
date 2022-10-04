public class RandomArray {
    public static int[][] random2DSquareArray(int n, int min, int max) {
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = (int) (Math.random() * (double) ((max - min + 1)) + (double) min);
                num = num < 0 ? 0 : num;
                array[i][j] = num;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        for (int[] a : random2DSquareArray(10, -10, 10)) {
            for (int b : a) {
                System.out.printf("%2d ", b);
            }
            System.out.printf("\n");
        }
    }
}