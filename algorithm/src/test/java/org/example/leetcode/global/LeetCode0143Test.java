package org.example.leetcode.global;

import org.example.builder.LinkedListBuilder;
import org.example.model.linkedlist.ListNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("LeetCode 143: Reorder List - Algorithm Variants")
class LeetCode0143Test {

    private static final LeetCode0143 LEET_CODE = new LeetCode0143();

    @FunctionalInterface
    interface ReorderListFunction {
        void apply(ListNode head);
    }

    private static final Map<String, ReorderListFunction> ALGO_VARIANTS = Map.of(
            "reverse_and_merge_list", LEET_CODE::reorderList,
            "stack_based", LEET_CODE::reorderList2
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, input={2}")
    @MethodSource("allCombinations")
    void testReorderList(String caseName, String algoName, int[] input, int[] expected) {
        // Deep clone: create fresh list for each test + algo
        ListNode head = LinkedListBuilder.fromArray(input);
        ALGO_VARIANTS.get(algoName).apply(head);
        int[] actual = LinkedListBuilder.toArray(head);

        assertArrayEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. input=%s"
                .formatted(caseName, algoName, java.util.Arrays.toString(input)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.input, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1 from LeetCode: [1,2,3,4] → [1,4,2,3]
                new TestCase("example_1", new int[]{1, 2, 3, 4}, new int[]{1, 4, 2, 3}),

                // Example 2 from LeetCode: [1,2,3,4,5] → [1,5,2,4,3]
                new TestCase("example_2", new int[]{1, 2, 3, 4, 5}, new int[]{1, 5, 2, 4, 3}),

                // Single node
                new TestCase("single_node", new int[]{1}, new int[]{1}),

                // Two nodes
                new TestCase("two_nodes", new int[]{1, 2}, new int[]{1, 2}),

                // Three nodes
                new TestCase("three_nodes", new int[]{1, 2, 3}, new int[]{1, 3, 2}),

                // Empty list (edge case, though LeetCode guarantees at least 1 node)
                new TestCase("empty_list", new int[]{}, new int[]{}),

                // Four nodes (even)
                new TestCase("four_nodes", new int[]{10, 20, 30, 40}, new int[]{10, 40, 20, 30}),

                // Five nodes (odd)
                new TestCase("five_nodes", new int[]{5, 4, 3, 2, 1}, new int[]{5, 1, 4, 2, 3})
        );
    }

    private record TestCase(String name, int[] input, int[] expected) {
    }

}