import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) throws IOException {
//        generateRandomData(10000, 10000, 5000);

        int n, m, p = 16;
        int[][] A, C;
        //p = Integer.parseInt(args[0]); // if i want the number of threads to be read from script

        String fileName = "C:\\Users\\Demian\\Desktop\\Programare paralela\\Laborator1_PPD\\Laborator1_PPD_Java\\data.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] dimensions = br.readLine().split(" ");
        n = Integer.parseInt(dimensions[0]);
        m = Integer.parseInt(dimensions[1]);
        A = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            String[] elements = br.readLine().split(" ");
            for (int j = 1; j <= m; j++)
                A[i][j] = Integer.parseInt(elements[j - 1]);//read the inner matrix
        }
        C = new int[3][3];
        for (int i = 0; i < 3; i++) {
            String[] elements = br.readLine().split(" ");
            for (int j = 0; j < 3; j++)
                C[i][j] = Integer.parseInt(elements[j]);
        }

        borderMatrix(A);

//        runStaticLine(n, m, A, C);

        solveThreadsLine(n, m, A, C, p);
    }

    public static class MyThreadLine extends Thread {
        int id, start, end, n, m;
        int[][] C, A, V;
        CyclicBarrier barrier;

        public MyThreadLine(int n, int m, int id, int start, int end, int[][] C, int[][] A, CyclicBarrier barrier) {
            this.id = id;
            this.end = end;
            this.start = start;
            this.C = C;
            this.A = A;
            this.n = n;
            this.m = m;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            int[] frontier_above = new int[m + 2];
            int[] frontier_below = new int[m + 2];
            System.arraycopy(A[start - 1], 0, frontier_above, 0, m + 2);
            System.arraycopy(A[end], 0, frontier_below, 0, m + 2);

            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException ignored) {
            }

            int[] above = new int[m + 2];
            int[] current = new int[m + 2];
            System.arraycopy(A[start], 0, above, 0, m + 2);
            int left = A[start][0];

            //first line
            for (int j = 1; j <= m; j++) {
                int value = 0;

                for (int ii = 0; ii <= 1; ii++)
                    for (int jj = -1; jj <= 1; jj++)
                        if (ii == 0 && jj == -1)
                            value += left * C[ii + 1][jj + 1];
                        else
                            value += A[start + ii][j + jj] * C[ii + 1][jj + 1];

                for (int jj = -1; jj <= 1; jj++)
                    value += frontier_above[j + jj] * C[0][jj + 1];

                left = A[start][j];
                A[start][j] = value;
            }

            left = A[start + 1][0];
            //inner lines
            for (int i = start + 1; i < end - 1; i++) {
                System.arraycopy(A[i], 0, current, 0, m + 2);
                for (int j = 1; j <= m; j++) {
                    int value = 0;

                    for (int ii = 0; ii <= 1; ii++)
                        for (int jj = -1; jj <= 1; jj++)
                            if (ii == 0 && jj == -1)
                                value += left * C[ii + 1][jj + 1];
                            else
                                value += A[i + ii][j + jj] * C[ii + 1][jj + 1];

                    for (int jj = -1; jj <= 1; jj++)
                        value += above[j + jj] * C[0][jj + 1];

                    if (j == m)
                        left = A[i + 1][0];
                    else
                        left = A[i][j];
                    A[i][j] = value;
                }
                System.arraycopy(current, 0, above, 0, m + 2);
            }

            //last line
            for (int j = 1; j <= m; j++) {
                int value = 0;

                for (int jj = -1; jj <= 1; jj++)
                    value += above[j + jj] * C[0][jj + 1];

                for (int jj = -1; jj <= 1; jj++)
                    if (jj == -1)
                        value += left * C[1][jj + 1];
                    else
                        value += A[end - 1][j + jj] * C[1][jj + 1];

                for (int jj = -1; jj <= 1; jj++)
                    value += frontier_below[j + jj] * C[2][jj + 1];

                left = A[end - 1][j];
                A[end - 1][j] = value;
            }
        }
    }

    public static void runStaticLine(int n, int m, int[][] A, int[][] C) {
        long startTime = System.nanoTime();
        int[] above = new int[m + 2];
        int[] current = new int[m + 2];
        System.arraycopy(A[0], 0, above, 0, m + 2);
        int left = A[1][0];

        for (int i = 1; i <= n; i++) {
            System.arraycopy(A[i], 0, current, 0, m + 2);
            for (int j = 1; j <= m; j++) {
                int value = 0;

                for (int ii = 0; ii <= 1; ii++)
                    for (int jj = -1; jj <= 1; jj++)
                        if (ii == 0 && jj == -1)
                            value += left * C[ii + 1][jj + 1];
                        else
                            value += A[i + ii][j + jj] * C[ii + 1][jj + 1];

                for (int jj = -1; jj <= 1; jj++)
                    value += above[j + jj] * C[0][jj + 1];

                if (j == m)
                    left = A[i + 1][0];
                else
                    left = A[i][j];
                A[i][j] = value;
            }
            System.arraycopy(current, 0, above, 0, m + 2);
        }

        System.out.println((System.nanoTime() - startTime) / 1E6);

        writeMatrixToFile("outSecvential.txt", A);
    }

    private static void solveThreadsLine(int n, int m, int[][] A, int[][] C, int p) {
        runThreadsLine(n, m, A, C, p);

        int[][] AA;
        AA = new int[n + 2][m + 2];
        readMatrixFromFile("outSecvential.txt", n + 2, m + 2, AA);
        boolean areEqual = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i][j] != AA[i][j]) {
                    areEqual = false;
                    break;
                }
            }
        }
        if (!areEqual) {
            throw new RuntimeException("A is not equal to AA");
        }
    }

    public static void runThreadsLine(int n, int m, int[][] A, int[][] C, int p) {
        Thread[] threads = new Thread[p];
        int rest = n % p;
        int cat = n / p;
        int start = 1;
        int end;

        long startTime = System.nanoTime();

        CyclicBarrier barrier = new CyclicBarrier(p);
        for (int i = 0; i < p; i++) {
            end = start + cat;
            if (rest > 0) {
                end++;
                rest--;
            }
            threads[i] = new MyThreadLine(n, m, i, start, end, C, A, barrier);
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

        System.out.println((System.nanoTime() - startTime) / 1E6);

        writeMatrixToFile("outThreadsLine.txt", A);
    }

    public static void borderMatrix(int[][] A) {
        int fullN = A.length;
        int fullM = A[0].length;

        // Fill the top border
        System.arraycopy(A[1], 0, A[0], 0, fullM);

        // Fill the right border
        for (int i = 0; i < fullN; i++)
            A[i][fullM - 1] = A[i][fullM - 2];

        // Fill the left border
        for (int i = 0; i < fullN; i++)
            A[i][0] = A[i][1];

        // Fill the bottom border
        System.arraycopy(A[fullN - 2], 0, A[fullN - 1], 0, fullM);

    }

    public static void generateRandomData(int n, int m, int L) {
        String filename = "data" + ".txt";

        int[][] A = new int[n][m];
        int[][] conv = new int[3][3];

        Random rand = new Random();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                A[i][j] = rand.nextInt(L);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                conv[i][j] = rand.nextInt(2);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(n + " " + m + "\n");
            for (int[] row : A) {
                for (int element : row)
                    writer.write(element + " ");
                writer.write("\n");
            }
            for (int[] row : conv) {
                for (int element : row)
                    writer.write(element + " ");
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void writeMatrixToFile(String filename, int[][] V) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int[] row : V) {
                for (int element : row)
                    writer.write(element + " ");
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readMatrixFromFile(String filename, int n, int m, int[][] V) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < n; i++) {
                String[] elements = br.readLine().split(" ");
                for (int j = 0; j < m; j++)
                    V[i][j] = Integer.parseInt(elements[j]);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
