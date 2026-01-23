package org.example.leetcode.global;

/**
 * <a href="https://leetcode.cn/problems/car-fleet">...</a>
 */
public class LeetCode0853_3 {

    public int carFleet(int target, int[] position, int[] speed) {
        double[] times = new double[target];
        for (int i = 0; i < position.length; i++) {
            times[position[i]] = (double) (target - position[i]) / speed[i];
        }

        int ans = 0;
        double maxTime = 0;
        for (int i = times.length - 1; i >= 0; i--) {
            if (times[i] != 0 && times[i] > maxTime) {
                ans++;
                maxTime = times[i];
            }
        }
        return ans;
    }

}
