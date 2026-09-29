class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Path length is (m + n - 1). A valid string must have an even length.
        if ((m + n - 1) % 2 != 0) return false;
        // Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        int maxBalance = (m + n) / 2;
        memo = new Boolean[m][n][maxBalance + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance) {
        balance += (grid[r][c] == '(' ? 1 : -1);

        // If balance drops below 0, it's invalid
        if (balance < 0) return false;

        // If the balance exceeds the remaining steps to reach (m - 1, n - 1),
        // we can never balance it back to 0
        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        if (balance > remainingSteps) return false;

        // Reached the destination
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean res = false;
        // Move Down
        if (r + 1 < m) {
            res = dfs(grid, r + 1, c, balance);
        }
        // Move Right
        if (!res && c + 1 < n) {
            res = dfs(grid, r, c + 1, balance);
        }

        return memo[r][c][balance] = res;
    }
}