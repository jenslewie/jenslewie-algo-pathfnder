package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 84: Largest Rectangle in Histogram - Algorithm Variants")
class LeetCode0084Test {

    @FunctionalInterface
    interface LargestRectangleFunction {
        int apply(int[] heights);
    }

    private static final Map<String, LargestRectangleFunction> ALGO_VARIANTS = Map.of(
            "two_pass_with_clear", heights -> new LeetCode0084_1().largestRectangleArea(heights),
            "one_pass_right_fill", heights -> new LeetCode0084_2().largestRectangleArea(heights)
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, heights={2}")
    @MethodSource("allCombinations")
    void testLargestRectangleArea(String caseName, String algoName, int[] heights, int expected) {
        int actual = ALGO_VARIANTS.get(algoName).apply(heights);

        assertEquals(expected, actual, () -> String.format(
                "Case '%s' with algo='%s' failed. Input heights: %s",
                caseName, algoName, java.util.Arrays.toString(heights)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.heights, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode 84
                new TestCase("example_1", new int[]{2, 1, 5, 6, 2, 3}, 10),

                // Example 2 from LeetCode 84
                new TestCase("example_2", new int[]{2, 4}, 4),

                // Single bar
                new TestCase("single", new int[]{5}, 5),

                // All equal
                new TestCase("all_equal", new int[]{3, 3, 3, 3}, 12),

                // Strictly increasing
                new TestCase("strictly_increasing", new int[]{1, 2, 3, 4, 5}, 9), // 3*3=9 or 4*2=8 → max=9

                // Strictly decreasing
                new TestCase("strictly_decreasing", new int[]{5, 4, 3, 2, 1}, 9), // same as above

                // Peak in middle
                new TestCase("peak", new int[]{1, 2, 3, 2, 1}, 6), // height=3, width=1 → 3; but height=2, width=3 → 6

                // Valley in middle
                new TestCase("valley", new int[]{3, 1, 3}, 3), // each 3 can form 3x1=3, middle 1 forms 1x3=3 → max=3

                // All zeros
                new TestCase("all_zeros", new int[]{0, 0, 0}, 0),

                // Mixed with zero
                new TestCase("with_zero", new int[]{2, 1, 0, 2}, 2), // left part: max=2, right part: max=2

                // Large input (known answer)
                new TestCase("large", new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 20) // 5*4=20 or 6*3=18 → max=20
        );
    }

    private record TestCase(String name, int[] heights, int expected) {
    }

}
