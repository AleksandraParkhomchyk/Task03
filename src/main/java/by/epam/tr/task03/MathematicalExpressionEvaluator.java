package by.epam.tr.task03;

import java.util.Stack;

public class MathematicalExpressionEvaluator {

    static public double calculate(String input) {
        String output = infixToRpn(input);
        return counting(output);
    }

    private static boolean letterOrDigit(char c) {
        return Character.isLetterOrDigit(c);
    }

    private static int getPrecedence(char ch) {
        if (ch == '+' || ch == '-')
            return 1;
        else if (ch == '*' || ch == '/')
            return 2;
        else if (ch == '^')
            return 3;
        else
            return -1;
    }

    private static String infixToRpn(String expression) {
        Stack<Character> stack = new Stack<>();
        String output = ("");
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (letterOrDigit(c))
                output += c;
            else if (c == '(')
                stack.push(c);
            else if (c == ')') {
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
            if (stack.peek() == '(')
                return "This expression is invalid";
            output += stack.pop();
        }
        return output;
    }

    private static double counting(String output) {
        double result;
        String operators = "+-*/";
        Stack<String> stack1 = new Stack<>();
        String[] tokens = output.split("\\.*");

        for (String t : tokens) {
            if (!operators.contains(t)) {
                stack1.push(t);
            } else {
                int a = Integer.parseInt(stack1.pop());
                int b = Integer.parseInt(stack1.pop());
                switch (t) {
                    case "+" -> stack1.push(String.valueOf((a + b)));
                    case "-" -> stack1.push(String.valueOf((b - a)));
                    case "*" -> stack1.push(String.valueOf((a * b)));
                    case "/" -> stack1.push(String.valueOf((b / a)));
                }
            }
        }
        result = Double.parseDouble(stack1.pop());
        return result;
    }
}




