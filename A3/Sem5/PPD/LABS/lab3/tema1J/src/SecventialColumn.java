import java.io.IOException;

public class SecventialColumn {
    static int n, m, k, p;
    static int[][] C, A, V;

    public static void main(String[] args) throws IOException {
        InputData data = new InputData(args);
        n = data.n;
        m = data.m;
        k = data.k;
        p = data.p;
        A = data.A;
        C = data.C;
        V = data.V;

        long startTime = System.nanoTime();

        for (int j = 0; j < m; j++)
            for (int i = 0; i < n; i++)
                for (int ii = -k / 2; ii < k - k / 2; ii++)
                    for (int jj = -k / 2; jj < k - k / 2; jj++)
                        V[i][j] += A[i + k / 2 + ii][j + k / 2 + jj] * C[ii + k / 2][jj + k / 2];

        System.out.println((System.nanoTime() - startTime)/1E6);//ms

        Utils.writeMatrixToFile("outSecvential.txt",V);
    }
}
