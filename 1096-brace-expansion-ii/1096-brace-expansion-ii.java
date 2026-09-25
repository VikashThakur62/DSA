import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length() - 1);
        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);
        return ans;
    }

    private Set<String> parse(String s, int l, int r) {
        Set<String> result = new HashSet<>();

        // Split by top-level comma
        int balance = 0;
        int start = l;

        for (int i = l; i <= r; i++) {
            if (s.charAt(i) == '{') {
                balance++;
            } else if (s.charAt(i) == '}') {
                balance--;
            } else if (s.charAt(i) == ',' && balance == 0) {
                result.addAll(parse(s, start, i - 1));
                start = i + 1;
            }
        }

        if (start != l) {
            result.addAll(parse(s, start, r));
            return result;
        }

        // No top-level comma: handle concatenation
        Set<String> current = new HashSet<>();
        current.add("");

        int i = l;

        while (i <= r) {

            Set<String> part;

            if (s.charAt(i) == '{') {
                int count = 1;
                int j = i + 1;

                while (count > 0) {
                    if (s.charAt(j) == '{') count++;
                    if (s.charAt(j) == '}') count--;
                    j++;
                }

                // j - 2 is the closing brace
                part = parse(s, i + 1, j - 2);
                i = j;

            } else {
                part = new HashSet<>();
                part.add(String.valueOf(s.charAt(i)));
                i++;
            }

            // Cartesian product for concatenation
            Set<String> next = new HashSet<>();

            for (String a : current) {
                for (String b : part) {
                    next.add(a + b);
                }
            }

            current = next;
        }

        return current;
    }
}