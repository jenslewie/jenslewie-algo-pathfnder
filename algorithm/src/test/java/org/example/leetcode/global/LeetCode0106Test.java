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

@DisplayName("LeetCode 106: Construct Binary Tree from Inorder and Postorder Traversal")
class LeetCode0106Test {

    @FunctionalInterface
    interface BuildTreeFunction {
        TreeNode apply(int[] inorder, int[] postorder);
    }

    private static final Map<String, BuildTreeFunction> ALGO_VARIANTS = Map.of(
            "recursive_divide_conquer_with_hash_map",
            (in, post) -> new LeetCode0106().buildTree(in, post)
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, inorder={2}, postorder={3}")
    @MethodSource("allCombinations")
    void testBuildTree(String caseName, String algoName, int[] inorder, int[] postorder, Integer[] expectedTree) {
        TreeNode actual = ALGO_VARIANTS.get(algoName).apply(inorder, postorder);
        TreeNode expected = BinaryTreeBuilder.build(expectedTree);
        assertTrue(BinaryTreeUtility.isSameTree(expected, actual),
                () -> String.format("Case '%s' with algo='%s' failed.", caseName, algoName));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.inorder, tc.postorder, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example 1: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
                // Expected: [3,9,20,null,null,15,7]
                new TestCase("example_1",
                        new int[]{9, 3, 15, 20, 7},
                        new int[]{9, 15, 7, 20, 3},
                        new Integer[]{3, 9, 20, null, null, 15, 7}),

                // Example 2: [-1]
                new TestCase("example_2",
                        new int[]{-1},
                        new int[]{-1},
                        new Integer[]{-1}),

                // Single node
                new TestCase("single_node",
                        new int[]{1},
                        new int[]{1},
                        new Integer[]{1}),

                // Left-skewed tree
                // inorder: [4,3,2,1], postorder: [4,3,2,1] → tree: 1 <- 2 <- 3 <- 4 (left chain)
                new TestCase("left_skewed",
                        new int[]{4, 3, 2, 1},
                        new int[]{4, 3, 2, 1},
                        new Integer[]{1, 2, null, 3, null, null, null, 4}),

                // Right-skewed tree
                // inorder: [1,2,3,4], postorder: [4,3,2,1] → tree: 1 -> 2 -> 3 -> 4 (right chain)
                new TestCase("right_skewed",
                        new int[]{1, 2, 3, 4},
                        new int[]{4, 3, 2, 1},
                        new Integer[]{1, null, 2, null, null, null, 3, null, null, null, null, null, null, null, 4}),

                // Complex asymmetric tree
                // Tree:
                //         1
                //        / \
                //       2   3
                //      /   / \
                //     4   5   6
                //        /
                //       7
                // inorder:  [4,2,1,7,5,3,6]
                // postorder: [4,2,7,5,6,3,1]
                new TestCase("complex_asymmetric",
                        new int[]{4, 2, 1, 7, 5, 3, 6},
                        new int[]{4, 2, 7, 5, 6, 3, 1},
                        new Integer[]{1, 2, 3, 4, null, 5, 6, null, null, null, null, 7}),

                // Full binary tree depth 2
                new TestCase("full_tree_depth_2",
                        new int[]{2, 1, 3},
                        new int[]{2, 3, 1},
                        new Integer[]{1, 2, 3})
        );
    }

    private record TestCase(String name, int[] inorder, int[] postorder, Integer[] expected) {
    }

}
