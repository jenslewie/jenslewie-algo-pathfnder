package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 232: Implement Queue using Stacks")
class LeetCode0232Test {

    @ParameterizedTest(name = "[{index}] case={0}, ops={1}, params={2}")
    @MethodSource("testCases")
    void testMyQueue(String caseName, String[] operations, Object[][] params, Object[] expected) {
        LeetCode0232 queue = new LeetCode0232();
        List<Object> actual = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];
            Object result = null;

            switch (op) {
                case "push":
                    queue.push((Integer) params[i][0]);
                    break;
                case "pop":
                    result = queue.pop();
                    break;
                case "peek":
                    result = queue.peek();
                    break;
                case "empty":
                    result = queue.empty();
                    break;
                case "MyQueue":
                    // Constructor, no action needed
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operation: " + op);
            }
            actual.add(result);
        }

        // Convert to array for comparison
        Object[] actualArr = actual.toArray(new Object[0]);
        assertEquals(expected.length, actualArr.length, () -> "Output length mismatch in case '%s'".formatted(caseName));

        for (int i = 0; i < expected.length; i++) {
            int finalI = i;
            assertEquals(expected[i], actualArr[i], () -> "Mismatch at index %d in case '%s': expected=%s, actual=%s"
                    .formatted(finalI, caseName, expected[finalI], actualArr[finalI]));
        }
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                // Example from LeetCode
                Arguments.of(
                        "example_1",
                        new String[]{"MyQueue", "push", "push", "peek", "pop", "empty"},
                        new Object[][]{{}, {1}, {2}, {}, {}, {}},
                        new Object[]{null, null, null, 1, 1, false}
                ),

                // Single element
                Arguments.of(
                        "single_element",
                        new String[]{"MyQueue", "push", "peek", "pop", "empty"},
                        new Object[][]{{}, {5}, {}, {}, {}},
                        new Object[]{null, null, 5, 5, true}
                ),

                // Multiple pops
                Arguments.of(
                        "multiple_ops",
                        new String[]{"MyQueue", "push", "push", "push", "pop", "pop", "peek", "empty"},
                        new Object[][]{{}, {10}, {20}, {30}, {}, {}, {}, {}},
                        new Object[]{null, null, null, null, 10, 20, 30, false}
                ),

                // Empty queue operations
                Arguments.of(
                        "empty_queue",
                        new String[]{"MyQueue", "empty", "push", "empty", "pop"},
                        new Object[][]{{}, {}, {100}, {}, {}},
                        new Object[]{null, true, null, false, 100}
                ),

                // Peek without pop
                Arguments.of(
                        "peek_only",
                        new String[]{"MyQueue", "push", "push", "peek", "peek", "pop", "peek"},
                        new Object[][]{{}, {7}, {8}, {}, {}, {}, {}},
                        new Object[]{null, null, null, 7, 7, 7, 8}
                )
        );
    }

}