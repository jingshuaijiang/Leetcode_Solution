package com.company;
import java.util.*;

public class Solution_Tree {


    public int Num1448goodNodes(TreeNode root) {
        int[] sum = new int[1];
        if(root==null)
            return 0;
        Num1448Helper(root,sum,root.val);
        return sum[0];
    }

    public void Num1448Helper(TreeNode node, int[] sum, int maxval)
    {
        if(node==null)
            return;
        if(maxval<=node.val)
            sum[0]+=1;
        maxval = Math.max(maxval,node.val);
        Num1448Helper(node.left,sum,maxval);
        Num1448Helper(node.right,sum,maxval);
    }

    public List<TreeNode> Num1110delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new LinkedList<>();
        boolean[] found = new boolean[to_delete.length];
        if(root==null)
            return ans;
        boolean roo = false;
        Num1110helper(root,ans,to_delete,found,null,"");
        for(int i=0;i<to_delete.length;i++)
        {
            if(to_delete[i]==root.val)
                roo = true;
        }
        if(!roo)
            ans.add(root);
        return ans;
    }

    public void Num1110helper(TreeNode node, List<TreeNode> ans, int[] to_delete, boolean[] found,TreeNode p,String direction)
    {
        if(node==null)
            return;
        Num1110helper(node.left,ans,to_delete,found,node,"left");
        Num1110helper(node.right,ans,to_delete,found,node,"right");
        for(int i=0;i<to_delete.length;i++)
        {
            if(found[i])
                continue;
            if(node.val==to_delete[i])
            {
                found[i] = true;
                if(node.left!=null)
                    ans.add(node.left);
                if(node.right!=null)
                    ans.add(node.right);
                if(direction.equals("left"))
                    p.left = null;
                else if(direction.equals("right"))
                    p.right = null;
                break;
            }
        }
    }

    public int Num1026maxAncestorDiff(TreeNode root) {
        int[] diff = new int[1];
        Num1026Helper(root,diff,root.val,root.val);
        return diff[0];
    }

    public void Num1026Helper(TreeNode node,int[] diff,int min,int max)
    {
        if(node==null)
            return;
        diff[0] = Math.max(diff[0],Math.max(Math.abs(node.val-max),Math.abs(node.val-min)));
        max = Math.max(max,node.val);
        min = Math.min(min,node.val);
        Num1026Helper(node.left,diff,min,max);
        Num1026Helper(node.right,diff,min,max);
    }

    public List<List<Integer>> Num429levelOrder(Node root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root==null)
            return ans;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            int length = queue.size();
            List<Integer> levellist = new LinkedList<>();
            for(int i=0;i<length;i++)
            {
                Node node = queue.poll();
                levellist.add(node.val);
                for(Node child: node.children)
                {
                    queue.add(child);
                }
            }
            ans.add(levellist);
        }
        return ans;
    }

    public boolean Num951flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1==root2)
            return true;
        if(root1==null||root2==null||root1.val!=root2.val)
            return false;
        return (Num951flipEquiv(root1.left, root2.left) && Num951flipEquiv(root1.right, root2.right) ||
                Num951flipEquiv(root1.left, root2.right) && Num951flipEquiv(root1.right, root2.left));
    }

    public List<Integer> Num515largestValues(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if(root==null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int length = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i=0;i<length;i++)
            {
                TreeNode node = queue.poll();
                max = Math.max(max,node.val);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            ans.add(max);
        }
        return ans;
    }

    public int Num513findBottomLeftValue(TreeNode root) {
        int[] nums = new int[2];
        nums[1] = -1;
        Num513Helper(root,0,nums);
        return nums[0];
    }

    public void Num513Helper(TreeNode node,int depth,int[] nums)
    {
        if(node==null)
            return;
        if(depth>nums[1])
        {
            nums[0] = node.val;
            nums[1] = depth;
        }
        Num513Helper(node.left,depth+1,nums);
        Num513Helper(node.right,depth+1,nums);
    }


    public TreeNode subtreeWithAllDeepest(TreeNode root) {

    }

    public TreeNode Num1123lcaDeepestLeaves(TreeNode root) {
        int[] maxdepth = new int[2];
        TreeNode ans = null;
        Num1123DepthHelper(root,maxdepth,0);
        Num1123LCAHelper(root,maxdepth,0,ans);
        return ans;
    }

    public int Num1123LCAHelper(TreeNode node,int[] maxdepth,int depth,TreeNode ans)
    {
        if(node==null)
            return 0;
        int left = Num1123LCAHelper(node.left,maxdepth,depth+1,ans);
        int right = Num1123LCAHelper(node.right,maxdepth,depth+1,ans);
        int sum = left+right;
        if(depth == maxdepth[0])
            sum+=1;
        if(sum==maxdepth[1]&&ans==null)
            ans = node;
        return sum;
    }

    public void Num1123DepthHelper(TreeNode root,int[] maxdepth,int depth)
    {
        if(root==null)
            return;
        if(depth>maxdepth[0])
        {
            maxdepth[0] = depth;
            maxdepth[1] = 1;
        }
        if(depth == maxdepth[0])
            maxdepth[1]++;
        Num1123DepthHelper(root.left,maxdepth,depth);
        Num1123DepthHelper(root.right,maxdepth,depth);
    }

    public int Num222countNodes(TreeNode root) {
        if(root==null)
            return 0;
        int depth = Num222depthhelper(root,0);
        if(depth==0)
            return 1;
        int left = 1;
        int right = (int)Math.pow(2,depth)-1;
        int pivot;
        while(left<=right)
        {
            pivot = left+(right-left)/2;
            if(Num222existHelper(pivot,depth,root))
                left = pivot+1;
            else right = pivot-1;
        }
        return (int)Math.pow(2,depth)-1+left;
    }

    public boolean Num222existHelper(int pivot,int depth,TreeNode root)
    {
        int left = 0, right = (int)Math.pow(2,depth)-1;
        int pi;
        for(int i=0;i<depth;i++)
        {
            pi = left + (right-left)/2;
            if(pi>=pivot)
            {
                root = root.left;
                right = pi;
            }
            else
            {
                root = root.right;
                left = pi+1;
            }
        }
        return root!=null;
    }


    public int Num222depthhelper(TreeNode node,int dep)
    {
        int depth = 0;
        while(node.left!=null)
        {
            node = node.left;
            dep++;
        }
        depth = dep;
        return depth;
    }

    /**
     * look like lowest common ancestor
     * @param root
     * @return
     */
    TreeNode Num865ans;
    int Num865max = 0;
    public TreeNode Num865subtreeWithAllDeepest(TreeNode root) {
        if(root==null)
            return root;
        Num865helper(root,0);
        return Num865ans;
    }

    public int Num865helper(TreeNode node,int depth)
    {
        if(node==null)
            return depth;
        int left = Num865helper(node.right,depth+1);
        int right = Num865helper(node.left,depth+1);
        if(left==right&&Num865max<=left)
        {
            Num865max = left;
            Num865ans = node;
        }
        return Math.max(left,right);
    }

    public boolean Num255verifyPreorder(int[] preorder) {
        return Num255verifyPreorderHelper(preorder,0,preorder.length-1);
    }

    public boolean Num255verifyPreorderHelper(int[] preorder,int left,int right)
    {
        if(left>=right)
            return true;
        int index = left;
        for(;index<=right;index++)
        {
            if(preorder[index]>preorder[left])
                break;
        }
        for(int i = index;i<=right;i++)
        {
            if(preorder[i]<preorder[left])
                return false;
        }
        boolean leftBST = Num255verifyPreorderHelper(preorder,left+1,index-1);
        boolean rightBST = Num255verifyPreorderHelper(preorder,index,right);
        return leftBST&&rightBST;
    }

    public double Num1120maximumAverageSubtree(TreeNode root) {
        double[] ans = new double[1];
        Num1120Helper(root,ans);
        return ans[0];
    }

    public int[] Num1120Helper(TreeNode node,double[] ans)
    {
        if(node==null)
            return new int[2];
        int[] left = Num1120Helper(node.left,ans);
        int[] right = Num1120Helper(node.right,ans);
        int[] returnlist = new int[2];
        int sum = left[0]+right[0];
        int nums = left[1]+right[1];
        returnlist[0] = sum+node.val;
        returnlist[1] = nums+1;
        double curr = (double)returnlist[0]/returnlist[1];
        ans[0] = Math.max(ans[0],curr);
        return returnlist;
    }

    public int Num1466minReorder(int n, int[][] connections) {
        ArrayList<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] directions = (ArrayList<Integer>[]) new ArrayList[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>(3);
            directions[i] = new ArrayList<Integer>(2);
        }

        for (int i = 0; i < connections.length; i++) {
            graph[connections[i][0]].add(connections[i][1]);
            graph[connections[i][1]].add(connections[i][0]);

            directions[connections[i][0]].add(connections[i][1]);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);

        int answer = 0;

        while (!queue.isEmpty()) {
            int city = queue.poll();

            if (visited[city]) {
                continue;
            }

            visited[city] = true;

            for (int i = 0; i < directions[city].size(); i++) {
                if (!visited[directions[city].get(i)]) {
                    ++answer;
                }
            }

            for (int i = 0; i < graph[city].size(); i++) {
                queue.offer(graph[city].get(i));
            }
        }
        return answer;
    }

    public TreeNode Num998insertIntoMaxTree(TreeNode root, int val) {
        if(root==null)
        {
            TreeNode node = new TreeNode(val);
            return node;
        }
        if(root.val<val)
        {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        root.right = Num998insertIntoMaxTree(root.right,val);
        return root;
    }

    public List<TreeNode> Num95generateTrees(int n) {
        int[] narray = new int[n];
        return Num95nodes(narray,0,n-1);
    }

    public List<TreeNode> Num95nodes(int[] narray,int left,int right)
    {
        List<TreeNode> ans = new LinkedList<>();
        for(int i=left;i<=right;i++)
        {

            List<TreeNode> leftlist = Num95nodes(narray,left,i-1);
            if(leftlist.size()==0)
                leftlist.add(null);
            List<TreeNode> rightlist = Num95nodes(narray,i+1,right);
            if(rightlist.size()==0)
                rightlist.add(null);
            for(TreeNode leftnode:leftlist)
            {
                for(TreeNode rightnode:rightlist)
                {
                    TreeNode node = new TreeNode(i+1);
                    node.left = leftnode;
                    node.right = rightnode;
                    ans.add(node);
                }
            }

        }
        return ans;
    }

    public TreeNode Num1382balanceBST(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        Num1382Inorder(root,values);
        return Num1382BSTConstructor(values,0,values.size()-1);
    }

    public void Num1382Inorder(TreeNode root,List<Integer> values)
    {
        if(root==null)
            return;
        Num1382Inorder(root.left,values);
        values.add(root.val);
        Num1382Inorder(root.right,values);
    }

    public TreeNode Num1382BSTConstructor(List<Integer> values,int left,int right)
    {
        if(left==right)
            return new TreeNode(values.get(left));
        if(left>right)
            return null;
        int mid = left+(right-left)/2;
        TreeNode node = new TreeNode(values.get(mid));
        node.left = Num1382BSTConstructor(values,left,mid-1);
        node.right = Num1382BSTConstructor(values,mid+1,right);
        return node;
    }

    /**
     * very tricky to use the next info
     * @param root
     * @return
     */
    public Node Num116connect(Node root) {
        if (root == null) {
            return root;
        }
        Node leftmost = root;
        while(leftmost.left!=null)
        {
            Node head = leftmost;
            while(head!=null)
            {
                head.next.left = head.right;
                if(head.next!=null)
                {
                    head.next.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }
    public boolean Num98helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (! Num98helper(node.right, val, upper)) return false;
        if (! Num98helper(node.left, lower, val)) return false;
        return true;
    }

    /**
     * recursive
     * @param root
     * @return
     */
    public boolean Num98isValidBST(TreeNode root) {
        return Num98helper(root, null, null);
    }

    /**
     * iterative
     * @param root
     * @return
     */
    public boolean Num98IterativeisValidBST(TreeNode root) {
        Queue<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> uppers = new LinkedList<>(), lowers = new LinkedList<>();
        Integer lower,upper;
        stack.add(root);
        uppers.add(null);
        lowers.add(null);
        while(!stack.isEmpty())
        {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();
            if(root==null)
                continue;
            if(lower!=null&&root.val<=lower)
                return false;
            if(upper!=null&&root.val>=upper)
                return false;
            stack.add(root.right);
            uppers.add(upper);
            lowers.add(root.val);
            stack.add(root.right);
            lowers.add(lower);
            uppers.add(root.val);
        }
        return true;
    }

    public int Num1302deepestLeavesSum(TreeNode root) {
        int[] sum = new int[2];
        Num1302Helper(root,sum,0);
        return sum[0];
    }

    public void Num1302Helper(TreeNode node,int[] sum,int depth)
    {
        if(node==null)
            return;
        if(depth>sum[1])
        {
            sum[0] = node.val;
            sum[1] = depth;
        }
        else if(depth==sum[1])
        {
            sum[0]+=node.val;
        }
        Num1302Helper(node.left,sum,depth+1);
        Num1302Helper(node.right,sum,depth+1);
    }

    public TreeNode Num654constructMaximumBinaryTree(int[] nums) {
        return Num654Helper(nums,0,nums.length-1);
    }

    public TreeNode Num654Helper(int[] nums,int left,int right)
    {
        if(left>right)
            return null;
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i=left;i<=right;i++)
        {
            if(max<nums[i])
            {
                max = nums[i];
                index = i;
            }
        }
        TreeNode node = new TreeNode(nums[index]);
        node.left = Num654Helper(nums,left,index-1);
        node.right = Num654Helper(nums,index+1,right);
        return node;
    }

    public final TreeNode Num1379getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Queue<TreeNode> origin = new LinkedList<>();
        Queue<TreeNode> clone = new LinkedList<>();
        origin.add(original);
        clone.add(cloned);
        while(!origin.isEmpty())
        {
            TreeNode ori = origin.poll();
            TreeNode clo = clone.poll();
            if(ori==target)
            {
                return clo;
            }
            if(ori.left!=null)
            {
                origin.add(ori.left);
                clone.add(clo.left);
            }
            if(ori.right!=null)
            {
                origin.add(ori.right);
                clone.add(clo.right);
            }
        }
        return null;
    }

    public int Num1315sumEvenGrandparent(TreeNode root) {
        int[] sum = new int[1];
        Num1315Helper(root,0,0,sum);
        return sum[0];
    }

    public void Num1315Helper(TreeNode node,int parentvalue,int grandparentvalue,int[] sum)
    {
        if(node==null)
            return;
        if(grandparentvalue%2==0&&grandparentvalue!=0)
            sum[0]+=node.val;
        Num1315Helper(node.left,node.val,parentvalue,sum);
        Num1315Helper(node.right,node.val,parentvalue,sum);
    }

    boolean pfound=false;
    boolean qfound = false;
    public TreeNode Num236lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = null;
        List<TreeNode> pathp = new LinkedList<>();
        List<TreeNode> pathq = new LinkedList<>();
        Num236helper(root,p,q,pathp,pathq);
        int length = Math.min(pathp.size(),pathq.size());
        int i = 0;
        for(;i<length;i++)
        {
            if(pathp.get(i)==pathq.get(i))
                continue;
            else
                break;
        }
        if(i==length)
            return pathp.get(length-1);
        return pathp.get(i-1);
    }

    public void Num236helper(TreeNode node,TreeNode p,TreeNode q,List<TreeNode> pathp,List<TreeNode> pathq)
    {
        if(node==null)
            return;
        if(!pfound)
            pathp.add(node);
        if(!qfound)
            pathq.add(node);
        if(node.val==p.val)
        {
            pfound = true;
        }
        if(node.val==q.val)
        {
            qfound = true;
        }
        Num236helper(node.left,p,q,pathp,pathq);
        Num236helper(node.right,p,q,pathp,pathq);
        if(!pfound)
            pathp.remove(pathp.size()-1);
        if(!qfound)
            pathq.remove(pathq.size()-1);
    }

    public TreeNode Num701insertIntoBST(TreeNode root, int val) {
        TreeNode node = root;
        if(root==null)
        {
            root = new TreeNode(val);
            return root;
        }
        Num701Helper(node,val);
        return root;
    }

    public void Num701Helper(TreeNode node,int val)
    {
        if(node.right==null&&node.val<val)
        {
            TreeNode newnode = new TreeNode(val);
            node.right = newnode;
            return;
        }
        if(node.left==null&&node.val>val)
        {
            TreeNode newnode = new TreeNode(val);
            node.left = newnode;
            return;
        }
        if(val>node.val)
            Num701Helper(node.right,val);
        else if(val<node.val)
            Num701Helper(node.left,val);
    }

    public TreeNode Num109sortedListToBST(ListNode head) {

    }

    public TreeNode Num1008bstFromPreorder(int[] preorder) {
        TreeNode root = Num1008helper(preorder,0,preorder.length-1);
        return root;
    }

    public TreeNode Num1008helper(int[] preorder,int left,int right)
    {
        if(left==right)
        {
            TreeNode node = new TreeNode(preorder[left]);
            return node;
        }
        TreeNode node = new TreeNode(preorder[left]);
        int i=left;
        for(;i<=right;i++)
        {
            if(preorder[i]>node.val)
                break;
        }
        if(i>right)
            node.right = null;
        else
            node.right = Num1008helper(preorder,i,right);

        if(preorder[left+1]>preorder[left])
            node.left = null;
        else
            node.left = Num1008helper(preorder,left+1,i-1);
        return node;
    }

    /**
     * needs to be done again
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> Num1305getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new LinkedList<>();
        ArrayDeque<TreeNode> stack1 = new ArrayDeque(), stack2 = new ArrayDeque();
        while(root1!=null||root2!=null||!stack1.isEmpty()||!stack2.isEmpty())
        {
            while(root1!=null)
            {
                stack1.push(root1);
                root1 = root1.left;
            }
            while(root2!=null)
            {
                stack2.push(root2);
                root2 = root2.left;
            }
            if(stack2.isEmpty()||!stack1.isEmpty()&&stack1.getFirst().val<=stack2.getFirst().val)
            {
                root1 = stack1.pop();
                ans.add(root1.val);
                root1 = root1.right;
            }
            else
            {
                root2 = stack2.poll();
                ans.add(root2.val);
                root2 = root2.right;
            }
        }
        return ans;
    }

    HashMap<Integer,List<TreeNode>> nodemap = new HashMap<>();
    public List<TreeNode> Num894allPossibleFBT(int N) {
        List<TreeNode> ans = new LinkedList<>();
        TreeNode node = new TreeNode(0);
        N-=1;
        if(N==0)
            ans.add(node);
        for(int i=1;i<N;i+=2)
        {
            List<TreeNode> leftlist = null;
            if(!nodemap.containsKey(i))
            {
                leftlist = Num894allPossibleFBT(i);
            }
            else
                leftlist = nodemap.get(i);
            List<TreeNode> rightlist = null;
            if(!nodemap.containsKey(N-i))
            {
                rightlist = Num894allPossibleFBT(N-i);
            }
            else
                rightlist = nodemap.get(N-i);
            for(int j=0;j<leftlist.size();j++)
            {
                for(int k=0;k<rightlist.size();k++)
                {
                    node.left = leftlist.get(j);
                    node.right = rightlist.get(k);
                    ans.add(cloneTree(node));
                }
            }
        }
        nodemap.put(N+1,ans);
        return ans;
    }

    public static TreeNode cloneTree(TreeNode root){
        TreeNode node=null;
        if(root==null) return null;
        node=new TreeNode(root.val);
        node.left=cloneTree(root.left);
        node.right=cloneTree(root.right);
        return node;
    }

    public TreeNode Num814pruneTree(TreeNode root) {
        boolean rootall = Num814Helper(root);
        if(rootall)
            root = null;
        return root;
    }

    public boolean Num814Helper(TreeNode node)
    {
        if(node==null)
            return true;
        boolean left = Num814Helper(node.left);
        boolean right = Num814Helper(node.right);
        if(left)
            node.left = null;
        if(right)
            node.right = null;
        if(!left||!right)
            return false;
        return node.val == 0;
    }

    public List<Integer> pathInZigZagTree(int label) {
        label+=1;
        if(label==2)
        {
            List<Integer> ans = new LinkedList<>();
            ans.add(1);
            return ans;
        }
        int depth = (int) Math.ceil(Math.log(label)/Math.log(2));
        double nums = Math.pow(2,depth-1);
        label-=1;
        double a = label - nums+1;
        int b = (int)Math.ceil(a/2);
        int aaaa;
        aaaa = (int) nums-b;
        List<Integer> last = pathInZigZagTree(aaaa);
        last.add(label);
        return last;
    }

    public TreeNode Num889constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = Num889helper(pre,post,0,pre.length-1, 0,post.length-1);
        return root;
    }

    public TreeNode Num889helper(int[] pre,int[] post,int preleft,int preright,int postleft,int postright)
    {
        if(preleft>preright||postleft>postright)
            return null;
        TreeNode node = new TreeNode(pre[preleft]);
        if(preleft==preright)
        {
            return node;
        }
        int i=postleft;
        for(;i<=postright;i++)
        {
            if(post[i]==pre[preleft+1])
                break;
        }
        int length = i-postleft+1;
        node.left = Num889helper(pre,post,preleft+1,preleft+length,postleft,postleft+length-1);
        node.right = Num889helper(pre,post,preleft+length+1,preright,postleft+length,postright);
        return node;
    }

    public Node Num1490cloneTree(Node root) {
        if(root==null)
            return null;
        Node node = new Node(root.val);
        for(int i=0;i<root.children.size();i++)
        {
            node.children.add(Num1490cloneTree(root.children.get(i)));
        }
        return node;
    }

    public int Num1130mctFromLeafValues(int[] arr) {
        int [][] max = new int[arr.length][arr.length], dp = new int[arr.length][arr.length];
        for(int i=0;i<arr.length;i++)
        {
            int max1 = 0;
            dp[i][i] = 0;
            for(int j=i;j<arr.length;j++)
            {
                max[i][j] = Math.max(max1,arr[j]);
                if(j==i+1)
                {
                    dp[i][j] = arr[i]*arr[j];
                }
            }
        }
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length; j++)
            {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int L = 2;L<arr.length;L++)
        {
            for(int i=0;i<arr.length-L;i++)
            {
                for(int j=i;j<i+L;j++)
                {
                    dp[i][i+L] = Math.min(dp[i][i+L],dp[i][j]+dp[j+1][i+L]+max[i][j]*max[j+1][i+L]);
                }
            }
        }
        return dp[0][arr.length-1];
    }

    public TreeNode Num1038bstToGst(TreeNode root) {
        int[] sum = new int[1];
        Num1038Helper(root,sum);
        return root;
    }

    public void Num1038Helper(TreeNode node,int[] sum)
    {
        if(node==null)
            return;
        Num1038Helper(node.right,sum);
        sum[0]+=node.val;
        node.val = sum[0];
        Num1038Helper(node.left,sum);
    }

    public TreeNode Num1325removeLeafNodes(TreeNode root, int target) {
        if(root==null)
            return root;
        root.left = Num1325removeLeafNodes(root.left,target);
        root.right = Num1325removeLeafNodes(root.right,target);
        if(root.left==null&&root.right==null&&root.val==target)
            return null;
        return root;
    }

    public int Num979distributeCoins(TreeNode root) {
        int[] ans = new int[1];
        Num979dfs(root,ans);
        return ans[0];
    }

    public int Num979dfs(TreeNode node,int[] ans) {
        if (node == null) return 0;
        int L = Num979dfs(node.left,ans);
        int R = Num979dfs(node.right,ans);
        ans[0] += Math.abs(L) + Math.abs(R);
        return node.val + L + R - 1;
    }

    public List<List<Integer>> Num102levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root==null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            List<Integer> levellist = new LinkedList<>();
            int length = queue.size();
            for(int i=0;i<length;i++)
            {
                TreeNode node = queue.poll();
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
                levellist.add(node.val);
            }
            ans.add(levellist);
        }
        return ans;
    }

    public int Num298longestConsecutive(TreeNode root) {
        int[] max =new int[1];
        Num298longestConsecutiveHelper(root,1,max);
        return max[0];
    }

    public void Num298longestConsecutiveHelper(TreeNode node,int length,int[] max)
    {
        if(node==null)
            return;
        max[0] = Math.max(max[0],length);
        if(node.left==null||node.left.val!=node.val+1)
        {
            Num298longestConsecutiveHelper(node.left,1,max);
        }
        else
            Num298longestConsecutiveHelper(node.left,length+1,max);
        if(node.right==null||node.right.val!=node.val+1)
        {
            Num298longestConsecutiveHelper(node.right,1,max);
        }
        else
            Num298longestConsecutiveHelper(node.right,length+1,max);
    }

    public int Num129sumNumbers(TreeNode root) {
        int[] sum = new int[1];
        int value = 0;
        Num129sumNumbersHelper(root,sum,value);
        return sum[0];
    }

    public void Num129sumNumbersHelper(TreeNode root,int[] sum,int value)
    {
        if(root==null)
            return;
        value = value*10+root.val;
        if(root.left==null&&root.right==null)
        {
            sum[0]+=value;
        }
        if(root.left!=null)
        {
            Num129sumNumbersHelper(root.left,sum,value);
        }
        if(root.right!=null)
        {
            Num129sumNumbersHelper(root.right,sum,value);
        }
    }

    public List<List<Integer>> Num113pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> pathlist = new LinkedList<>();
        Num113pathSumHelper(root,ans,sum,pathlist);
        return ans;
    }

    public void Num113pathSumHelper(TreeNode node,List<List<Integer>>ans,int sum,List<Integer> pathlist)
    {
        if(node==null)
            return;
        sum-=node.val;
        pathlist.add(node.val);
        if(sum==0&&node.left==null&&node.right==null)
        {
            ans.add(new LinkedList<>(pathlist));
        }
        if(node.left!=null)
        {
            Num113pathSumHelper(node.left,ans,sum,pathlist);
            pathlist.remove(pathlist.size()-1);
        }
        if(node.right!=null)
        {
            Num113pathSumHelper(node.right,ans,sum,pathlist);
            pathlist.remove(pathlist.size()-1);
        }
    }

    public int Num250countUnivalSubtrees(TreeNode root) {
        int[] nums = new int[1];
        Num250countHelper(root,nums,root.val);
        return nums[0];
    }

    public boolean Num250countHelper(TreeNode node,int[] nums,int value)
    {
        if(node==null)
            return true;

        boolean left = Num250countHelper(node.left,nums,node.val);
        boolean right = Num250countHelper(node.right,nums,node.val);
        if(!left||!right)
            return false;
        if(left&&right)
        {
            nums[0]++;
        }
        if(node.val!=value)
            return false;
        return true;
    }

    public int Num124maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0]  = Integer.MIN_VALUE;
        Num124Helper(root,max);
        return max[0];
    }

    public int Num124Helper(TreeNode node,int[] max)
    {
        if(node==null)
            return 0;
        int left = Math.max(Num124Helper(node.left,max),0);
        int right = Math.max(Num124Helper(node.right,max),0);
        max[0] = Math.max(max[0],left+right+node.val);
        return Math.max(left,right)+node.val;
    }

    public void Num99recoverTree(TreeNode root) {
        int[] wronglist = new int[4];
        boolean[] wr = new boolean[2];
        Num99InorderHelper(root,wronglist,wr);
        Num99recoverHelper(root,wronglist);
    }

    public void Num99InorderHelper(TreeNode node, int[] wronglist,boolean[] wr)
    {
        if(node==null)
            return;
        Num99InorderHelper(node.left,wronglist,wr);
        if(wronglist[1]==0)
        {
            wronglist[0] = node.val;
            wronglist[1] = 1;
        }
        else
        {
            if(wr[0]==false&&wronglist[0]>node.val)
            {
                wr[0] = true;
                wronglist[2] = wronglist[0];
            }
            if(wronglist[0]>node.val)
            {
                wronglist[3] = node.val;
            }
        }
        wronglist[0] = node.val;
        Num99InorderHelper(node.right,wronglist,wr);
    }

    public void Num99recoverHelper(TreeNode node,int[] wronglist)
    {
        if(node==null)
            return;
        Num99recoverHelper(node.left,wronglist);
        if(node.val==wronglist[2])
            node.val = wronglist[3];
        else if(node.val==wronglist[3])
            node.val = wronglist[2];
        Num99recoverHelper(node.right,wronglist);

    }

    /**
     * This is a very tricky problem. As depth we can use another variable to
     * write down the width.
     * @param root
     * @return
     */
    public List<List<Integer>> Num314verticalOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();
        if (root == null) {
            return traversal;
        }

        Map<Integer, List<Integer>> verticals = new HashMap<>();
        Queue<TreeNode> nodeQ = new ArrayDeque<>();
        Queue<Integer> idQ = new ArrayDeque<>();
        nodeQ.add(root);
        idQ.add(0);
        int minId = 0;
        while (!nodeQ.isEmpty()) {
            TreeNode node = nodeQ.remove();
            int id = idQ.remove();
            verticals.computeIfAbsent(id, (k) -> new ArrayList<>()).add(node.val);
            minId = Math.min(minId, id);

            if (node.left != null) {
                nodeQ.add(node.left);
                idQ.add(id - 1);
            }
            if (node.right != null) {
                nodeQ.add(node.right);
                idQ.add(id + 1);
            }
        }

        for (int i = minId; i < verticals.size() + minId; i++) {
            traversal.add(verticals.get(i));
        }
        return traversal;
    }


}
