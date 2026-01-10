package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("LeetCode 739: Daily Temperatures - Algorithm Variants")
class LeetCode0739Test {

    private static final LeetCode0739 LEET_CODE = new LeetCode0739();

    @FunctionalInterface
    interface DailyTemperaturesFunction {
        int[] apply(int[] temperatures);
    }

    private static final Map<String, DailyTemperaturesFunction> ALGO_VARIANTS = Map.of(
            "forward_monotonic_stack", LEET_CODE::dailyTemperatures,
            "backward_monotonic_stack", LEET_CODE::dailyTemperatures2
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, temps={2}")
    @MethodSource("allCombinations")
    void testDailyTemperatures(String caseName, String algoName, int[] temperatures, int[] expected) {
        int[] actual = ALGO_VARIANTS.get(algoName).apply(temperatures);

        assertArrayEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. temperatures=%s"
                .formatted(caseName, algoName, java.util.Arrays.toString(temperatures)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.temperatures, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode
                new TestCase("example_1",
                        new int[]{73, 74, 75, 71, 69, 72, 76, 73},
                        new int[]{1, 1, 4, 2, 1, 1, 0, 0}),

                // Example 2 from LeetCode
                new TestCase("example_2",
                        new int[]{30, 40, 50, 60},
                        new int[]{1, 1, 1, 0}),

                // Example 3 from LeetCode
                new TestCase("example_3",
                        new int[]{30, 60, 90},
                        new int[]{1, 1, 0}),

                // Strictly decreasing
                new TestCase("strictly_decreasing",
                        new int[]{90, 80, 70, 60},
                        new int[]{0, 0, 0, 0}),

                // Strictly increasing
                new TestCase("strictly_increasing",
                        new int[]{60, 70, 80, 90},
                        new int[]{1, 1, 1, 0}),

                // Single day
                new TestCase("single_day",
                        new int[]{70},
                        new int[]{0}),

                // All same
                new TestCase("all_same",
                        new int[]{70, 70, 70},
                        new int[]{0, 0, 0}),

                // Peak in middle
                new TestCase("peak_middle",
                        new int[]{70, 80, 90, 80, 70},
                        new int[]{1, 1, 0, 0, 0}),

                // Valley in middle
                new TestCase("valley_middle",
                        new int[]{90, 70, 80, 100},
                        new int[]{3, 1, 1, 0})
        );
    }

    private record TestCase(String name, int[] temperatures, int[] expected) {
    }

}
