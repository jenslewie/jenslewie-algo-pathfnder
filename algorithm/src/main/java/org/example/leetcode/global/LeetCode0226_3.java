package org.example.leetcode.global;

import org.example.model.tree.TreeNode;

import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/invert-binary-tree">...</a>
 */
public class LeetCode0226_3 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }

        return root;
    }

}
