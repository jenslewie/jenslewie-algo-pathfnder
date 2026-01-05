package org.example.leetcode.global;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/min-stack">...</a>
 */
public class LeetCode0155 {

    Stack<Integer> stack;
    Stack<Integer> minStack;
    Stack<int[]> stack2;
    Map<Integer, Integer> map;

    public LeetCode0155() {
        stack = new Stack<>();
        minStack = new Stack<>();
        stack2 = new Stack<>();
        stack2.push(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE});
        map = new HashMap<>();
    }

    public static void main(String[] args) {
        LeetCode0155 leetCode = new LeetCode0155();
        leetCode.push(-2);
        leetCode.push(0);
        leetCode.push(-3);
        leetCode.getMin();
    }

    public void push(int val) {
        stack2.push(new int[]{val, Math.min(stack2.peek()[1], val)});
    }

    public void pop() {
        stack2.pop();
    }

    public int top() {
        return stack2.peek()[0];
    }

    public int getMin() {
        return stack2.peek()[1];
    }

    public void push2(int val) {
        stack.push(val);
        if (minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
    }

    public void pop2() {
        if (minStack.peek().equals(stack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top2() {
        return stack.peek();
    }

    public int getMin2() {
        return minStack.peek();
    }

    public void push3(int val) {
        stack.push(val);
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    public void pop3() {
        int val = stack.pop();
        map.put(val, map.get(val) - 1);
    }

    public int top3() {
        return stack.peek();
    }

    public int getMin3() {
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                min = Math.min(min, entry.getKey());
            }
        }
        return min;
    }

}
