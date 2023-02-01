import java.io.*;
import java.util.*;

public class ScratchI {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        String[] powers = reader.readLine().split(" ");
        Set<Process> loading = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int power = Integer.parseInt(powers[i]);
            loading.add(new Process(0, 0, power));
        }
        Set<Integer> times = new TreeSet<>();
        Map<Integer, Integer> tasks = new HashMap<>();
        for (int j = 0; j < m; j++) {
            String[] taskString = reader.readLine().split(" ");
            int time = Integer.parseInt(taskString[0]);
            int duration = Integer.parseInt(taskString[1]);
            times.add(time - 1);
            times.add(time + duration - 1);
            tasks.put(time, duration);
        }


        HashMap<Integer, Set<Process>> processes = new HashMap<>();
        long result = 0;


        Integer time = times.stream().findFirst().orElse(null);
        while (time != null) {

            int current = time + 1;
            Set<Process> remove = processes.remove(current);
            if (remove != null) {
                for (Process process : remove) {
                    loading.remove(process);
                    process.start = 0;
                    process.time = 0;
                    loading.add(process);
                }
            }

            Integer taskTime = tasks.remove(current);
            if (taskTime != null) {
                Process process = loading.stream().findFirst().get();

                if (process.start == 0 && process.time == 0) {
                    loading.remove(process);
                    process.time = taskTime;
                    process.start = time;
                    processes.computeIfAbsent(time + taskTime + 1, k -> new HashSet<>());
                    processes.get(time + taskTime + 1).add(process);
                    times.add(time + taskTime);
                    loading.add(process);
                    long res = (long) process.power * taskTime;
                    result = result + res;
                }

            }

            time = times.stream().findFirst().orElse(null);
            if (time != null) {
                times.remove(time);
            }
        }

        System.out.println(result);
    }

    private static class Process implements Comparable<Process> {

        private int start;
        private int time;
        private final int power;

        public Process(int start, int time, int power) {
            this.start = start;
            this.time = time;
            this.power = power;
        }

        @Override
        public int compareTo(Process o) {
            int compare = Integer.compare(start + time, o.start + o.time);
            if (compare == 0) {
                compare = Integer.compare(power, o.power);
            }
            return compare;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            Process process = (Process) o;
            return power == process.power;
        }

        @Override
        public int hashCode() {
            return power;
        }
    }
}