import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ScratchH {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N; i++) {
            String[] nm = reader.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            char[][] map = new char[n][m];

            for (int j = 0; j < n; j++) {
                char[] line = reader.readLine().toCharArray();
                for (int k = 0; k < m; k++) {
                    map[j][k] = line[k];
                }
            }

            HashSet<String> visited = new HashSet<>();
            HashSet<Character> results = new HashSet<>();
            boolean result = false;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    char c = map[j][k];
                    if (c == '.') continue;
                    if (!visited.contains(code(j, k))) {
                        if (results.contains(c)) {
                            result = false;
                        } else {
                            result = visit(map, n, m, j, k, c, visited);
                        }
                    }
                    if (!result) {
                        break;
                    } else {
                        results.add(c);
                    }
                }
                if (!result) {
                    break;
                }
            }
            System.out.println(result ? "YES" : "NO");
        }
    }


    private static boolean visit(char[][] map, int n, int m, int j, int k, char c, HashSet<String> visited) {

        String str = "j" + j + "k" + k;
        visited.add(str);

        int j1 = j - 1;
        int k1 = k - 1;
        if (j1 >= 0 && k1 >= 0 && map[j1][k1] == c && !visited.contains(code(j1, k1))) {
            visit(map, n, m, j1, k1, c, visited);
        }

        int j2 = j;
        int k2 = k - 2;
        if (k2 >= 0 && map[j2][k2] == c && !visited.contains(code(j2, k2))) {
            visit(map, n, m, j2, k2, c, visited);
        }

        int j3 = j + 1;
        int k3 = k - 1;
        if (j3 < n && k3 >= 0 && map[j3][k3] == c && !visited.contains(code(j3, k3))) {
            visit(map, n, m, j3, k3, c, visited);
        }

        int j4 = j - 1;
        int k4 = k + 1;
        if (j4 >= 0 && k4 < m && map[j4][k4] == c && !visited.contains(code(j4, k4))) {
            visit(map, n, m, j4, k4, c, visited);
        }

        int j5 = j;
        int k5 = k + 2;
        if (k5 < m && map[j5][k5] == c && !visited.contains(code(j5, k5))) {
            visit(map, n, m, j5, k5, c, visited);
        }

        int j6 = j + 1;
        int k6 = k + 1;
        if (j6 < n && k6 < m && map[j6][k6] == c && !visited.contains(code(j6, k6))) {
            visit(map, n, m, j6, k6, c, visited);
        }

        return true;
    }

    private static String code(int j, int k) {
        return "j" + j + "k" + k;
    }

}