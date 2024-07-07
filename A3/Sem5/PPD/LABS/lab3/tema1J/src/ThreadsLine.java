import java.io.IOException;

public class ThreadsLine {
    static int n, m, k, p;
    static int[][] C, A, V, TEST;

    private static void test() {
        for (int j = 0; j < m; j++)
            for (int i = 0; i < n; i++)
                for (int ii = -k / 2; ii < k - k / 2; ii++)
                    for (int jj = -k / 2; jj < k - k / 2; jj++)
                        TEST[i][j] += A[i + k / 2 + ii][j + k / 2 + jj] * C[ii + k / 2][jj + k / 2];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if(TEST[i][j] != V[i][j])
                    throw new RuntimeException("TEST[i][j] != V[i][j] : "+TEST[i][j]+" != "+V[i][j]);
    }

    public static void main(String[] args) throws IOException {
        InputData data = new InputData(args);
        n = data.n;
        m = data.m;
        k = data.k;
        p = data.p;
        A = data.A;
        C = data.C;
        V = data.V;
        TEST = new int[n][m];

        Thread[] threads = new Thread[p];
        int rest = n % p;
        int cat = n / p;
        int start = 0;
        int end;

        long startTime = System.nanoTime();

        for (int i = 0; i < p; i++) {
            end = start + cat;
            if (rest > 0) {
                end++;
                rest--;
            }
            threads[i] = new LineThread(i, start, end);
            threads[i].start();
            start = end;
        }

        for (int i = 0; i < p; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println((System.nanoTime() - startTime)/1E6);//ms

        Utils.writeMatrixToFile("outThreadsLine.txt",V);
        test();
    }

    static class LineThread extends Thread {
        int id,start,end;

        public LineThread(int id, int si, int ei) {
            this.id = id;
            this.end = ei;
            this.start = si;
        }

        @Override
        public void run() {
            for(int i = start; i< end;i++)
                for (int j = 0; j < m; j++)
                    for (int ii = -k / 2; ii < k - k / 2; ii++)
                        for (int jj = -k / 2; jj < k - k / 2; jj++)
                            V[i][j] += A[i + k / 2 + ii][j + k / 2 + jj] * C[ii + k / 2][jj + k / 2];
        }
    }
}
