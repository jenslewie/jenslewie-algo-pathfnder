package org.example.leetcode.global;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/merge-sorted-array/">...</a>
 */
public class LeetCode0088 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        while (j >= 0) {
            nums1[j] = nums2[j];
            j--;
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] nums = new int[m + n];
        for (int i = 0, j = 0, k = 0; i < m || j < n; ) {
            if (j == n) {
                nums[k++] = nums1[i++];
            } else if (i == m) {
                nums[k++] = nums2[j++];
            } else if (nums1[i] < nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        System.arraycopy(nums, 0, nums1, 0, nums.length);
    }

    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m, j = 0; i < m + n; i++, j++) {
            nums1[i] = nums2[j];
        }
        Arrays.sort(nums1);
    }

}
