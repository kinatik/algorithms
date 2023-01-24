import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ScratchC {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(reader.readLine());
            String[] s = reader.readLine().split(" ");
            Integer[] levels = new Integer[n];
            HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                int level = Integer.parseInt(s[j]);
                levels[j] = level;
                map.computeIfAbsent(level, k -> new LinkedList<>());
                map.get(level).add(j + 1);
            }

            int count = 0;
            for (int j = 0; j < levels.length; j++) {
                Integer level = levels[j];
                if (level.equals(0)) {
                    continue;
                }

                if (!map.get(level).isEmpty()) {
                    Integer firstIndex = map.get(level).removeFirst();
                    levels[firstIndex - 1] = 0;
                    System.out.print(firstIndex);
                    System.out.print(" ");
                } else {
                    continue;
                }

                if (!map.get(level).isEmpty()) {
                    Integer secondIndex = map.get(level).removeFirst();
                    levels[secondIndex - 1] = 0;
                    System.out.println(secondIndex);
                } else {

                    int left = level;
                    int right = level;
                    while (true) {
                        left = left - 1;
                        right = right + 1;
                        LinkedList<Integer> indexesLeft = map.get(left);
                        LinkedList<Integer> indexesRight = map.get(right);

                        if (indexesLeft != null && !indexesLeft.isEmpty()
                                && (indexesRight == null || indexesRight.isEmpty() || indexesLeft.getFirst() < indexesRight.getFirst())) {
                            Integer secondIndex = indexesLeft.removeFirst();
                            levels[secondIndex - 1] = 0;
                            System.out.println(secondIndex);
                            break;
                        }

                        if (indexesRight != null && !indexesRight.isEmpty()) {
                            Integer secondIndex = indexesRight.removeFirst();
                            levels[secondIndex - 1] = 0;
                            System.out.println(secondIndex);
                            break;
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}