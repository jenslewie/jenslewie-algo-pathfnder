package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 303: Range Sum Query - Immutable")
class LeetCode0303Test {

    private static final Function<int[], LeetCode0303> CONSTRUCTOR = LeetCode0303::new;

    @FunctionalInterface
    interface SumRangeFunction {
        int apply(LeetCode0303 instance, int left, int right);
    }

    private static final Map<String, SumRangeFunction> ALGO_VARIANTS = Map.of(
            "prefix_sum", LeetCode0303::sumRange
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, nums={2}, range=[{3}, {4}]")
    @MethodSource("allCombinations")
    void testSumRange(String caseName, String algoName, int[] nums, int left, int right, int expected) {
        LeetCode0303 instance = CONSTRUCTOR.apply(nums);
        int actual = ALGO_VARIANTS.get(algoName).apply(instance, left, right);

        assertEquals(expected, actual, () -> "Case '%s' with algo '%s' failed. nums=%s, range=[%d, %d]"
                .formatted(caseName, algoName, java.util.Arrays.toString(nums), left, right));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.nums, tc.left, tc.right, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example from LeetCode
                new TestCase("example",
                        new int[]{-2, 0, 3, -5, 2, -1}, 0, 2, 1),

                // Full array
                new TestCase("full_range",
                        new int[]{1, 2, 3, 4, 5}, 0, 4, 15),

                // Single element
                new TestCase("single_element",
                        new int[]{10, -5, 3}, 1, 1, -5),

                // Zero-length range (single element)
                new TestCase("zero_length",
                        new int[]{100}, 0, 0, 100),

                // Negative numbers
                new TestCase("all_negative",
                        new int[]{-1, -2, -3}, 0, 2, -6),

                // Mixed with zero
                new TestCase("with_zero",
                        new int[]{0, 1, 0, 2, 0}, 1, 3, 3),

                // Range at end
                new TestCase("range_at_end",
                        new int[]{1, 2, 3, 4}, 2, 3, 7),

                // Range at start
                new TestCase("range_at_start",
                        new int[]{5, 4, 3, 2, 1}, 0, 1, 9),

                // Large values
                new TestCase("large_values",
                        new int[]{1000000, -1000000, 1000000}, 0, 2, 1000000)
        );
    }

    private record TestCase(String name, int[] nums, int left, int right, int expected) {
    }

}