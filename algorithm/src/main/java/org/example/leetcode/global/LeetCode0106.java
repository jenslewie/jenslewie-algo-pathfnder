package org.example.leetcode.global;

import org.example.model.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal">...</a>
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class LeetCode0106 {

    private final Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return dfs(postorder, 0, n, 0, n);
    }

    private TreeNode dfs(int[] postorder, int inorderLeft, int inorderRight, int postorderLeft, int postorderRight) {
        if (inorderLeft == inorderRight) {
            return null;
        }
        int rootIndex = map.get(postorder[postorderRight - 1]);
        int leftSize = rootIndex - inorderLeft;
        TreeNode left = dfs(postorder, inorderLeft, rootIndex, postorderLeft, postorderLeft + leftSize);
        TreeNode right = dfs(postorder, rootIndex + 1, inorderRight, postorderLeft + leftSize, postorderRight - 1);
        return new TreeNode(postorder[postorderRight - 1], left, right);
    }

}
