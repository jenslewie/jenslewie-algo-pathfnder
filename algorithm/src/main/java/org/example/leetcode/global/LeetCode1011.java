package org.example.leetcode.global;

public class LeetCode1011 {

    public int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0;
        for (int weight : weights) {
            low = Math.max(weight, low);
            high += weight;
        }

        while (low < high) {
            int capacity = low + (high - low) / 2;
            if (days >= getDays(weights, capacity)) {
                high = capacity;
            } else {
                low = capacity + 1;
            }
        }

        return low;
    }

    private int getDays(int[] weights, int capacity) {
        int days = 0;
        int temp = 0;
        for (int weight : weights) {
            if (temp + weight > capacity) {
                days++;
                temp = weight;
            } else {
                temp += weight;
            }
        }
        return days + 1;
    }

}
