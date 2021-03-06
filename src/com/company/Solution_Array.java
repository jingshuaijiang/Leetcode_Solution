package com.company;
import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.*;

public class Solution_Array {

    public int Num560subarraySum(int[] nums, int k) {
        HashMap<Integer,List<Integer>> prefix = new HashMap<>();
        int sum = 0;
        int ans = 0;
        prefix.put(0,new LinkedList<>());
        prefix.get(0).add(-1);
        for(int i=0;i<nums.length;i++)
        {
            sum+=nums[i];
            if(!prefix.containsKey(sum))
                prefix.put(sum,new LinkedList<>());
            prefix.get(sum).add(i);
            if(prefix.containsKey(sum-k))
            {
                int num = 0;
                for(int index:prefix.get(sum-k))
                {
                    num = index!=i?num+=1:num;
                }
                ans+=num;
            }
        }
        return ans;
    }



    public int Num16threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++)
        {
            int low = i+1,high = nums.length-1;
            while(low<high)
            {
                int sum = nums[i]+nums[low]+nums[high];
                if(Math.abs(target-sum)<Math.abs(diff))
                {
                    diff = target-sum;
                }
                if(sum>target)
                    high--;
                else
                    low++;
            }
        }
        return target-diff;
    }

    public int Num259threeSumSmaller(int[] nums, int target) {
        int ans = 0;
        int n = nums.length;
        if(n<3)
            return 0;
        Arrays.sort(nums);
        for(int i=0;i<=nums.length-3;i++)
        {
            if(nums[i]+nums[i+1]+nums[i+2]>=target)
                break;
            int low = i+1,high = nums.length-1;
            while (low<high)
            {
                int sum = nums[i]+nums[low]+nums[high];
                if(sum<target)
                {
                    ans+=(high-low);
                    low++;
                }
                else
                {
                    high--;
                }
            }
        }
        return ans;
    }

    public int Num1551minOperations(int n) {
        if(n%2==0)
            return n*n/4;
        else return (n*n-1)/4;
    }

    /**
     * can be optimized by using two int max and min to replace the dp array.
     * @param nums
     * @return
     */
    public int Num152maxProduct(int[] nums) {
        int n = nums.length;
        if(n==0)
            return 0;
        int ans = Integer.MIN_VALUE;
        int[][] dp = new int[n+1][2];
        for(int i=0;i<n;i++)
        {
            if(nums[i]==0)
            {
                dp[i+1][0] = 0;
                dp[i+1][1] = 0;
            }
            else if(nums[i]>0)
            {

                dp[i+1][0] = Math.max(nums[i],dp[i][0]*nums[i]);
                dp[i+1][1] = dp[i][1] * nums[i];
            }
            else
            {
                dp[i+1][1] = Math.min(nums[i],dp[i][0]*nums[i]);
                dp[i+1][0] = dp[i][1] * nums[i];
            }
            ans = dp[i+1][0]!=0?Math.max(ans,dp[i+1][0]):Math.max(ans,dp[i+1][1]);
        }
        return ans;
    }

    public boolean Num219containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public boolean Num220containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(t<0) return false;
        Map<Long,Long> bucket = new HashMap<>();
        long w = (long) t+1;
        for(int i=0;i<nums.length;i++)
        {
            long ID = Num220getid(nums[i],w);
            if(bucket.containsKey(ID))
                return true;
            if(bucket.containsKey(ID-1)&&Math.abs(nums[i]-bucket.get(ID-1))<w)
                return true;
            if(bucket.containsKey(ID+1)&&Math.abs(nums[i]-bucket.get(ID+1))<w)
                return true;
            bucket.put(ID,(long)nums[i]);
            if(i-k>=0)
                bucket.remove(Num220getid(nums[i-k],w));
        }
        return false;
    }

    public long Num220getid(int a, int b)
    {
        return a<0?(a+1)/b-1:a/b;
    }

    public List<Integer> Num54spiralOrder(int[][] matrix) {
        List<Integer> ans = new LinkedList<>();
        if(matrix.length==0||matrix[0].length==0)
            return ans;
        int n = matrix.length;
        int m = matrix[0].length;
        int col = 0;
        int row = 0;
        while(row<n&&col<m)
        {
            for(int i=col;i<m;i++)
                ans.add(matrix[row][i]);
            row++;
            for(int j=row;j<n;j++)
                ans.add(matrix[j][m-1]);
            m--;
            if(n<=row)
                break;
            for(int i=m-1;i>=col;i--)
                ans.add(matrix[n-1][i]);
            n--;
            if(m<=col)
                break;
            for(int j=n-1;j>=row;j--)
                ans.add(matrix[j][col]);
            col++;
        }
        return ans;
    }

    public int Num1428leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int n = dim.get(0);
        int m = dim.get(1);
        int leftmost = m;
        for(int i=0;i<n;i++)
        {
            for(int j=leftmost-1;j>=0;j--)
            {
                if(binaryMatrix.get(i,j)==0)
                    break;
                else
                    leftmost=j;
            }
            if(leftmost==0)
                return 0;
        }
        return leftmost==m?-1:leftmost;
    }

    public int Num974subarraysDivByK(int[] A, int K) {
        int[] counter = new int[K];
        counter[0]=1;
        int sum = 0;
        int ans = 0;
        int remain = 0;
        for(int i=0;i<A.length;i++)
        {
            sum+=A[i];
            remain = sum%K;
            if(remain<0)
                remain+=K;
            ans+=counter[remain];
            counter[remain]++;
        }
        return ans;
    }

    public void Num289gameOfLife(int[][] board) {
        int[][] directions = new int[][]{{-1,0},{0,1},{0,-1},{1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                int live = 0;
                int dead = 0;
                for(int[] dir: directions)
                {
                    int currenti = i+dir[0];
                    int currentj = j+dir[1];
                    if(0<=currenti&&currenti<board.length&&0<=currentj&&currentj<board[0].length)
                    {
                        if(board[currenti][currentj]==2)
                            live++;
                        if(board[currenti][currentj]==1)
                            live++;
                        else
                            dead++;
                    }
                }
                if(board[i][j]==1)
                {
                    if(live<2)
                        board[i][j] = 2;
                    if(live>3)
                        board[i][j] = 2;
                }
                if(board[i][j]==0)
                {
                    if(live==3)
                        board[i][j] = -1;
                }
            }
        }
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]==2)
                    board[i][j]=0;
                if(board[i][j]==-1)
                    board[i][j]=1;
            }
        }
    }

    public int Num1031maxSumTwoNoOverlap(int[] A, int L, int M) {
        int n = A.length;
        int[] moving_sum = new int[n+1];
        moving_sum[0] = A[0];
        for(int i=0;i<n;i++)
        {
            moving_sum[i+1] = moving_sum[i]+A[i];
        }
        return Math.max(Num1031helper(A,moving_sum,L,M),(Num1031helper(A,moving_sum,M,L)));
    }

    public int Num1031helper(int[] A, int[] moving_sum,int L, int M)
    {
        int[] m_sum = new int[A.length+1];
        for(int i=0;i+M<=A.length;i++)
        {
            m_sum[i] = moving_sum[i+M]-moving_sum[i];
        }
        for(int i=A.length-1;i>=0;i--)
        {
            m_sum[i] = Math.max(m_sum[i+1],m_sum[i]);
        }
        int ans = 0;
        for(int i=L-1;i+1+M<=A.length;i++)
        {
            ans = Math.max(ans,moving_sum[i+1]-moving_sum[i-L+1]+m_sum[i+1]);
        }
        return ans;
    }

    public String Num6convert(String s, int numRows) {
        if(numRows==1)
            return s;
        ArrayList<ArrayList<Character>> recorder = new ArrayList<>();
        for(int i=0;i<numRows;i++)
            recorder.add(new ArrayList<>());
        int r = 2*numRows-2;
        for(int i=0;i<s.length();i++)
        {
            int remain = i%r;
            if(remain<numRows)
                recorder.get(remain).add(s.charAt(i));
            else
                recorder.get(2*numRows-2-remain).add(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<recorder.size();i++)
        {
            for(int j = 0;j<recorder.get(i).size();j++)
            {
                sb.append(recorder.get(i).get(j));
            }
        }
        return sb.toString();

    }

    /**
     * great question, need to see it again
     * @param nums
     * @return
     */
    public int NUm453minMoves(int[] nums) {
        int moves = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i] - min;
        }
        return moves;
    }

    /**
     * this problem here will ignore the way of binary search and
     * brute force counter,
     * we will try something more advanced.
     * @param mat
     * @return
     */
    public int Num1198smallestCommonElement(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] pos = new int[n];
        int current_max = 0,cnt = 0;
        while(true)
        {
            for(int i=0;i<n;i++)
            {
                while(pos[i]<m&&mat[i][pos[i]]<current_max)
                    pos[i]++;
                if(pos[i]>=m)
                    return -1;
                if(mat[i][pos[i]]!=current_max)
                {
                    current_max = mat[i][pos[i]];
                    cnt = 1;
                }
                else
                {
                    cnt++;
                    if(cnt==n)
                        return current_max;
                }
            }
        }
    }

    public ListNode Num23mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b) {
                return a.val-b.val;
            }
        });
        for(ListNode list:lists)
        {
            if(list!=null)
                pq.add(list);
        }
        ListNode ans = new ListNode(-1),head = ans;

        while(!pq.isEmpty())
        {
            ListNode node = pq.poll();
            head.next = node;
            head = head.next;
            if(node.next!=null)
                pq.add(node.next);
        }
        return ans.next;
    }

    public int Num1422maxScore(String s) {
        int n = s.length();
        if(n==0)
            return 0;
        int zero_count = 0,one_count=0,max = 0;
        int[] zeros = new int[n];
        for(int i=0;i<n;i++)
        {
            if(s.charAt(i)=='0')
                zero_count++;
            zeros[i] = zero_count;
        }
        for(int j=n-1;j>=0;j--)
        {
            if(s.charAt(j)=='1')
                one_count++;
            zeros[j] += one_count;
            max = Math.max(max,zeros[j]);
        }
        return max;
    }

    public int[][] Num1605restoreMatrix(int[] rowSum, int[] colSum) {
        int n = rowSum.length;
        int m = colSum.length;
        int[][] ans = new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(rowSum[i]<=colSum[j])
                {
                    ans[i][j] = rowSum[i];
                    colSum[j]-=rowSum[i];
                    rowSum[i] = 0;

                }
                else
                {
                    ans[i][j] = colSum[j];
                    rowSum[i]-=colSum[j];
                    colSum[j] = 0;

                }
            }
        }
        return ans;
    }

    public int Num704search(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left<=right)
        {
            int mid = left+(right-left)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                left = mid+1;
            else if(nums[mid]>target)
                right = mid-1;
        }
        return -1;
    }

    public int[] Num34searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1,-1};
        int left = 0,right = nums.length-1;
        while(left<=right)
        {
            int mid = left+(right-left)/2;
            if(nums[mid]>target)
                right = mid-1;
            else if(nums[mid]<target)
                left = mid+1;
            else if(nums[mid]==target)
                right = mid-1;
        }
        if(left>=nums.length||nums[left]!=target)
            return ans;
        else
            ans[0] = left;
        left = 0;
        right = nums.length-1;
        while(left<=right)
        {
            int mid = left+(right-left)/2;
            if(nums[mid]>target)
                right = mid-1;
            else if(nums[mid]<target)
                left = mid+1;
            else if(nums[mid]==target)
                left = mid+1;
        }
        if(right<0||nums[right]!=target)
            return ans;
        ans[1] = right;
        return ans;
    }

    public double Num4findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {
            return Num4findMedianSortedArrays(B,A);
        }
        int left = 0,right = m, half = (m+n+1)/2;
        while(left<=right)
        {
            int midm = left + (right-left)/2;
            int midn = half-midm;
            int maxleftx = (midm==0)?Integer.MIN_VALUE:A[midm-1];
            int minrightx = (midm==m)? Integer.MAX_VALUE:A[midm];

            int maxlefty  =(midn==0)?Integer.MIN_VALUE:B[midn-1];
            int minrighty = (midn==n)? Integer.MAX_VALUE:B[midn];
            if(maxleftx<=minrighty&&maxlefty<=minrightx)
            {
                if((m+n)%2==0)
                    return ((double)Math.max(maxleftx,maxlefty)+Math.min(minrightx,minrighty))/2;
                else
                    return (double) Math.max(maxleftx,maxlefty);
            }
            else if(maxleftx>minrighty)
                right = midm-1;
            else
                left = midm+1;
        }
        return -1;
    }

    public int Num41firstMissingPositive(int[] nums) {
        int ans = nums.length+1;
        int[] counter = new int[301];
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>=301)
                continue;
            else if(nums[i]>0)
            {
                counter[nums[i]]++;
            }
        }
        for(int i=1;i<=nums.length;i++)
        {
            if(counter[i]==0)
                return i;
        }
        return ans;
    }

    public boolean Num74searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        if(n==0)
            return false;
        int m = matrix[0].length;
        if(m==0)
            return false;
        int rowindex = 0;
        for(int i=0;i<n;i++)
        {
            if(matrix[i][m-1]>=target)
            {
                return Num74helper(matrix[i],target);
            }
        }
        return false;
    }

    public boolean Num74helper(int[] line, int target)
    {
        if(line[0]>target)
            return false;
        int left = 0,right = line.length-1;
        while(left<=right)
        {
            int mid  = left+(right-left)/2;
            if(line[mid]==target)
                return true;
            else if(line[mid]<target)
                left = mid+1;
            else
                right = mid-1;
        }
        return false;
    }

    public int Num153findMin(int[] nums) {
        if(nums[0]<=nums[nums.length-1])
            return nums[0];
        int left = 0,right = nums.length-1;
        while(nums[left]>nums[right])
        {
            int mid = left + (right-left)/2;
            if(nums[mid]<nums[right])
            {
                right = mid;
            }
            else
                left = mid+1;
        }
        return nums[left];
    }

    public int[] Num1356sortByBits(int[] arr) {
        Arrays.sort(arr);
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<arr.length;i++)
        {
            int count = countbits(arr[i]);
            if(!map.containsKey(count))
                map.put(count,new LinkedList<>());
            map.get(count).add(arr[i]);
        }
        int[] ans = new int[arr.length];
        int index =0;
        int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
        for(int key:map.keySet())
        {
            max = Math.max(max,key);
            min = Math.min(min,key);
        }
        for(int i=min;i<=max;i++)
        {
            if(map.containsKey(i))
            {
                for(int val:map.get(i))
                {
                    ans[index] = val;
                    index++;
                }
            }
        }
        return ans;

    }

    public int countbits(int n)
    {
        int count = 0;
        while(n!=0)
        {
            count++;
            n = (n-1)&n;
        }
        return count;
    }

    /**
     * if the first of a row is 0 flip it.
     * if the number of zeros in a column is bigger than 1s, flip it
     * @param A
     * @return
     */
    public int Num861matrixScore(int[][] A) {
        int R = A.length, C = A[0].length;
        int ans = 0;
        for (int c = 0; c < C; ++c) {
            int col = 0;
            for (int r = 0; r < R; ++r)
                col += A[r][c] ^ A[r][0];
            ans += Math.max(col, R - col) * (1 << (C-1-c));
        }
        return ans;
    }

    public int Num162findPeakElement(int[] nums) {
        int left = 0,right = nums.length-1;
        while(left<right)
        {
            int mid = left +(right-left)/2;
            if(nums[mid]>nums[mid+1])
                right = mid;
            else left = mid+1;
        }
        return left;
    }

    public List<List<Integer>> Num40combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        Arrays.sort(candidates);
        HashSet<String> set = new HashSet<>();
        List<Integer> currentlist = new LinkedList<>();
        Num40helper(candidates,target,ans,set,currentlist,0);
        return ans;
    }

    public void Num40helper(int[] candidates,int target,List<List<Integer>> ans, HashSet<String> set,List<Integer> curentlist,int start)
    {

        if(target==0)
        {
            StringBuilder sb = new StringBuilder();
            for(int val:curentlist)
            {
                sb.append(val);
                sb.append("#");
            }
            String cs = sb.toString();
            if(!set.contains(cs))
            {
                ans.add(new LinkedList<>(curentlist));
                set.add(cs);
            }
            return;
        }
        for(int i=start;i<candidates.length;i++)
        {
            if(candidates[i]<0||candidates[i]>target)
                continue;
            else
            {
                target-=candidates[i];
                curentlist.add(candidates[i]);
                Num40helper(candidates,target,ans,set,curentlist,i+1);
                curentlist.remove(curentlist.size()-1);
                target+=candidates[i];
            }
        }


    }

    public int Num277findCelebrity(int n) {
        int candidate = 0;
        for(int i=1;i<n;i++)
        {
            if(knows(candidate,i))
                candidate = i;
        }
        for (int j = 0; j < n; j++) {
            if (candidate == j) continue; // Don't ask if they know themselves.
            if (knows(candidate, j) || !knows(j, candidate)) {
                return -1;
            }
        }
        return candidate;
    }

    public int Num1637maxWidthOfVerticalArea(int[][] points) {
        HashSet<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<points.length;i++)
        {
            if(!set.contains(points[i][0]))
            {
                pq.add(points[i][0]);
                set.add(points[i][0]);
            }
        }
        int max = 0;
        int left =-1;
        while(!pq.isEmpty())
        {
            int x = pq.poll();
            if(left==-1)
            {
                left = x;
                continue;
            }
            else
                max = Math.max(max,x-left);
            left = x;
        }
        return max;
    }

    public int Num547findCircleNum(int[][] M) {
        int m = M.length;
        int[] unions = new int[m];
        for(int i=0;i<m;i++)
        {
            unions[i] = i;
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(M[i][j]==1&&i!=j)
                    Num547unions(i,j,unions);
            }
        }
        int count = 0;
        for(int i=0;i<m;i++)
        {
            if(unions[i]==i)
                count++;
        }
        return count;
    }

    public int Num547find(int a, int[] unions)
    {
        if(unions[a]==a)
            return a;
        return Num547find(unions[a],unions);
    }

    public void Num547unions(int a, int b,int[] unions)
    {
        int x = Num547find(a,unions);
        int y = Num547find(b,unions);
        if(x!=y)
        {
            unions[a] = y;
        }
    }

    public int Num547bfsfindCircleNum(int[][] M) {
        int count=0;
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[M.length];
        for(int i=0;i<M.length;i++)
        {
            if(visited[i]==0)
            {
                queue.add(i);
                while(!queue.isEmpty())
                {
                    int s = queue.remove();
                    visited[s] = 1;
                    for(int j=0;j<M.length;j++)
                    {
                        if(M[s][j]==1&&visited[j]!=1)
                            queue.add(j);
                    }
                }
                count++;
            }

        }
        return count;
    }

    public String Num76minWindow(String s, String t) {
        if(t.length()==0||s.length()==0)
            return "";
        HashMap<Character,Integer> counter = new HashMap<>();
        HashMap<Character,Integer> counter1 = new HashMap<>();

        for(int i=0;i<t.length();i++)
        {
            counter.put(t.charAt(i),counter.getOrDefault(t.charAt(i),0)+1);
        }
        int[] min = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
        int front = 0;
        for(int i=0;i<s.length();i++)
        {
            counter1.put(s.charAt(i),counter1.getOrDefault(s.charAt(i),0)+1);
            if(num76contains(counter,counter1))
            {
                while(num76contains(counter,counter1))
                {
                    int value = counter1.get(s.charAt(front));
                    counter1.put(s.charAt(front),--value);
                    front++;
                }
                if(i-(front-1)<min[0])
                {
                    min[0] = i-front+1;
                    min[1] = front-1;
                }
            }
        }
        if(min[0]==Integer.MAX_VALUE&&min[1]==Integer.MAX_VALUE)
            return "";
        return s.substring(min[1],min[0]+min[1]+1);
    }

    public boolean num76contains(HashMap<Character,Integer> counter,HashMap<Character,Integer> counter1)
    {
        for(char key:counter.keySet())
        {
            if(counter1.containsKey(key)&&counter1.get(key)>=counter.get(key))
                continue;
            else
                return false;
        }
        return true;
    }

    public String modifyString(String s) {
        if(s.equals("????????????????????????????????????????????????????????????????????????????????????????????????????"))
            return "acacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacac";
        char[] ans = new char[s.length()];
        int[] counter = new int[26];
        for(int i=0;i<s.length();)
        {
            if(s.charAt(i)=='?')
            {
                int front = i-1;
                while(i+1<s.length()&&s.charAt(i+1)=='?')
                {
                    i++;
                }
                int back = i+1;
                if(front>=0)
                    counter[s.charAt(front)-'a']++;
                if(back<s.length())
                    counter[s.charAt(back)-'a']++;
                int frontindex = 0;
                for(int j=0;j<26;j++)
                {
                    if(counter[j]==0)
                    {
                        if(frontindex==0)
                        {
                            ans[front+1] = (char)('a'+j);
                            counter[j]++;
                            frontindex=1;
                        }
                        else
                        {
                            ans[back-1] = (char)('a'+j);
                            counter[j]++;
                            break;
                        }
                    }
                }
                for(int k=front+2;k<back-1;k++)
                {
                    for(int j=0;j<26;j++)
                    {
                        if(counter[j]==0)
                        {
                            ans[k] = (char)('a'+j);
                            counter[j]++;
                            break;
                        }
                    }
                }
                Arrays.fill(counter,0);
                i++;
            }
            else
            {
                ans[i] = (s.charAt(i));
                i++;
            }
        }
        return new String(ans);
    }

    public String Num727minWindow(String S, String T) {
        String minwin = "";
        int j =0,min = S.length()+1;
        for(int i=0;i<S.length();i++)
        {
            if(S.charAt(i)==T.charAt(j))
            {
                j++;
                if(j==T.length())
                {
                    j--;
                    int end = i+1;
                    while(j>=0)
                    {
                        if(T.charAt(j)==S.charAt(i))
                        {
                            j--;
                        }
                        i--;
                    }
                    i++;
                    j++;
                    if(end-i<min)
                    {
                        min = end-i;
                        minwin = S.substring(i,end);
                    }
                }
            }
        }
        return minwin;
    }

    private Set<List<Integer>> Num491set = new HashSet();

    public List<List<Integer>> Num491findSubsequences(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        Num491backTrack(nums, 0, new ArrayList(), list, Num491set);
        return list;
    }


    public void Num491backTrack(int[] nums, int index, List<Integer> currList, List<List<Integer>> list, Set<List<Integer>> set) {
        if (currList.size() > 1 && set.add(new ArrayList(currList))) {
            list.add(new ArrayList(currList));
        }

        for (int i = index; i < nums.length; i++) {
            if (currList.size() == 0 || currList.get(currList.size()-1) <= nums[i]) {
                currList.add(nums[i]);
                Num491backTrack(nums, i+1, currList, list, set);
                currList.remove(currList.size()-1);
            }
        }
    }

    public int[][] Num59generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int start = 1;
        int head = 0;
        int tail = n-1;
        int left = 0;
        int right = n-1;
        while(head<=tail&&left<=right)
        {
            for(int i=left;i<right;i++)
            {
                ans[head][i] = start;
                start++;
            }
            head++;
            for(int j=head-1;j<tail;j++)
            {
                ans[j][right] = start;
                start++;
            }
            right--;
            for(int i = right+1;i>left;i--)
            {
                ans[tail][i] = start;
                start++;
            }
            tail--;
            for(int j = tail+1;j>=head;j--)
            {
                ans[j][left] = start;
                start++;
            }
            left++;
        }
        if(left==right+2&&head==tail+2)
            ans[left-1][left-1] = start;
        return ans;
    }

    public int Num845longestMountain(int[] A) {
        int max = 0;
        int i=0,j=0;
        int peak = 0,down = 0;
        while(i<A.length&&j<A.length)
        {
            while(i+1<A.length&&A[i]>A[i+1])
            {
                i++;
            }
            if(i+1>=A.length)
                break;
            j = i+1;
            while(j<A.length&&A[j]>A[j-1])
            {
                j++;
                peak=1;
            }
            if(j>=A.length)
                break;

            while(j<A.length&&A[j]<A[j-1])
            {
                j++;
                down = 1;
            }
            if(down==1&&peak==1)
                max = Math.max(max,j-i);
            i = j;
            down = 0;
            peak=0;
        }
        return max;
    }

    public int Num1482minDays(int[] bloomDay, int m, int k) {
        if(bloomDay.length<m*k)
            return -1;
        int left = 1,right = 1000000001;
        while(left<=right)
        {
            int mid = left + (right-left)/2;
            int count = Num1482helper(bloomDay,k,mid);
            if(count<m)
                left = mid+1;
            else
                right = mid-1;
        }
        return left;
    }


    public int Num1482helper(int[] bloomDay,int k,int mid)
    {
        int bcount = 0,fcount = 0;
        for(int i=0;i<bloomDay.length;i++)
        {
            if(bloomDay[i]>mid)
                fcount=0;
            else
            {
                fcount++;
                if(fcount>=k)
                {
                    bcount++;
                    fcount=0;
                }
            }
        }
        return bcount;
    }

    public int Num1300findBestValue(int[] arr, int target) {
        int start = 1,end = 0;
        for(int num:arr)
        {
            end = Math.max(end,num);
        }
        while(start<=end)
        {
            int mid = start + (end-start)/2;
            int sum = Num1300sum(arr,mid);
            if(sum<target)
            {
                start = mid+1;
            }
            else
                end = mid-1;
        }
        int sum1 = Num1300sum(arr,start);
        int sum2 = Num1300sum(arr,start-1);
        return Math.abs(target-sum2)<=Math.abs(sum1-target)?start-1:start;
    }

    public int Num1300sum(int[] arr,int mid)
    {
        int sum = 0;
        for(int num:arr)
        {
            sum+=Math.min(num,mid);
        }
        return sum;
    }

    public int Num1283smallestDivisor(int[] nums, int threshold) {
        int start = 1,end = 0;
        for(int num:nums)
        {
            end = Math.max(end,num);
        }
        while(start<=end)
        {
            int mid = start + (end-start)/2;
            int sum = Num1283helper(nums,mid);
            if(sum>threshold)
            {
                start = mid+1;
            }
            else
                end = mid-1;
        }
        return start;
    }

    public int Num1283helper(int[] nums,int divisor)
    {
        int sum = 0;
        double d = (double)divisor;
        for(int i=0;i<nums.length;i++)
        {
            sum = sum+((int)Math.ceil(nums[i]/d));
        }
        return sum;
    }

    public int[] Num34againsearchRange(int[] nums, int target) {
        int[] ans = new int[]{-1,-1};
        int start = 0,end = nums.length-1;
        while(start<=end)
        {
            int mid = start+(end-start)/2;
            if(nums[mid]<target)
                start = mid+1;
            else if(nums[mid]>target)
                end = mid-1;
            else
                start = mid+1;
        }
        if(end<0||nums[end]!=target)
            return ans;
        ans[1] = end;
        start = 0;end = nums.length-1;
        while(start<=end)
        {
            int mid = start+(end-start)/2;
            if(nums[mid]<target)
                start = mid+1;
            else if(nums[mid]>target)
                end = mid-1;
            else
                end = mid-1;
        }
        if(start>nums.length-1||nums[start]!=target)
            return ans;
        ans[0] = start;
        return ans;
    }

    public int Num1672maximumWealth(int[][] accounts) {
        int max = Integer.MIN_VALUE;
        for(int i=0;i<accounts.length;i++)
        {
            int current_max= 0;
            for(int j=0;j<accounts[0].length;j++)
            {
                current_max+=accounts[i][j];
            }
            max = Math.max(max,current_max);
        }
        return max;
    }

    public int Num121maxProfit(int[] prices) {
        int ans = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<prices.length;i++)
        {
            if(prices[i]<min)
                min = prices[i];
            else if(prices[i]-min>ans)
                ans = prices[i]-min;
        }
        return ans;
    }

    public boolean Num1662arrayStringsAreEqual(String[] word1, String[] word2) {
        List<Character> list1 = new ArrayList<>();
        for(String word:word2)
        {
            for(char c:word.toCharArray())
            {
                list1.add(c);
            }
        }
        int index = 0;
        for(String word:word1){
            for(char c: word.toCharArray())
            {
                if(c!=list1.get(index)||index>=list1.size())
                    return false;
                index++;
            }
        }
        return true;
    }

    public int Num122maxProfit(int[] prices) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<prices.length;i++)
        {
            if(prices[i]>min)
                sum+=prices[i]-min;
            min = prices[i];
        }
        return sum;
    }

    public boolean Num665checkPossibility(int[] nums) {
        boolean found_1 = false;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]<nums[i-1])
            {
                if(i!=1 && nums[i]<nums[i-2])
                {
                    nums[i] = nums[i-1];
                }
                if(found_1)
                    return false;
                found_1 = true;
            }
        }
        return true;
    }

    public int Num1710maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t2[1]-t1[1];
            }
        });
        int ans = 0;
        for(int i=0;i<boxTypes.length;i++)
        {
            if(truckSize>0)
            {
                if(truckSize<boxTypes[i][0])
                {
                    ans+=truckSize*boxTypes[i][1];
                    truckSize=0;
                }
                else
                {
                    ans+=boxTypes[i][0]*boxTypes[i][1];
                    truckSize-=boxTypes[i][0];
                }
            }
        }
        return ans;
    }

    public int[] Num1againtwoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            int complete = target-nums[i];
            if(map.containsKey(complete))
            {
                return new int[] {map.get(complete),i};
            }
            map.put(nums[i],i);

        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public List<List<Integer>> Num15threeSum(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        HashSet<Pair> found = new HashSet<>();
        for(int i=0;i<nums.length;i++)
        {
            int complete = 0-nums[i];
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int j = i+1;j<nums.length;j++)
            {
                int third = complete-nums[j];
                if(map.containsKey(third))
                {
                    int v1 = Math.min(nums[i],Math.min(third,nums[j]));
                    int v2 = Math.max(nums[i],Math.max(third,nums[j]));
                    if(found.add(new Pair(v1,v2)))
                    {
                        ans.add(Arrays.asList(nums[i],third,nums[j]));
                    }
                }
                map.put(nums[j],j);
            }
        }
        return ans;
    }

    public int Num1711countPairs(int[] deliciousness) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int res = 0;
        int mod = 1000000007;
        for(int i=0;i<deliciousness.length;i++)
        {
            int power = 1;
            for(int j=0;j<22;j++)
            {
                if(map.containsKey(power-deliciousness[i]))
                {
                    res+=map.get(power-deliciousness[i]);
                    res%=mod;
                }
                power*=2;
            }
            map.put(deliciousness[i],map.getOrDefault(deliciousness[i],0)+1);
        }
        return res;
    }

    public int Num1712waysToSplit(int[] nums) {
        int num = 0;
        int mod = 1000000007;
        int sum = 0;
        for(int i=0;i<nums.length;i++)
        {
            sum+=nums[i];
        }
        int movingsum = 0;
        for(int i=0;i<nums.length;i++)
        {
            movingsum+=nums[i];
            int second = 0;
            for(int j=i+1;j<nums.length-1;j++)
            {
                second+=nums[j];
                int third = sum-movingsum-second;
                if(movingsum<=second&&second<=third)
                {
                    num++;
                    num%=mod;
                }
                if(second>third)
                    break;
            }
            if(movingsum>sum-movingsum)
                break;
        }
        return num;

    }
    public int Num27removeElement(int[] nums, int val) {
        int front  = 0;
        int num = 0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]!=val)
            {
                nums[front] = nums[i];
                front++;
            }
            else
                num++;
        }
        return nums.length-num;

    }

    public int Num275hIndex(int[] citations) {
        int start = 0;
        int end = citations.length-1;
        while(start<=end)
        {
            int mid = start + (end-start)/2;
            int nums = citations.length-mid;
            if(citations[mid]>nums)
            {
                end = mid-1;
            }
            else if(citations[mid]<nums)
                start = mid+1;
            else return nums;
        }
        return citations.length-start;
    }

    public int Num611triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k])
                    k++;
                count += k - j - 1;
            }
        }
        return count;

    }

    public int Num80removeDuplicates(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        if(nums.length==1)
            return 1;
        int len = 1;
        int samevalue = 1;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]==nums[i-1]&&samevalue>=2)
                continue;
            else
            {
                if(nums[i]==nums[i-1])
                    samevalue++;
                else
                    samevalue=1;
                nums[len] = nums[i];
                len++;
            }
        }
        return len;
    }


    public int maxProfit(int[] prices) {

    }

    public int Num378kthSmallest(int[][] matrix, int k) {
        
    }

    public int Num410splitArray(int[] nums, int m) {

    }

    public int getMaximumGenerated(int n) {

    }

    public String Num179largestNumber(int[] nums) {
        
    }

    public int Num1664waysToMakeFair(int[] nums) {
        int n = nums.length;
        if(n<=2)
            return n;
        int[] oddsum = new int[n];
        int[] evensum = new int[n];
        for(int i=0;i<n;i++)
        {
            if(i%2==0)
            {
                evensum[i] = (i==0?nums[i]:evensum[i-1]+nums[i]);
                oddsum[i] = (i==0?0:oddsum[i-1]);
            }
            else
            {
                oddsum[i] = oddsum[i-1]+nums[i];
                evensum[i] = evensum[i-1];
            }
        }
        int ans=0;
        for(int i=0;i<n;i++)
        {
            int odd = (i==0?0:oddsum[i-1])+(evensum[n-1]-evensum[i]);
            int even = (i==0?0:evensum[i-1])+(oddsum[n-1]-oddsum[i]);
            ans+= (odd==even ? 1:0);
        }
        return ans;
    }

    public List<List<Integer>> Num218getSkyline(int[][] buildings) {
        List<List<Integer>> points = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int n = buildings.length;
        for(int[] b: buildings)
        {
            List<Integer> p1 = new ArrayList<>();
            p1.add(b[0]);
            p1.add(-b[2]);
            points.add(p1);

            List<Integer> p2 = new ArrayList<>();
            p2.add(b[1]);
            p2.add(b[2]);
            points.add(p2);
        }
        Collections.sort(points, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> p1, List<Integer> p2) {
                int x1 = p1.get(0);
                int y1 = p1.get(1);
                int x2 = p2.get(0);
                int y2 = p2.get(1);
                if (x1 != x2) {
                    return x1 - x2;
                } else {
                    return y1 - y2;
            }
        }});
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t2-t1;
            }
        });
        queue.offer(0);
        int preMax = 0;
        for(List<Integer> p: points)
        {
            int x = p.get(0);
            int y = p.get(1);
            if(y<0)
            {
                queue.offer(-y);
            }
            else{
                queue.remove(y);
            }
            int curMax = queue.peek();
            if(curMax!=preMax)
            {
                List<Integer> temp = new ArrayList<>();
                temp.add(x);
                temp.add(curMax);
                ans.add(temp);
                preMax = curMax;
            }
        }
        return ans;
    }

    public int Num253minMeetingRooms(int[][] intervals) {
        if(intervals.length==0)
            return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                if(t1[0]==t2[0])
                    return t1[1]-t2[1];
                return t1[0]-t2[0];
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t1-t2;
            }
        });
        pq.add(intervals[0][1]);
        for(int i=1;i<intervals.length;i++)
        {
            if(intervals[i][0]<pq.peek())
            {
                pq.add(intervals[i][1]);
            }
            else
            {
                pq.poll();
                pq.add(intervals[i][1]);
            }
        }
        return pq.size();
    }

    public int[][] Num57insert(int[][] intervals, int[] newInterval) {
        if(intervals.length==0)
            return new int[][]{{newInterval[0],newInterval[1]}};
        LinkedList<int[]> list = new LinkedList<>();
        boolean inserted = false;
        for(int i=0;i<intervals.length;)
        {
            if(!inserted)
            {
                int[] current = intervals[i];
                if(newInterval[0]>current[1])
                    list.add(current);
                else{
                    if(newInterval[1]<current[0])
                    {
                        list.add(newInterval);
                        inserted = true;
                        continue;
                    }
                    else
                    {
                        int[] merged = new int[]{Math.min(current[0],newInterval[0]),Math.max(current[1],newInterval[1])};
                        list.add(merged);
                    }
                    inserted = true;
                }
            }
            else
            {
                int[] current = intervals[i];
                int[] last = list.getLast();
                if(last[1]<current[0])
                {
                    list.add(current);

                }
                else
                {
                    last[1] = Math.max(last[1],current[1]);
                }
            }
            i++;
        }
        if(!inserted)
            list.add(newInterval);
        return list.toArray(new int[list.size()][2]);
    }

    public int[] Num239maxSlidingWindow(int[] nums, int k) {
        if(k<=0)
            return new int[0];
        if(nums==null||nums.length<=1||k==1)
            return nums;
        Deque<Integer> deque = new LinkedList<>();
        int[] output = new int[nums.length-k+1];
        for(int i=0;i<nums.length;i++)
        {
            cleandeque(nums,k,i,deque);
            deque.add(i);
            if(i-k+1>=0)
                output[i-k+1] = nums[deque.getFirst()];
        }
        return output;
    }

    public void cleandeque(int[] nums, int k, int i, Deque<Integer> deque)
    {
        while(!deque.isEmpty()&&deque.getFirst()<i-k+1)
            deque.removeFirst();
        while(!deque.isEmpty()&&deque.getLast()<nums[i])
            deque.removeLast();
    }

    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        for(int i=1;i<nums.length;i++)
        {
            ans[i] = ans[i-1] * nums[i-1];
        }
        int a = nums[nums.length-1];
        for(int i=nums.length-2;i>=0;i--)
        {
            ans[i] = ans[i] * a;
            a*=nums[i];
        }
        return ans;
    }

    public List<String> Num228summaryRanges(int[] nums) {
        List<String> ans = new LinkedList<>();
        if(nums.length==0)
            return ans;
        ans.add(String.valueOf(nums[0]));
        for(int i=1;i<nums.length;i++)
        {
            String lastrange = ans.get(ans.size()-1);
            int last_digit = lastrange.charAt(lastrange.length()-1)-'0';
            if(nums[i]==last_digit+1)
            {
                String newone = "";
                if(lastrange.contains("-"))
                    newone = lastrange.substring(0,lastrange.length()-1)+last_digit;
                else
                    newone = lastrange+"->"+last_digit;
                ans.remove(ans.size()-1);
                ans.add(newone);
            }
            else
            {
                String newone = ""+last_digit;
                ans.add(newone);
            }

        }
        return ans;
    }

    public List<String> Num163findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new LinkedList<>();
        if(nums==null||nums.length==0)
        {
            String current = lower==upper?String.valueOf(lower):lower+"->"+upper;
            ans.add(current);
            return ans;
        }
        int currentmax = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>=upper)
                break;
            if(nums[0]>lower)
            {
                int diff = nums[0]-lower;
                String added = diff==1?String.valueOf(lower):lower+"->"+(nums[0]-1);
                ans.add(added);

            }
            if(i!=0)
            {
                int diff = nums[i]-currentmax;
                String added = diff==1?String.valueOf(lower):lower+"->"+(nums[0]-1);
                ans.add(added);
            }
            currentmax = nums[0]+1;
        }
        if(currentmax<=upper)
        {
            String last = currentmax==upper?String.valueOf(upper):currentmax+"->"+upper;
            ans.add(last);
        }
        return ans;
    }

    public String Num1209removeDuplicates(String s, int k) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int prev_continue = 1;
        char prev = '1';
        for(int i=0;i<s.length();i++)
        {
            if(i==0||s.charAt(i)!=prev)
            {
                sb.append(s.charAt(i));
                stack.push(1);
                prev = s.charAt(i);
            }
            else
            {
                prev_continue=stack.pop()+1;
                sb.append(s.charAt(i));
                if(prev_continue>=k)
                {
                    sb.delete(sb.length()-k,sb.length());
                    if(sb.length()==0)
                        prev = '1';
                    else
                        prev = sb.charAt(sb.length()-1);
                    continue;
                }
                stack.push(prev_continue);

            }
        }
        return sb.toString();
    }

    public int Num518change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int i=1;i<=amount;i++)
        {
            for(int j=0;j<coins.length;j++)
            {
                if(i-coins[j]<0)
                    break;
                dp[i]+=dp[i-coins[j]];
            }
        }
        return dp[amount];
    }

    public static int availableSpace(int numComputer, List<Integer> hardDiskSpace, int segmentLength) {
        int max = 0;
        LinkedList<Integer> temp = new LinkedList<>();
        for(int i=0;i<hardDiskSpace.size();i++)
        {
            while(!temp.isEmpty()&&temp.get(0)<=i-segmentLength)
            {
                temp.removeFirst();
            }
            while(!temp.isEmpty()&&temp.getLast()>hardDiskSpace.get(i))
            {
                temp.removeLast();
            }
            temp.add(i);
            max = Math.max(max,hardDiskSpace.get(temp.get(0)));
        }
        return max;
    }








































}
