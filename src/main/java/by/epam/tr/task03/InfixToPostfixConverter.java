package by.epam.tr.task03;

import java.util.Stack;

class InfixToPostfixConverter {

    private static boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private static int getPrecedence(char ch) {
        if (ch == '+' || ch == '-')
            return 1;
        else if (ch == '*' || ch == '/')
            return 2;
        else
            return -1;
    }

    public static String infixToRpn(String expression) {
        Stack<Character> stack = new Stack<>();
        String output = "";
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (isDigit(c)) {
                output += c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty()
                        && stack.peek() != '(')
                    output += stack.pop();
                stack.pop();
            } else {
                while (
                        !stack.isEmpty()
                                && getPrecedence(c)
                                <= getPrecedence(stack.peek())) {
                    output += stack.pop();
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "This expression is invalid";
            }
            output += stack.pop();
        }
        return output;
    }
}
