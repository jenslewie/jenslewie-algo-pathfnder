package org.example.leetcode.global;

import org.example.model.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal">...</a>
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class LeetCode0889 {

    private final Map<Integer, Integer> map = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = postorder.length;
        for (int i = 0; i < n; i++) {
            map.put(postorder[i], i);
        }
        return dfs(preorder, 0, n, 0, n);
    }

    private TreeNode dfs(int[] preorder, int preLeft, int preRight, int postLeft, int postRight) {
        if (preLeft == preRight) {
            return null;
        }

        int leftRootIndex = preLeft + 1;
        int leftSize = leftRootIndex == preRight ? 0 : map.get(preorder[leftRootIndex]) + 1 - postLeft;
        TreeNode left = dfs(preorder, leftRootIndex, leftRootIndex + leftSize, postLeft, postLeft + leftSize);
        TreeNode right = dfs(preorder, leftRootIndex + leftSize, preRight, postLeft + leftSize, postRight - 1);
        return new TreeNode(preorder[preLeft], left, right);
    }

}
