import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ScratchD {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String s = reader.readLine();
            String[] nm = reader.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            int[][] matrix = new int[n][m];

            for (int j = 0; j < n; j++) {
                String[] row = reader.readLine().split(" ");
                for (int k = 0; k < m; k++) {
                    matrix[j][k] = Integer.parseInt(row[k]);
                }
            }
            String clicksCount = reader.readLine();
            List<Integer> clicks = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

            for (Integer click : clicks) {
                mergeSort(matrix, n, m, click - 1);
            }

            print(matrix);
        }
    }

    public static void mergeSort(int[][] a, int n, int m, int index) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[][] l = new int[mid][m];
        int[][] r = new int[n - mid][m];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid, m, index);
        mergeSort(r, n - mid, m, index);

        merge(a, l, r, mid, n - mid, index);
    }

    public static void merge(int[][] a, int[][] l, int[][] r, int left, int right, int index) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i][index] <= r[j][index]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println(matrix[i][matrix[i].length - 1]);
        }
        System.out.println();
    }
}