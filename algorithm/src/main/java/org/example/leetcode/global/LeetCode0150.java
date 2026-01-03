package org.example.leetcode.global;

import java.util.Stack;

public class LeetCode0150 {

    public int evalRPN(String[] tokens) {
        int[] stack = new int[(tokens.length + 1) / 2];
        int top = -1;
        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                int a = stack[top--];
                switch (token) {
                    case "+":
                        stack[top] += a;
                        break;
                    case "-":
                        stack[top] -= a;
                        break;
                    case "*":
                        stack[top] *= a;
                        break;
                    case "/":
                        stack[top] /= a;
                        break;
                }
            } else {
                stack[++top] = Integer.parseInt(token);
            }
        }
        return stack[top];
    }

    public int evalRPN2(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                int a = stack.pop(), b = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(b - a);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(b / a);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public int evalRPN3(String[] tokens) {
        String lastToken = tokens[tokens.length - 1];
        if (!"+".equals(lastToken) && !"-".equals(lastToken) && !"*".equals(lastToken) && !"/".equals(lastToken)) {
            return Integer.parseInt(lastToken);
        }

        int result = -201;
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if ("+".equals(token)) {
                result = Integer.parseInt(stack.pop());
                result += Integer.parseInt(stack.pop());
                stack.push(String.valueOf(result));
            } else if ("-".equals(token)) {
                result = Integer.parseInt(stack.pop());
                result = Integer.parseInt(stack.pop()) - result;
                stack.push(String.valueOf(result));
            } else if ("*".equals(token)) {
                result = Integer.parseInt(stack.pop());
                result *= Integer.parseInt(stack.pop());
                stack.push(String.valueOf(result));
            } else if ("/".equals(token)) {
                result = Integer.parseInt(stack.pop());
                result = Integer.parseInt(stack.pop()) / result;
                stack.push(String.valueOf(result));
            } else {
                stack.push(token);
            }
        }

        return result;
    }
}
