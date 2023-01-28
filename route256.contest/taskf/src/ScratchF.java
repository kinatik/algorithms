import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.*;

public class ScratchF {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(reader.readLine());
            boolean yes = true;
            Segment[] segments = new Segment[n];
            for (int j = 0; j < n; j++) {
                String[] time = reader.readLine().split("[:\\-]");
                if (yes) {
                    int lh = Integer.parseInt(time[0]);
                    int lm = Integer.parseInt(time[1]);
                    int ls = Integer.parseInt(time[2]);
                    int rh = Integer.parseInt(time[3]);
                    int rm = Integer.parseInt(time[4]);
                    int rs = Integer.parseInt(time[5]);
                    if (lh > 23 || rh > 23 || lm > 59 || ls > 59 || rm > 59 || rs > 59) {
                        yes = false;
                        continue;
                    }

                    LocalTime left = LocalTime.of(lh, lm, ls);
                    LocalTime right = LocalTime.of(rh, rm, rs);
                    if (left.isAfter(right)) {
                        yes = false;
                        continue;
                    }
                    segments[j] = new Segment(left, right);
                }
            }
            if (n > 1 && yes) {
                Arrays.sort(segments);
                for (int j = 0; j < n - 1; j++) {
                    if (segments[j].right.equals(segments[j + 1].left) || segments[j].right.isAfter(segments[j + 1].left)) {
                        yes = false;
                        break;
                    }
                }
            }

            System.out.println(yes ? "YES" : "NO");
        }
    }

    private record Segment(LocalTime left, LocalTime right) implements Comparable<Segment> {

        @Override
            public int compareTo(Segment other) {
                return this.left.compareTo(other.left);
            }
        }
}