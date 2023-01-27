import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ScratchE {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(reader.readLine());
            String[] tasks = reader.readLine().split(" ");
            HashSet<String> done = new HashSet<>();
            boolean yes = true;
            for (int j = 0; j < n; j++) {
                if (j == n - 1 || !tasks[j].equals(tasks[j + 1]) ) {
                    if (!done.contains(tasks[j])) {
                        done.add(tasks[j]);
                    } else {
                        yes = false;
                        break;
                    }
                }
            }
            System.out.println(yes ? "YES" : "NO");
        }
    }
}