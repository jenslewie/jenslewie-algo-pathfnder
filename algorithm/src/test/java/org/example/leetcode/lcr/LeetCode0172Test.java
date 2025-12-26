package org.example.leetcode.lcr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LCR 172: Count Target in Sorted Array - Algorithm Variants")
class LeetCode0172Test {

    private static final LeetCode0172 LEET_CODE = new LeetCode0172();

    @FunctionalInterface
    interface CountTargetFunction {
        int apply(int[] scores, int target);
    }

    private static final Map<String, CountTargetFunction> ALGO_VARIANTS = Map.of(
            "left_bound_twice", LEET_CODE::countTarget
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, scores={2}, target={3}")
    @MethodSource("allCombinations")
    void testCountTarget(String caseName, String algoName, int[] scores, int target, int expected) {
        int actual = ALGO_VARIANTS.get(algoName).apply(scores, target);

        assertEquals(expected, actual, () -> "Case '%s' with algo '%s' failed. scores=%s, target=%d"
                .formatted(caseName, algoName, java.util.Arrays.toString(scores), target));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.scores, tc.target, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LCR 172
                new TestCase("example_1",
                        new int[]{5, 7, 7, 8, 8, 10}, 8, 2),

                // Example 2 from LCR 172
                new TestCase("example_2",
                        new int[]{5, 7, 7, 8, 8, 10}, 6, 0),

                // Empty array
                new TestCase("empty_array",
                        new int[]{}, 1, 0),

                // Single element matches
                new TestCase("single_match",
                        new int[]{3}, 3, 1),

                // Single element not match
                new TestCase("single_mismatch",
                        new int[]{3}, 5, 0),

                // All elements are target
                new TestCase("all_same_target",
                        new int[]{2, 2, 2, 2}, 2, 4),

                // Target at beginning
                new TestCase("target_at_start",
                        new int[]{1, 1, 2, 3, 4}, 1, 2),

                // Target at end
                new TestCase("target_at_end",
                        new int[]{1, 2, 3, 5, 5, 5}, 5, 3),

                // No occurrence
                new TestCase("not_present",
                        new int[]{1, 2, 3, 4, 5}, 6, 0),

                // Large array with many duplicates
                new TestCase("many_duplicates",
                        new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3}, 2, 4),

                // Target less than all
                new TestCase("target_too_small",
                        new int[]{10, 20, 30}, 5, 0),

                // Target greater than all
                new TestCase("target_too_large",
                        new int[]{10, 20, 30}, 40, 0)
        );
    }

    private record TestCase(String name, int[] scores, int target, int expected) {
    }

}