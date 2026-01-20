package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 918: Maximum Sum Circular Subarray")
class LeetCode0918Test {

    @FunctionalInterface
    interface MaxSubarraySumCircularFunction {
        int apply(int[] nums);
    }

    private static final Map<String, MaxSubarraySumCircularFunction> ALGO_VARIANTS = Map.of(
            "circular_with_prefix_array", nums -> new LeetCode0918_1().maxSubarraySumCircular(nums),
            "circular_with_running_sum", nums -> new LeetCode0918_2().maxSubarraySumCircular(nums)
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, nums={2}")
    @MethodSource("allCombinations")
    void testMaxSubarraySumCircular(String caseName, String algoName, int[] nums, int expected) {
        int actual = ALGO_VARIANTS.get(algoName).apply(nums);

        assertEquals(expected, actual, () -> String.format(
                "Case '%s' with algo='%s' failed. Input: nums=%s",
                caseName, algoName, java.util.Arrays.toString(nums)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc ->
                ALGO_VARIANTS.keySet().stream()
                        .map(algo -> Arguments.of(tc.name, algo, tc.nums, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode 918
                new TestCase("example_1", new int[]{1, -2, 3, -2}, 3),

                // Example 2 from LeetCode 918
                new TestCase("example_2", new int[]{5, -3, 5}, 10),

                // Example 3 from LeetCode 918
                new TestCase("example_3", new int[]{-3, -2, -3}, -2),

                // All positive
                new TestCase("all_positive", new int[]{1, 2, 3, 4}, 10),

                // All negative
                new TestCase("all_negative", new int[]{-1, -2, -3}, -1),

                // Circular wrap-around is optimal
                new TestCase("wrap_around", new int[]{3, -1, 2, -1}, 4), // [2, -1, 3]

                // Single element
                new TestCase("single", new int[]{5}, 5),

                // Two elements
                new TestCase("two_elements", new int[]{-2, -3}, -2),

                // Mixed with zero
                new TestCase("with_zero", new int[]{0, 5, -3, 0}, 5),

                // Large circular sum
                new TestCase("verified_circular", new int[]{5, -2, 3, -1, 4}, 11)
        );
    }

    private record TestCase(String name, int[] nums, int expected) {
    }

}
