package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("LeetCode 88: Merge Sorted Array - Algorithm Variants")
class LeetCode0088Test {

    private static final LeetCode0088 LEET_CODE = new LeetCode0088();

    @FunctionalInterface
    interface MergeFunction {
        void merge(int[] nums1, int m, int[] nums2, int n);
    }

    private static final Map<String, MergeFunction> ALGO_VARIANTS = Map.of(
            "merge", LEET_CODE::merge,
            "merge2", LEET_CODE::merge2,
            "merge3", LEET_CODE::merge3
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}")
    @MethodSource("allCombinations")
    void test(String caseName, String algoName, int[] nums1, int m, int[] nums2, int n, int[] expected) {
        int[] nums1Copy = Arrays.copyOf(nums1, nums1.length);
        int[] nums2Copy = Arrays.copyOf(nums2, nums2.length);

        ALGO_VARIANTS.get(algoName).merge(nums1Copy, m, nums2Copy, n);

        assertArrayEquals(expected, nums1Copy, () -> "Case '%s' with algo '%s' failed. Input: nums1=%s (m=%d), nums2=%s (n=%d)"
                .formatted(caseName, algoName, Arrays.toString(nums1), m, Arrays.toString(nums2), n));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.nums1, tc.m, tc.nums2, tc.n, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("standard",
                        new int[]{1, 2, 3, 0, 0, 0}, 3,
                        new int[]{2, 5, 6}, 3,
                        new int[]{1, 2, 2, 3, 5, 6}),

                new TestCase("m_is_zero",
                        new int[]{0}, 0,
                        new int[]{1}, 1,
                        new int[]{1}),

                new TestCase("n_is_zero",
                        new int[]{1}, 1,
                        new int[]{}, 0,
                        new int[]{1}),

                new TestCase("nums1_smaller",
                        new int[]{4, 0, 0, 0, 0, 0}, 1,
                        new int[]{1, 2, 3, 5, 6}, 5,
                        new int[]{1, 2, 3, 4, 5, 6}),

                new TestCase("nums2_smaller",
                        new int[]{2, 0}, 1,
                        new int[]{1}, 1,
                        new int[]{1, 2}),

                new TestCase("all_from_nums2",
                        new int[]{0, 0, 0}, 0,
                        new int[]{1, 2, 3}, 3,
                        new int[]{1, 2, 3}),

                new TestCase("duplicates",
                        new int[]{1, 1, 1, 0, 0}, 3,
                        new int[]{1, 1}, 2,
                        new int[]{1, 1, 1, 1, 1}),

                new TestCase("nums2_all_smaller",
                        new int[]{4, 5, 6, 0, 0, 0}, 3,
                        new int[]{1, 2, 3}, 3,
                        new int[]{1, 2, 3, 4, 5, 6})
        );
    }

    private record TestCase(String name, int[] nums1, int m, int[] nums2, int n, int[] expected) {
        private TestCase(String name, int[] nums1, int m, int[] nums2, int n, int[] expected) {
            this.name = name;
            this.nums1 = nums1.clone();
            this.m = m;
            this.nums2 = nums2.clone();
            this.n = n;
            this.expected = expected.clone();
        }
    }
}