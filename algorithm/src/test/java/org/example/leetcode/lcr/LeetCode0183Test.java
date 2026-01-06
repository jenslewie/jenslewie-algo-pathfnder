package org.example.leetcode.lcr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("LCR 183: Sliding Window Maximum - Algorithm Variants")
class LeetCode0183Test {

    private static final LeetCode0183 LEET_CODE = new LeetCode0183();

    @FunctionalInterface
    interface MaxAltitudeFunction {
        int[] apply(int[] heights, int limit);
    }

    private static final Map<String, MaxAltitudeFunction> ALGO_VARIANTS = Map.of(
            "monotonic_deque", LEET_CODE::maxAltitude,
            "priority_queue", LEET_CODE::maxAltitude2
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, heights={2}, limit={3}")
    @MethodSource("allCombinations")
    void testMaxAltitude(String caseName, String algoName, int[] heights, int limit, int[] expected) {
        int[] actual = ALGO_VARIANTS.get(algoName).apply(heights, limit);

        assertArrayEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. heights=%s, limit=%d"
                .formatted(caseName, algoName, java.util.Arrays.toString(heights), limit));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.heights, tc.limit, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LCR 183 / LeetCode 239
                new TestCase("example_1",
                        new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3,
                        new int[]{3, 3, 5, 5, 6, 7}),

                // Example 2 from LCR 183 / LeetCode 239
                new TestCase("example_2",
                        new int[]{1}, 1,
                        new int[]{1}),

                // Empty input
                new TestCase("empty_input",
                        new int[]{}, 1,
                        new int[]{}),

                // limit = 0
                new TestCase("limit_zero",
                        new int[]{1, 2, 3}, 0,
                        new int[]{}),

                // limit equals array length
                new TestCase("limit_equals_length",
                        new int[]{1, -1}, 2,
                        new int[]{1}),

                // Monotonically increasing
                new TestCase("increasing",
                        new int[]{1, 2, 3, 4, 5}, 2,
                        new int[]{2, 3, 4, 5}),

                // Monotonically decreasing
                new TestCase("decreasing",
                        new int[]{5, 4, 3, 2, 1}, 2,
                        new int[]{5, 4, 3, 2}),

                // All same values
                new TestCase("all_same",
                        new int[]{2, 2, 2, 2}, 3,
                        new int[]{2, 2}),

                // Negative numbers
                new TestCase("negative_values",
                        new int[]{-1, -2, -3, -4}, 2,
                        new int[]{-1, -2, -3}),

                // limit = 1 (each element is its own window)
                new TestCase("limit_one",
                        new int[]{10, 20, 30}, 1,
                        new int[]{10, 20, 30})
        );
    }

    private record TestCase(String name, int[] heights, int limit, int[] expected) {
    }

}
