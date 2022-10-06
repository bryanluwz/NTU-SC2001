public class RandomArray {
    /**
     * Returns a 2D Square array of a connected graph.
     * <p>
     * The smaller the min value the more likely the array generated is not
     * connected which in that case the function would just repeat until it generate
     * a connected graph which will take quite some time if the value of n is big
     * <p>
     * TL;DR big n smol min run long
     * 
     * @param n   the size of the array
     * @param min the minimum value of the array
     * @param max the maximum value of the array
     */
    public static int[][] random2DSquareArray(int n, int min, int max) {
        int[][] arr = new int[n][n];
        do {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        arr[i][j] = 0;
                        continue;
                    }
                    int num = (int) (Math.random() * (double) ((max - min + 1)) + (double) min);
                    num = num < 0 ? 0 : num;
                    arr[i][j] = num;
                }
            }
        } while (!isConnectedFromFirstNode(arr));

        return arr;
    }

    public static boolean isConnectedFromFirstNode(int[][] arr) {
        boolean[] checked = new boolean[arr.length];
        for (int i = 0; i < checked.length; i++) {
            checked[i] = false;
        }
        isConnectedFromFirstNodeUtil(arr, 0, checked);
        for (boolean i : checked) {
            if (!i) {
                return false;
            }
        }
        return true;
    }

    public static void isConnectedFromFirstNodeUtil(int[][] arr, int cur, boolean[] checked) {
        checked[cur] = true;
        for (int i = 0; i < arr[cur].length; i++) {
            // Checked if is connected
            if (arr[cur][i] > 0 && !checked[i]) {
                isConnectedFromFirstNodeUtil(arr, i, checked);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = random2DSquareArray(5, -10, 10);

        for (int[] a : arr) {
            for (int b : a) {
                System.out.printf("%2d ", b);
            }
            System.out.printf("\n");
        }
        System.out.println(isConnectedFromFirstNode(arr));
    }
}