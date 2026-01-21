package org.example.learning.tree.binarytree;

import org.example.builder.BinaryTreeBuilder;
import org.example.model.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {

    public static void main(String[] args) {
        TreeNode root = BinaryTreeBuilder.buildTree(new Integer[] {1, 2, 3, null, 5, 6}, 0);
        DepthFirstSearch dfs = new DepthFirstSearch();

        System.out.print("Preorder Traversal : \n");
        dfs.PreOrderTraversal(root);
        System.out.println();
        System.out.println(dfs.PreOrderTraversal2(root));
        System.out.println();

        System.out.print("Inorder Traversal : \n");
        dfs.InOrderTraversal(root);
        System.out.println();
        System.out.println(dfs.InOrderTraversal2(root));
        System.out.println();

        System.out.print("Postorder Traversal : \n");
        dfs.PostOrderTraversal(root);
        System.out.println();
        System.out.println(dfs.PostOrderTraversal2(root));
        System.out.println();
    }

    public void PreOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        PreOrderTraversal(root.left);
        PreOrderTraversal(root.right);
    }

    public List<Integer> PreOrderTraversal2(TreeNode root) {
        var ans = new ArrayList<Integer>();
        if (root == null) {
            return ans;
        }

        ans.add(root.val);
        ans.addAll(PreOrderTraversal2(root.left));
        ans.addAll(PreOrderTraversal2(root.right));
        return ans;
    }

    public void InOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        InOrderTraversal(root.left);
        System.out.print(root.val + " ");
        InOrderTraversal(root.right);
    }

    public List<Integer> InOrderTraversal2(TreeNode root) {
        var ans = new ArrayList<Integer>();
        if (root == null) {
            return ans;
        }

        ans.addAll(InOrderTraversal2(root.left));
        ans.add(root.val);
        ans.addAll(InOrderTraversal2(root.right));
        return ans;
    }

    public void PostOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        PostOrderTraversal(root.left);
        PostOrderTraversal(root.right);
        System.out.print(root.val + " ");
    }

    public List<Integer> PostOrderTraversal2(TreeNode root) {
        var ans = new ArrayList<Integer>();
        if (root == null) {
            return ans;
        }

        ans.addAll(PostOrderTraversal2(root.left));
        ans.addAll(PostOrderTraversal2(root.right));
        ans.add(root.val);
        return ans;
    }
}
