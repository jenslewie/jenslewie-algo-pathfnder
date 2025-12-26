package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 1011: Capacity To Ship Packages - Algorithm Variants")
class LeetCode1011Test {

    private static final LeetCode1011 LEET_CODE = new LeetCode1011();

    @FunctionalInterface
    interface ShipCapacityFunction {
        int apply(int[] weights, int days);
    }

    private static final Map<String, ShipCapacityFunction> ALGO_VARIANTS = Map.of(
            "binary_search_on_capacity", LEET_CODE::shipWithinDays
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, weights={2}, days={3}")
    @MethodSource("allCombinations")
    void testShipWithinDays(String caseName, String algoName, int[] weights, int days, int expected) {
        int actual = ALGO_VARIANTS.get(algoName).apply(weights, days);

        assertEquals(expected, actual, () -> "Case '%s' with algo '%s' failed. weights=%s, days=%d"
                .formatted(caseName, algoName, java.util.Arrays.toString(weights), days));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.weights, tc.days, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode
                new TestCase("example_1",
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5, 15),

                // Example 2 from LeetCode
                new TestCase("example_2",
                        new int[]{3, 2, 2, 4, 1, 4}, 3, 6),

                // Example 3 from LeetCode
                new TestCase("example_3",
                        new int[]{1, 2, 3, 1, 1}, 4, 3),

                // Single package
                new TestCase("single_package",
                        new int[]{1000}, 1, 1000),

                // Days equals number of packages → capacity = max(weights)
                new TestCase("days_equals_packages",
                        new int[]{1, 2, 3, 4, 5}, 5, 5),

                // Only 1 day → capacity = sum(weights)
                new TestCase("one_day",
                        new int[]{1, 2, 3, 4, 5}, 1, 15),

                // Large weights
                new TestCase("large_weights",
                        new int[]{500000000, 500000000}, 2, 500000000),

                // Tight capacity constraint
                new TestCase("tight_constraint",
                        new int[]{10, 50, 100, 200}, 2, 200),

                // All weights same
                new TestCase("uniform_weights",
                        new int[]{10, 10, 10, 10}, 3, 20), // 10+10 | 10+10 → cap=20

                // Minimal weights, many days
                new TestCase("minimal_weights_many_days",
                        new int[]{1, 1, 1, 1, 1}, 10, 1)
        );
    }

    private record TestCase(String name, int[] weights, int days, int expected) {
    }

}