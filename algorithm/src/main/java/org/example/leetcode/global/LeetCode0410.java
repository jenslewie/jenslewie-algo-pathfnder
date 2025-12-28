package org.example.leetcode.global;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://leetcode.cn/problems/split-array-largest-sum">...</a>
 */
public class LeetCode0410 {

    public int splitArray(int[] nums, int k) {
        int low = 0, high = 0;
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            System.out.println("low = " + low + ", high = " + high + ", mid = " + mid);
            if (k >= get(nums, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int get(int nums[], int mid) {
        int result = 0, temp = 0;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        for (int num : nums) {
            if (temp + num > mid) {
                temp = num;
                result++;
                list.add(subList);
                subList = new ArrayList<>();
                subList.add(temp);
            } else {
                temp += num;
                subList.add(num);
            }
        }
        list.add(subList);
        System.out.println(list);
        return result + 1;
    }

    public int splitArray2(int[] nums, int k) {
        int mx = 0;
        int sum = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
            sum += x;
        }

        int low = mx - 1;
        int high = sum;
        while (low + 1 < high) {
            int mid = (low + high) >>> 1;
            System.out.println("low = " + low + ", high = " + high + ", mid = " + mid);
            if (check(nums, k, mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return high;
    }

    private boolean check(int[] nums, int k, int mx) {
        int cnt = 1;
        int s = 0;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        for (int x : nums) {
            if (s + x <= mx) {
                s += x;
                subList.add(x);
                continue;
            }
            if (cnt == k) {
                System.out.println(list);
                return false;
            }
            cnt++;
            s = x;
            list.add(subList);
            subList = new ArrayList<>();
            subList.add(x);
        }
        list.add(subList);
        System.out.println(list);
        return true;
    }

}
