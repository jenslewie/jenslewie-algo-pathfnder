package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 1438: Longest Continuous Subarray With Absolute Diff <= Limit")
class LeetCode1438Test {

    @FunctionalInterface
    interface LongestSubarrayFunction {
        int apply(int[] nums, int limit);
    }

    private static final Map<String, LongestSubarrayFunction> ALGO_VARIANTS = Map.of(
            "monotonic_queue_wrapper", (nums, limit) -> new LeetCode1438_1().longestSubarray(nums, limit),
            "dual_monotonic_queues", (nums, limit) -> new LeetCode1438_2().longestSubarray(nums, limit),
            "array", (nums, limit) -> new LeetCode1438_3().longestSubarray(nums, limit)
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, nums={2}, limit={3}")
    @MethodSource("allCombinations")
    void testLongestSubarray(String caseName, String algoName, int[] nums, int limit, int expected) {
        int actual = ALGO_VARIANTS.get(algoName).apply(nums, limit);

        assertEquals(expected, actual, () -> String.format(
                "Case '%s' with algo='%s' failed. Input: nums=%s, limit=%d",
                caseName, algoName, java.util.Arrays.toString(nums), limit));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.nums, tc.limit, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode 1438
                new TestCase("example_1", new int[]{8, 2, 4, 7}, 4, 2),

                // Example 2 from LeetCode 1438
                new TestCase("example_2", new int[]{10, 1, 2, 4, 7, 2}, 5, 4),

                // Example 3 from LeetCode 1438
                new TestCase("example_3", new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0, 3),

                // Single element
                new TestCase("single", new int[]{5}, 0, 1),

                // All equal
                new TestCase("all_equal", new int[]{3, 3, 3, 3}, 0, 4),

                // Strictly increasing with large limit
                new TestCase("increasing_large_limit", new int[]{1, 2, 3, 4, 5}, 10, 5),

                // Strictly increasing with small limit
                new TestCase("increasing_small_limit", new int[]{1, 2, 3, 4, 5}, 1, 2),

                // Alternating high-low
                new TestCase("alternating", new int[]{1, 10, 1, 10, 1}, 9, 5),

                // Large numbers
                new TestCase("large_numbers", new int[]{1000000000, 1000000000}, 0, 2),

                // Edge: limit = 0 → only consecutive equal elements
                new TestCase("limit_zero", new int[]{1, 2, 1, 2, 1}, 0, 1),

                // Entire array valid
                new TestCase("entire_valid", new int[]{1, 3, 2, 2, 3}, 2, 5)
        );
    }

    private record TestCase(String name, int[] nums, int limit, int expected) {
    }

}
