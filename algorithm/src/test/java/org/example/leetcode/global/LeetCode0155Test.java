package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 155: Min Stack - Algorithm Variants")
class LeetCode0155Test {

    private static final List<String> ALGO_NAMES = Arrays.asList(
            "int_array_stack",
            "dual_integer_stack",
            "stack_with_map"
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, ops={2}, values={3}")
    @MethodSource("allCombinations")
    void testMinStack(String caseName, String algoName, List<String> ops, List<Integer> vals, List<Integer> expected) {
        List<Integer> actual = simulate(algoName, ops, vals);
        assertEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. ops=%s, vals=%s"
                .formatted(caseName, algoName, ops, vals));
    }

    private static List<Integer> simulate(String algo, List<String> ops, List<Integer> vals) {
        LeetCode0155 stack = new LeetCode0155();
        List<Integer> result = new ArrayList<>();
        int valIndex = 0;

        for (String op : ops) {
            switch (op) {
                case "MinStack":
                    break;
                case "push":
                    int val = vals.get(valIndex++);
                    switch (algo) {
                        case "int_array_stack" -> stack.push(val);
                        case "dual_integer_stack" -> stack.push2(val);
                        case "stack_with_map" -> stack.push3(val);
                    }
                    break;
                case "pop":
                    switch (algo) {
                        case "int_array_stack" -> stack.pop();
                        case "dual_integer_stack" -> stack.pop2();
                        case "stack_with_map" -> stack.pop3();
                    }
                    break;
                case "top":
                    int top = switch (algo) {
                        case "int_array_stack" -> stack.top();
                        case "dual_integer_stack" -> stack.top2();
                        case "stack_with_map" -> stack.top3();
                        default -> throw new IllegalStateException("Unexpected value: " + algo);
                    };
                    result.add(top);
                    break;
                case "getMin":
                    int min = switch (algo) {
                        case "int_array_stack" -> stack.getMin();
                        case "dual_integer_stack" -> stack.getMin2();
                        case "stack_with_map" -> stack.getMin3();
                        default -> throw new IllegalStateException("Unexpected value: " + algo);
                    };
                    result.add(min);
                    break;
            }
        }
        return result;
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_NAMES.stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.ops, tc.vals, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("example",
                        List.of("MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"),
                        List.of(-2, 0, -3),
                        List.of(-3, 0, -2)),

                // Single element
                new TestCase("single_element",
                        List.of("MinStack", "push", "getMin", "top", "pop", "push", "getMin"),
                        List.of(5, 10),
                        List.of(5, 5, 10)),

                // Repeating minimum
                new TestCase("repeating_min",
                        List.of("MinStack", "push", "push", "push", "getMin", "pop", "getMin", "pop", "getMin"),
                        List.of(3, 1, 1),
                        List.of(1, 1, 3)),

                // Increasing values
                new TestCase("increasing",
                        List.of("MinStack", "push", "push", "push", "getMin", "top"),
                        List.of(1, 2, 3),
                        List.of(1, 3)),

                // Decreasing values
                new TestCase("decreasing",
                        List.of("MinStack", "push", "push", "push", "getMin", "pop", "getMin"),
                        List.of(3, 2, 1),
                        List.of(1, 2)),

                // Negative values
                new TestCase("negative_values",
                        List.of("MinStack", "push", "push", "getMin", "push", "getMin"),
                        List.of(-1, -2, -3),
                        List.of(-2, -3)),

                // Empty after pops
                new TestCase("empty_after_pops",
                        List.of("MinStack", "push", "pop", "push", "getMin"),
                        List.of(100, 200),
                        List.of(200))
        );
    }

    private record TestCase(String name, List<String> ops, List<Integer> vals, List<Integer> expected) {
    }

}
