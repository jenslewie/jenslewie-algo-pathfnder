package org.example.leetcode.global;

import org.example.model.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal">...</a>
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class LeetCode0105_2 {

    private final Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return dfs(preorder, 0, n, 0);
    }

    private TreeNode dfs(int[] preorder, int preorderLeft, int preorderRight, int inorderLeft) {
        if (preorderLeft == preorderRight) {
            return null;
        }
        int leftSize = map.get(preorder[preorderLeft]) - inorderLeft;
        TreeNode left = dfs(preorder, preorderLeft + 1, preorderLeft + 1 + leftSize, inorderLeft);
        TreeNode right = dfs(preorder, preorderLeft + 1 + leftSize, preorderRight, inorderLeft + 1 + leftSize);
        return new TreeNode(preorder[preorderLeft], left, right);
    }

}
