package org.example.leetcode.global;

/**
 * <a href="https://leetcode.cn/problems/koko-eating-bananas">...</a>
 */
public class LeetCode0875 {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        while (left <= right) {
            int speed = left + (right - left) / 2;
            if (h >= getTime(piles, speed)) {
                right = speed - 1;
            } else {
                left = speed + 1;
            }
        }
        return left;
    }

    private long getTime(int[] piles, int speed) {
        long hours = 0;
        for(int pile : piles) {
            hours += (pile + speed - 1) / speed;
        }
        return hours;
    }

}
