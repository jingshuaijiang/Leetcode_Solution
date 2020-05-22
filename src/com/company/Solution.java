package com.company;

import javax.print.attribute.HashAttributeSet;
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
    public String Num1119removeVowels(String S) {
        return S.replaceAll("[aeiou]","");
    }
    public String Num1108defangIPaddr(String address) {
        String[] slist = address.split("[.]");
        String ans= "";
        for(int i=0;i<slist.length;i++)
        {
            ans=ans+slist[i]+"[.]";
        }
        ans = ans.substring(0,ans.length()-3);
        return ans;
    }
    public int Num1342numberOfSteps (int num) {
        int count = 0;
        while(num!=0)
        {
            if(num%2==1)
            {
                num-=1;
            }
            else
            {
                num/=2;
            }
            count++;
        }
        return count;
    }
    public int[] Num1365smallerNumbersThanCurrent(int[] nums) {
        int [] counter = new int[101];
        for(int i=0;i<nums.length;i++)
        {
            counter[nums[i]]++;
        }
        for(int i=1;i<counter.length;i++)
        {
            counter[i]+=counter[i-1];
        }
        int[] ans = new int[nums.length];
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]-1<0)
            {
                ans[i] = 0;
            }
            else {
                ans[i] = counter[nums[i] - 1];
            }
        }
        return ans;
    }
    public int Num1290getDecimalValue(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while(head!=null)
        {
            sb.append(head.val);
            head = head.next;
        }
        return Integer.parseInt(sb.toString(),2);
    }
    public int Num1221balancedStringSplit(String s) {
        Stack<Character> stack = new Stack<Character>();
        int count = 0;
        for(int i=0;i<s.length();i++)
        {
            char c =s.charAt(i);
            if(stack.isEmpty() || c==stack.peek())
            {
                stack.push(c);
            }
            else
            {
                stack.pop();
            }
            if(stack.isEmpty())
            {
                count++;
            }
        }
        return count;
    }

    public int Num1296findNumbers(int[] nums) {
        int count = 0;
        for(int i=0;i<nums.length;i++)
        {
            if(Num1296EvenNumber(nums[i]))
                count++;
        }
        return count;
    }
    boolean Num1296EvenNumber(int number)
    {
        if(Integer.toString(number).length()%2==1)
        {
            return false;
        }
        return true;
    }

    public int Num1281subtractProductAndSum(int n) {
        if(n == 0) return 0;
        int product = 1;
        int sum = 0;
        while(n!=0)
        {
            int left = n%10;
            n = n/10;
            product*=left;
            sum+=left;
        }
        return product - sum;
    }
    public int[] Num1313decompressRLElist(int[] nums) {
        int count = 0;
        for(int i=0;i<nums.length;i+=2)
        {
            count+=nums[i];
        }
        int[] ans = new int[count];
        int init = 0;
        for(int i=0;i<nums.length;i+=2)
        {
            for(int j=0;j<nums[i];j++)
            {
                ans[init] = nums[i+1];
                init++;
            }
        }
        return ans;
    }
    //do it again
    public int[][] Num1086highFive(int[][] items) {
        Arrays.sort(items, (a,b) -> (b[1]-a[1]));
        List<int[]> list = new ArrayList();
        int[] idSum = new int[1000];
        int[] idcount = new int[1000];

        Map<Integer, Integer>  map = new HashMap<>();
        for(int[] i : items) {
            if(idcount[i[0]] < 5) {
                idSum[i[0]] += i[1];
                idcount[i[0]]++;
                map.put(i[0],idSum[i[0]]);
            }
        }
        int[][] res = new int[map.size()][2];
        int j=0;
        for(Map.Entry<Integer, Integer>  m : map.entrySet()) {
            list.add(new int[]{m.getKey(), m.getValue()});
            res[j][0] = m.getKey();
            res[j][1] = m.getValue()/5;
            j++;
        }
        return res;
    }
    public int Num1252oddCells(int n, int m, int[][] indices) {
        int [][] matrix = new int[n][m];
        for(int[] c:indices)
        {
            for(int j=0;j<m;j++)
            {
                matrix[c[0]][j]++;
            }
            for(int j=0;j<n;j++)
            {
                matrix[j][c[1]]++;
            }
        }
        int count = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j]%2!=0)
                {
                    count++;
                }
            }
        }
        return count;

    }
    public List<Integer> Num1213arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int [] counter = new int[2001];
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<arr1.length;i++)
        {
            counter[arr1[i]]++;
        }
        for(int i=0;i<arr2.length;i++)
        {
            counter[arr2[i]]++;
        }
        for(int i=0;i<arr3.length;i++)
        {
            counter[arr3[i]]++;
        }
        for(int i=0;i<counter.length;i++)
        {
            if(counter[i]==3)
            {
                list.add(i);
            }
        }
        return list;
    }
    public int Num1266minTimeToVisitAllPoints(int[][] points) {
        int count = 0;
        for(int i=1;i<points.length;i++)
        {
            count+=Math.max(Math.abs(points[i-1][0]-points[i][0]),Math.abs(points[i-1][1]-points[i][1]));
        }
        return count;
    }
    public int Num1323maximum69Number (int num) {
        String nums = Integer.toString(num);
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for(int i=0;i<nums.length();i++)
        {
            if(nums.charAt(i)=='6'&&count==1)
            {
                sb.append('9');
                count--;
            }
            else
            {
                sb.append(nums.charAt(i));
            }
        }
        return Integer.parseInt(sb.toString());
    }
    public int[] Num760anagramMappings(int[] A, int[] B) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] p = new int[A.length];
        for(int i=0;i<B.length;i++)
        {
            map.put(B[i],i);
        }
        for(int i=0;i<A.length;i++)
        {
            p[i] = map.get(A[i]);
        }
        return p;
    }
    public int Num1165calculateTime(String keyboard, String word) {
        Map<Character, Integer> index = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++){
            index.put(keyboard.charAt(i), i);
        }
        int prev = 0;
        int time = 0;
        for (int i = 0; i < word.length(); i++){
            time += Math.abs(index.get(word.charAt(i)) - prev);
            prev = index.get(word.charAt(i));
        }
        return time;
    }
    public int[] Num1389createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new LinkedList<>();
        for(int i=0;i<nums.length;i++)
        {
            list.add(index[i],nums[i]);
        }
        int[] ans = new int[nums.length];
        for(int i=0;i<list.size();i++)
        {
            ans[i] = list.get(i);
        }
        return ans;
    }
    public int[] Num1299replaceElements(int[] arr) {
        int max = arr[arr.length-1];
        int[] ans = new int[arr.length];
        ans[arr.length-1] = -1;
        for(int i=arr.length-2;i>=0;i--)
        {
            ans[i] = max;
            if(arr[i] > max)
            {
                max = arr[i];
            }
        }
        return ans;
    }
    public int Num1351countNegatives(int[][] grid) {
        int count = 0;
        for(int[] sub: grid)
        {
            for(int i=0;i<sub.length;i++)
            {
                if(sub[i]<0)
                {
                    count+=sub.length-i;
                    break;
                }
            }
        }
        return count;
    }

    public String Num1021removeOuterParentheses(String S) {
        Stack<Character> stack = new Stack<>();
        String split = "";
        String ans = "";
        for(int i=0;i< S.length();i++)
        {
            if(stack.empty())
            {
                if(split.length()==0)
                    ans+=split;
                else
                    ans = ans+split.substring(1,split.length()-1);
                split = "";

            }
            if(S.charAt(i)=='(')
            {
                stack.push('(');
            }
            else
            {
                stack.pop();
            }
            split+=S.charAt(i);
        }
        return ans;
    }
    public boolean Num1134isArmstrong(int N) {
        int temp = N;
        int sum = 0;
        if(N==0) return false;
        int length = Integer.toString(N).length();
        while(N!=0)
        {
            int count = N%10;
            sum+=Math.pow(count,length);
            N = N/10;
        }
        return sum==temp;
    }
    public int Num1180countLetters(String S) {
        Character pre = S.charAt(0);
        int count = 0;
        int length = 1;
        for(int i=1;i<S.length();i++)
        {
            if(S.charAt(i)==pre)
                length++;
            else
            {
                count= count +(length*(length+1))/2;
                length = 1;
                pre = S.charAt(i);
            }
        }
        count=count +(length*(length+1))/2;
        return count;
    }
    public int[] Num1304sumZero(int n) {
        int[] ans = new int[n];
        int count = 0;
        if(n%2 == 0 )
        {
            count = n/2;
        }
        else
            count = (n-1)/2;
        int index = 0;
        for(int i=1;i<=count;i++)
        {
            ans[index] = i;
            ans[index+1] = -i;
            index+=2;
        }
        return ans;

    }
    public String Num1374generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if(n%2==0)
        {
            sb.append('b');
            n = n-1;
        }
        for(int i=0;i<n;i++)
        {
            sb.append('a');
        }
        return sb.toString();
    }
    public List<Integer> Num728selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for(int i=left;i<=right;i++)
        {
            if(selfdividingNumber(i))
                ans.add(i);
        }
        return ans;
    }
    public boolean selfdividingNumber(int number)
    {
        int p = number;
        if(Integer.toString(number).contains("0"))
            return false;
        while(number!=0)
        {
            int re = number %10 ;
            if(p%re!=0)
                return false;
            number = number/10;
        }
        return true;
    }
    public List<Integer> Num1380luckyNumbers (int[][] matrix) {
        int r=matrix.length;
        int c=matrix[0].length;
        List<Integer> list = new ArrayList();

        for(int i=0; i<r; i++ ){
            for(int j=0; j<c; j++){
                if(minInRow(matrix,i,j) && maxInColumn(matrix,i,j))
                    list.add(matrix[i][j]);
            }
        }
        return list;
    }

    public boolean minInRow(int[][] matrix, int i, int j){
        for(int k=0; k<matrix[0].length; k++){
            if(k!=j){
                if(matrix[i][k] < matrix[i][j]) return false;
            }
        }
        return true;
    }

    public boolean maxInColumn(int[][] matrix, int i, int j){
        for(int k=0; k<matrix.length; k++){
            if(k!=i){
                if(matrix[k][j] > matrix[i][j]) return false;
            }
        }
        return true;
    }
    public List<Integer> Num1403minSubsequence(int[] nums) {
        int sum = 0;
        for(int i=0;i<nums.length;i++)
        {
            sum+=nums[i];
        }
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        for(int i = nums.length-1;i>=0;i--)
        {
            count+=nums[i];
            ans.add(nums[i]);
            if(count > sum/2)
                break;
        }
        return ans;
    }
    public boolean Num1207uniqueOccurrences(int[] arr) {
        int[] count = new int[2001];
        for(int ele: arr){
            count[ele+1000]++;
        }
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<count.length;i++){
            if(count[i]!=0){
                if(set.contains(count[i])){
                    return false;
                }
                set.add(count[i]);
            }
        }
        return true;
    }

    public int Num852peakIndexInMountainArray(int[] A) {
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i=0;i<A.length;i++)
        {
            if(A[i] >max)
            {
                max = A[i];
                index = i;
            }
        }
        return index;
    }

    public int Num1385findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int distance = 0;
        for(int i=0;i<arr1.length;i++){
            List<Boolean> boundaryList = new ArrayList<Boolean>();
            for(int j=0;j<arr2.length;j++){
                if(Math.abs(arr1[i]-arr2[j]) <= d) boundaryList.add(true);
            }
            if(boundaryList.size() == 0) distance++;
        }
        return distance;
    }

    public int Num1051heightChecker(int[] heights) {
        int[] sorted = heights.clone();

        Arrays.sort(sorted);

        int count = 0;
        for (int i = 0; i < heights.length; ++i) {
            if (heights[i] != sorted[i]) count++;
        }
        return count;
    }
    public int Num1394findLucky(int[] arr) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int a: arr) {
            hm.put(a, hm.getOrDefault(a, 0) + 1);
        }
        int res = -1;
        for(Map.Entry<Integer, Integer> entry: hm.entrySet()) {
            int key = entry.getKey();
            if(key > res && key == entry.getValue()) {
                res = key;
            }
        }
        return res;
    }
    public int[] Num1337kWeakestRows(int[][] mat, int k) {
        List<int[]> list  = new ArrayList<>();
        for(int i=0;i<mat.length;i++)
        {
            int sum = 0;
            for(int j = 0;j<mat[i].length;j++)
            {
                if(mat[i][j]==1)
                    sum+=1;
            }
            list.add(new int[] {i,sum});
        }
        Collections.sort(list, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[1]!=b[1]){
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });
        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = list.get(i)[0];
        }

        return res;


    }
    public ListNode Num876middleNode(ListNode head) {
        ListNode one=head,two = head;
        while(two!=null)
        {
            one = one.next;
            two = two.next.next;
        }
        return one;
    }

    public List<String> Num811subdomainVisits(String[] cpdomains) {
        List<String> ans = new LinkedList<>();
        HashMap<String,Integer> map = new HashMap<>();
        for(String s:cpdomains)
        {
            String[] splits = s.split(" ");
            int count = Integer.parseInt(splits[0]);
            splits = splits[1].split("\\.");
            String temp = "";
            for(int i=splits.length-1; i>=0; i--)
            {
                // empty string
                if(temp.length() == 0){
                    temp = splits[i];
                }else{
                    temp = splits[i] + "." + temp;
                }
                System.out.println(temp);
                map.put(temp, map.getOrDefault(temp, 0)+count);
            }

        }
        for(String key : map.keySet())
        {
            ans.add(String.valueOf(map.get(key)) + " " + key);
        }
        return ans;
    }
    public String Num1047removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<S.length();i++)
        {
            if(!stack.isEmpty()&&S.charAt(i)==stack.peek())
                stack.pop();
            else
            {
                stack.push(S.charAt(i));
            }
        }
        String ans = "";
        while(!stack.isEmpty())
        {
            ans = stack.pop()+ans;
        }
        return ans;
    }
    public List<List<Integer>> Num1200minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for(int i=0;i<arr.length-1;i++)
        {
            if(arr[i+1] - arr[i] < min)
            {
                min = arr[i+1] - arr[i];
            }
        }
        List<List<Integer>> ans  = new LinkedList<>();
        for(int i=0;i<arr.length-1;i++)
        {
            if(arr[i+1] - arr[i] == min)
            {
                List<Integer> list = new LinkedList<>();
                list.add(arr[i]);
                list.add(arr[i+1]);
                ans.add(list);
            }
        }
        return ans;
    }
    public int[] Num821shortestToChar(String S, char C) {
        int index = 0;
        for(int i = 0;i< S.length();i++)
        {
            if(S.charAt(i) ==C)
            {
                index = i;
                break;
            }
        }
        int[] ans = new int[S.length()];
        for(int i=0;i<S.length();i++)
        {
            if(S.charAt(i)==C)
            {
                index = i;
                for(int j = i-1;j>=0;j--)
                {
                    if(ans[j] > i-j)
                    {
                        ans[j] = i-j;
                    }
                    else
                        break;
                }
            }
            ans[i] = Math.abs(i-index);
        }
        return ans;
    }
    public int[] Num1122relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001], res = new int[arr1.length];
        for(int e : arr1) count[e]++;
        int idx = 0;
        for(int e : arr2) {
            for(int i= 0; i < count[e]; i++) res[idx++] = e;
            count[e] = 0;
        }

        for(int i = 0; i <= 1000; i++) {
            for(int j= 0; j < count[i]; j++) res[idx++] = i;
        }
        return res;
    }
    public int Num1160countCharacters(String[] words, String chars) {
        int num = 0;
        String[] arr = chars.split("");

        for(int i = 0; i< words.length; i++){
            int count = 0;
            int lengthOfWord = words[i].length();
            for(int x =0; x<arr.length; x++){

                if(words[i].contains(arr[x])){
                    count++;
                    words[i] = words[i].replaceFirst(arr[x],"");
                }
            }
            if(count >= lengthOfWord){
                num+= lengthOfWord;
            }

        }
        return num;
    }
    public int Num1064fixedPoint(int[] A) {
        for(int i=0;i<A.length;i++)
        {
            if(A[i] == i)
                return i;
        }
        return -1;
    }
    public int Num1133largestUniqueNumber(int[] A) {
        int[] bucket = new int[1001];
        for(int a : A)
            bucket[a]++;

        for(int i = 1000; i >= 0; i--)
            if(bucket[i] == 1)
                return i;
        return -1;
    }
    public int Num1217minCostToMoveChips(int[] chips) {
        int odd = 0;
        for(int i = 0;i<chips.length;i++)
        {
            if(chips[i] %2 ==1)
                odd++;
        }
        return Math.min(odd,chips.length-odd);
    }

    public String[] Num500findWords(String[] words) {
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('q',1);
        map.put('w',1);
        map.put('e',1);
        map.put('r',1);
        map.put('t',1);
        map.put('y',1);
        map.put('u',1);
        map.put('i',1);
        map.put('o',1);
        map.put('p',1);
        map.put('a',2);
        map.put('s',2);
        map.put('d',2);
        map.put('f',2);
        map.put('g',2);
        map.put('h',2);
        map.put('j',2);
        map.put('k',2);
        map.put('l',2);
        map.put('z',3);
        map.put('x',3);
        map.put('c',3);
        map.put('v',3);
        map.put('b',3);
        map.put('n',3);
        map.put('m',3);
        List<String> ans = new LinkedList<>();
        for(int i=0;i<words.length;i++)
        {
            String word = words[i].toLowerCase();
            int j = map.get(word.charAt(0));
            int tr = 0;
            for(int k = 0;k<word.length();k++)
            {
                if(map.get(word.charAt(k))!=j){
                    tr = 1;
                    break;
            }
            }
            if(tr==0)
                ans.add(words[i]);
        }
        String[] array = new String[ans.size()];
        System.arraycopy(ans.toArray(), 0, array, 0, ans.size());
        return array;
    }

    public int[][] Num867transpose(int[][] A) {
        int[][] transpose = new int[A[0].length][A.length];
        for(int i=0;i<A.length;i++)
        {
            for(int j=0;j<A[0].length;j++)
            {
                transpose[j][i] = A[i][j];
            }
        }
        return transpose;
    }

    public int[] Num496nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        HashMap<Integer,Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(nums2[0]);
        for(int i=1;i<nums2.length;i++)
        {
            while(!stack.isEmpty() && stack.peek()<nums2[i])
            {
                map.put(stack.pop(),nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while(!stack.isEmpty())
        {
            map.put(stack.pop(),-1);
        }
        for(int i=0;i< nums1.length;i++)
        {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int stone: stones)
        {
            pq.add(stone);
        }
        while(pq.size()!=0)
        {
            int s1 = pq.poll();
            if(pq.size()==0)
                return s1;
            int s2 = pq.poll();
            if(s1-s2!=0)
                pq.add(s1-s2);
        }
        return 0;
    }

    public boolean Num20isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i) =='{'|| s.charAt(i) =='('||s.charAt(i) =='[')
                stack.push(s.charAt(i));
            else
            {
                if(stack.size()==0)
                    return false;
                else
                {
                    Character c = stack.pop();
                    if(s.charAt(i)==')'&& c!='('  )
                        return false;
                    if(s.charAt(i)=='}'&& c!='{')
                        return false;
                    if(s.charAt(i)==']'&& c!='[')
                        return false;
                }

            }
        }
        if(stack.size()!=0)
            return false;
        return true;
    }
    public boolean Num844backspaceCompare(String S, String T) {
        Stack<Character> S_stack = new Stack<>();
        for(int i=0;i<S.length();i++)
        {
            if(S.charAt(i)!='#')
                S_stack.push(S.charAt(i));
            else if(S.charAt(i)=='#' && !S_stack.isEmpty())
                S_stack.pop();
        }
        Stack<Character> T_stack = new Stack<>();
        for(int i=0;i<T.length();i++)
        {
            if(T.charAt(i)!='#')
                T_stack.push(T.charAt(i));
            else if(T.charAt(i)=='#' && !T_stack.isEmpty())
                T_stack.pop();
        }
        return S_stack.toString().equals(T_stack.toString());
    }
    public int Num682calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<ops.length;i++)
        {
            if(ops[i].equals("C"))
                stack.pop();
            else if(ops[i].equals("D"))
                stack.push(2*stack.peek());
            else if(ops[i].equals("+"))
            {
                int pre = stack.pop();
                int ppre = stack.pop();
                stack.push(pre);
                stack.push(ppre);
                stack.push(pre+ppre);
            }
            else
                stack.push(Integer.valueOf(ops[i]));
        }
        int ans = 0;
        while(!stack.isEmpty())
        {
            ans+=stack.pop();
        }
        return ans;
    }
    public int Num1196maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int count = 0;
        int weight = 5000;
        for(int i=0;i<arr.length;i++)
        {
            if(weight>=arr[i])
            {
                weight-=arr[i];
                count++;
            }
        }
        return count;
    }
    public int Num476findComplement(int num) {
        int temp = num,bit = 1;
        while(temp!=0)
        {
            num = num^bit;
            bit = bit <<1;
            temp = temp >>1;
        }
        return num;
    }
    public List<String> Num784letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        if(S == null || S.length() == 0) {
            result.add(S);
            return result;
        }

        char[] str = S.toCharArray();
        findPermutations(result, str, 0);
        return result;
    }

    public void findPermutations(List<String> result, char[] str, int index){
        result.add(new String(str));

        for(int i = index; i < str.length; i++){
            if(!Character.isDigit(str[i])){
                char temp = str[i];
                str[i] = (Character.isLowerCase(str[i])) ? Character.toUpperCase(str[i]) : Character.toLowerCase(str[i]);
                findPermutations(result, str, i + 1);
                str[i] = temp;
            }
        }
    }
    public String[] Num884uncommonFromSentences(String A, String B) {
        HashMap<String,String> map = new HashMap<>();
        List<String> ans = new LinkedList<>();
        String[] alist = A.split(" ");
        String[] blist = B.split(" ");
        for(int i=0;i<alist.length;i++)
        {
            if(map.containsKey(alist[i]))
                continue;
            else map.put(alist[i],"A");
        }
        for(int i=0;i<blist.length;i++)
        {
            if(map.containsKey(blist[i]))
            {
                if(map.get(blist[i]).equals("A"))
                    map.put(blist[i],"AB");
            }
            else
            {
                map.put(blist[i],"B");
            }
        }
        for (String key1: map.keySet()
             ) {
            if(!map.get(key1).equals("AB"))
            {
                ans.add(key1);
            }
        }
        return ans.toArray(new String[ans.size()]);
    }

    public int[] Num985sumEvenAfterQueries(int[] A, int[][] queries) {
        int even = 0;
        int[] ans = new int[queries.length];
        for(int i=0;i<A.length;i++)
        {
            if(A[i] %2 ==0)
                even+=A[i];
        }
        for(int i=0;i<queries.length;i++)
        {
            int a = A[queries[i][1]]%2;
            int b = (A[queries[i][1]]+queries[i][0])%2;
            if(a==0 && b!=0)
            {
                even = even - A[queries[i][1]];
            }
            else if(a!=0 && b==0)
            {
                even = even+A[queries[i][1]]+queries[i][0];
            }
            else if(a ==0 && b==0 && A[queries[i][1]]!=(A[queries[i][1]]+queries[i][0]))
            {
                even = even +queries[i][0];
            }
            A[queries[i][1]]=A[queries[i][1]]+queries[i][0];
            ans[i] = even;
        }
        return ans;
    }

    public int maxNumberOfBalloons(String text) {
        int count[] = new int[26];
        for(char c: text.toCharArray())
        {
            count[c - 'a']++;
        }
        int min = count[1];
        min=Math.min(min,count[0]);
        min=Math.min(min,count['l'-'a']/2);
        min=Math.min(min,count['o'-'a']/2);
        min=Math.min(min,count['n'-'a']);
        return min;
    }
    public int Num1099twoSumLessThanK(int[] A, int K) {
        int i = A.length-1;
        int j = 0;
        int max = Integer.MIN_VALUE;
        while(i>j)
        {
            int sum = A[i] + A[j];

            if (sum < K) {
                max = Math.max(max, sum);
                i++;
            } else{
                j--;
            }
        }
        return max;
    }
    public int[] Num167twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int i = 0;  // left pointer
        int j = numbers.length - 1;  // right pointer
        while (i < j) {  // two numbers, so "i != j"
            int sum = numbers[i] + numbers[j];
            if (sum == target) {  // not zero-based
                result[0] = i + 1;
                result[1] = j + 1;
                return result;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }
    public int Num243shortestDistance(String[] words, String word1, String word2) {
        int i1 = Integer.MAX_VALUE,i2 = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<words.length;i++)
        {
            if(words[i].equals(word1))
                i1 = i;
            else if(words[i].equals(word2))
                i2 = i;
            if(Math.abs(i2-i1) < min)
                min = Math.abs(i2-i1);
        }
        return min;
    }
    public void Num237deleteNode(ListNode node) {
        while(node.next.next!=null)
        {
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }

    public void Num283moveZeroes(int[] nums) {

        int i = 0,j = 0;
        while(i<=nums.length-1&& j<=nums.length-1)
        {
            while(i<=nums.length-1&&nums[i]!=0)
            {
                i++;
            }
            while(j<=nums.length-1&&nums[j]==0)
            {
                j++;
            }
            if(i>nums.length-1 || j>nums.length-1 || i>j)
                break;
            else
            {
                nums[i] = nums[j];
                nums[j] = 0;
            }
        }
    }
    public int Num169majorityElement(int[] nums) {
        if(nums.length==1) return nums[0];
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++)
        {
            if(!map.containsKey(nums[i]))
                map.put(nums[i],1);
            else
            {
                if(map.get(nums[i])+1>nums.length/2)
                    return nums[i];
                map.put(nums[i],map.get(nums[i])+1);
            }
        }
        return -1;
    }
    public List<Integer> Num448findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new LinkedList<>();
        for(int i=0;i<nums.length;i++)
        {
            int index = Math.abs(nums[i])-1;
            if(nums[index] >0)
            {
                nums[index]*=-1;
            }
        }
        for(int i=1;i<=nums.length;i++)
        {
            if(nums[i-1]>0)
                ans.add(i);
        }
        return ans;
    }
    public List<Integer> Num442findDuplicates(int[] nums) {
        List<Integer> ans = new LinkedList<>();
        for(int i=0;i<nums.length;i++)
        {
            int index = Math.abs(nums[i])-1;
            if(nums[index]>0)
            {
                nums[index]*=-1;
            }
            else if(nums[index]<0)
            {
                ans.add(index+1);
            }
        }
        return ans;
    }
    public int NUm256minCost(int[][] costs) {
        if(costs.length==0) return 0;
        int[][] dp = new int[costs.length][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for(int i=1;i<costs.length;i++)
        {
            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2])+costs[i][0];
            dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2])+costs[i][1];
            dp[i][2] = Math.min(dp[i-1][1],dp[i-1][0])+costs[i][2];
        }

        int min = Math.min(dp[costs.length-1][0],dp[costs.length-1][1]);
        min = Math.min(min,dp[costs.length-1][2]);
        return min;
    }
    public int Num1022sumRootToLeaf(TreeNode root) {
        int[] ans = new int[1];
        Num1022SUm(root,ans,"");
        return ans[0];
    }
    public void Num1022SUm(TreeNode node,int[] ans,String ancestor)
    {
        if(node.left==null&&node.right==null)
        {
            ancestor = ancestor+Integer.toString(node.val);
            int i = Integer.parseInt(ancestor,2);
            ans[0]+=i;
        }
        else
        {
            if(node.left!=null)
            {
                Num1022SUm(node.left,ans,ancestor+Integer.toString(node.val));
            }
            if(node.right!=null)
            {
                Num1022SUm(node.right,ans,ancestor+Integer.toString(node.val));
            }
        }
    }

    public String Num1370sortString(String s) {
        int[] carray = new int[26];
        for(int i=0;i<s.length();i++)
        {
            carray[s.charAt(i)-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while(sb.length()<s.length())
        {
            for(int i=0;i<26;i++)
            {
                if(carray[i]!=0)
                {
                    sb.append((char)(i+'a'));
                    carray[i]--;
                }
            }
            for(int i=25;i>=0;i--)
            {
                if(carray[i]!=0)
                {
                    sb.append((char)(i+'a'));
                    carray[i]--;
                }
            }
        }
        return sb.toString();
    }

    public int[] Num349intersection(int[] nums1, int[] nums2) {
        List<Integer> list= new ArrayList<>();
        Map<Integer,Integer> map= new HashMap<>();
        for(int i=0;i<nums1.length;i++){
            map.put(nums1[i],1);
        }
        for(int i=0;i<nums2.length;i++){
            if(map.containsKey(nums2[i])){
                list.add(nums2[i]);
                map.remove(nums2[i]);
            }
        }
        int[] arr= new int[list.size()];
        for(int i=0;i<arr.length;i++){
            arr[i]=list.get(i);
        }
        return arr;
    }

    public boolean Num860lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill: bills) {
            if (bill == 5)
                five++;
            else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public TreeNode Num108sortedArrayToBST(int[] nums) {
        return Num108ToBST(nums,0,nums.length-1);
    }

    public TreeNode Num108ToBST(int[] nums,int i,int j)
    {
        if(i>j)
        {
            return null;
        }
        else
        {
            int mid = Math.round((i+j)/2);
            TreeNode node = new TreeNode(nums[mid]);
            node.left = Num108ToBST(nums,i,mid-1);
            node.right = Num108ToBST(nums,mid+1,j);
            return node;
        }
    }

    public TreeNode Num538convertBST(TreeNode root) {
        int[] sum = new int[1];
        Num538BST(root,sum);
        return root;
    }

    public void Num538BST(TreeNode node, int[] sum)
    {
        if(node ==null) return;
        Num538BST(node.right,sum);
        int temp = node.val;
        node.val +=sum[0];
        sum[0]+=temp;
        Num538BST(node.left,sum);
    }

    public int Num111minDepth(TreeNode root) {
        if(root==null) return 0;
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        Num111Mindepth(root,min,0);
        return min[0];
    }

    public void Num111Mindepth(TreeNode node,int[] min,int depth)
    {
        if(node.left==null&&node.right==null)
        {
            if(depth+1<min[0])
            {
                min[0] = depth+1;
            }
        }
        if(node.left!=null)
        {
            Num111Mindepth(node.left,min,depth+1);
        }
        if(node.right!=null)
        {
            Num111Mindepth(node.right,min,depth+1);
        }
    }

    public boolean Num112hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        sum -= root.val;
        if ((root.left == null) && (root.right == null))
            return (sum == 0);
        return Num112hasPathSum(root.left, sum) || Num112hasPathSum(root.right, sum);
    }

    int ans;
    public int Num687longestUnivaluePath(TreeNode root) {
        ans = 0;
        Num687arrowLength(root);
        return ans;
    }
    public int Num687arrowLength(TreeNode node) {
        if (node == null) return 0;
        int left = Num687arrowLength(node.left);
        int right = Num687arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    public TreeNode Num105buildTree(int[] preorder, int[] inorder) {
        return buildtreehelper(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }

    public TreeNode buildtreehelper(int[] preorder,int[] inorder,int prei,int prej,int ini,int inj)
    {
        if(prei>prej||ini>inj)
            return null;
        TreeNode node = new TreeNode(preorder[prei]);
        int i = ini;
        for(;i<=inj;i++)
        {
            if(inorder[i]==preorder[prei])
            {
                break;
            }
        }
        node.left = buildtreehelper(preorder,inorder,prei+1,prei+i-ini,ini,i-1);
        node.right = buildtreehelper(preorder,inorder,prei+i-ini+1,prej,i+1,inj);
        return node;
    }
    public List<Integer> Num145postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        postordertraversalhelper(root,result);
        return result;
    }
    public void postordertraversalhelper(TreeNode node,List<Integer> result)
    {
        if(node==null)
            return;
        postordertraversalhelper(node.left,result);
        postordertraversalhelper(node.right,result);
        result.add(node.val);
    }
    public List<List<Integer>> Num103zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if(root==null)
        {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count=0;
        while(queue.size()!=0)
        {
            int num = queue.size();
            List<Integer> levellist = new LinkedList<>();
            for(int i=0;i<num;i++)
            {
                TreeNode node = queue.peek();
                if(node.left!=null)
                {
                    queue.add(node.left);
                }
                if(node.right!=null)
                {
                    queue.add(node.right);
                }
                if(count%2==0)
                {
                    levellist.add(node.val);
                    queue.remove();
                }
                else
                {
                    levellist.add(0,node.val);
                    queue.remove();
                }
            }
            count+=1;
            result.add(levellist);
        }
        return result;
    }
    public int Num276numWays(int n, int k) {
        if(n==0)
        {
            return 0;
        }else if (n==1)
        {
            return k;
        }
        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = k*k;
        for(int i=2;i<n;i++)
        {
            dp[i] = dp[i-1]*(k-1)+dp[i-2]*(k-1);
        }
        return dp[n-1];
    }
    public List<Integer> Num94inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        inordertraversalhelper(root,result);
        return result;
    }

    public void inordertraversalhelper(TreeNode node,List<Integer>result)
    {
        if(node==null)
            return;
        inordertraversalhelper(node.left,result);
        result.add(node.val);
        inordertraversalhelper(node.right,result);
    }
    public int Num230kthSmallest(TreeNode root, int k) {
        int[] result = new int[2];
        result[1] = 1;
        Kthsmallesthelper(root,k,result);
        return result[0];
    }

    public void Kthsmallesthelper(TreeNode node,int k,int[] result)
    {
        if(node==null)
            return;
        Kthsmallesthelper(node.left,k,result);
        if(result[1]==k)
        {
            result[0] = node.val;
        }
        result[1]+=1;
        Kthsmallesthelper(node.right,k,result);
    }
    public void Num114flatten(TreeNode root) {
        if(root==null)
            return;
        Num114flatten(root.left);
        Num114flatten(root.right);
        TreeNode node = root.right;
        root.right = root.left;
        root.left=null;
        while(root.right!=null)
        {
            root = root.right;
        }
        root.right = node;

    }
    public List<Integer> Num144preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        preordertraversalhelper(root,result);
        return result;
    }
    public void preordertraversalhelper(TreeNode node,List<Integer> result)
    {
        if(node==null)
            return;
        result.add(node.val);
        preordertraversalhelper(node.left,result);
        preordertraversalhelper(node.right,result);
    }
    public List<Integer> Num144Sol2preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty())
        {
            TreeNode node = stack.pop();
            if(node.right!=null)
            {
                stack.push(node.right);
            }
            if(node.left!=null)
            {
                stack.push(node.left);
            }
            result.add(node.val);
        }
        return result;
    }
    public int[] Num238productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        for(int i=1;i<nums.length;i++)
        {
            ans[i] = ans[i-1] * nums[i-1];
        }
        int a = nums[nums.length-1];
        for(int i=nums.length-2;i>0;i--)
        {
            ans[i] = ans[i] * a;
            a*=nums[i];
        }
        return ans;
    }
    public ListNode Num19removeNthFromEnd(ListNode head, int n) {
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode ihead = node,jnode = node;
        for(int i=0;i<n;i++)
        {
            jnode = jnode.next;
        }
        while(jnode.next!=null)
        {
            jnode = jnode.next;
            ihead = ihead.next;
        }
        ihead.next = ihead.next.next;
        return node.next;

    }
    public List<List<Integer>> Num366findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        int i=Num366helper(root,result);
        return result;
    }
    public int Num366helper(TreeNode node,List<List<Integer>> result)
    {
        if(node==null)
            return -1;
        int leftdepth = Num366helper(node.left,result);
        int rightdepth = Num366helper(node.right,result);
        int currentdepth = Math.max(leftdepth,rightdepth)+1;
        if(result.size()<=currentdepth)
        {
            result.add(new ArrayList<>());
        }
        result.get(currentdepth).add(node.val);
        return currentdepth;
    }
    public void Num48rotate(int[][] matrix) {

    }
    public int minPathSum(int[][] grid) {
        if(grid==null)
            return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int i=1;i<grid.length;i++)
        {
            dp[i][0] = dp[i-1][0];
        }
        for(int j=1;j<grid[0].length;j++)
        {
            dp[0][j] = dp[0][j-1];
        }
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {

                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];

            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
    public int Num96numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1] = 1;
        return Num96helper(n,dp);
    }
    public int Num96helper(int n,int[] dp)
    {
        if(dp[n]!=0)
        {
            return dp[n];
        }
        int sum = 0;
        for(int i=0;i<n;i++)
        {
            sum+=Num96helper(i,dp)*Num96helper(n-i-1,dp);
        }
        dp[n] = sum;
        return sum;
    }
    public List<Integer> Num199rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            int k = Integer.MAX_VALUE;
            int i = queue.size();
            for(int j=0;j<i;j++)
            {
                TreeNode node = queue.poll();
                if(node.right!=null)
                {
                    queue.add(node.right);
                }
                if(node.left!=null)
                {
                    queue.add(node.left);
                }
                if(k==Integer.MAX_VALUE)
                {
                    k = node.val;
                }
            }
            result.add(k);
        }
        return result;
    }
    public List<Boolean> Num1431kidsWithCandies(int[] candies, int extraCandies) {
        int max = -1;
        for (int i = 0; i < candies.length; i++) {
            max = Math.max(candies[i], max);
        }
        List<Boolean> b = new ArrayList<>();
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                b.add(Boolean.TRUE);
            } else {
                b.add(Boolean.FALSE);
            }
        }
        return b;
    }
    public int Num279numSquares(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // bottom case
        dp[0] = 0;

        // pre-calculate the square numbers.
        int max_square_index = (int) Math.sqrt(n) + 1;
        int square_nums[] = new int[max_square_index];
        for (int i = 1; i < max_square_index; ++i) {
            square_nums[i] = i * i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int s = 1; s < max_square_index; ++s) {
                if (i < square_nums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
            }
        }
        return dp[n];
    }
    public int Num120minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0)
        {
            return 0;
        }
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        int pre = 0,current;
        for(int i=1;i<triangle.size();i++)
        {
            List<Integer> rows = triangle.get(i);
            for(int j=0;j<=i;j++)
            {
                current=dp[j];
                if(j==0)
                {
                    dp[j] = current+rows.get(j);
                }
                else if(j==i)
                {
                    dp[j] = pre+rows.get(j);
                }
                else
                {
                    dp[j] = Math.min(current,pre)+rows.get(i);
                }
                pre = current;
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i=0;i<triangle.size();i++)
        {
            res = Math.min(res,dp[i]);
        }
        return res;
    }
    public int Num11maxArea(int[] height) {
        if(height.length<=1)
        {
            return 0;
        }
        int front = 0,end = height.length-1;
        int maxarea = Integer.MIN_VALUE;
        while(front!=end)
        {
            maxarea = Math.max(maxarea,(end-front)*Math.min(height[front],height[end]));
            if(height[front] <=height[end])
            {
                front++;
            }
            else
            {
                end--;
            }
        }
        return maxarea;
    }
    public void Num75sortColors(int[] nums) {
        int curr = 0,p2 = nums.length-1,p0 = 0;
        int tmp;
        while(curr<=p2)
        {
            if(nums[curr]==0)
            {
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            }
            else if(nums[curr]==2)
            {
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2++] = tmp;
            }
            else
            {
                curr++;
            }
        }

    }
    public int Num312maxCoins(int[] nums) {
        int[][] dp = new int[nums.length+2][nums.length+2];
        int[] newarray = new int[nums.length+2];
        newarray[0] = 1;
        newarray[nums.length+1] = 1;
        for(int i=1;i<newarray.length-1;i++)
        {
            newarray[i] = nums[i-1];
        }
        for (int j = 2; j < newarray.length; j++) {
            for (int i = 0; i < newarray.length - j; i++) {
                for (int k = i + 1; k < i + j; k++) {
                    dp[i][i + j] = Math.max(dp[i][i + j], dp[i][k] + dp[k][i + j] + newarray[i] * newarray[k] * newarray[i + j]);
                }
            }
        }
        return dp[0][newarray.length-1];
    }
    public List<String> Num293generatePossibleNextMoves(String s) {
        List<String> ans = new LinkedList<>();
        char[] sarray = s.toCharArray();
        for(int i=0;i<s.length()-1;i++)
        {
            if(sarray[i]=='+'&&sarray[i+1]=='+')
            {
                sarray[i] = '-';
                sarray[i+1] ='-';
                ans.add(new String((sarray)));
                sarray[i] = '+';
                sarray[i+1] ='+';
            }
        }
        return ans;
    }
    public int Num171titleToNumber(String s) {
        int ans = 0;
        int base = 26;
        int length  = s.length()-1;
        for(int i=0;i<s.length();i++)
        {
            ans= (int) (ans + ((s.charAt(i)-'A')+1)*Math.pow(base,length));
            length-=1;
        }
        return ans;
    }
    public List<List<Integer>> Num118generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if(numRows ==0)
        {
            return ans;
        }
        ans.add(new ArrayList<>());
        ans.get(0).add(1);
        for(int i=1;i<numRows;i++)
        {
            List<Integer> row = new ArrayList<>();
            List<Integer> rowpre = ans.get(i-1);
            row.add(1);
            for(int j=1;j<numRows-1;j++)
            {
                row.add(rowpre.get(j-1)+rowpre.get(j));
            }
            row.add(1);
            ans.add(row);
        }
        return ans;
    }

    public int Num122maxProfit(int[] prices) {
        int maxprofit = 0;
        for(int i=1;i<prices.length;i++)
        {
            if(prices[i] > prices[i-1])
            {
                maxprofit+=(prices[i]-prices[i-1]);
            }
        }
        return maxprofit;
    }

    public int Num997findJudge(int N, int[][] trust) {
        if (trust.length < N - 1) {
            return -1;
        }

        int[] trustScores = new int[N + 1];

        for (int[] relation : trust) {
            trustScores[relation[0]]--;
            trustScores[relation[1]]++;
        }

        for (int i = 1; i <= N; i++) {
            if (trustScores[i] == N - 1) {
                return i;
            }
        }
        return -1;
    }
    public String Num38countAndSay(int n) {
        return Num38Answer(n);
    }

    public String Num38Answer(int n)
    {
        if(n==0)
        {
            return "";
        }
        if(n==1)
        {
            return "1";
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            String lastone = Num38Answer(n-1);
            int i=0;
            while(i<lastone.length())
            {
                int count = 1;
                while(i<lastone.length()-1&&lastone.charAt(i)==lastone.charAt(i+1))
                {
                    count++;
                    i+=1;
                }
                sb.append(Integer.toString(count)+lastone.charAt(i));
            }
            return sb.toString();
        }

    }
    public int NUm628maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int max1 = nums[0]*nums[1]*nums[nums.length-1];
        int max2 = nums[nums.length-1] * nums[nums.length-2] *nums[nums.length-3];
        return Math.max(max1,max2);
    }

    public int[] Num1042gardenNoAdj(int N, int[][] paths) {
        Map<Integer,List<Integer>> graph = new HashMap<>();
        for(int i=0;i<N;i++)
        {
            graph.put(i,new ArrayList<>());
        }
        for(int[] pairs:paths)
        {
            graph.get(pairs[0]-1).add(pairs[1]-1);
            graph.get(pairs[1]-1).add(pairs[0]-1);
        }
        int[] ans = new int[N];
        for(int i=0;i<N;i++)
        {
            boolean used[] = new boolean[5];
            for(int adj:graph.get(i))
            {
                used[ans[adj]] = true;
            }
            for(int j=1;j<=4;j++)
            {
                if(!used[j])
                {
                    ans[i] = j;
                }
            }
        }
        return ans;
    }




}
