package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 20: Valid Parentheses - Algorithm Variants")
class LeetCode0020Test {

    private static final LeetCode0020 LEET_CODE = new LeetCode0020();

    @FunctionalInterface
    interface ValidParenthesesFunction {
        boolean apply(String s);
    }

    private static final Map<String, ValidParenthesesFunction> ALGO_VARIANTS = Map.of(
            "push_expected_closing", LEET_CODE::isValid,
            "push_opening_match_left", LEET_CODE::isValid2,
            "string_substring_based", LEET_CODE::isValid3
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, s={2}")
    @MethodSource("allCombinations")
    void testIsValid(String caseName, String algoName, String s, boolean expected) {
        boolean actual = ALGO_VARIANTS.get(algoName).apply(s);

        assertEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. s=%s"
                .formatted(caseName, algoName, s));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.s, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode
                new TestCase("example_1", "()", true),

                // Example 2 from LeetCode
                new TestCase("example_2", "()[]{}", true),

                // Example 3 from LeetCode
                new TestCase("example_3", "(]", false),

                // Example 4 from LeetCode
                new TestCase("example_4", "([)]", false),

                // LeetCode example 5
                new TestCase("example_5", "{[]}", true),

                // Empty string
                new TestCase("empty", "", true),

                // Single pair
                new TestCase("single_pair", "[]", true),

                // Nested valid
                new TestCase("nested_valid", "({[]})", true),

                // Odd length (invalid)
                new TestCase("odd_length", "(((", false),

                // Mismatched closing
                new TestCase("mismatch_closing", "({)}", false),

                // Extra closing
                new TestCase("extra_closing", "()]", false),

                // Extra opening
                new TestCase("extra_opening", "(()", false),

                // Complex valid
                new TestCase("complex_valid", "([]){}", true),

                // Complex invalid
                new TestCase("complex_invalid", "([{}])]", false),

                // Only opening
                new TestCase("only_opening", "{{[", false)
        );
    }

    private record TestCase(String name, String s, boolean expected) {
    }

}