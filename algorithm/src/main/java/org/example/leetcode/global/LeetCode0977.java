package org.example.leetcode.global;

/**
 * <a href="https://leetcode.cn/problems/squares-of-a-sorted-array">...</a>
 */
public class LeetCode0977 {

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1, p = n - 1;
        while (left <= right) {
            if (Math.abs(nums[right]) > Math.abs(nums[left])) {
                result[p--] = nums[right] * nums[right];
                right--;
            } else {
                result[p--] = nums[left] * nums[left];
                left++;
            }
        }
        return result;
    }

    public int[] sortedSquares2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int right = 0;
        while (right < n && nums[right] < 0) {
            right++;
        }
        int left = right - 1;
        for (int i = 0; i < n; i++) {
            if (left < 0) {
                result[i] = nums[right] * nums[right];
                right++;
            } else if (right > n - 1) {
                result[i] = nums[left] * nums[left];
                left--;
            } else if (Math.abs(nums[right]) > Math.abs(nums[left])) {
                result[i] = nums[left] * nums[left];
                left--;
            } else {
                result[i] = nums[right] * nums[right];
                right++;
            }
        }
        return result;
    }

}
