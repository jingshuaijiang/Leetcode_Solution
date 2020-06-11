package com.company;

import javafx.util.Pair;

import javax.print.attribute.HashAttributeSet;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
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

    /**
     * This is a very classic dp problem. The key is not the formular, but what to return.
     * @param cost
     * @return
     */
    public int Num746minCostClimbingStairs(int[] cost) {
        int [] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = Math.min(cost[0],cost[1]);
        for(int i=2;i<cost.length;i++)
        {
            dp[i] = cost[i] + Math.min(dp[i-1],dp[i-2]);
        }
        return Math.min(dp[cost.length-1],dp[cost.length-2]);
    }

    public boolean Num717isOneBitCharacter(int[] bits) {
        int count = 0;
        for(int i=0;i<bits.length-1;)
        {
            if(bits[i]==0)
            {
                count+=1;
                i+=1;
            }
            else
            {
                count+=2;
                i+=2;
            }
        }
        return count==bits.length-2;
    }
    public int Num1450busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        for(int i=0;i<startTime.length;i++)
        {
            if(startTime[i]<=queryTime && queryTime<=endTime[i])
            {
                count++;
            }
        }
        return count;
    }
    public int Num1085sumOfDigits(int[] A) {
        int min = Integer.MAX_VALUE;
        for (int i : A) {
            min = Math.min(min, i);
        }
        int digits = 0;
        while (min != 0) {
            digits += (min % 10);
            min /= 10;
        }
        return digits % 2 == 0 ? 1 : 0;
    }

    public int[] Num922sortArrayByParityII(int[] A) {
        int[] ans = new int[A.length];
        int odd = 1;
        int even = 0;
        for(int i=0;i<A.length;i++)
        {
            if(A[i]%2==0)
            {
                ans[even]= A[i];
                even+=2;
            }
            else
            {
                ans[odd] =A[i];
                odd+=2;
            }
        }
        return ans;
    }

    public int Num1413minStartValue(int[] nums) {
        int min = nums[0];
        int sum = nums[0];
        for(int i=1;i<nums.length;i++)
        {
            sum+=nums[i];
            min = Math.min(sum,min);
        }
        if(min<=0)
        {
            return 1-min;
        }
        return min;
    }

    public boolean Num766isToeplitzMatrix(int[][] matrix) {
        for(int i=0;i<matrix[0].length;i++)
        {
            int increment = 1;
            int t = matrix[0][i];
            while((i+increment)<matrix[0].length&& increment <matrix.length)
            {
                if(matrix[increment][i+increment]!=t)
                {
                    return false;
                }
                increment++;
            }
        }
        for(int i=1;i<matrix.length;i++)
        {
            int increment = 1;
            int t = matrix[i][0];
            while((i+increment)<matrix.length&& increment <matrix[0].length)
            {
                if(matrix[i+increment][increment]!=t)
                {
                    return false;
                }
                increment++;
            }
        }
        return true;
    }

    public List<List<Integer>> Num1260shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        k = k%(m*n);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<m;i++)
        {
            List<Integer> row = new ArrayList<>();
            ans.add(i,row);
            for(int j=0;j<n;j++)
            {
                row.add(0);
            }
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                int newcol = (j+k)% n;
                int row = (j+k) /n;
                row = (i +row)%m;
                ans.get(row).set(newcol,grid[i][j]);
            }
        }
        return ans;

    }

    public int[][] Num566matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int col = nums[0].length;
        if((row*col)!=(r*c))
            return nums;
        int[][] ans = new int[r][c];
        int arow = 0;
        int acol = 0;
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                int newcol = (i*col+j)%c;
                int newrow = (i*col+j)/c;
                ans[newrow][newcol] = nums[i][j];
            }
        }
        return ans;
    }

    public List<Integer> NUm989addToArrayForm(int[] A, int K) {
        int carrier = 0;
        int point = A.length-1;
        List<Integer> ans = new ArrayList<>();
        while(point>=0||K>0)
        {
            int a = K%10;
            int curr = 0;
            if(point >=0)
            {
                curr = A[point]+a+carrier;
            }
            else
            {
                curr = a+carrier;
            }
            carrier= curr/10;
            curr = curr%10;
            ans.add(0,curr);
            point--;
            K = K/10;
        }
        if(carrier!=0)
        {
            ans.add(0,carrier);
        }
        return ans;

    }

    public int Num724pivotIndex(int[] nums) {
        if(nums.length==0||nums.length==2)
            return -1;
        if(nums.length==1)
        {
            return 0;
        }
        int leftsum = nums[0];
        int rightsum = 0;
        for(int i=2;i<nums.length;i++)
        {
            rightsum+=nums[i];
        }

        for(int j=1;j<nums.length-1;j++)
        {
            if(leftsum==rightsum)
            {
                return j;
            }
            leftsum+=nums[j];
            rightsum-=nums[j+1];
        }
        return -1;
    }

    /**
     * Needs to be done again
     * @param seats
     * @return
     */

    public int Num849maxDistToClosest(int[] seats) {
        int pre = -1;
        int after = 0;
        int max = Integer.MIN_VALUE;
        int ans = 0;
        for(int i=0;i<seats.length;i++)
        {
            if(seats[i]==1)
            {
                pre = i;
            }
            else
            {
                while(after <seats.length && seats[after]==0 || after < i)
                {
                    after++;
                }
                int left = pre == -1? seats.length:i-pre;
                int right = after == seats.length? seats.length: after -i;
                ans = Math.max(ans,Math.min(left,right));
            }
        }
        return ans;
    }

    public int[] Num66plusOne(int[] digits) {
        int n = digits.length-1;
        int carrier = 0;
        for(int i=n;i>=0;i--)
        {
            if(digits[i]==9)
            {
                digits[i] = 0;

            }
            else
            {
                digits[i]+=1;
                return digits;
            }
        }
        int[] ans = new int[n+2];
        ans[0] = 1;
        for(int i=0;i<=n;i++)
        {
            ans[i+1] = digits[i];
        }
        return ans;

    }

    public int[] Num1409processQueries(int[] queries, int m) {
        int[] ans = new int[queries.length];
        List<Integer> helplist = new ArrayList<>();
        for(int i=1;i<=m;i++)
        {
            helplist.add(i);
        }
        for(int i=0;i<queries.length;i++)
        {
            int query = queries[i];
            for(int j=0;j<m;j++)
            {
                if(helplist.get(j) == query)
                {
                    ans[i] = j;
                    helplist.remove(j);
                    helplist.add(0,query);
                    break;
                }
            }
        }

        return ans;
    }
    public int Num1395numTeams(int[] rating) {
        int n = rating.length;
        int sum = 0;
        for(int i=0;i<n;i++)
        {
            int leftless=0,leftmore = 0;
            int rightless=0,rightmore = 0;
            for(int j=0;j<i;j++)
            {
                if(rating[j]<rating[i])
                {
                    leftless++;
                }
                else if(rating[j]>rating[i])
                {
                    leftmore++;
                }
            }
            for(int j=i+1;j<n;j++)
            {
                if(rating[j]<rating[i])
                {
                    rightless++;
                }
                else if(rating[j]>rating[i])
                {
                    rightmore++;
                }
            }
            sum += (leftless*rightmore);
            sum+= (leftmore* rightless);
        }
        return sum;
    }

    public int[][] Num1329diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int diagnal = m+n-1;
        ArrayList<Integer>[] alist = new ArrayList[diagnal];
        for(int i=0;i<diagnal;i++)
        {
            alist[i] = new ArrayList<>(m);
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                alist[j-i+(m-1)].add(mat[i][j]);
            }
        }
        for(int i=0;i<diagnal;i++)
        {
            Collections.sort(alist[i]);
        }
        int[] a = new int[diagnal];
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                mat[i][j] = alist[j-i+m-1].get(a[j-i+m-1]);
                a[j-i+m-1]++;
            }
        }
        return mat;
    }

    public int Num1287findSpecialInteger(int[] arr) {
        int num = arr.length;
        int count = 1;
        for(int j=0;j<arr.length-1;j++)
        {
            if(arr[j]==arr[j+1])
            {
                count++;
                if(count > (num/4))
                {
                    return arr[j];
                }
            }
            else
            {
                count = 1;
            }

        }
        return -1;
    }

    public int[] Num1170numSmallerByFrequency(String[] queries, String[] words) {
        int[] query = new int[queries.length];
        int[] word = new int[12];
        for(String w: words)
        {
            word[smallCharFrequency(w)]++;
        }
        for(int i=word.length-2;i>=0;i--)
        {
            word[i] += word[i+1];
        }
        for(int i=0;i<queries.length;i++)
        {
            query[i] = word[smallCharFrequency(queries[i])+1];
        }
        return query;

    }

    private int smallCharFrequency(String s) {
        char max = 'z';
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr < max) {
                count = 1;
                max = curr;
            } else if (max == curr) count++;
        }
        return count;
    }

    public int Num1426countElements(int[] arr) {
        int [] counter = new int[1001];
        int sum = 0;
        for(int i=0;i<arr.length;i++)
        {
            counter[arr[i]]++;
        }
        for(int i=0;i<1000;i++)
        {
            sum = (counter[i+1]!=0)? (sum+counter[i]) : sum;
        }
        return sum;
    }

    public int[] Num1331arrayRankTransform(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> posMap = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        int cnt = 0;
        for(int n : set){
            posMap.put(n, ++cnt);
        }
        for(int i=0;i<arr.length;i++){
            arr[i] = posMap.get(arr[i]);
        }
        return arr;
    }

    /**
     * 模拟整个过程，直接放进去
     * @param deck
     * @return
     */
    public int[] Num950deckRevealedIncreasing(int[] deck) {
        int[] ans = new int[deck.length];
        Deque<Integer> help = new LinkedList<>();
        for(int i=0;i<deck.length;i++)
        {
            help.add(i);
        }
        Arrays.sort(deck);
        for(int i=0;i<deck.length;i++)
        {
            int index = help.pollFirst();
            ans[index] = deck[i];
            if(!help.isEmpty())
            {
                help.add(help.pollFirst());
            }
        }
        return ans;
    }

    public boolean Num1150isMajorityElement(int[] nums, int target) {
        int count = 1;
        int N = nums.length;
        for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i]==target)
            {
                if(nums[i+1]==nums[i])
                {
                    count++;
                    if(count > (N/2))
                    {
                        return true;
                    }
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }

    public int Num697findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> left = new HashMap<>(),
        right = new HashMap<>(),count = new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            if(!left.containsKey(nums[i])) {
                left.put(nums[i], i);
            }



                right.put(nums[i],i);
                count.put(nums[i],count.getOrDefault(nums[i],0)+1);

        }
        int ans = Integer.MAX_VALUE;
        int degree = Collections.max(count.values());
        for(int x: count.keySet())
        {
            if(count.get(x)==degree)
            {
                ans = Math.min(ans,right.get(x)-left.get(x)+1);
            }
        }
        return ans;
    }

    public int[] Num888fairCandySwap(int[] A, int[] B) {
        int suma = 0;
        int sumb = 0;
        for(int a:A)
        {
            suma+=a;
        }
        for(int b:B)
        {
            sumb+=b;
        }
        int p = (suma-sumb)/2;
        Set<Integer> setB = new HashSet();
        for (int x: B) setB.add(x);

        for (int x: A)
            if (setB.contains(x + p))
                return new int[]{x, x + p};

            throw null;
    }

    public List<Integer> Num119getRow(int rowIndex) {
        List<List<Integer>> ans =  new ArrayList<>();
        List<Integer> rowone = new ArrayList<>();
        rowone.add(0,1);
        ans.add(rowone);
        for(int i=1;i<=rowIndex;i++)
        {
            List<Integer> rowlist = new ArrayList<>();
            rowlist.add(0,1);
            rowlist.add(0,1);
            ans.add(rowlist);
        }
        for(int i=2;i<=rowIndex;i++)
        {
            for(int j = 1;j<i-1;j++)
            {
                int a = ans.get(i-2).get(j-1)+ans.get(i-2).get(j);
                ans.get(i).add(j,a);
            }
        }
        return ans.get(rowIndex-1);
    }

    public List<Integer> OptimizedNum119getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        pre.add(1);
        if(rowIndex==0)
        {
            return pre;
        }
        pre.add(1);
        if(rowIndex==1)
        {
            return pre;
        }
        for(int i=2;i<=rowIndex;i++)
        {

            for(int j=1;j<=i-1;j++)
            {
                int a = pre.get(j-1)+pre.get(j);
                pre.set(j,a);
            }

        }
        return pre;

    }

    public int Num1277countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++)
        {
            dp[i][0] = matrix[i][0];
        }
        for(int j=0;j<matrix[0].length;j++)
        {
            dp[0][j] = matrix[0][j];
        }
        for(int i=1;i<matrix.length;i++)
        {
            for(int j=1;j<matrix[0].length;j++)
            {
                if(matrix[i][j]==0)
                {
                    dp[i][j] = 0;
                }
                else
                {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1])+1;
                }
            }
        }

        int ans = 0;
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++) {
                ans+=dp[i][j];
            }
            }
        return ans;
    }

    public int Num695maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int ans = 0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                {
                    int area = Num695DFS(grid,i,j);
                    ans = Math.max(ans,area);
                }
            }
        }
        return ans;

    }
    public int Num695DFS(int[][] grid,int i,int j)
    {
        if (!(0 <= i && i < grid.length
                && 0 <= j && j < grid[0].length)) {
            return 0;
        }

        if(grid[i][j]!=1)
            return 0;

        grid[i][j] = 2;

        return 1
                + Num695DFS(grid, i - 1, j)
                + Num695DFS(grid, i + 1, j)
                + Num695DFS(grid, i, j - 1)
                + Num695DFS(grid, i, j + 1);

    }

    public List<List<Integer>> Num1222queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[][] seen = new boolean[8][8];

        for(int[] queen : queens){
            seen[queen[0]][queen[1]] = true;
        }

        int[] directions = {-1, 0, 1};
        for(int dx : directions){
            for(int dy : directions){
                if(dx == 0 && dy == 0) continue;

                int x = king[0];
                int y = king[1];

                while(x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8){
                    x += dx;
                    y += dy;
                    if(seen[x][y]){
                        result.add(Arrays.asList(x, y));
                        break;
                    }
                }
            }
        }
        return result;
    }

    public int Num485findMaxConsecutiveOnes(int[] nums) {
        if(nums.length==0)
        {
            return 0;
        }
        int pre = -1;
        int index = 0;
        int max = Integer.MIN_VALUE;
        while(index<nums.length)
        {
            if(nums[index]==1)
            {
                pre = index;
                while(index<nums.length && nums[index]==1)
                {
                    max = Math.max(index-pre,max);
                    index++;
                }
            }
            index++;
        }
        return max;
    }

    public boolean Num896isMonotonic(int[] A) {
        if(A.length==1)
            return true;
        int store = 0;
        int c = 0;
        for(int i=0;i<A.length-1;i++)
        {
            if(A[i]>A[i+1])
            {
                c = -1;
            }
            else if(A[i]==A[i+1])
            {
                c = 0;
                continue;
            }
            else
            {
                c = 1;
            }

            if(c!=store&&store!=0)
            {
                return false;
            }
            store = c;
        }
        return true;
    }
    /**
     * special topic of two pointers of the same direction
     */
    /**
     * Using two pointers of the same direction
     * @param s
     * @param nums
     * @return
     */
    public int Num209minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int min = nums.length;
        for(int i=0;i<nums.length;i++)
        {
            if(sum<s)
            {
                sum+=nums[i];
            }
            while(sum>=s)
            {
                sum-=nums[left];
                left++;
                min = Math.min(min,i-left+1);
            }
        }
        return min;
    }

    public int Num3lengthOfLongestSubstring(String s) {
        if(s.length()==1)
        {
            return 1;
        }
        int min = 0;
        int left = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++)
        {
            if(map.containsKey(s.charAt(i)))
            {
                int index = map.get(s.charAt(i));
                left = Math.max(index+1,left);
            }

            map.put(s.charAt(i),i);
            min = Math.max(min,i-left+1);


        }
        return min;
    }

    public int Num340lengthOfLongestSubstringKDistinct(String s, int k) {
        HashMap<Character,Integer> map = new HashMap<>();
        int min = 0;
        int left = 0;
        if(k==0)
            return 0;
        for(int i=0;i<s.length();i++)
        {
            if(!map.containsKey(s.charAt(i)))
            {
                map.put(s.charAt(i),1);
            }
            else
            {
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }
            if(map.size()<k)
            {

                min = Math.max(min,i-left+1);
            }
            else if(map.size()==k&&map.containsKey(s.charAt(i)))
            {
                min = Math.max(min,i-left+1);
            }
            else
            {
                while(map.size()>=k)
                {
                    if(map.get(s.charAt(left))==1)
                    {
                        map.remove(s.charAt(left));
                    }
                    else
                    {
                        map.put(s.charAt(left),map.get(s.charAt(left))-1);
                    }
                    left++;
                }
            }
        }
        return min;
    }

    public int[][] Num661imageSmoother(int[][] M) {
        int[][] ans = new int[M.length][M[0].length];
        for(int i=0;i<M.length;i++)
        {
            for(int j=0;j<M[0].length;j++)
            {
                int sum = 0;
                int count= 0;
                for(int ri=i-1;ri<=i+1;ri++)
                {
                    for(int rj = j-1;rj<=j+1;rj++)
                    {
                        if(0<=ri&&ri<=M.length-1&&0<=rj&&rj<=M[0].length-1)
                        {
                            count++;
                            sum+=M[ri][rj];
                        }
                    }
                }
                ans[i][j] = Math.round(sum/count);
            }
        }
        return ans;
    }

    public int Num674findLengthOfLCIS(int[] nums) {
        int max = 0;
        if(nums.length==0)
        {
            return max;
        }
        max+=1;
        int left = 0;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]>nums[i-1])
            {
                max = Math.max(max,i-left+1);
            }
            else
            {
                left = i;
            }
        }
        return max;
    }

    public void Num1089duplicateZeros(int[] arr) {
        int zeros = 0;
        for(int i=0;i<arr.length-zeros;i++)
        {
            if(arr[i]==0)
            {
                if(i+zeros==arr.length-1-zeros)
                {
                    arr[arr.length-1] = -1;
                    break;
                }
                zeros++;
            }
        }
        int last = (arr[arr.length-1] ==-1)? arr.length-1-zeros:arr.length-2-zeros;
        for(int i=last;i>=0;i--)
        {
            if(arr[i]==0)
            {
                arr[i+zeros]=0;
                zeros--;
                arr[i+zeros]=0;
            }
            else
            {
                arr[i+zeros] = arr[i];
            }
        }
    }

    public int Num1184distanceBetweenBusStops(int[] distance, int start, int destination) {
        int ans = 0;
        if(start==destination)
            return 0;
        if(start>destination)
        {
            ans = start;
            start = destination;
            destination = ans;
        }
        ans = 0;
        int clock = 0;
        for(int i=0;i<distance.length;i++)
        {
            ans+=distance[i];
            if(i>=start&&i<destination)
            {
                clock+=distance[i];
            }
        }
        return Math.min(clock,ans-clock);

    }

    public boolean Num1013canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for(int i=0;i<A.length;i++)
        {
            sum+=A[i];
        }
        if(sum%3!=0)
        {
            return false;
        }
        int sum1 = 0;
        int i=0,j = A.length-1;
        for(;i<A.length;i++)
        {
            sum1+=A[i];
            if(sum1==(sum/3))
                break;
        }
        sum1=0;
        for(;j>=0;j--)
        {
            sum1+=A[j];
            if(sum1==(sum/3))
                break;
        }
        if((i-j)>=-1)
            return false;
        return true;
    }

    public int[] Num350intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0,j=0,k=0;
        List<Integer> ans = new ArrayList<>();
        while(i<nums1.length&&j<nums2.length)
        {
            if(nums1[i]<nums2[j])
            {
                i++;
            }
            else if(nums1[i]>nums2[j])
            {
                j++;
            }
            else
            {
                nums1[k++] = nums1[i++];
                ++j;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }

    public List<List<Integer>> Num15threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<Pair> found = new HashSet<>();
        for(int i=0;i<nums.length;i++)
        {
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int j = i+1;j<nums.length;j++)
            {
                int complement = -nums[i]-nums[j];
                if(map.containsKey(complement)&&map.get(complement)!=j)
                {
                    int v1 = Math.min(nums[i],Math.min(complement,nums[j]));
                    int v2 = Math.max(nums[i],Math.max(complement,nums[j]));
                    if(found.add(new Pair(v1,v2)))
                    {
                        ans.add(Arrays.asList(nums[i],complement,nums[j]));
                    }
                }
                map.put(nums[j],j);
            }
        }
        return ans;
    }

    public List<List<Integer>> Num78subsets(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> addlist = new LinkedList<>();
        Num78recursiveHelper(ans,nums,0,addlist);
        return ans;
    }

    public void Num78recursiveHelper(List<List<Integer>> ans,int[] nums,int index,List<Integer> addlist)
    {
        if(index>nums.length-1)
        {

            ans.add(new ArrayList(addlist));
            return;
        }
        Num78recursiveHelper(ans,nums,index+1,addlist);
        addlist.add(nums[index]);
        Num78recursiveHelper(ans,nums,index+1,addlist);
        addlist.remove(addlist.size()-1);
        return;
    }

    public List<Integer> Num969pancakeSort(int[] A) {
        List<Integer> ans = new LinkedList<>();
        for(int i=A.length;i>=1;i--)
        {
            int index = Num969findmax(i,A);
            if(index == i-1)
                continue;
            if(index!=0)
            {
                Num969reverse(A,0,index);
                ans.add(index+1);
            }
            Num969reverse(A,0,i-1);
            ans.add(i);
        }
        return ans;
    }

    public int Num969findmax(int num,int[] A)
    {
        for(int i=0;i<A.length;i++)
        {
            if(A[i]==num)
            {
                return i;
            }
        }
        return -1;
    }

    public void Num969reverse(int[] A,int start,int end)
    {
        for(int i=0;i<(end-start+1)/2;i++)
        {
            int temp = A[start+i];
            A[start+i] = A[end-i];
            A[end-i] = temp;
        }
    }

    public int Num35searchInsert(int[] nums, int target) {
        if(target<nums[0])
            return 0;
        for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i]<target && nums[i+1]>target)
            {
                return i+1;
            }
            else if(nums[i]==target)
            {
                return i;
            }
        }
        if(nums[nums.length-1]==target)
            return nums.length-1;
        return nums.length;
    }

    public List<List<Integer>> Num830largeGroupPositions(String S) {
        List<List<Integer>> ans = new LinkedList<>();
        int count=1;
        for(int i=0;i<S.length();i++)
        {
            if(i==S.length()-1||S.charAt(i)!=S.charAt(i+1))
            {
                if(count>=3)
                {
                    List<Integer> newlist = new LinkedList<>();
                    newlist.add(i-count+1);
                    newlist.add(i);
                    ans.add(newlist);
                }
                count=1;
            }
            else
                count++;
        }
        return ans;
    }

    public void Num48rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i=0;i<n/2;i++)
        {
            for(int j=0;j<(n+1)/2;j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }

    }
    public void Num189rotate(int[] nums, int k) {
        k = k% nums.length;
        int count = 0;
        for(int start = 0;count<nums.length;start++)
        {
            int curr = start;
            int pre = nums[start];
            do{
                int next = (curr+k)%nums.length;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                curr = next;
                count++;
            }
            while(start!=curr);
        }
    }

    public int Num41firstMissingPositive(int[] nums) {
        int count1= 0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]==1)
            {
                count1++;
                break;
            }
        }
        if(count1==0)
            return 1;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]<=0||nums[i]>nums.length)
            {
                nums[i] = 1;
            }
        }
        for(int i=0;i<nums.length;i++)
        {
            int a  = Math.abs(nums[i]);
            if(a==nums.length)
            {
                nums[0] = -Math.abs(nums[0]);
            }
            else
                nums[a] = -Math.abs(nums[a]);

        }
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]>0)
                return i;
        }
        if(nums[0]>0)
            return 0;
        return nums.length+1;
    }

    public int Num28strStr(String haystack, String needle) {
        if(needle.equals(""))
        {
            return 0;
        }
        int L =haystack.length(), n = needle.length();
        for(int i=0;i<=L-n;i++)
        {
            if(haystack.substring(i,i+n).equals(needle))
            {
                return i;
            }
        }
        return -1;
    }

    public String Num299getHint(String secret, String guess) {
        int bulls=0;
        int cows = 0;
        int[] helplist = new int[10];
        for(int i=0;i<secret.length();i++)
        {
            if(secret.charAt(i)==guess.charAt(i))
            {
                bulls++;
            }

            helplist[secret.charAt(i)-'0']++;

        }
        for(int i=0;i<guess.length();i++)
        {
            if(helplist[guess.charAt(i)-'0']!=0)
            {
                cows++;
                helplist[guess.charAt(i)-'0'] -=1;
            }

        }
        return (bulls+ "A" +(cows-bulls)+"B");
    }

    /**
     * greedy algorithm maybe math problems.
     * Needs to be calculated in a math way.
     * @param gas
     * @param cost
     * @return
     */
    public int Num134canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        for(int i=0;i<gas.length;i++)
        {
            gas[i] = gas[i]-cost[i];
            sum+=gas[i];
        }
        if(sum<0)
            return -1;
        sum=0;
        int start = 0;
        for(int i=0;i<gas.length;i++)
        {
            sum+=gas[i];
            if(sum<0)
            {
                start=i+1;
                sum = 0;
            }
        }
        return start;
    }

    public int Num243shortestDistanceagain(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int minDistance = words.length;
        int currentDistance;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                i1 = i;
            } else if (words[i].equals(word2)) {
                i2 = i;
            }

            if (i1 != -1 && i2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(i1 - i2));
            }
        }
        return minDistance;
    }

    public int Num245shortestWordDistance(String[] words, String word1, String word2) {
        int i1=-1,i2 = -1;
        int minDistance = words.length;
        for(int i=0;i<words.length;i++)
        {
            if(word1.equals(word2))
            {
                if(words[i].equals(word1))
                {
                    if(i1==-1)
                    {
                        i1 = i;
                    }
                    else
                    {
                        minDistance = Math.min(minDistance,Math.abs(i-i1));
                        i1 = i;
                    }
                }

            }
            else
            {
                if(words[i].equals(word1))
                {
                    i1=i;
                }
                else if(words[i].equals(word2))
                {
                    i2 = i;
                }
                if(i1!=-1&&i2!=-1)
                {
                    minDistance = Math.min(minDistance,Math.abs(i1-i2));
                }
            }
        }
        return minDistance;
    }

    /**
     * using sort time is O(nlogn)
     * @param citations
     * @return
     */
    public int Num274hIndex(int[] citations) {
        int h_index = -1;
        Arrays.sort(citations);
        int n = citations.length;
        for(int i=0;i<n;i++)
        {
            if((n-i)<=citations[i])
                h_index = Math.max(h_index,n-i);
        }
        return h_index;
    }

    /**
     * O(n) time solution for Leetcode Num274
     * @param citations
     * @return
     */
    public int Num274hIndexOn(int[] citations) {
        int n = citations.length;
        int[] newlist = new int[n+1];
        for(int c:citations)
        {
            newlist[Math.min(n,c)]++;
        }
        int k=n;
        for(int s = newlist[n];k>s;s+=newlist[k])
            k--;
        return k;
    }

    public int numPairsDivisibleBy60(int[] time) {
        int[] list = new int[60];
        for(int t:time)
        {
            list[t%60]++;
        }
        int count = 0;
        for(int i=1;i<=29;i++)
        {
            count = count + list[i]*list[60-i];
        }
        count = (list[0]==0)? count : (list[0]*(list[0]-1))/2+count;
        count = (list[30]==0)? count : (list[30]*(list[30]-1))/2+count;
        return count;

    }

    /**
     * Dynamic programming
     * @param nums
     * @return
     */
    public boolean Num55canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for(int i=1;i<nums.length;i++)
        {
            for(int j=i-1;j>=0;j--)
            {
                dp[i] = dp[j] && (nums[j]>=i-j);
                if(dp[i]==true)
                    break;
            }
        }
        return dp[nums.length-1];
    }

    /**
     * typical problem that should be well studied.
     * Greedy vs DP
     * @param nums
     * @return
     */
    public boolean Num55canJumpGreedy(int[] nums) {
        int start = 0;
        while(start<nums.length||nums[start]!=0)
        {
            int max = -1;
            int maxindex = 0;
            for(int i=1;i<nums[start];i++)
            {
                if(start+i+nums[start+i]>max)
                {
                    max = start+i+nums[start+i];
                    maxindex = start+i;
                }
            }
            start = maxindex;
        }

        if(start<nums.length && nums[start]==0)
            return false;
        return true;
    }

    /**
     * the following question of jump game II, Since it is O(n^2) it exceeds the time limit
     * @param nums
     * @return
     */
    public int Num45jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for(int i=1;i<nums.length;i++)
        {
            dp[i] = nums.length;
            for(int j = i-1;j>=0;j--)
            {
                if(nums[j]+j>=i)
                {
                    dp[i] = Math.min(dp[j],dp[i]);
                }
            }
            dp[i]+=1;
        }
        return dp[nums.length-1];
    }

    /**
     * The greedy version of solution to question 45
     * Pretty typical problem. we have a start point and we use greedy strategy
     * to check in the range of the next point we can jump, which one can make us
     * move to the most far away. Then we made this point our next starting point
     * ANd iterate.
     *
     * Since this point can lead us to the furthest position. When we check its next,there is no
     * way that a point before this point whose cover is further than this point.
     * So, the greedy strategy works
     * @param nums
     * @return
     */
    public int Num45jumpGreedy(int[] nums)
    {
        int start = 0;
        int count = 0;
        while(start<nums.length-1)
        {
            int max = -1;
            int maxindex = 0;
            if(start+nums[start]>=nums.length-1)
            {
                count++;
                break;
            }
            for(int i=1;i<=nums[start];i++)
            {
                if(start+i+nums[start+i]>max)
                {
                    max = start+i+nums[start+i];
                    maxindex = start+i;
                }
            }
            start = maxindex;
            count++;
        }
        return count;
    }
    public List<Integer> Num1243transformArray(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        ans.add(arr[0]);
        if(arr.length==1)
            return ans;
        boolean changed = true;
        while(changed)
        {
            changed = false;
            int p = arr[0],t=0;
            for(int i=1;i<arr.length-1;i++)
            {
                t = arr[i];
                if(arr[i]>p && arr[i]>arr[i+1])
                {
                    changed = true;
                    arr[i]--;
                }
                if(arr[i]<p && arr[i]<arr[i+1])
                {
                    changed = true;
                    arr[i]++;
                }
                p = t;
            }
        }
        for(int i=1;i<arr.length-1;i++)
        {
            ans.add(arr[i]);
        }
        ans.add(arr[arr.length-1]);
        return ans;
    }

    /**
     * seris of best buy and sell. Question 1.
     * @param prices
     * @return
     */
    public int Num121maxProfit(int[] prices) {
        int maxprofit = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<prices.length;i++)
        {

            if(prices[i]<min)
            {
                min = prices[i];
            }else if(prices[i] - min > maxprofit)
            {
                maxprofit = prices[i] - min;
            }
        }
        return maxprofit;
    }

    public int Num122maxProfit2(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    public String Num1436destCity(List<List<String>> paths) {
        Map<String,String> map = new HashMap();
        for(List<String> l : paths){

            map.put(l.get(0),l.get(1));
        }
        for(Map.Entry<String,String> x : map.entrySet()){
            if(!map.containsKey(x.getValue()))
                return x.getValue();
        }

        return null;
    }

    public String Num1309freqAlphabets(String s) {
        StringBuilder result = new StringBuilder();
        int aValue = 'a' -1;
        for(int i = s.length() -1; i >= 0 ; i--) {
            int ascii = 0;
            if(s.charAt(i) == '#') {
                String sub = s.substring(i-2, i);
                ascii = Integer.parseInt(sub) + aValue;
                i-=2;
            }
            else {
                ascii =s.charAt(i)-'0' + aValue;
            }
            result.append((char)ascii);
        }
        return result.reverse().toString();
    }

    /**
     * This problem looks like some brain teaser.
     * If we have one odd number, it can only be divided with odd numbers. Then
     * his opponent can get an even number, in that turn odd lose, even win.
     * @param N
     * @return
     */
    public boolean Num1025divisorGame(int N) {
        if(N%2==0)
            return true;
        else
            return false;
    }

    public int[][] Num1030allCellsDistOrder(int R, int C, int r0, int c0) {
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a,b) ->
                (Math.abs(a[0]-r0) + Math.abs(a[1]-c0)) - (Math.abs(b[0]-r0) + Math.abs(b[1]-c0)));

        for(int row=0; row < R; row++){
            for(int col=0; col<C; col++){
                q.add(new int[]{row,col});
            }
        }

        int result[][] = new int[R*C][2];

        for(int i=0; i<result.length;i++){
            result[i] = q.poll();
        }

        return result;
    }

    /**
     * beautiful solution
     * @param prices
     * @return
     */
    public int Num123maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int K = 2;
        int[] dp = new int[prices.length];
        for (int k = 1; k <= K; k++) {
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                min = Math.min(prices[i] - dp[i], min);
                dp[i] = Math.max(dp[i - 1], prices[i] - min);
            }
        }
        return dp[prices.length - 1];
    }

    /**
     * Dynamic process space not optimal solution
     * @param prices
     * @return
     */
    public int Num309maxProfit(int[] prices) {
        int[][] dp = new int[prices.length+2][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = Integer.MIN_VALUE;
        for(int i=2;i<=prices.length+1;i++)
        {
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i-2]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-2][0]-prices[i-2]);
        }
        return dp[prices.length+1][0];
    }

    /**
     * Optimized DP of leetcode num309
     * @param prices
     * @return
     */
    public int Num309OptimizedmaxProfit(int[] prices) {
        int dp_i_0 = 0,dp_i_1 = Integer.MIN_VALUE;
        int dp_i_pre = 0;
        for(int i=0;i<prices.length;i++)
        {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0,dp_i_1+prices[i]);
            dp_i_1 = Math.max(dp_i_1,dp_i_pre-prices[i]);
            dp_i_pre = temp;
        }
        return dp_i_0;
    }

    /**
     * The end of this series questions
     * @param k
     * @param prices
     * @return
     */
    public int Num188maxProfit(int k, int[] prices) {
        int[][] dp = new int[prices.length][k+1];
        for(int j=1;j<=k;j++)
        {
            int min = Integer.MAX_VALUE;
            for(int i=1;i<prices.length;i++)
            {
                min = Math.min(prices[i]-dp[i][j-1],min);
                dp[i][j] = Math.max(dp[i-1][j],prices[i]-min);
            }
        }
        return dp[prices.length-1][k];
    }

    public int Num188maxProfitOptimized(int k, int[] prices) {
        if(prices.length==0)
            return 0;
        int[] dp = new int[prices.length];
        if(k>prices.length)
        {
            k = prices.length/2;
        }
        for(int j=0;j<=k;j++)
        {
            int min = prices[0];
            for(int i=1;i<prices.length;i++)
            {
                min = Math.min(prices[i]-dp[i],min);
                dp[i] = Math.max(dp[i-1],prices[i]-min);
            }
        }
        return dp[prices.length-1];
    }

    public int Num1464maxProduct(int[] nums) {
        int n = nums.length-1;
        Arrays.sort(nums);
        return (nums[n]-1)*(nums[n-1]-1);
    }

    public boolean Num1460canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target,arr);
    }

    /**
     * two pointers
     * @param height
     * @return
     */
    public int Num11maxAreaAgain(int[] height) {
        int start = 0;
        int max = Integer.MIN_VALUE;
        int end = height.length-1;
        while(start<end)
        {
            if(height[start]<=height[end])
            {
                max = Math.max(max,height[start]*(end-start));
                start++;
            }
            else
            {
                max = Math.max(max,height[end]*(end-start));
                end--;
            }
        }
        return max;
    }

    public int Num42trap(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        for(int i=1;i<height.length-1;i++)
        {
            max_left[i] = Math.max(max_left[i-1],height[i-1]);
        }
        for(int i=height.length-2;i>=0;i--)
        {
            max_right[i] = Math.max(max_right[i+1],height[i+1]);
        }
        for(int i=1;i<height.length-1;i++)
        {
            int min = Math.min(max_left[i],max_right[i]);
            if(min > height[i])
            {
                sum +=(min-height[i]);
            }
        }
        return sum;
    }

    public int Num1176dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int n = calories.length;
        int sum = 0;
        int ans = 0;
        for(int i=0;i<k;i++)
        {
            sum +=calories[i];
        }
        for(int i=0;i<n-k;i++)
        {
            if(sum>upper)
            {
                ans++;
            }
            else if(sum<lower)
            {
                ans--;
            }
            if(i!=n-k-1)
            {
                sum=sum-calories[i]+calories[i+k];
            }
        }
        return ans;
    }

    /**
     * two pointer
     * @param nums
     * @return
     */
    public boolean Num334increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for(int num:nums)
        {
            if(num<=first)
            {
                first = num;
            }
            else if(num<=second)
            {
                second = num;
            }
            else if(num>second)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * time for hashset to find is O(1)
     * @param nums
     * @return
     */
    public int Num128longestConsecutive(int[] nums) {
        Set<Integer> numset = new HashSet<>();
        for(int num:nums)
        {
            numset.add(num);
        }
        int ans = 0;
        for(int num:nums)
        {
            if(!numset.contains(num-1))
            {
                int current = num;
                int length = 1;
                while(numset.contains(current+1)){
                    current+=1;
                    length+=1;
                }
                ans = Math.max(ans,length);
            }
        }
        return ans;
    }

    public int Num287findDuplicate(int[] nums) {
        for(int i=0;i<nums.length;i++)
        {
            int value = Math.abs(nums[i]);
            if(nums[value]<0)
                return nums[value];
            else{
                nums[value] = -Math.abs(nums[value]);
            }
        }
        return -1;
    }

    public int Num1338minSetSize(int[] arr) {
        int n = arr.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num:arr)
        {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        List<Integer> counts = new ArrayList<>(map.values());
        Collections.sort(counts);
        Collections.reverse(counts);

        int length = 0;
        int sum = 0;
        for(int num:counts)
        {
            sum+=num;
            length++;
            if(sum>=n/2)
                break;
        }
        return length;
    }

    /**
     * DFS
     * @param grid
     * @return
     */
    public int Num1267countServers(int[][] grid) {
        int count = 0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                {
                    int num = Num1267counthelper(grid,i,j);
                    if(num>1)
                    {
                        count+=num;
                    }
                }
            }
        }
        return count;
    }

    public int Num1267counthelper(int[][] grid,int i,int j){
        if (!(0 <= i && i < grid.length
                && 0 <= j && j < grid[0].length)) {
            return 0;
        }
        grid[i][j] = 0;
        int sum = 1;
        for(int row = 0;row < grid.length; row++)
        {
            if(grid[row][j]==1)
            {
                sum+=Num1267counthelper(grid,row,j);
            }
        }
        for(int column = 0;column<grid[0].length;column++)
        {
            if(grid[i][column]==1)
            {
                sum+=Num1267counthelper(grid,i,column);
            }
        }

        return sum;
    }

    /**
     * very tricky problem. Turns out that we are going to find the number of subsequences
     * from 0 to i that contains exactly the number of 1 to i+1.
     * Then we just need to record the maximum number. If the length of the number equals to the maximum number, then we find a match.
     * @param light
     * @return
     */
    public int Num1375numTimesAllBlue(int[] light) {
        int count = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<light.length;i++)
        {
            max = Math.max(max,light[i]);
            if(max==i+1)
                count++;
        }
        return count;
    }

    /**
     * still very tricky problem, Its final state depends on the number of times it has been operated on: if the number of times is odd, it will eventually be on, if it is even, it will eventually be off.
     * For any number i, that is to find the number of its factors, and then judge the parity of the number, assuming i = ab, a and b always appear in pairs, that is, the number of factors is even.
     * But there is an exception, that is, when a=b, its factor number will be odd. Other times are always in pairs, that is, even numbers, which can be ignored.
     * Therefore, for n lights, after n operations, the number of lights on depends on 1~n. How many previous lights can be written in the form of aa.
     * @param n
     * @return
     */
    public int Num319bulbSwitch(int n) {
        int count = 0;
        for(int i=1;i*i<n;i++)
        {
            count++;
        }
        return count;
    }

    public int Num672flipLights(int n, int m) {
        n = Math.min(n, 3);
        if (m == 0) return 1;
        if (m == 1) return n == 1 ? 2 : n == 2 ? 3 : 4;
        if (m == 2) return n == 1 ? 2 : n == 2 ? 4 : 7;
        return n == 1 ? 2 : n == 2 ? 4 : 8;

    }

    public List<Integer> Num763partitionLabels(String S) {
        List<Integer> ans = new LinkedList<>();
        int[] counter = new int[26];
        Arrays.fill(counter,-1);
        for(int i=0;i<S.length();i++)
        {
            int index = S.charAt(i)-'a';
            counter[index] = i;
        }
        int max = -1;
        int a = 0;
        for(int i=0;i<S.length();i++)
        {
            int index = S.charAt(i)-'a';
            max = Math.max(max,counter[index]);
            if(i>=max)
            {
                ans.add(i-a+1);
                a = i+1;
            }
        }
        return ans;
    }

    public int[][] Num973kClosest(int[][] points, int K) {
        int[] dist = new int[points.length];
        for(int i=0;i<points.length;i++)
        {
            dist[i] = points[i][0]*points[i][0]+points[i][1]*points[i][1];
        }
        Arrays.sort(dist);
        int distk = dist[K-1];
        int[][] ans = new int[K][2];
        int t=0;
        for(int i=0;i<points.length;i++)
        {
            if((points[i][0]*points[i][0]+points[i][1]*points[i][1])<=distk)
            {
                ans[t] = points[i];
                t++;
            }
        }
        return ans;
    }

    public List<List<String>> Num49groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        int[] record = new int[26];
        for(int i=0;i<strs.length;i++)
        {
            Arrays.fill(record,0);
            for(int j=0;j<strs[i].length();j++)
            {
                record[strs[i].charAt(j)-'a']++;
            }
            String recordtostring = "";
            for(int j=0;j<26;j++)
            {
                recordtostring=recordtostring+record[j]+":";
            }
            if(!map.containsKey(recordtostring))
            {
                map.put(recordtostring, new LinkedList<>());
            }

            List<String> analist = map.get(recordtostring);
            analist.add(strs[i]);
            map.put(recordtostring,analist);
        }

        List<List<String>> ans = new LinkedList<>();
        for(String ke: map.keySet())
        {
            ans.add(map.get(ke));
        }
        return ans;
    }

    public List<String> Num692topKFrequent(String[] words, int k) {
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i< words.length;i++)
        {
            map.put(words[i],map.getOrDefault(words[i],0)+1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1,w2)-> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) :
                map.get(w1) - map.get(w2)
        );
        for(String word:map.keySet())
        {
            heap.offer(word);
            if(heap.size()>k)
                heap.poll();
        }
        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }

    public int Num1455isPrefixOfWord(String sentence, String searchWord) {
        int ans = -1;
        String[] word = sentence.split(" ");
        int n = searchWord.length();
        for(int i=0;i<word.length;i++)
        {
            if(word[i].length()<n)
                continue;
            if(word[i].substring(0,n-1).equals(searchWord))
            {
                ans = i+1;
            }
        }
        return ans;
    }

    /**
     * BFS needs to be done again.
     * @param grid
     * @return
     */
    public int Num994orangesRotting(int[][] grid) {
        Queue<Pair<Integer,Integer>> queue = new ArrayDeque<>();
        int freshorange = 0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                    freshorange++;
                if(grid[i][j]==2)
                    queue.add(new Pair(i,j));
            }
        }
        int ans = -1;
        ans+=1;
        int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while(!queue.isEmpty())
        {
            int length  = queue.size();
            for(int i=0;i<length;i++)
            {
                Pair<Integer,Integer> p = queue.poll();
                int prow = p.getKey();
                int pcol = p.getValue();
                for(int[] d:directions)
                {
                    int nrow = prow+d[0];
                    int ncol = pcol+d[1];
                    if(nrow>=0&&nrow<grid.length && ncol>=0 && ncol<grid[0].length)
                    {
                        if(grid[nrow][ncol]==1)
                        {
                            grid[nrow][ncol]=2;
                            freshorange--;
                            queue.add(new Pair(nrow,ncol));
                        }
                    }
                }

            }
            ans+=1;
        }
        return freshorange==0? ans: -1;
    }

    public int Num200numIslands(char[][] grid) {
        int num = 0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]=='1')
                {
                    Num200IslandHelper(grid,i,j);
                    num+=1;
                }
            }
        }
        return num;
    }

    public void Num200IslandHelper(char[][] grid,int i,int j)
    {
        if(!(0<=i&&i<grid.length&&0<=j && j<grid[0].length))
        {
            return;
        }
        if(grid[i][j]!='1')
            return;
        grid[i][j] = '2';
        Num200IslandHelper(grid,i+1,j);
        Num200IslandHelper(grid,i-1,j);
        Num200IslandHelper(grid,i,j-1);
        Num200IslandHelper(grid,i,j+1);
    }

    public int[] Num239maxSlidingWindow(int[] nums, int k) {
        if (k <= 0) return new int[0];
        if (nums == null || nums.length <= 1 || k == 1) return nums;

        Deque<Integer> deque = new ArrayDeque<>(k);
        int[] output = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            cleanDeque(deque, nums, i, k);
            deque.addLast(i);
            if (i - k + 1 >= 0) output[i - k + 1] = nums[deque.getFirst()];
        }
        return output;
    }

    private void cleanDeque(Deque<Integer> deque, int[] nums, int i, int k) {
        if (!deque.isEmpty() && deque.getFirst() < i - k + 1) deque.removeFirst();
        while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) deque.removeLast();
    }

    public int Num325maxSubArrayLen(int[] nums, int k) {
        if(nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int maxLength = Integer.MIN_VALUE;
        int runningSum = 0;
        for(int i=0;i<nums.length;i++){
            runningSum += nums[i];
            if(map.containsKey(runningSum - k)) {
                maxLength = Math.max(maxLength, i-map.get(runningSum-k));
            }
            if(!map.containsKey(runningSum)) map.put(runningSum, i);
        }
        return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }

    public List<Integer> Num1469getLonelyNodes(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Num1469TraverseHelper(root,ans);
        return ans;
    }

    public void Num1469TraverseHelper(TreeNode node, List<Integer> ans)
    {
        if(node==null)
            return;
        if(node.left==null&&node.right==null)
            return;
        if(node.left==null)
        {
            ans.add(node.right.val);
        }
        else if(node.right==null)
        {
            ans.add(node.left.val);
        }
        Num1469TraverseHelper(node.left,ans);
        Num1469TraverseHelper(node.right,ans);

    }

    public int Num694numDistinctIslands(int[][] grid) {
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                {
                    String[] pattern = new String[1];
                    Num694DFSHelper(grid,i,j,pattern,"0",0);
                    if(!map.containsKey(pattern[0]))
                    {
                        map.put(pattern[0],1);
                    }
                }
            }
        }
        return map.size();
    }

    public void Num694DFSHelper(int[][] grid, int i, int j,String[] pattern,String direction,int depth)
    {
        if(!(i>=0&&i<grid.length&&j>=0&&j<grid[0].length))
        {
            return;
        }
        if(grid[i][j]!=1)
            return;
        grid[i][j] = 2;
        pattern[0]=depth+":"+direction;
        Num694DFSHelper(grid,i-1,j,pattern,"up",depth+1);
        Num694DFSHelper(grid,i+1,j,pattern,"down",depth+1);
        Num694DFSHelper(grid,i,j-1,pattern,"left",depth+1);
        Num694DFSHelper(grid,i,j+1,pattern,"right",depth+1);
    }

    public boolean Num79exist(char[][] board, String word) {
        if(board.length == 0) return false;
        int h = board.length;
        int w = board[0].length;
        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                if(search(board, word, 0, i, j, w, h)){
                    return true;
                }
            }
        }
        return false;

    }

    private boolean search(char[][] board, String word, int d, int x, int y, int w, int h){
        // out of bound
        if(x<0 || x==w || y<0 || y==h || word.charAt(d) != board[y][x]){
            return false;
        }
        if(d == word.length() - 1){
            return true; // last word
        }
        char current = board[y][x];
        board[y][x] = 0;
        Boolean found = search(board, word, d+1, x+1, y, w, h) ||
                search(board, word, d+1, x-1, y, w,h) ||
                search(board, word, d+1, x, y+1, w,h) ||
                search(board, word, d+1, x, y-1, w,h);
        board[y][x] = current;
        return found;

    }

    /**
     * the logic is trivial, when you draw the picture you can see any points
     * that is Monotonically increasing of decreasing is useless, when just need one and the others
     * can be removed. So we just need to count the turing point
     *
     * @param nums
     * @return
     */
    public int Num376wiggleMaxLength(int[] nums) {
        if(nums.length<2)
            return nums.length;
        int prediff = nums[1]-nums[0];
        int count = prediff !=0?2:1;
        for(int i=2;i<nums.length;i++)
        {
            int diff = nums[i]-nums[i-1];
            if((diff>0&&prediff<=0)||(diff<0&&prediff>=0))
            {
                count++;
                prediff = diff;
            }
        }
        return count;
    }

    /**
     * just flip those abnormal ones with its previous one
     * @param nums
     */
    public void Num280wiggleSort(int[] nums) {
        boolean increasing = true;
        int temp =0;
        for(int i=1;i<nums.length;i++)
        {
            if(increasing)
            {
                if(nums[i]-nums[i-1]<0)
                {
                    temp = nums[i-1];
                    nums[i-1] = nums[i];
                    nums[i] = temp;
                }
                increasing = false;
            }
            else
            {
                if(nums[i]-nums[i-1]>0)
                {
                    temp = nums[i-1];
                    nums[i-1] = nums[i];
                    nums[i] = temp;
                }
                increasing = true;
            }
        }
    }

    /**
     * this is a wrong answer. Do not copy this.
     * @param nums
     */
    public void Num324wiggleSortWrong(int[] nums) {
        boolean increasing = true;
        int temp = 0;
        for(int i=1;i<nums.length;i++)
        {
            if(increasing)
            {
                if(nums[i]-nums[i-1]<0)
                {
                    Num324Swap(nums,i,i-1);
                }
                else if(nums[i]-nums[i-1]==0)
                {
                    int right = i+1;
                    while(nums[right]-nums[i-1]==0)
                    {
                        right++;
                    }
                    if(nums[right]<nums[i-1])
                        Num324Swap(nums,i-1,right);
                    else
                        Num324Swap(nums,i,right);
                }
                increasing = false;
            }
            else
            {
                if(nums[i]-nums[i-1]>0)
                {
                    Num324Swap(nums,i,i-1);
                }
                else if(nums[i]-nums[i-1]==0)
                {
                    int right = i+1;
                    while(nums[right]-nums[i-1]==0)
                    {
                        right++;
                    }
                    if(nums[right]>nums[i-1])
                        Num324Swap(nums,i-1,right);
                    else
                        Num324Swap(nums,i,right);
                }
                increasing = true;
            }
        }
    }

    private void Num324Swap(int[] nums,int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * needs to be done again
     * @param nums
     */
    public void wiggleSort(int[] nums)
    {
        Arrays.sort(nums);
        int len=nums.length,i = 0;
        int[] smaller=new int[len%2==0?len/2:(len/2+1)],bigger=new int[len/2];
        System.arraycopy(nums,0,smaller,0,smaller.length);
        System.arraycopy(nums,smaller.length,bigger,0,len/2);
        for (; i < len / 2; i++) {
            nums[2*i]=smaller[smaller.length-1-i];
            nums[2*i+1]=bigger[len/2-1-i];
        }
        if (len%2!=0) nums[2*i]=smaller[smaller.length-1-i];
    }

    public int[] Num1470shuffle(int[] nums, int n) {
        int[] ans = new int[nums.length];
        for(int i=0;i<n;i++)
        {
            ans[2*i] = nums[i];
            ans[2*i+1] = nums[i+n];
        }
        return ans;
    }

    public int Num278firstBadVersion(int n) {
        return Num278Helper(0,n);
    }

    public int Num278Helper(int start,int end)强核磁共振造影剂的副作用
    {
        if(start==end)
            return start;
        int index = (start+end+1)/2;
        if(isBadVersion(index))
        {
            return Num278Helper(start,index-1);
        }
        else
        {
            return Num278Helper(index+1,end);
        }
    }

    public int Num1011shipWithinDays(int[] weights, int D) {
        int right = 0;
        int left = Integer.MIN_VALUE;
        for(int i=0;i<weights.length;i++)
        {
            right+=weights[i];
            left = Math.max(weights[i],left);
        }

        while(left<right)
        {
            int mid = left + (right-left)/2;
            if(!Num1011LongerThanD(weights,D,mid))
            {
                left = mid+1;
            }
            else {
                right = mid;
            }
        }
        return left;
    }

    public boolean Num1011LongerThanD(int[] weights,int D,int maxweight)
    {
        int day = 0;
        int i=0;
        int compacity = 0;
        while(i<weights.length)
        {
            while(compacity<=maxweight&&i<weights.length)
            {
                compacity+=weights[i];
                i++;
            }
            if(compacity>maxweight)
            {
                i--;
                compacity=0;;
            }
            day++;
        }
        return day<=D;

    }

    public String Num1427stringShift(String s, int[][] shift) {
        int sum = 0;
        for(int i=0;i<shift.length;i++)
        {
            if(shift[i][0]==0)
            {
                sum-=shift[i][1];
            }
            else
            {
                sum+=shift[i][1];
            }
        }
        sum = sum%s.length();
        if(sum>0)
        {
            sum = s.length()-sum;
        }
        String removepart = s.substring(0,Math.abs(sum));
        String prefix = s.substring(Math.abs(sum),s.length());
        return prefix+removepart;

    }

    public int Num33search(int[] nums, int target) {
        int pivot = 0, ans = 0;
        if(nums.length==0)
            return -1;
            if(nums[0]>nums[nums.length-1])
            {
                pivot = Num33pivotpointhelper(nums,0,nums.length-1);
                if(nums[0]>target)
                {
                    ans = Num33SearchNumhelper(nums,pivot,nums.length-1,target);
                }
                else
                {
                    ans = Num33SearchNumhelper(nums,0,pivot-1,target);

                }
            }
            else
            {
                ans = Num33SearchNumhelper(nums,0,nums.length-1,target);
            }

        return ans;

    }

    public int Num33pivotpointhelper(int[] nums,int left,int right)
    {
        while(left<right)
        {
            int mid = left+(right-left)/2;
            if(nums[mid]>nums[right])
            {
                left = mid+1;
            }
            else
            {
                right = mid;
            }
        }
        return left;
    }

    public int Num33SearchNumhelper(int[] nums,int left,int right,int target)
    {
        while(left<right)
        {
            int mid = (right+left+1)/2;
            if(nums[mid]>target)
            {
                right = mid-1;
            }
            else
            {
                left = mid;
            }
        }
        if(nums[left]!=target)
            return -1;
        return left;
    }

    public int Num33Onepasssearch(int[] nums, int target)
    {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) end = mid - 1;
                else start = mid + 1;
            }
            else {
                if (target <= nums[end] && target > nums[mid]) start = mid + 1;
                else end = mid - 1;
            }
        }
        return -1;

    }

    /**
     * needs to be done again.
     * @param nums
     * @param target
     * @return
     */
    public boolean Num81search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start<=end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return true;
            else if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                start++;
                end--;
            } else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
            return false;
        }

    public int Num58lengthOfLastWord(String s) {
        int i = s.length()-1;
        int length = 0;
        while(i>=0)
        {
            if(s.charAt(i)==' ')
            {
                if(length==0)
                {
                    i--;
                }
                else
                {
                    return length;
                }
            }
            else
            {
                length+=1;
                i--;
            }
        }
        return length;
    }

    public int Num387firstUniqChar(String s) {
        int[] counter = new int[26];
        int min = Integer.MAX_VALUE;
        for(int i=0;i<s.length();i++)
        {
            if(counter[s.charAt(i)-'a']!=0)
            {
                counter[s.charAt(i)-'a'] = -1;
            }
            else
                counter[s.charAt(i)-'a'] = i+1;
        }

        for(int i=0;i<counter.length;i++)
        {
            if(counter[i]!=-1&&counter[i]!=0)
                min = Math.min(min,counter[i]-1);
        }
        return min==Integer.MAX_VALUE?-1 : min;
    }

    public boolean Num205isIsomorphic(String s, String t) {
        int[] s1=new int[256];
        int[] s2=new int[256];
        for(int i=0;i<s.length();i++){
            if(s1[s.charAt(i)]==0 && s2[t.charAt(i)]==0){
                s1[s.charAt(i)]=t.charAt(i);
                s2[t.charAt(i)]=s.charAt(i);
            }else if(s1[s.charAt(i)]!=(t.charAt(i)) ||
                    s2[t.charAt(i)]!=(s.charAt(i))) return false;
        }
        return true;
    }

    public String Num151reverseWords(String s) {
        String ans = "";
        if(s.length()==0)
            return ans;
        int index = s.length()-1;
        while(index>=0)
        {
            while(index>=0&&s.charAt(index)==' ')
            {
                index--;
            }
            if(index<0)
                break;
            String append = "";
            while(index>=0&&s.charAt(index)!=' ')
            {
                append = s.charAt(index)+append;
                index--;
            }
            ans+=append;
            ans +=" ";
        }
        if(ans.length()!=0)
        {
            ans = ans.substring(0,ans.length()-1);
        }
        return ans;
    }

    public boolean Num242isAnagram(String s, String t) {
        if(s.length()!=t.length())
            return false;
        int[] counter = new int[26];
        for(int i=0;i<s.length();i++)
        {
            counter[s.charAt(i)-'a']++;
            counter[t.charAt(i)-'a']--;
        }
        for(int i=0;i<26;i++)
        {
            if(counter[i]!=0)
                return false;
        }
        return true;
    }

    public boolean wordPattern(String pattern, String str) {
        boolean ans = true;
        Map<Character, String> map = new HashMap<>();
        String[] words = str.split(" ");
        char[] symbols = pattern.toCharArray();
        if (symbols.length != words.length) return false;

        for (int i = 0; i < words.length; i++) {
            if (map.get(symbols[i]) == null) {
                if (map.containsValue(words[i])) {
                    ans = false;
                    break;
                }
                map.put(symbols[i], words[i]);
            } else if (!map.get(symbols[i]).equals(words[i])) {
                ans = false;
                break;
            }
        }

        return ans;
    }

    /**
     * needs to be done again
     * i don't know if there is a easier solution.
     * @param s
     * @return
     */
    public boolean Num294canWin(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        return Num294Helper(arr);
    }

    public boolean Num294Helper(char[] arr)
    {
        for(int i=0;i<arr.length-1;i++)
        {
            if(arr[i]=='+'&&arr[i+1]=='+')
            {
                arr[i] = '-';
                arr[i+1] = '-';
                boolean win = !Num294Helper(arr);
                arr[i] = '+';
                arr[i+1] = '+';
                if(win)
                    return true;
            }
        }
        return false;
    }

    /**
     * I don't think some result to this standard problem is right.
     * For example "" and "" . It should be true. But the program provided by the website will return false;
     * @param s
     * @param t
     * @return
     */
    public boolean Num161isOneEditDistance(String s, String t) {
        if(Math.abs(s.length()-t.length())>=2||(s.equals("")&&t.equals("")))
            return false;
        int flag = 0;
        int si = 0;
        int ti = 0;
        while(si<s.length()&&ti<t.length())
        {
            if(s.charAt(si)!=t.charAt(ti))
            {
                if(flag==1)
                    return false;
                else if(s.length()==t.length())
                {
                    si++;
                    ti++;
                }
                else
                {
                    if(s.length()<t.length())
                    {
                        ti++;
                    }
                    else
                    {
                        si++;
                    }
                }
                flag = 1;
            }
            else
            {
                si++;
                ti++;
            }
        }
        return true;
    }

    public int Num72minDistance(String word1, String word2) {
        if(word1.length()==0||word2.length()==0)
        {
            return word1.length()==0? word2.length(): word1.length();
        }
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i=0;i<len1+1;i++)
        {
            dp[i][0] = i;
        }

        for(int j=0;j<len2+1;j++)
        {
            dp[0][j] = j;
        }
        for(int i=1;i<len1;i++)
        {
            for(int j=1;j<len2;j++)
            {
                int left = dp[i-1][j];
                int right = dp[i][j-1];
                int pre = dp[i-1][j-1];
                if(word1.charAt(i)==word2.charAt(j))
                {
                    pre-=1;
                }
                dp[i][j] = Math.min(left,Math.min(right,pre))+1;
            }
        }
        return dp[len1][len2];
    }

    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> ans = new LinkedList<>();
        HashMap<String,List<String>> map = new HashMap<>();
        for(int i=0;i<strings.length;i++)
        {
            String acer  = "0#";
            int front = strings[i].charAt(0)-'a';

            for(int j=1;j<strings[i].length();j++)
            {
                int curr = (strings[i].charAt(j)-'a'-front);
                if(curr<0)
                    curr+=26;
                acer = acer + curr+"#";
            }
            List<String> values = new LinkedList<>();
            if(map.containsKey(acer))
            {
                values = map.get(acer);
            }
            values.add(strings[i]);
            map.put(acer,values);
        }

        for(String key:map.keySet())
        {
            ans.add(map.get(key));
        }
        return ans;
    }


}
