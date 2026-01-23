package org.example.leetcode.global;

import org.example.builder.BinaryTreeBuilder;
import org.example.leetcode.utility.BinaryTreeUtility;
import org.example.model.tree.TreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("LeetCode 116: Populating Next Right Pointers in Each Node")
class LeetCode0116Test {

    @FunctionalInterface
    interface ConnectFunction {
        TreeNode apply(TreeNode root);
    }

    private static final Map<String, ConnectFunction> ALGO_VARIANTS = Map.of(
            "bfs_iterative_traverse_with_queue", root -> new LeetCode0116_1().connect(root),
            "dfs_recursive_traverse_connect_siblings", root -> new LeetCode0116_2().connect(root),
            "dfs_recursive_divide_conquer_with_parent_next", root -> new LeetCode0116_3().connect(root),
            "bfs_iterative_traverse_with_dummy_head", root -> new LeetCode0116_4().connect(root)
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, tree={2}")
    @MethodSource("allCombinations")
    void testConnect(String caseName, String algoName, Integer[] input, Integer[] expectedNextVals) {
        TreeNode root = BinaryTreeBuilder.build(input);
        TreeNode actual = ALGO_VARIANTS.get(algoName).apply(root);
        TreeNode expected = BinaryTreeBuilder.buildTreeWithNext(input, expectedNextVals);
        assertTrue(BinaryTreeUtility.isSameNextStructure(expected, actual),
                () -> String.format("Case '%s' with algo='%s' failed.", caseName, algoName));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.input, tc.expectedNextVals))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1: [1,2,3,4,5,6,7]
                // next: 2->3, 4->5->6->7, others -> null
                new TestCase("example_1",
                        new Integer[]{1, 2, 3, 4, 5, 6, 7},
                        new Integer[]{2, 3, null, 5, 6, 7, null}),

                // Single node
                new TestCase("single_node",
                        new Integer[]{1},
                        new Integer[]{null}),

                // Empty tree
                new TestCase("empty_tree",
                        new Integer[]{},
                        new Integer[]{}),

                // Full tree depth 2
                new TestCase("depth_2",
                        new Integer[]{1, 2, 3},
                        new Integer[]{2, 3, null})
        );
    }

    private record TestCase(String name, Integer[] input, Integer[] expectedNextVals) {
    }

}
