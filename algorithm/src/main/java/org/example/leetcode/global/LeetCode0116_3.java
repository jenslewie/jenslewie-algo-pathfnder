package org.example.leetcode.global;

import org.example.model.tree.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/populating-next-right-pointers-in-each-node">...</a>
 */
public class LeetCode0116_3 {

    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left != null) {
            root.left.next = root.right;
            root.right.next = root.next == null ? null : root.next.left;
            connect(root.left);
            connect(root.right);
        }

        return root;
    }

}
