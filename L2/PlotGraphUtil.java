import java.util.ArrayList;
import java.util.List;

/**
 * Writes all the edges into a csv file in the format of node1, node2, weight
 */
public class PlotGraphUtil {
    public static void squareMatrixToCSV(int[][] arr, String csv_file_name) {
        try {
            List<String[]> dataLines = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] > 0) {
                        String[] data = { Integer.toString(i), Integer.toString(j), Integer.toString(arr[i][j]) };
                        dataLines.add(data);
                    }
                }
            }

            CSVthingy.givenDataArray_whenConvertToCSV_thenOutputCreated(csv_file_name, dataLines);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
