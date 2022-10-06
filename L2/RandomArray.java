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
        int[][] array = new int[n][n];
        int num;
        do {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                	if (i == 0 && j == 0)
                		num = 0;
                	else 
                		num = (int) (Math.random() * (double) ((max - min + 1)) + (double) min);
                    num = num < 0 ? 0 : num;
                    array[i][j] = num;
                }
            }
        } while (!isConnectedFromFirstNode(array));

        return array;
    }
