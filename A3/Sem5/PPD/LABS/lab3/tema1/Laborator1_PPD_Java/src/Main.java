 import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static class MyThreadLine extends Thread {
        int id,start,end,n,m,k;
        int[][] C, A, V;

        public MyThreadLine(int n, int m, int k, int id, int si, int ei, int[][] c, int[][] a, int[][] v) {
            this.id = id;
            this.end = ei;
            this.start = si;
            this.C = c;
            this.A = a;
            this.V = v;
            this.n = n;
            this.k = k;
            this.m = m;
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

    public static class MyThreadColumn extends Thread {
        int id,start,end,n,m,k;
        int[][] C, A, V;

        public MyThreadColumn(int n, int m, int k, int id, int si, int ei, int[][] c, int[][] a, int[][] v) {
            this.id = id;
            this.end = ei;
            this.start = si;
            this.C = c;
            this.A = a;
            this.V = v;
            this.n = n;
            this.k = k;
            this.m = m;
        }

        @Override
        public void run() {
            for(int j = start; j< end;j++)
                for (int i = 0; i < n; i++)
                    for (int ii = -k / 2; ii < k - k / 2; ii++)
                        for (int jj = -k / 2; jj < k - k / 2; jj++)
                            V[i][j] += A[i + k / 2 + ii][j + k / 2 + jj] * C[ii + k / 2][jj + k / 2];
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

    public static void generateRandomData(int n, int m, int k, int L) {
        String filename = "data" + ".txt";

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
            writer.write(n + " " + m + "\n");
            for (int[] row : matrix) {
                for (int element : row)
                    writer.write(element + " ");
                writer.write("\n");
            }
            writer.write(k + "\n");
            for (int[] row : conv) {
                for (int element : row)
                    writer.write(element + " ");
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

//        int[][] matrix = {
//                {35, 40, 41, 45, 50},
//                {40, 40, 42, 46, 52},
//                {42, 46, 50, 55, 55},
//                {48, 52, 56, 58, 60},
//                {56, 60, 65, 70, 75}
//        };
//
//        int[][] C = {
//                {0, 1, 0},
//                {0, 0, 0},
//                {0, 0, 0}
//        };
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

    public static void runStaticLine(int n, int m, int k, int[][] A, int[][] V, int[][] C){
        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int ii = -k / 2; ii < k - k / 2; ii++)
                    for (int jj = -k / 2; jj < k - k / 2; jj++)
                        V[i][j] += A[i + k / 2 + ii][j + k / 2 + jj] * C[ii + k / 2][jj + k / 2];

        System.out.println((System.nanoTime() - startTime)/1E6);//ms

        writeMatrixToFile("outSecvential.txt",V);
    }
    public static void runStaticColumn(int n, int m, int k, int[][] A, int[][] V, int[][] C){
        long startTime = System.nanoTime();

        for (int j = 0; j < m; j++)
            for (int i = 0; i < n; i++)
                for (int ii = -k / 2; ii < k - k / 2; ii++)
                    for (int jj = -k / 2; jj < k - k / 2; jj++)
                        V[i][j] += A[i + k / 2 + ii][j + k / 2 + jj] * C[ii + k / 2][jj + k / 2];

        System.out.println((System.nanoTime() - startTime)/1E6);//ms

        writeMatrixToFile("outSecvential.txt",V);
    }

    public static void runThreadsLine(int n, int m, int k, int[][] A, int[][] V, int[][] C, int p) {
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
            threads[i] = new MyThreadLine(n,m,k,i, start, end, C, A, V);
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

        writeMatrixToFile("outThreadsLine.txt",V);
    }

    public static void runThreadsColumn(int n, int m, int k, int[][] A, int[][] V, int[][] C, int p) {
        Thread[] threads = new Thread[p];
        int rest = m % p;
        int cat = m / p;
        int start = 0;
        int end;

        long startTime = System.nanoTime();

        for (int i = 0; i < p; i++) {
            end = start + cat;
            if (rest > 0) {
                end++;
                rest--;
            }
            threads[i] = new MyThreadColumn(n,m,k,i, start, end, C, A, V);
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

        writeMatrixToFile("outThreadsColumn.txt",V);
    }

    public static void main(String[] args) throws IOException {
        // Generate random data for file
        //generateRandomData(10000, 10, 5, 5000);

        int n, m, k, p = 1;
        int[][] A, V, C, matrix;
        //p = Integer.parseInt(args[0]); // if i want the number of threads to be read from script

        // Read data from file
        String fileName = "D:\\Folder Facultate\\Anul 3 Semestrul 1\\Programare_Paralela_si_Distribuita\\Rezolvari_Laboratoare_PPD\\Laborator1_PPD\\Laborator1_PPD_Java\\data.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] dimensions = br.readLine().split(" ");
        n = Integer.parseInt(dimensions[0]);
        m = Integer.parseInt(dimensions[1]);
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] elements = br.readLine().split(" ");
            for (int j = 0; j < m; j++)
                matrix[i][j] = Integer.parseInt(elements[j]);
        }
        k = Integer.parseInt(br.readLine());
        C = new int[k][k];
        for (int i = 0; i < k; i++) {
            String[] elements = br.readLine().split(" ");
            for (int j = 0; j < k; j++)
                C[i][j] = Integer.parseInt(elements[j]);
        }

        A = borderMatrix(matrix, k / 2);
        V = new int[n][m];

        //runStaticLine(n,m,k,A,V,C); // for static lines
        //runStaticColumn(n,m,k,A,V,C); // for static column
        runThreadsLine(n,m,k,A,V,C,p); // for lines
        //runThreadsColumn(n,m,k,A,V,C,p); // for columns
    }
}