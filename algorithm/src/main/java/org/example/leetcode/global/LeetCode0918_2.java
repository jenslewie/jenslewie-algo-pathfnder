package org.example.leetcode.global;

import java.util.ArrayDeque;

/**
 * <a href="https://leetcode.cn/problems/maximum-sum-circular-subarray">...</a>
 */
public class LeetCode0918_2 {

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        var queue = new ArrayDeque<int[]>();
        int ans = Integer.MIN_VALUE;
        int preSum = 0;
        for (int i = 0; i < 2 * n; i++) {
            preSum += nums[i % n];
            if (!queue.isEmpty()) {
                ans = Math.max(ans, preSum - queue.peekFirst()[1]);
            }
            while (!queue.isEmpty() && preSum < queue.peekLast()[1]) {
                queue.pollLast();
            }
            if (!queue.isEmpty() && i - queue.peekFirst()[0] == n) {
                queue.pollFirst();
            }
            queue.offerLast(new int[]{i, preSum});
        }
        return ans;
    }

}
