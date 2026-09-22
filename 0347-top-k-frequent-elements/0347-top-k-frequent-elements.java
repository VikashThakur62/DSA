import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Frequency count
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Min Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> map.get(a) - map.get(b)
        );

        for (int num : map.keySet()) {
            pq.add(num);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] ans = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            ans[i] = pq.poll();
        }

        return ans;
    }
}