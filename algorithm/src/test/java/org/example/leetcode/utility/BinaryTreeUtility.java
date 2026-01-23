package org.example.leetcode.utility;

import org.example.model.tree.TreeNode;

import java.util.Objects;

public class BinaryTreeUtility {

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static boolean isSameNextStructure(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        Integer aNextVal = (a.next != null) ? a.next.val : null;
        Integer bNextVal = (b.next != null) ? b.next.val : null;

        return a.val == b.val &&
                Objects.equals(aNextVal, bNextVal) &&
                isSameNextStructure(a.left, b.left) &&
                isSameNextStructure(a.right, b.right);
    }
}
