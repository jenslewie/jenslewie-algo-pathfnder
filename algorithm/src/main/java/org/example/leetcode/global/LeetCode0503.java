package org.example.leetcode.global;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/next-greater-element-ii">...</a>
 */
public class LeetCode0503 {

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        for (int i = 0; i < len * 2; i++) {
            int j = i % len;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[j]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = nums[j];
            }
            if (i < len) {
                stack.push(i);
            }
        }
        return ans;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int len = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] ans = new int[len];
        for (int i = 2 * len - 1; i >= 0; i--) {
            int j = i % len;
            while (!stack.isEmpty() && stack.peek() <= nums[j]) {
                stack.pop();
            }
            ans[j] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[j]);
        }
        return ans;
    }

}
