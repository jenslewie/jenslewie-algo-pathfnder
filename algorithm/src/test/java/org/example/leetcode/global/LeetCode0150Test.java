package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 150: Evaluate Reverse Polish Notation - Algorithm Variants")
class LeetCode0150Test {

    private static final LeetCode0150 LEET_CODE = new LeetCode0150();

    @FunctionalInterface
    interface EvalRPNFunction {
        int apply(String[] tokens);
    }

    private static final Map<String, EvalRPNFunction> ALGO_VARIANTS = Map.of(
            "array_mock_stack", LEET_CODE::evalRPN,
            "integer_stack", LEET_CODE::evalRPN2,
            "string_stack", LEET_CODE::evalRPN3
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, tokens={2}")
    @MethodSource("allCombinations")
    void testEvalRPN(String caseName, String algoName, String[] tokens, int expected) {
        int actual = ALGO_VARIANTS.get(algoName).apply(tokens);

        assertEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. tokens=%s"
                .formatted(caseName, algoName, java.util.Arrays.toString(tokens)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.tokens, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode
                new TestCase("example_1", new String[]{"2", "1", "+", "3", "*"}, 9),

                // Example 2 from LeetCode
                new TestCase("example_2", new String[]{"4", "13", "5", "/", "+"}, 6),

                // Example 3 from LeetCode
                new TestCase("example_3",
                        new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, 22),

                // Single number
                new TestCase("single_number", new String[]{"42"}, 42),

                // Negative result
                new TestCase("negative_result", new String[]{"3", "4", "-"}, -1),

                // Division truncation towards zero
                new TestCase("division_truncation",
                        new String[]{"13", "5", "/"}, 2),

                // Negative division
                new TestCase("negative_division",
                        new String[]{"-13", "5", "/"}, -2),

                // Complex with negatives
                new TestCase("complex_negatives",
                        new String[]{"4", "-2", "/", "2", "-3", "-", "*"}, -10),

                // Verified complex case
                new TestCase("verified_complex",
                        new String[]{"4", "-2", "/", "5", "*"}, -10),

                // Multiplication
                new TestCase("multiplication", new String[]{"5", "3", "*"}, 15),

                // Addition chain
                new TestCase("addition_chain", new String[]{"1", "2", "+", "3", "+"}, 6)
        );
    }

    private record TestCase(String name, String[] tokens, int expected) {
    }

}
