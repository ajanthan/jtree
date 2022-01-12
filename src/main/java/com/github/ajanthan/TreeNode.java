package com.github.ajanthan;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public Integer[] toArray() {
        List<Integer> result = new ArrayList<>();
        toArrayHelper(this, result);
        while (result.get(result.size() - 1) == null) result.remove(result.size() - 1);
        return result.toArray(new Integer[0]);
    }

    private void toArrayHelper(TreeNode root, List<Integer> result) {
        if (root == null) {
            result.add(null);
            return;
        }
        result.add(root.val);
        toArrayHelper(root.left, result);
        toArrayHelper(root.right, result);
    }

    public static TreeNode getTree(Integer[] tree) {
        Result result = getTree(tree, 0);
        return result == null ? null : result.root;
    }

    private static Result getTree(Integer[] tree, int pos) {
        if (tree == null || tree.length == 0 || pos >= tree.length) return null;
        if (tree[pos] == null) return new Result(pos);
        int nextPos;
        TreeNode root = new TreeNode(tree[pos]);
        Result result = getTree(tree, pos + 1);
        if (result != null) {
            root.left = result.root;
            nextPos = result.pos;
        } else {
            nextPos = pos + 1;
        }
        result = getTree(tree, nextPos + 1);
        if (result != null) {
            root.right = result.root;
            nextPos = result.pos;
        } else {
            nextPos = pos + 1;
        }
        return new Result(root, nextPos);
    }

    private static class Result {
        TreeNode root;
        int pos;

        public Result(int pos) {
            this.pos = pos;
        }

        public Result(TreeNode root, int pos) {
            this.root = root;
            this.pos = pos;
        }
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
