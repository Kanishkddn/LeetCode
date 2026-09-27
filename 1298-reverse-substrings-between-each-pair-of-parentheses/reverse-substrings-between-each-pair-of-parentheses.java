import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        String current = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(current);
                current = "";
            } 
            else if (c == ')') {
                current = new StringBuilder(current).reverse().toString();
                current = stack.pop() + current;
            } 
            else {
                current += c;
            }
        }

        return current;
    }
}