package org.example.builder;

import org.example.model.tree.TreeNode;

public class BinaryTreeBuilder {

    public static TreeNode build(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }
        return buildTree(arr, 0);
    }

    public static TreeNode buildTree(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }

        TreeNode node = new TreeNode(arr[index]);
        node.left = buildTree(arr, 2 * index + 1);
        node.right = buildTree(arr, 2 * index + 2);

        return node;
    }

    public static TreeNode buildTreeWithNext(Integer[] treeVals, Integer[] nextVals) {
        if (treeVals.length == 0) return null;
        TreeNode[] nodes = new TreeNode[treeVals.length];

        for (int i = 0; i < treeVals.length; i++) {
            if (treeVals[i] != null) {
                nodes[i] = new TreeNode(treeVals[i]);
            }
        }

        for (int i = 0; i < treeVals.length; i++) {
            if (nodes[i] != null) {
                int leftIdx = 2 * i + 1;
                int rightIdx = 2 * i + 2;
                if (leftIdx < treeVals.length) nodes[i].left = nodes[leftIdx];
                if (rightIdx < treeVals.length) nodes[i].right = nodes[rightIdx];

                if (nextVals[i] != null) {
                    int levelStart = levelStartIndex(i);
                    int levelEnd = Math.min(levelStart * 2 + 1, treeVals.length);
                    for (int j = i + 1; j < levelEnd; j++) {
                        if (nodes[j] != null && nodes[j].val == nextVals[i]) {
                            nodes[i].next = nodes[j];
                            break;
                        }
                    }
                }
            }
        }
        return nodes[0];
    }

    private static int levelStartIndex(int i) {
        int level = 0;
        while ((1 << level) - 1 <= i) level++;
        return (1 << (level - 1)) - 1;
    }

}
