package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 388: Longest Absolute File Path - Algorithm Variants")
class LeetCode0388Test {

    private static final LeetCode0388 LEET_CODE = new LeetCode0388();

    @FunctionalInterface
    interface LongestPathFunction {
        int apply(String input);
    }

    private static final Map<String, LongestPathFunction> ALGO_VARIANTS = Map.of(
            "array_level_tracking", LEET_CODE::lengthLongestPath,
            "stack_integer", LEET_CODE::lengthLongestPath2,
            "stack_string", LEET_CODE::lengthLongestPath3
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, input={2}")
    @MethodSource("allCombinations")
    void testLengthLongestPath(String caseName, String algoName, String input, int expected) {
        int actual = ALGO_VARIANTS.get(algoName).apply(input);

        assertEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. input=%s"
                .formatted(caseName, algoName, escapeNewlines(input)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.input, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode
                new TestCase("example_1",
                        "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext",
                        20),

                // Example 2 from LeetCode
                new TestCase("example_2",
                        "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext",
                        32),

                // No file
                new TestCase("no_file",
                        "dir\n\tsubdir1\n\tsubdir2",
                        0),

                // Single file at root
                new TestCase("single_file_root",
                        "file.txt",
                        8),

                // Deep nesting
                new TestCase("deep_nesting",
                        "a\n\tb\n\t\tc\n\t\t\td.txt",
                        11),

                // Verified deep nesting
                new TestCase("verified_deep",
                        "a\n\tbb\n\t\tccc.txt",
                        12),

                // Empty input
                new TestCase("empty_input", "", 0),

                // Only directories
                new TestCase("only_directories",
                        "dir\n\tsubdir1\n\tsubdir2\n\t\tsubsubdir",
                        0),

                // File with multiple dots
                new TestCase("file_with_dots",
                        "dir\n\tfile.name.ext",
                        17) //
        );
    }

    private record TestCase(String name, String input, int expected) {
    }

    // Helper: replace \n and \t with visible characters for error messages
    private static String escapeNewlines(String s) {
        if (s == null) return "null";
        return s.replace("\n", "\\n").replace("\t", "\\t");
    }

}