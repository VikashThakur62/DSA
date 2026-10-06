class Solution {
    public int minSwaps(String s) {
        int open = 0;
        int maxImbalance = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '[') {
                open++;

            } else {
                open--;
            }
            maxImbalance = Math.min(maxImbalance, open);

        }
        return (-maxImbalance + 1) / 2;
    }
}