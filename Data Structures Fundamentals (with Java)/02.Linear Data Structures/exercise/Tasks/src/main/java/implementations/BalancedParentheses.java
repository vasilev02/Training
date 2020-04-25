package implementations;

import interfaces.Solvable;

import java.util.Arrays;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {

        java.util.ArrayDeque<String> deque = new java.util.ArrayDeque<String>();

        String[] split = this.parentheses.split("");

        if (split.length % 2 != 0) {
            return false;
        }

        for (String current : split) {

            if (current.equals("(") || current.equals("{") || current.equals("[")) {

                deque.push(current);

            } else if (current.equals(")") || current.equals("}") || current.equals("]")) {

                if (deque.peek().equals("(") && current.equals(")")) {
                    deque.pop();
                } else if (deque.peek().equals("{") && current.equals("}")) {
                    deque.pop();
                } else if (deque.peek().equals("[") && current.equals("]")) {
                    deque.pop();
                } else {
                    return false;
                }

            }

        }


        return true;
    }
}
