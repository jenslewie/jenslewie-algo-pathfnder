package org.example.model.tree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    public int val;
    public TreeNode left, right, next;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
