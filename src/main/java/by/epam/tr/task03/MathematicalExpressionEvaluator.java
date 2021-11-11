package by.epam.tr.task03;

import java.util.Scanner;

public class MathematicalExpressionEvaluator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        String rpnLine = InfixToPostfixConverter.infixToRpn(line);

        String[] tokens = rpnLine.split("\\.*");

        int result = Eval.evalRpn(tokens);
        System.out.println(result);

    }
}