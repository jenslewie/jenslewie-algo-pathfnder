package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 875: Koko Eating Bananas - Algorithm Variants")
class LeetCode0875Test {

    private static final LeetCode0875 LEET_CODE = new LeetCode0875();

    @FunctionalInterface
    interface MinEatingSpeedFunction {
        int apply(int[] piles, int h);
    }

    private static final Map<String, MinEatingSpeedFunction> ALGO_VARIANTS = Map.of(
            "binary_search_on_speed", LEET_CODE::minEatingSpeed
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, piles={2}, h={3}")
    @MethodSource("allCombinations")
    void testMinEatingSpeed(String caseName, String algoName, int[] piles, int h, int expected) {
        int actual = ALGO_VARIANTS.get(algoName).apply(piles, h);

        assertEquals(expected, actual, () -> "Case '%s' with algo '%s' failed. piles=%s, h=%d"
                .formatted(caseName, algoName, java.util.Arrays.toString(piles), h));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.piles, tc.h, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode
                new TestCase("example_1",
                        new int[]{3, 6, 7, 11}, 8, 4),

                // Example 2 from LeetCode
                new TestCase("example_2",
                        new int[]{30, 11, 23, 4, 20}, 5, 30),

                // Example 3 from LeetCode
                new TestCase("example_3",
                        new int[]{30, 11, 23, 4, 20}, 6, 23),

                // Minimum possible: one pile, just enough time
                new TestCase("single_pile_exact",
                        new int[]{1000000000}, 1, 1000000000),

                // Minimum speed = 1 (very slow, but enough time)
                new TestCase("speed_1_sufficient",
                        new int[]{1, 1, 1}, 10, 1),

                // All piles same, h = number of piles → speed = max(piles)
                new TestCase("h_equals_piles",
                        new int[]{5, 5, 5}, 3, 5),

                // Large piles, moderate h
                new TestCase("large_piles",
                        new int[]{312884470}, 312884469, 2),

                // Edge: h just enough for max pile
                new TestCase("h_equals_max_pile",
                        new int[]{3, 6, 9, 12}, 4, 12),

                // Multiple small piles, small h
                new TestCase("small_piles_tight_h",
                        new int[]{1, 2, 3, 4, 5}, 5, 5),

                // Very large h → speed = 1
                new TestCase("huge_h",
                        new int[]{100, 200, 300}, 1000, 1)
        );
    }

    private record TestCase(String name, int[] piles, int h, int expected) {
    }

}