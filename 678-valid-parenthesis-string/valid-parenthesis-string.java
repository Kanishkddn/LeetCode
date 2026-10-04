class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else { // '*'
                low--;
                high++;
            }

            // Even the maximum possible number of '(' is negative.
            if (high < 0) {
                return false;
            }

            // We can choose '*' as empty, so low cannot be negative.
            low = Math.max(low, 0);
        }

        // Valid only if we can end with exactly 0 unmatched '('.
        return low == 0;
    }
}
