package com.company;

import java.util.*;

public class Solution {

    public int Num938rangeSumBST(TreeNode root, int L, int R) {
        int[] ans = new int[1];
        Num938RangeSumBST(root,L,R,ans);
        return ans[0];
    }
    public void Num938RangeSumBST(TreeNode node,int L, int R,int[] ans)
    {
        if(node ==null) return;
        if(node.val >=L&&node.val<=R)
        {
            ans[0]+=node.val;
            Num938RangeSumBST(node.left,L,R,ans);
            Num938RangeSumBST(node.right,L,R,ans);
        }
        else if(node.val<L)
        {
            Num938RangeSumBST(node.right,L,R,ans);
        }
        else if(node.val>R)
        {
            Num938RangeSumBST(node.left,L,R,ans);
        }

    }
    public TreeNode Num700searchBST(TreeNode root, int val) {
        if(root==null) return null;
        if(root.val<val) return Num700searchBST(root.right,val);
        if(root.val>val) return Num700searchBST(root.left,val);
        return root;
    }
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new LinkedList<>();
        Num589Preorder(root,ans);
        return ans;
    }
    public void Num589Preorder(Node root,List<Integer> ans)
    {
        if(root==null) return;
        ans.add(root.val);
        for(Node child:root.children)
        {
            Num589Preorder(child,ans);
        }
    }
    public int Num559maxDepth(Node root) {
        int[] ans = new int[1];
        Num559Depth(root,ans,1);
        return ans[0];
    }
    public void Num559Depth(Node root,int[] ans,int depth)
    {
        if(root==null) return;
        if(depth >ans[0]) ans[0] = depth;
        for(Node child : root.children)
        {
            Num559Depth(child,ans,depth+1);
        }
    }
    public boolean Num965isUnivalTree(TreeNode root) {
        if(root==null) return true;
        HashSet<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            TreeNode node = queue.remove();
            if(set.contains(node.val))
            {

            }
            else
            {
                set.add(node.val);
                if(set.size()>=2) return false;
            }
            if(node.left!=null) queue.add(node.left);
            if(node.right!=null)queue.add(node.right);
        }
        return true;
    }
    public boolean Num872leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList();
        List<Integer> leaves2 = new ArrayList();
        dfs(root1, leaves1);
        dfs(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    public void dfs(TreeNode node, List<Integer> leafValues) {
        if (node != null) {
            if (node.left == null && node.right == null)
                leafValues.add(node.val);
            dfs(node.left, leafValues);
            dfs(node.right, leafValues);
        }
    }
    public int Num1022sumRootToLeaf(TreeNode root) {
        int[] ans = new int[1];
        Num1022Sumroottoleaf(root,ans);
        return ans[0];
    }
    public void Num1022Sumroottoleaf(TreeNode root,int[] ans,string forpart)
    {
        if(root==null) return;
        if(root.left==null&&root.right==null)
    }
    public TreeNode Num897increasingBST(TreeNode root) {
        List<Integer> vals = new ArrayList();
        inorder(root, vals);
        TreeNode ans = new TreeNode(0), cur = ans;
        for (int v: vals) {
            cur.right = new TreeNode(v);
            cur = cur.right;
        }
        return ans.right;
    }

    public void inorder(TreeNode node, List<Integer> vals) {
        if (node == null) return;
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }
    public int Num104maxDepth(TreeNode root) {
        if(root==null) return 0;
        int leftdepth = Num104maxDepth(root.left);
        int rightdepth = Num104maxDepth(root.right);
        return Math.max(leftdepth,rightdepth)+1;
    }

    public TreeNode Num226invertTree(TreeNode root) {
        if(root==null) return null;
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        Num226invertTree(root.left);
        Num226invertTree(root.right);
        return root;
    }
    public List<Double> Nu637averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            int sum = 0;
            int size = queue.size();
            for(int i=0;i<size;i++)
            {
                TreeNode node = queue.remove();
                sum+=node.val;
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            ans.add((double)sum/size);
        }
        return ans;
    }
    public TreeNode Num669trimBST(TreeNode root, int L, int R) {
        if(root==null) return null;
        if(root.val<L)
        {
            root = Num669trimBST(root.right,L,R);
            return root;
        }else if(root.val > R)
        {
            root = Num669trimBST(root.left,L,R);
            return root;
        }
        root.left = Num669trimBST(root.left,L,R);
        root.right = Num669trimBST(root.right,L,R);
        return root;
    }
//    public TreeNode Num108sortedArrayToBST(int[] nums) {
//
//    }
    public boolean Num653findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return Num653find(root,k,set);
    }
    public boolean Num653find(TreeNode root,int k,Set<Integer> set)
    {
        if(root==null) return false;
        if(set.contains(k-root.val)) return true;
        set.add(root.val);
        return Num653find(root.left,k,set)||Num653find(root.right,k,set);
    }
    public TreeNode Num538convertBST(TreeNode root) {
        if(root==null) return null;
        if()
    }
    public int Num530getMinimumDifference(TreeNode root) {
        int[] ans = new int[2];
        ans[0] = Integer.MAX_VALUE;
        ans[1] = Integer.MAX_VALUE;
        Num530GetMinD(root,ans);
        return ans[0];
    }
    public void Num530GetMinD(TreeNode root,int[] ans)
    {
        if(root==null) return;
        Num530GetMinD(root.left,ans);
        if(ans[0] ==Integer.MAX_VALUE&&ans[1]==Integer.MAX_VALUE)
        {

        }
        else if(Math.abs(root.val-ans[1])<ans[0])
        {
            ans[0] = Math.abs(root.val-ans[1]);
        }
        ans[1] = root.val;
        Num530GetMinD(root.right,ans);
    }

}
