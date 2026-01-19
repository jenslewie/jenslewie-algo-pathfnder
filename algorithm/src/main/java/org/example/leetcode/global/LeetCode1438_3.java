package org.example.leetcode.global;

/**
 * <a href="https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit">...</a>
 */
public class LeetCode1438_3 {

    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int[] maxQueue = new int[n];
        int[] minQueue = new int[n];
        int maxLeft = 0, maxRight = -1;
        int minLeft = 0, minRight = -1;
        int left = 0, ans = 0;

        for (int i = 0; i < n; i++) {
            while (maxRight >= maxLeft && maxQueue[maxRight] < nums[i]) {
                maxRight--;
            }
            maxQueue[++maxRight] = nums[i];
            while (minRight >= minLeft && minQueue[minRight] > nums[i]) {
                minRight--;
            }
            minQueue[++minRight] = nums[i];

            if (maxQueue[maxLeft] - minQueue[minLeft] > limit) {
                if (nums[left] == maxQueue[maxLeft]) {
                    maxLeft++;
                }
                if (nums[left] == minQueue[minLeft]) {
                    minLeft++;
                }
                left++;
            }

            ans = Math.max(ans, i - left + 1);
        }

        return ans;
    }

}
