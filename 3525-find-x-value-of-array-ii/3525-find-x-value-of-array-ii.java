class Solution {

    static class Node {
        int prod;
        int[] cnt = new int[5];

        Node() {
            prod = 1;
        }
    }

    int k;
    Node[] tree;

    Node merge(Node left, Node right) {
        Node res = new Node();

        res.prod = (left.prod * right.prod) % k;

        for (int i = 0; i < k; i++) {
            res.cnt[i] = left.cnt[i];
        }

        for (int i = 0; i < k; i++) {
            int newRem = (left.prod * i) % k;
            res.cnt[newRem] += right.cnt[i];
        }

        return res;
    }

    Node makeNode(int value) {
        Node node = new Node();

        value %= k;

        node.prod = value;
        node.cnt[value] = 1;

        return node;
    }

    void build(int[] nums, int index, int left, int right) {

        if (left == right) {
            tree[index] = makeNode(nums[left]);
            return;
        }

        int mid = (left + right) / 2;

        build(nums, index * 2, left, mid);
        build(nums, index * 2 + 1, mid + 1, right);

        tree[index] = merge(
            tree[index * 2],
            tree[index * 2 + 1]
        );
    }

    void update(int index, int left, int right,
                int pos, int value) {

        if (left == right) {
            tree[index] = makeNode(value);
            return;
        }

        int mid = (left + right) / 2;

        if (pos <= mid) {
            update(index * 2, left, mid, pos, value);
        } else {
            update(index * 2 + 1, mid + 1, right, pos, value);
        }

        tree[index] = merge(
            tree[index * 2],
            tree[index * 2 + 1]
        );
    }

    Node query(int index, int left, int right,
               int ql, int qr) {

        if (ql <= left && right <= qr) {
            return tree[index];
        }

        int mid = (left + right) / 2;

        if (qr <= mid) {
            return query(index * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(index * 2 + 1, mid + 1, right, ql, qr);
        }

        Node leftNode =
            query(index * 2, left, mid, ql, qr);

        Node rightNode =
            query(index * 2 + 1, mid + 1, right, ql, qr);

        return merge(leftNode, rightNode);
    }

    public int[] resultArray(int[] nums, int k,
                             int[][] queries) {

        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        build(nums, 1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update persists for future queries
            update(1, 0, n - 1, index, value);

            // Query [start, n-1]
            Node result =
                query(1, 0, n - 1, start, n - 1);

            ans[i] = result.cnt[x];
        }

        return ans;
    }
}