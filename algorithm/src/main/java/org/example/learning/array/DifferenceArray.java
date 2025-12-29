package org.example.learning.array;

import java.util.Arrays;

public class DifferenceArray {

    private final int[] diff;

    public DifferenceArray(int[] nums) {
        assert nums != null;
        assert nums.length > 0;

        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i-1];
        }
    }

    public void increment(int from, int to, int value) {
        assert from <= to;
        assert from >= 0;

        diff[from] += value;
        if (to + 1 < diff.length) {
            diff[to + 1] -= value;
        }
        System.out.println("[" + from + "," + to + "] value increases: " + value);
    }

    public int[] result() {
        int[] result = new int[diff.length];
        result[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            result[i] = result[i - 1] + diff[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Difference Array Practice");
        int[] nums = new int[]{8,2,6,3,1};
        System.out.println("nums:\t" + Arrays.toString(nums));

        DifferenceArray differenceArray = new DifferenceArray(nums);
        System.out.println("diff:\t" + Arrays.toString(differenceArray.diff));
        System.out.println("result:\t" + Arrays.toString(differenceArray.result()));
        System.out.println();

        differenceArray.increment(1, 3, 3);
        System.out.println("diff:\t" + Arrays.toString(differenceArray.diff));
        System.out.println("result:\t" + Arrays.toString(differenceArray.result()));
        System.out.println();

        differenceArray.increment(2, 4, -2);
        System.out.println("diff:\t" + Arrays.toString(differenceArray.diff));
        System.out.println("result:\t" + Arrays.toString(differenceArray.result()));
        System.out.println();
    }

}
