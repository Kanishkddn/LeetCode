class Solution {
    public int minAddToMakeValid(String s) {
        int balance = 0;
        int insertions = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else {
                if (balance > 0) {
                    balance--;
                } else {
                    insertions++;
                }
            }
        }

        return insertions + balance;
    }
}
