package org.example.leetcode.lcr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("LCR 038: Daily Temperatures - Algorithm Variants")
class LeetCode0038Test {

    private static final LeetCode0038 LEET_CODE = new LeetCode0038();

    @FunctionalInterface
    interface DailyTemperaturesFunction {
        int[] apply(int[] temperatures);
    }

    private static final Map<String, DailyTemperaturesFunction> ALGO_VARIANTS = Map.of(
            "monotonic_stack", LEET_CODE::dailyTemperatures
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, temperatures={2}")
    @MethodSource("allCombinations")
    void testDailyTemperatures(String caseName, String algoName, int[] temperatures, int[] expected) {
        int[] actual = ALGO_VARIANTS.get(algoName).apply(temperatures);

        assertArrayEquals(expected, actual, () -> String.format("Case '%s' with algo='%s' failed. temperatures=%s",
                caseName, algoName, java.util.Arrays.toString(temperatures)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.temperatures, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LCR 038 / LeetCode 739
                new TestCase("example_1", new int[]{73, 74, 75, 71, 69, 72, 76, 73},
                        new int[]{1, 1, 4, 2, 1, 1, 0, 0}),

                // Example 2
                new TestCase("example_2", new int[]{30, 40, 50, 60},
                        new int[]{1, 1, 1, 0}),

                // Example 3
                new TestCase("example_3", new int[]{30, 60, 90},
                        new int[]{1, 1, 0}),

                // All decreasing — no warmer day
                new TestCase("decreasing", new int[]{90, 80, 70, 60},
                        new int[]{0, 0, 0, 0}),

                // All same
                new TestCase("all_same", new int[]{70, 70, 70, 70},
                        new int[]{0, 0, 0, 0}),

                // Single day
                new TestCase("single_day", new int[]{75},
                        new int[]{0}),

                // Increasing sequence
                new TestCase("strictly_increasing", new int[]{1, 2, 3, 4, 5},
                        new int[]{1, 1, 1, 1, 0}),

                // Peak in middle
                new TestCase("peak_middle", new int[]{70, 80, 90, 80, 70},
                        new int[]{1, 1, 0, 0, 0}),

                // Complex real-world like
                new TestCase("complex", new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70},
                        new int[]{8, 1, 5, 4, 3, 2, 1, 1, 0, 0})
        );
    }

    private record TestCase(String name, int[] temperatures, int[] expected) {
    }

}
