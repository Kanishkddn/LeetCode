class Solution {
    private static final long MOD = 1_000_000_007L;

    private int n;
    private long[] sum, sq, lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> last = new java.util.HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int p = last.getOrDefault(x, -1);

            update(1, 0, n - 1, p + 1, i);
            last.put(x, i);

            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }

    private void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;
        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int l, int r, long v) {
        long len = r - l + 1L;

        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                    + len % MOD * v % MOD * v % MOD) % MOD;

        sum[node] = (sum[node] + len % MOD * v % MOD) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = (l + r) / 2;
        long v = lazy[node];

        apply(node * 2, l, mid, v);
        apply(node * 2 + 1, mid + 1, r, v);

        lazy[node] = 0;
    }
}