package org.example.leetcode.lcr;

/**
 * <a href="https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof">...</a>
 */
public class LeetCode0172 {

    public int countTarget(int[] scores, int target) {
        int left = leftBound(scores, target);
        if (left == scores.length || scores[left] != target) {
            return 0;
        }

        int right = leftBound(scores, target + 1) - 1;
        return right - left + 1;
    }

    private int leftBound(int[] scores, int target) {
        int left = 0, right = scores.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > scores[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}
