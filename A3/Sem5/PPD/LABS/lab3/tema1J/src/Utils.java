import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Utils {
    static List<String> read(String filename) throws IOException {
        File file = new File(filename);
        Path path = Paths.get(file.getPath());
        return Files.readAllLines(path);
    }

    public static void writeMatrixToFile(String filename, int[][] V){
        try (FileWriter writer = new FileWriter(filename)) {
            for (int[] row : V) {
                for (int element : row)
                    writer.write(element + " ");
                writer.write("\n");
            }
        }
        catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void generateRandomData(int n, int m, int k, int L) {
        String filename = "src/data" + ".txt";

        int[][] matrix = new int[n][m];
        int[][] conv = new int[k][k];

        Random rand = new Random();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] = rand.nextInt(L);

        for (int i = 0; i < k; i++)
            for (int j = 0; j < k; j++)
                conv[i][j] = rand.nextInt(2);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(n + " " + m + " " + k + "\n");
            for (int[] row : conv) {
                for (int element : row)
                    writer.write(element + " ");
                writer.write("\n");
            }
            for (int[] row : matrix) {
                for (int element : row)
                    writer.write(element + " ");
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int[][] borderMatrix(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int newN = n + 2 * k;
        int newM = m + 2 * k;
        int[][] matrixWithBorder = new int[newN][newM];

        // Fill the inner matrix
        for (int i = 0; i < n; i++)
            System.arraycopy(matrix[i], 0, matrixWithBorder[i + k], k, m);

        // Fill the top border
        for (int i = 0; i < k; i++)
            for (int j = k; j < newM; j++)
                matrixWithBorder[i][j] = matrix[0][Math.min(j - k, m - 1)];

        // Fill the right border
        for (int i = 0; i < newN; i++)
            for (int j = newM - k; j < newM; j++)
                matrixWithBorder[i][j] = matrixWithBorder[i][newM - k - 1];

        // Fill the left border
        for (int i = 0; i < newN; i++)
            for (int j = 0; j < k; j++)
                matrixWithBorder[i][j] = matrixWithBorder[i][k];

        // Fill the bottom border
        for (int i = newN - k; i < newN; i++)
            System.arraycopy(matrixWithBorder[i - 1], 0, matrixWithBorder[i], 0, newM);

        return matrixWithBorder;
    }
}
