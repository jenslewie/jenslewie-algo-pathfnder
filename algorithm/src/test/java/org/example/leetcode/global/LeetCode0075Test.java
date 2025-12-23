package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("LeetCode 75: Sort Colors - Algorithm Variants Test")
public class LeetCode0075Test {

    private static final LeetCode0075 LEET_CODE = new LeetCode0075();

    private static final Map<String, Consumer<int[]>> ALGO_VARIANTS = Map.of(
            "sortColors", LEET_CODE::sortColors,
            "sortColors2", LEET_CODE::sortColors2,
            "sortColors3", LEET_CODE::sortColors3,
            "sortColors4", LEET_CODE::sortColors4
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}")
    @MethodSource("allCombinations")
    void testSortColors(String caseName, String algoName, int[] input, int[] expected) {
        int[] actual = input.clone();
        ALGO_VARIANTS.get(algoName).accept(actual);
        assertArrayEquals(expected, actual,
                () -> "Case '%s' with algo '%s' failed on input %s".formatted(caseName, algoName, Arrays.toString(input)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc ->
                ALGO_VARIANTS.keySet().stream()
                        .map(algo -> Arguments.of(tc.name, algo, tc.input, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("mixed", new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2}),
                new TestCase("short", new int[]{2, 0, 1}, new int[]{0, 1, 2}),
                new TestCase("no_zero", new int[]{2, 1, 2}, new int[]{1, 2, 2}),
                new TestCase("empty", new int[]{}, new int[]{}),
                new TestCase("single", new int[]{1}, new int[]{1}),
                new TestCase("all_ones", new int[]{1, 1, 1}, new int[]{1, 1, 1}),
                new TestCase("sorted", new int[]{0, 1, 2}, new int[]{0, 1, 2}),
                new TestCase("reverse", new int[]{2, 2, 1, 1, 0, 0}, new int[]{0, 0, 1, 1, 2, 2})
        );
    }

    private record TestCase(String name, int[] input, int[] expected) {
        private TestCase(String name, int[] input, int[] expected) {
            this.name = name;
            this.input = input.clone();
            this.expected = expected.clone();
        }
    }

}
