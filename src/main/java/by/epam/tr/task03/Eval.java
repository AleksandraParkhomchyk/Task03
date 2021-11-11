package by.epam.tr.task03;

import java.util.Stack;

class Eval {

    public static int evalRpn(String[] tokens) {
        int returnValue;
        String operators = "+-*/";

        Stack<String> stack = new Stack<>();

        for (String t : tokens) {
            if (!operators.contains(t)) {
                stack.push(t);
            } else {
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                switch (t) {
                    case "+" -> stack.push(String.valueOf(a + b));
                    case "-" -> stack.push(String.valueOf(b - a));
                    case "*" -> stack.push(String.valueOf(a * b));
                    case "/" -> stack.push(String.valueOf(b / a));
                }
            }
        }
        returnValue = Integer.parseInt(stack.pop());
        return returnValue;
    }
}
