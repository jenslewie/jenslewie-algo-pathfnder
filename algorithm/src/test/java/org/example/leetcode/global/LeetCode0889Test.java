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

@DisplayName("LeetCode 889: Construct Binary Tree from Preorder and Postorder Traversal")
class LeetCode0889Test {

    @FunctionalInterface
    interface ConstructFromPrePostFunction {
        TreeNode apply(int[] preorder, int[] postorder);
    }

    private static final Map<String, ConstructFromPrePostFunction> ALGO_VARIANTS = Map.of(
            "recursive_divide_conquer_with_hash_map",
            (pre, post) -> new LeetCode0889().constructFromPrePost(pre, post)
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, preorder={2}, postorder={3}")
    @MethodSource("allCombinations")
    void testConstructFromPrePost(String caseName, String algoName, int[] preorder, int[] postorder, Integer[] expectedTree) {
        TreeNode actual = ALGO_VARIANTS.get(algoName).apply(preorder, postorder);
        TreeNode expected = BinaryTreeBuilder.build(expectedTree);
        assertTrue(BinaryTreeUtility.isSameTree(expected, actual),
                () -> String.format("Case '%s' with algo='%s' failed.", caseName, algoName));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.preorder, tc.postorder, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // === Official Example 1===
                new TestCase("example_1",
                        new int[]{1, 2, 4, 5, 3, 6, 7},
                        new int[]{4, 5, 2, 6, 7, 3, 1},
                        new Integer[]{1, 2, 3, 4, 5, 6, 7}),

                // === Official Example 2===
                new TestCase("example_2",
                        new int[]{1},
                        new int[]{1},
                        new Integer[]{1}),

                // === Deep Left Chain (only left children) ===
                // Tree: 1 <- 2 <- 3 <- 4
                // Pre: [1,2,3,4], Post: [4,3,2,1]
                new TestCase("deep_left_chain",
                        new int[]{1, 2, 3, 4},
                        new int[]{4, 3, 2, 1},
                        new Integer[]{1, 2, null, 3, null, null, null, 4}),

                // === Complex Asymmetric Tree ===
                // Tree:
                //         1
                //        / \
                //       2   3
                //      /   / \
                //     4   5   6
                //        /
                //       7
                // Preorder: [1,2,4,3,5,7,6]
                // Postorder: [4,2,7,5,6,3,1]
                new TestCase("complex_asymmetric",
                        new int[]{1, 2, 4, 3, 5, 7, 6},
                        new int[]{4, 2, 7, 5, 6, 3, 1},
                        new Integer[]{1, 2, 3, 4, null, 5, 6, null, null, null, null, 7}),

                // === Full binary tree depth 3 (7 nodes) ===
                // Pre: [1,2,4,5,3,6,7]
                // Post: [4,5,2,6,7,3,1]
                // Same as example_1, but explicitly tested
                new TestCase("full_tree_depth_3",
                        new int[]{1, 2, 4, 5, 3, 6, 7},
                        new int[]{4, 5, 2, 6, 7, 3, 1},
                        new Integer[]{1, 2, 3, 4, 5, 6, 7})
        );
    }

    private record TestCase(String name, int[] preorder, int[] postorder, Integer[] expected) {
    }

}
