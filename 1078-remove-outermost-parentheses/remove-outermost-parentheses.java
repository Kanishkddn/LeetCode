class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Not an outermost '('
                if (balance > 0) {
                    result.append(c);
                }
                balance++;
            } else {
                balance--;

                // Not an outermost ')'
                if (balance > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}