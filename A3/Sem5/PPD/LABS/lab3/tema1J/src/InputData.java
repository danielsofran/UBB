import java.io.IOException;
import java.util.List;

public class InputData {
    int n, m, k, p;
    int[][] C, A, V;

    public InputData(String[] args) throws IOException {
        this.p = 4;

        String filename = "data.txt";
        List<String> lines = Utils.read(filename);
        String[] firstLine = lines.get(0).split(" ");

        this.n = Integer.parseInt(firstLine[0]);
        this.m = Integer.parseInt(firstLine[1]);
        this.k = Integer.parseInt(firstLine[2]);

        this.C = new int[k][k];
        for (int i = 0; i < k; i++) {
            String[] line = lines.get(i + 1).split(" ");
            for (int j = 0; j < k; j++)
                C[i][j] = Integer.parseInt(line[j]);
        }

        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = lines.get(k + 1 + i).split(" ");
            for (int j = 0; j < m; j++)
                matrix[i][j] = Integer.parseInt(line[j]);
        }

        this.A = Utils.borderMatrix(matrix, k);
        this.V = new int[n][m];
    }
}
