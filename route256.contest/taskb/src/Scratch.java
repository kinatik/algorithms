import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Scratch {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(reader.readLine());
            String[] str = reader.readLine().trim().split(" ");
            HashMap<Integer, Integer> map = new HashMap<>();
            int sum = 0;
            for (int j = 0; j < k; j++) {
                Integer val = Integer.parseInt(str[j]);
                map.putIfAbsent(val, 0);
                map.put(val, map.get(val) + 1);
                sum = sum + (map.get(val) % 3 == 0 ? 0 : val);
            }
            System.out.println(sum);
        }
    }

}