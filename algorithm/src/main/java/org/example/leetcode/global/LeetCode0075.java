package org.example.leetcode.global;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/sort-colors/">...</a>
 */
public class LeetCode0075 {

    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                swap(nums, i, p0);
                if (p0 < p1) {
                    swap(nums, i, p1);
                }
                p0++;
                p1++;
            } else if (nums[i] == 1) {
                swap(nums, i, p1);
                p1++;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1, i = 0;
        while (i <= p2) {
            if (nums[i] == 0) {
                swap(nums, i, p0);
                p0++;
            } else if (nums[i] == 2) {
                swap(nums, i, p2);
                p2--;
            } else {
                i++;
            }

            if (i < p0) {
                i = p0;
            }
        }
    }

    public void sortColors3(int[] nums) {
        int n = nums.length;
        int p = 0;
        for (int num = 0; num < 2; num++) {
            for (int i = p; i < n; i++) {
                if (nums[i] == num) {
                    swap(nums, i, p);
                    p++;
                }
            }
        }
    }

    public void sortColors4(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(Map.of(0, 0, 1, 0, 2, 0));
        for (int num : nums) {
            map.put(num, map.get(num) + 1);
        }
        int i = 0;
        for (int key : new int[] {0, 1, 2}) {
            int j = 0;
            while (map.get(key) > j) {
                nums[i++] = key;
                j++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
