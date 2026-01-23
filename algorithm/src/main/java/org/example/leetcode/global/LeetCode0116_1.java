package org.example.leetcode.global;

import org.example.model.tree.TreeNode;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/populating-next-right-pointers-in-each-node">...</a>
 */
public class LeetCode0116_1 {

    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }

        var queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i != size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return root;
    }

}
