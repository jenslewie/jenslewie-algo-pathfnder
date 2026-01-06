package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 895: Maximum Frequency Stack - Algorithm Variants")
class LeetCode0895Test {

    @FunctionalInterface
    interface FreqStackFunction {
        List<Integer> apply(List<String> ops, List<Integer> vals);
    }

    private static final Map<String, FreqStackFunction> ALGO_VARIANTS = Map.of(
            "array_linkedlist", LeetCode0895Test::executeArrayVersion,
            "map_stack", LeetCode0895Test::executeMapVersion
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, ops={2}, values={3}")
    @MethodSource("allCombinations")
    void testFreqStack(String caseName, String algoName, List<String> ops, List<Integer> vals, List<Integer> expected) {
        List<Integer> actual = ALGO_VARIANTS.get(algoName).apply(ops, vals);

        assertEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. ops=%s, vals=%s"
                .formatted(caseName, algoName, ops, vals));
    }

    private static List<Integer> executeArrayVersion(List<String> ops, List<Integer> vals) {
        LeetCode0895 stack = new LeetCode0895();
        List<Integer> result = new ArrayList<>();
        int valIndex = 0;

        for (String op : ops) {
            switch (op) {
                case "FreqStack":
                    break;
                case "push":
                    stack.push(vals.get(valIndex++));
                    break;
                case "pop":
                    result.add(stack.pop());
                    break;
            }
        }
        return result;
    }

    private static List<Integer> executeMapVersion(List<String> ops, List<Integer> vals) {
        LeetCode0895 stack = new LeetCode0895();
        List<Integer> result = new ArrayList<>();
        int valIndex = 0;

        for (String op : ops) {
            switch (op) {
                case "FreqStack":
                    break;
                case "push":
                    stack.push2(vals.get(valIndex++));
                    break;
                case "pop":
                    result.add(stack.pop2());
                    break;
            }
        }
        return result;
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.ops, tc.vals, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example from LeetCode 895
                new TestCase("example",
                        List.of("FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"),
                        List.of(5, 7, 5, 7, 4, 5),
                        List.of(5, 7, 5, 4)),

                // Single value
                new TestCase("single_value",
                        List.of("FreqStack", "push", "pop"),
                        List.of(42),
                        List.of(42)),

                // Two values, same frequency
                new TestCase("same_freq",
                        List.of("FreqStack", "push", "push", "pop", "pop"),
                        List.of(1, 2),
                        List.of(2, 1)),

                // High frequency of one value
                new TestCase("high_freq",
                        List.of("FreqStack", "push", "push", "push", "pop", "pop", "pop"),
                        List.of(10, 10, 10),
                        List.of(10, 10, 10)),

                // Mixed frequencies
                new TestCase("mixed_freq",
                        List.of("FreqStack", "push", "push", "push", "push", "pop", "pop", "pop", "pop"),
                        List.of(1, 2, 1, 2),
                        List.of(2, 1, 2, 1)),

                // Empty after pops
                new TestCase("empty_after_pops",
                        List.of("FreqStack", "push", "pop", "push", "pop"),
                        List.of(100, 200),
                        List.of(100, 200))
        );
    }

    private record TestCase(String name, List<String> ops, List<Integer> vals, List<Integer> expected) {
    }

}
