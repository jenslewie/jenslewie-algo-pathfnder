package org.example.leetcode.global;

import java.util.Stack;

public class LeetCode0388 {

    public int lengthLongestPath(String input) {
        int n = input.length();
        int pos = 0, ans = 0;
        int[] level = new int[n + 1];

        while (pos < n) {
            int depth = 1;
            while (pos < n && input.charAt(pos) == '\t') {
                pos++;
                depth++;
            }

            boolean isFile = false;
            int len = 0;
            while (pos < n && input.charAt(pos) != '\n') {
                if (input.charAt(pos) == '.') {
                    isFile = true;
                }
                pos++;
                len++;
            }

            pos++;
            if (depth > 1) {
                len += level[depth - 1] + 1;
            }
            if (isFile) {
                ans = Math.max(ans, len);
            } else {
                level[depth] = len;
            }
        }

        return ans;
    }

    public int lengthLongestPath2(String input) {
        int n = input.length();
        int pos = 0, ans = 0;
        Stack<Integer> stack = new Stack<>();

        while (pos < n) {
            int depth = 1;
            while (pos < n && input.charAt(pos) == '\t') {
                pos++;
                depth++;
            }

            boolean isFile = false;
            int len = 0;
            while (pos < n && input.charAt(pos) != '\n') {
                if (input.charAt(pos) == '.') {
                    isFile = true;
                }
                pos++;
                len++;
            }

            pos++;
            while (stack.size() >= depth) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                len += stack.peek() + 1;
            }
            if (isFile) {
                ans = Math.max(ans, len);
            } else {
                stack.push(len);
            }
        }

        return ans;
    }

    public int lengthLongestPath3(String input) {
        int maxLen = 0;
        Stack<String> stack = new Stack<>();
        for (String item : input.split("\n")) {
            int level = item.lastIndexOf("\t") + 1;
            while (level < stack.size()) {
                stack.pop();
            }
            stack.add(item.substring(level));
            if (item.contains(".")) {
                int sum = stack.stream().mapToInt(String::length).sum();
                sum += stack.size() - 1;
                maxLen = Math.max(maxLen, sum);
            }
        }
        return maxLen;
    }

}
