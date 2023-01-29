import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ScratchG {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        String[] mn = reader.readLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);

        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int j = 0; j < n; j++) {
            String s1 = reader.readLine();
            String[] s = s1.split(" ");
            Integer left = Integer.parseInt(s[0]);
            Integer right = Integer.parseInt(s[1]);
            map.computeIfAbsent(left, k -> new HashSet<>());
            map.computeIfAbsent(right, k -> new HashSet<>());
            map.get(left).add(right);
            map.get(right).add(left);
        }

        for (int i = 0; i < m; i++) {
            int current = i + 1;

            Set<Integer> currentFriends = map.get(current);

            TreeSet<Integer> result = new TreeSet<>();
            if (currentFriends == null) {
                result.add(0);
                print(result);
                continue;
            }

            ArrayList<Suggest> suggests = new ArrayList<>();

            Set<Integer> toScan = new HashSet<>();
            for (Integer currentFriend : currentFriends) {
                toScan.addAll(map.get(currentFriend));
            }

            toScan.remove(current);

            for (Integer scan : toScan) {
                if (!currentFriends.contains(scan)) {
                    TreeSet<Integer> mayBeSuggested = new TreeSet<>(map.get(scan));
                    mayBeSuggested.retainAll(currentFriends);
                    suggests.add(new Suggest(scan, mayBeSuggested));
                }
            }

            Collections.sort(suggests);

            boolean hasSuggest = false;
            if (suggests.size() > 0) {
                int maxSize = suggests.get(suggests.size() - 1).friends.size();
                for (Suggest suggest : suggests) {
                    int s = suggest.friends.size();

                    if (currentFriends.contains(suggest.friend)) {
                        continue;
                    }

                    if (maxSize == s) {
                        hasSuggest = true;
                        result.add(suggest.friend);
                    }
                }
            }
            if (!hasSuggest) {
                result.add(0);
            }

            print(result);
        }
    }

    private static void print(Set<Integer> result) {
        StringBuilder ans = new StringBuilder();

        for (Integer integer : result) {
            ans.append(integer);
            ans.append(" ");
        }
        System.out.println(ans.substring(0, ans.length() - 1));
    }

    private record Suggest(Integer friend, Set<Integer> friends) implements Comparable<Suggest> {
        @Override
        public int compareTo(Suggest o) {
            int thisSize = this.friends.size();
            int otherSize = o.friends.size();
            return Integer.compare(thisSize, otherSize);
        }

    }
}


