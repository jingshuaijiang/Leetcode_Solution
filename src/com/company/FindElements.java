package com.company;
import java.util.*;

public class FindElements {
    TreeNode root;

    public FindElements(TreeNode root) {
        if(root==null)
        {
            this.root = null;
        }
        else
        {
            int val = 0;
            ConstructHelper(root,val);
        }
        this.root = root;
    }

    public void ConstructHelper(TreeNode node,int value)
    {
        node.val = value;
        if(node.left!=null)
            ConstructHelper(node.left,2*value+1);
        if(node.right!=null)
            ConstructHelper(node.right,2*value+2);
    }

    public boolean Num1261find(int target) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this.root);
        while(!queue.isEmpty())
        {
            TreeNode node = queue.poll();
            if(node.val==target)
                return true;
            if(node.left!=null)
                queue.add(node.left);
            if(node.right!=null)
                queue.add(node.right);
        }
        return false;
    }
}
