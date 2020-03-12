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
 /*   public TreeNode Num538convertBST(TreeNode root) {
        if(root==null) return null;
        if()
    }*/
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
    public boolean Num100isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        else if((p==null&& q!=null ) || (q==null&& p!=null)) return false;
        boolean current = p.val==q.val;
        return Num100isSameTree(p.left,q.left) && Num100isSameTree(p.right,q.right) && current;
    }
    /*
    * Code for leetcode Num783
    * */
    List<Integer> vals;
    public int Num783minDiffInBST(TreeNode root) {
        vals = new ArrayList();
        Num783dfs(root);
        Collections.sort(vals);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < vals.size() - 1; ++i)
            ans = Math.min(ans, vals.get(i+1) - vals.get(i));

        return ans;
    }

    public void Num783dfs(TreeNode node) {
        if (node == null) return;
        vals.add(node.val);
        Num783dfs(node.left);
        Num783dfs(node.right);
    }
    public int Num404sumOfLeftLeaves(TreeNode root) {
        int[] ans = new int[1];
        if(root.left==null&&root.right==null) return 0;
        Num404SumOfLeftLeaves(root,"left",ans);
        return ans[0];
    }
    public void Num404SumOfLeftLeaves(TreeNode root,String direction,int[] ans)
    {
        if(root==null) return;
        if(direction == "left")
        {
            if(root.left==null&&root.right==null)
            {
                ans[0]+=root.val;
            }
        }
        Num404SumOfLeftLeaves(root.left,"left",ans);
        Num404SumOfLeftLeaves(root.right,"right",ans);
    }
    public boolean Num993isCousins(TreeNode root, int x, int y) {
        if(root==null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            int size = queue.size();
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int i=0;i<size;i++)
            {
                TreeNode node = queue.remove();
                if(node.left!=null)
                {
                    queue.add(node.left);
                    map.put(node.left.val,node.val);
                }
                if(node.right!=null)
                {
                    queue.add(node.right);
                    map.put(node.right.val,node.val);
                }
            }
            if(map.containsKey(x)&&map.containsKey(y) && map.get(x)!=map.get(y))
            {
                return true;
            }
            map.clear();
        }
        return false;
    }
    /*
    * Code for Leetcode543 focus on the height of each node*/
    public int Num543diameterOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        int[] max = new int[1];
        Num543DOBT(root,max);
        return max[0];
    }
    public int Num543DOBT(TreeNode root,int[] max)
    {
        if(root==null) return 0;
        int left = Num543DOBT(root.left,max);
        int right = Num543DOBT(root.right,max);
        if(left+right > max[0])
        {
            max[0] = left+right;
        }
        return Math.max(left,right)+1;
    }
    public List<List<Integer>> Num107levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root==null) return ans;
        int depth = 0;
        Num107DFS(root,depth,ans);
        return ans;
    }
    public void Num107DFS(TreeNode root,int depth,List<List<Integer>> ans)
    {
        if(root==null) return ;
        if(ans.size()<=depth)
        {
            ans.add(0,new LinkedList<>());
        }
        ans.get(ans.size()-1-depth).add(root.val);
        Num107DFS(root.left,depth+1,ans);
        Num107DFS(root.right,depth+1,ans);
    }
    public List<List<Integer>> Num107AlevelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root==null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            int size = queue.size();
            List<Integer> level = new LinkedList<>();
            for(int i=0;i<size;i++)
            {
                TreeNode node = queue.remove();
                level.add(node.val);
                if(node.left!=null)
                {
                    queue.add(node.left);
                }
                if(node.right!=null)
                    queue.add(node.right);
            }
            ans.add(0,level);
        }
       return ans;
    }
    public List<String> Num257binaryTreePaths(TreeNode root) {
        List<String> ans = new LinkedList<>();
        if(root==null) return ans;
        Num257BTP(root,ans,"");
        return ans;
    }
    public void Num257BTP(TreeNode node,List<String> ans, String ancestor)
    {

        if(node.left==null&&node.right==null)
        {
            ancestor=ancestor+"->"+String.valueOf(node.val);
            ancestor = ancestor.substring(2,ancestor.length());
            ans.add(ancestor);
            return;
        }
        if(node.left!=null )Num257BTP(node.left,ans,ancestor+"->"+String.valueOf(node.val));
        if(node.right!=null)Num257BTP(node.right,ans,ancestor+"->"+String.valueOf(node.val));
    }
    public boolean Num101isSymmetric(TreeNode root) {
        TreeNode root1 = root;
        return Num101sym(root1,root);
    }
    public boolean Num101sym(TreeNode root1,TreeNode root)
    {
        if(root1==null&&root==null) return true;
        else if(root1!=null &&root!=null && root1.val ==root.val)
        {
            return true && Num101sym(root1.left,root.right) && Num101sym(root1.right,root.left);
        }
        else return false;
    }
    /*
    * code for leetcode Num572*/
    HashSet < String > trees = new HashSet < > ();
    public boolean Num572isSubtree(TreeNode s, TreeNode t) {
        String tree1 = Num572preorder(s, true);
        String tree2 = Num572preorder(t, true);
        return tree1.indexOf(tree2) >= 0;
    }
    public String Num572preorder(TreeNode t, boolean left) {
        if (t == null) {
            if (left)
                return "lnull";
            else
                return "rnull";
        }
        return "#"+t.val + " " +Num572preorder(t.left, true)+" " +Num572preorder(t.right, false);
    }
    public void Num270inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int Num270closestValue(TreeNode root, double target) {
        List<Integer> nums = new ArrayList();
        Num270inorder(root, nums);
        return Collections.min(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });
    }
    /*code for Leetcode 563*/
    int tilt = 0;
    public int Num563findTilt(TreeNode root) {
        Num563traverse(root);
        return tilt;
    }
    public int Num563traverse(TreeNode root)
    {
        if (root == null )
            return 0;
        int left = Num563traverse(root.left);
        int right = Num563traverse(root.right);
        tilt += Math.abs(left-right);
        return left + right + root.val;
    }


    public void Num671dfs(TreeNode root, Set<Integer> uniques) {
        if (root != null) {
            uniques.add(root.val);
            Num671dfs(root.left, uniques);
            Num671dfs(root.right, uniques);
        }
    }
    public int Num671findSecondMinimumValue(TreeNode root) {
        Set<Integer> uniques = new HashSet<Integer>();
        Num671dfs(root, uniques);

        int min1 = root.val;
        long ans = Long.MAX_VALUE;
        for (int v : uniques) {
            if (min1 < v && v < ans) ans = v;
        }
        return ans < Long.MAX_VALUE ? (int) ans : -1;
    }

    public boolean Num110isBalanced(TreeNode root) {
        if(root==null) return true;
        return Math.abs(Num110IBT(root.left) - Num110IBT(root.right)) <2 && Num110isBalanced(root.left) && Num110isBalanced(root.right);
    }
    public int Num110IBT(TreeNode root)
    {
        if(root==null) return 0;
        return Math.max(Num110IBT(root.left),Num110IBT(root.right))+1;
    }

    public int Num437pathSum(TreeNode root, int sum) {
        if(root==null) return 0;
        List<String> paths = new ArrayList<>();
        String values="";
        Num437DFSpathSearch(root,sum,paths,values);
        return paths.size();
    }
    public void Num437DFSpathSearch(TreeNode node,int sum,List<String> paths,String values)
    {
        if(node==null) return;
        values+=String.valueOf(sum);
        String[] allvalues = values.split(" ");
        String valu ="";
        for(String value :allvalues)
        {
            if(Integer.valueOf(value)-node.val==0)
            {
                paths.add("finish");
            }
            value = String.valueOf(Integer.valueOf(value)-node.val);
            valu=valu+value+" ";
        }
        Num437DFSpathSearch(node.left,sum,paths,valu);
        Num437DFSpathSearch(node.right,sum,paths,valu);
    }

    public TreeNode Num235lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while(node!=null)
        {
            if(p.val >node.val &&q.val >node.val) node = node.right;
            else if(p.val<node.val && q.val <node.val) node = node.left;
            else return node;
        }
        return null;
    }

}
