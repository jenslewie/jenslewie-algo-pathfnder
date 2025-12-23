package org.example.leetcode.global;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LeetCode0080Test {

    private static final LeetCode0080 LEET_CODE = new LeetCode0080();
    private static final Map<String, Function<int[], Integer>> ALGO_VARIANTS = Map.of(
            "removeDuplicates1", LEET_CODE::removeDuplicates1,
            "removeDuplicates2", LEET_CODE::removeDuplicates2,
            "removeDuplicates3", LEET_CODE::removeDuplicates3
    );

    @ParameterizedTest(name = "[{index}] {0} | Input: {1}")
    @MethodSource("testCases")
    void test(String caseName, int[] input, int expectedLength, int[] expectedPrefix) {
        ALGO_VARIANTS.forEach((algoName, algo) -> {
            int[] nums = input.clone();
            int actualLength = algo.apply(nums);

            assertEquals(
                    expectedLength,
                    actualLength,
                    () -> "[%s|%s] Length mismatch: expected %d, got %d"
                            .formatted(algoName, caseName, expectedLength, actualLength)
            );

            assertArrayPrefixEquals(
                    expectedPrefix,
                    nums,
                    actualLength,
                    () -> "[%s|%s]".formatted(algoName, caseName)
            );
        });
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("test1", new int[]{1, 1, 1, 2, 2, 3}, 5, new int[]{1, 1, 2, 2, 3}),
                Arguments.of("test2", new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}, 7, new int[]{0, 0, 1, 1, 2, 3, 3}),
                Arguments.of("test3", new int[]{1}, 1, new int[]{1})
        );
    }

    private static void assertArrayPrefixEquals(
            int[] expected,
            int[] actual,
            int actualLength,
            Supplier<String> messageSupplier
    ) {
        if (actualLength < expected.length) {
            fail("%s: Actual array truncated (length=%d < expected=%d)".formatted(messageSupplier.get(), actualLength, expected.length));
        }

        for (int i = 0; i < expected.length; i++) {
            assertEquals(
                    expected[i],
                    actual[i],
                    String.format("%s: Mismatch at index %d: expected %d but was %d", messageSupplier.get(), i, expected[i], actual[i])
            );
        }
    }
}
