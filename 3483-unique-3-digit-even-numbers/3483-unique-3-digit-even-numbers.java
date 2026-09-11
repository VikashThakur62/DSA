class Solution {
    public int totalNumbers(int[] digits) {
        boolean[] used = new boolean[10];
        int count = 0;

        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                for (int k = 0; k < digits.length; k++) {

                    if (i == j || j == k || i == k)
                        continue;

                    if (digits[i] == 0 || digits[k] % 2 != 0)
                        continue;

                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];

                    if (!used[num / 1000]) {
                        // handled below
                    }
                }
            }
        }

        java.util.HashSet<Integer> set = new java.util.HashSet<>();

        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                for (int k = 0; k < digits.length; k++) {
                    if (i == j || i == k || j == k)
                        continue;

                    if (digits[i] == 0 || digits[k] % 2 != 0)
                        continue;

                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    set.add(num);
                }
            }
        }

        return set.size();
    }
}