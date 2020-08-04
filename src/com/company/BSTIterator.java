package com.company;

import java.util.Stack;

/**
 * needs to be done again.
 */
public class BSTIterator {

    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        this.stack = new Stack<TreeNode>();
        this.leftmostinorder(root);
    }

    private void leftmostinorder(TreeNode node)
    {
        while(node!=null)
        {
            this.stack.push(node);
            node = node.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode lnode = this.stack.pop();
        if(lnode.right!=null)
            leftmostinorder(lnode.right);
        return lnode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !this.stack.isEmpty();
    }
}
