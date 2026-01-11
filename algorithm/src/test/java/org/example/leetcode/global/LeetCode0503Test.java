package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("LeetCode 503: Next Greater Element II - Algorithm Variants")
class LeetCode0503Test {

    private static final LeetCode0503 LEET_CODE = new LeetCode0503();

    @FunctionalInterface
    interface NextGreaterElementsFunction {
        int[] apply(int[] nums);
    }

    private static final Map<String, NextGreaterElementsFunction> ALGO_VARIANTS = Map.of(
            "forward_double_pass", LEET_CODE::nextGreaterElements,
            "backward_double_pass", LEET_CODE::nextGreaterElements2
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, nums={2}")
    @MethodSource("allCombinations")
    void testNextGreaterElements(String caseName, String algoName, int[] nums, int[] expected) {
        int[] actual = ALGO_VARIANTS.get(algoName).apply(nums);

        assertArrayEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. nums=%s"
                .formatted(caseName, algoName, java.util.Arrays.toString(nums)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.nums, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode
                new TestCase("example_1",
                        new int[]{1, 2, 1},
                        new int[]{2, -1, 2}),

                // Example 2 from LeetCode
                new TestCase("example_2",
                        new int[]{1, 2, 3, 4, 3},
                        new int[]{2, 3, 4, -1, 4}),

                // Specific case
                new TestCase("specific_case",
                        new int[]{100, 1, 11, 1, 120, 111, 123, 1, -1, -100},
                        new int[]{120, 11, 120, 120, 123, 123, -1, 100, 100, 100}),

                // All same elements
                new TestCase("all_same",
                        new int[]{5, 5, 5},
                        new int[]{-1, -1, -1}),

                // Strictly increasing
                new TestCase("strictly_increasing",
                        new int[]{1, 2, 3, 4},
                        new int[]{2, 3, 4, -1}),

                // Strictly decreasing
                new TestCase("strictly_decreasing",
                        new int[]{4, 3, 2, 1},
                        new int[]{-1, 4, 4, 4}),

                // Single element
                new TestCase("single_element",
                        new int[]{10},
                        new int[]{-1}),

                // Two elements
                new TestCase("two_elements",
                        new int[]{2, 1},
                        new int[]{-1, 2}),

                // Circular peak
                new TestCase("circular_peak",
                        new int[]{3, 1, 2, 1, 3},
                        new int[]{-1, 2, 3, 3, -1}),

                // Large values
                new TestCase("large_values",
                        new int[]{1000000000, 1, 1000000000},
                        new int[]{-1, 1000000000, -1})
        );
    }

    private record TestCase(String name, int[] nums, int[] expected) {
    }

}
