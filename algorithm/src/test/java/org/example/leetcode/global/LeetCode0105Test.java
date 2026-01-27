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

@DisplayName("LeetCode 105: Construct Binary Tree from Preorder and Inorder Traversal")
class LeetCode0105Test {

    @FunctionalInterface
    interface BuildTreeFunction {
        TreeNode apply(int[] preorder, int[] inorder);
    }

    // Naming: [recursive/iterative]_[divide_conquer]_with_[linear_scan/hash_map]
    private static final Map<String, BuildTreeFunction> ALGO_VARIANTS = Map.of(
            "recursive_divide_conquer_with_linear_scan",
            (pre, in) -> new LeetCode0105_1().buildTree(pre, in),

            "recursive_divide_conquer_with_hash_map",
            (pre, in) -> new LeetCode0105_2().buildTree(pre, in)
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, preorder={2}, inorder={3}")
    @MethodSource("allCombinations")
    void testBuildTree(String caseName, String algoName, int[] preorder, int[] inorder, Integer[] expectedTree) {
        TreeNode actual = ALGO_VARIANTS.get(algoName).apply(preorder, inorder);
        TreeNode expected = BinaryTreeBuilder.build(expectedTree);
        assertTrue(BinaryTreeUtility.isSameTree(expected, actual),
                () -> String.format("Case '%s' with algo='%s' failed.", caseName, algoName));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.preorder, tc.inorder, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // === Official Examples ===
                new TestCase("example_1",
                        new int[]{3, 9, 20, 15, 7},
                        new int[]{9, 3, 15, 20, 7},
                        new Integer[]{3, 9, 20, null, null, 15, 7}),

                new TestCase("example_2",
                        new int[]{-1},
                        new int[]{-1},
                        new Integer[]{-1}),

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
                // Inorder:  [4,2,1,7,5,3,6]
                new TestCase("complex_asymmetric",
                        new int[]{1, 2, 4, 3, 5, 7, 6},
                        new int[]{4, 2, 1, 7, 5, 3, 6},
                        new Integer[]{1, 2, 3, 4, null, 5, 6, null, null, null, null, 7}),

                // === Deep Left Subtree, Shallow Right ===
                // Tree:
                //     1
                //    / \
                //   2   5
                //  /
                // 3
                ///
                //4
                // Preorder: [1,2,3,4,5]
                // Inorder:  [4,3,2,1,5]
                new TestCase("deep_left_shallow_right",
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{4, 3, 2, 1, 5},
                        new Integer[]{1, 2, 5, 3, null, null, null, 4}),

                // === Deep Right Subtree, Shallow Left ===
                // Tree:
                // 1
                //  \
                //   2
                //    \
                //     3
                //      \
                //       4
                //        \
                //         5
                // Preorder: [1,2,3,4,5]
                // Inorder:  [1,2,3,4,5]
                new TestCase("deep_right_only",
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{1, 2, 3, 4, 5},
                        new Integer[]{1, null, 2, null, null, null, 3, null, null, null, null, null, null, null, 4,
                                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 5}),

                // === Large Balanced Tree (depth=3, 7 nodes) ===
                // Preorder: [4,2,1,3,6,5,7]
                // Inorder:  [1,2,3,4,5,6,7]
                new TestCase("balanced_depth_3",
                        new int[]{4, 2, 1, 3, 6, 5, 7},
                        new int[]{1, 2, 3, 4, 5, 6, 7},
                        new Integer[]{4, 2, 6, 1, 3, 5, 7}),

                // === Zigzag Tree ===
                //     1
                //    /
                //   2
                //    \
                //     3
                //    /
                //   4
                // Preorder: [1,2,3,4]
                // Inorder:  [2,4,3,1]
                new TestCase("zigzag",
                        new int[]{1, 2, 3, 4},
                        new int[]{2, 4, 3, 1},
                        new Integer[]{1, 2, null, null, 3, null, null, null, null, 4}),

                // === Only Root with Two Children ===
                new TestCase("root_two_children",
                        new int[]{1, 2, 3},
                        new int[]{2, 1, 3},
                        new Integer[]{1, 2, 3}),

                // === Single Node ===
                new TestCase("single_node",
                        new int[]{42},
                        new int[]{42},
                        new Integer[]{42})
        );
    }

    private record TestCase(String name, int[] preorder, int[] inorder, Integer[] expected) {
    }

}
