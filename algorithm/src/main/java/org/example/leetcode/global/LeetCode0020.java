package org.example.leetcode.global;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/valid-parentheses">...</a>
 */
public class LeetCode0020 {

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && leftOf(c) == stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    char leftOf(char c) {
        if (c == '}') return '{';
        if (c == ')') return '(';
        return '[';
    }

    public boolean isValid3(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String c = s.substring(i, i + 1);
            if ("(".equals(c) || "[".equals(c) || "{".equals(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && (("(".equals(stack.peek()) && ")".equals(c)) ||
                        ("[".equals(stack.peek()) && "]".equals(c)) ||
                        ("{".equals(stack.peek()) && "}".equals(c)))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
