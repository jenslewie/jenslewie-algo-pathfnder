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

@DisplayName("LeetCode 226: Invert Binary Tree")
class LeetCode0226Test {

    @FunctionalInterface
    interface InvertTreeFunction {
        TreeNode apply(TreeNode root);
    }

    private static final Map<String, InvertTreeFunction> ALGO_VARIANTS = Map.of(
            "divide_and_conquer", root -> new LeetCode0226_1().invertTree(root),
            "recursive_traversal", root -> new LeetCode0226_2().invertTree(root),
            "iterative_with_stack", root -> new LeetCode0226_3().invertTree(root)
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, tree={2}")
    @MethodSource("allCombinations")
    void testInvertTree(String caseName, String algoName, Integer[] input, Integer[] expected) {
        TreeNode root = BinaryTreeBuilder.build(input);
        TreeNode expectedTree = BinaryTreeBuilder.build(expected);
        TreeNode actualTree = ALGO_VARIANTS.get(algoName).apply(root);
        assertTrue(BinaryTreeUtility.isSameTree(expectedTree, actualTree),
                () -> String.format("Case '%s' with algo='%s' failed.", caseName, algoName));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.input, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1: [4,2,7,1,3,6,9] -> [4,7,2,9,6,3,1]
                new TestCase("example_1",
                        new Integer[]{4, 2, 7, 1, 3, 6, 9},
                        new Integer[]{4, 7, 2, 9, 6, 3, 1}),

                // Example 2: [2,1,3] -> [2,3,1]
                new TestCase("example_2",
                        new Integer[]{2, 1, 3},
                        new Integer[]{2, 3, 1}),

                // Single node
                new TestCase("single_node",
                        new Integer[]{1},
                        new Integer[]{1}),

                // Empty tree
                new TestCase("empty_tree",
                        new Integer[]{},
                        new Integer[]{}),

                // Skewed left: [1,2,null,3] -> [1,null,2,null,null,null,3]
                new TestCase("left_skewed",
                        new Integer[]{1, 2, null, 3},
                        new Integer[]{1, null, 2, null, null, null, 3}),

                // Full tree depth 3
                new TestCase("full_tree",
                        new Integer[]{1, 2, 3, 4, 5, 6, 7},
                        new Integer[]{1, 3, 2, 7, 6, 5, 4})
        );
    }

    private record TestCase(String name, Integer[] input, Integer[] expected) {
    }

}
