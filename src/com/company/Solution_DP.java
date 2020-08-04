package com.company;
import java.util.*;

/**
 * I create a new class to do the dp problems.
 */

public class Solution_DP {

    public int Num62uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++)
        {
             dp[i][0] = 1;
        }
        for(int j = 0;j<n;j++)
        {
            dp[0][j] = 1;
        }

        for(int i=1;i<m;i++)
        {
            for(int j=1;j<n;j++)
            {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * Spaced optimized solution
     * @param
     * @return
     */

    public int Num62uniquePathsSpaceOptimized(int m, int n) {
        int[] dp = new int[n];
        for(int i=0;i<n;i++)
        {
            dp[i] = 1;
        }
        for(int j=1;j<m;j++)
        {
            dp[0] = 1;
            for(int i=1;i<n;i++)
            {
                dp[i]+=dp[i-1];
            }
        }
        return dp[n-1];
    }

    public int Num63uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0]==1)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        boolean osbtacle = false;
        for(int i=0;i<n;i++)
        {
            if(obstacleGrid[0][i]==1)
            {
                osbtacle = true;
            }
            if(osbtacle)
                dp[i] = 0;
            else dp[i] = 1;
        }
        osbtacle = false;
        for(int j=1;j<m;j++)
        {
            if(obstacleGrid[j][0]==1)
            {
                osbtacle = true;
                dp[0] = 0;
            }
            else
            {
                dp[0] = osbtacle?0:1;
            }
            for(int i=1;i<n;i++)
            {
                dp[i] = obstacleGrid[j][i]==1?0:dp[i]+dp[i-1];
            }
        }
        return dp[n-1];
    }

    /**
     * needs to be done again
     *
     * actually should pay attention to 2D-prefix
     * @param mat
     * @param K
     * @return
     */
    public int[][] Num1314matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        int[][] prefix = new int[m+1][n+1];
        /**
         * construction of 2D-prefix
         */
        for(int i=1;i<m+1;i++)
        {
            for(int j=1;j<n+1;j++)
            {
                prefix[i][j] = prefix[i-1][j]+prefix[i][j-1]+mat[i-1][j-1]-prefix[i-1][j-1];
            }
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                int r1 = Math.max(0,i-K);
                int c1 = Math.max(0,j-K);
                int r2 = Math.min(m,i+K+1);
                int c2 = Math.min(n,j+K+1);
                ans[i][j] = prefix[r2][c2]-prefix[r1][c2]-prefix[r2][c1]+prefix[r1][c1];
            }
        }
        return ans;
    }

    public int Num322coinChange(int[] coins, int amount) {
        if(amount<coins[0])
            return -1;
        Arrays.sort(coins);
        int[] dp = new int[amount+1];
        for(int i=0;i<coins.length;i++)
        {
            if(coins[i]<=amount)
                dp[coins[i]] = 1;
        }
        for(int i=coins[0]+1;i<=amount;i++)
        {
            if(dp[i]==1)
                continue;
            boolean none = false;
            dp[i] = Integer.MAX_VALUE;
            for(int j=0;j<coins.length;j++)
            {
                int index = i-coins[j];
                if(index<0)
                    break;
                if(dp[index]!=0)
                {
                    dp[i] = Math.min(dp[i],dp[index]+1);
                    none = true;
                }
            }
            if(!none)
                dp[i] = 0;
        }
        return dp[amount]==0?-1:dp[amount];
    }

    public boolean Num139wordBreak(String s, List<String> wordDict) {
        Set<String> worddict = new HashSet<>(wordDict);
        int length = s.length();
        boolean[] dp = new boolean[length+1];
        dp[0] = true;
        for(int i=1;i<=s.length();i++)
        {
            for(int j=0;j<i;j++)
            {
                if(dp[j]&&wordDict.contains(s.substring(j,i)))
                {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * needs to be done again
     * @param n
     * @return
     */
    public int Num375getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int len = 2;len<=n;len++)
        {
            for(int start = 1;start<=n-len+1;start++)
            {
                int minres = Integer.MAX_VALUE;
                for(int piv = start;piv<start+len-1;piv++)
                {
                    int res = piv+Math.max(dp[start][piv-1],dp[piv+1][start+len-1]);
                    minres = Math.min(res,minres);
                }
                dp[start][start+len-1] = minres;
            }
        }
        return dp[1][n];
    }

    public int Num279numSquares(int n) {
        int[] dp = new int[n+1];
        int i=1;
        for(;i*i<=n;i++)
        {
            dp[i] = 1;
        }
        i-=1;
        for(int j=1;j<=n;j++)
        {
            if(dp[j]!=0)
                continue;
            else dp[j] = Integer.MAX_VALUE;
            for(int p=1;p<=i;p++)
            {
                if(j-p*p>0)
                {
                    dp[j] = Math.min(dp[j],dp[j-p*p]+1);
                }
            }
        }
        return dp[n];

    }

    /**
     * needs to be done again. Very important space optimization skills.
     * @param triangle
     * @return
     */
    public int Num120minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0)
            return 0;
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        int prev = 0,cur;
        for(int i=1;i<triangle.size();i++)
        {
            List<Integer> row = triangle.get(i);
            for(int j=0;j<=i;j++)
            {
                cur = dp[j];
                if(j==0)
                    dp[j] = cur+row.get(j);
                else if(j==i)
                    dp[j] = prev+row.get(j);
                else
                    dp[j] = Math.min(cur,prev)+row.get(j);
                prev = cur;
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i=0;i<triangle.size();i++)
        {
            res = Math.min(res,dp[i]);
        }
        return res;
    }

    public int Num256minCost(int[][] costs) {
        if(costs.length==0)
            return 0;
        int[] dp = new int[3];
        int prev1,prev2;
        dp[0] = costs[0][0];
        dp[1] = costs[0][1];
        dp[2] = costs[0][2];
        for(int i=1;i<costs.length;i++)
        {
            prev1 = dp[0];
            dp[0] = Math.min(dp[1],dp[2])+costs[i][0];
            prev2 = dp[1];
            dp[1] = Math.min(prev1,dp[2])+costs[i][1];
            dp[2] = Math.min(prev1,prev2)+costs[i][2];
        }
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<3;i++)
        {
            ans = Math.min(ans,dp[i]);
        }
        return ans;
    }

    public int Num64minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(m==0)
            return 0;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for(int i=1;i<n;i++)
        {
            dp[i] = grid[0][i]+dp[i-1];
        }
        for(int i=1;i<m;i++)
        {
            dp[0] = dp[0]+grid[i][0];
            for(int j=1;j<n;j++)
            {
                dp[j] = Math.min(dp[j],dp[j-1])+grid[i][j];
            }
        }
        return dp[n-1];
    }

    /**
     * needs to be done again.
     * Very tricky problem, trying to focus on how to derive from previous numbers
     * @param num
     * @return
     */
    public int[] Num338countBits(int num) {
        int[] dp = new int[num+1];
        int i=0,b = 1;
        while(b<=num)
        {
            while(i<b&&i+b<=num)
            {
                dp[i+b] = dp[i]+1;
                i++;
            }
            i = 0;
            b<<=1;
        }
        return dp;
    }

    public int[] Num338COuntBitsSolution2(int num)
    {
        int[] dp = new int[num+1];
        for(int i=1;i<=num;i++)
        {
            dp[i] = dp[i>>1]+i%2;
        }
        return dp;
    }

    public int Num931minFallingPathSum(int[][] A) {
        int N = A.length;
        for (int r = N-2; r >= 0; --r) {
            for (int c = 0; c < N; ++c) {
                int best = A[r+1][c];
                if (c > 0)
                    best = Math.min(best, A[r+1][c-1]);
                if (c+1 < N)
                    best = Math.min(best, A[r+1][c+1]);
                A[r][c] += best;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int x: A[0])
            ans = Math.min(ans, x);
        return ans;
    }

    public boolean Num877stoneGame(int[] piles) {
        int N = piles.length;
        int[][] dp = new int[N][N];
        for(int i=0;i<N;i++)
        {
            dp[i][i] = piles[i];
        }
        for(int i=1;i<N;i++)
        {
            for(int j=0;j+i<N;j++)
            {
                dp[j][j+i] = Math.max(piles[j]-dp[j+1][j+i],piles[j+i]-dp[j][j+i-1]);
            }
        }
        return dp[0][N-1]>0;
    }

    /**
     * needs to be done again
     * @param piles
     * @return
     */
    public int Num1140stoneGameII(int[] piles) {
        int n = piles.length;
        int sum = 0;
        int[][] dp = new int[n][n+1];
        for(int i=n-1;i>=0;i--)
        {
            sum+=piles[i];
            for(int M = 1;M<=n;M++)
            {
                if(i+2*M>=n)
                    dp[i][M] = sum;
                else
                {
                    for(int x = 1;x<=2*M;x++)
                    {
                        dp[i][M] = Math.max(dp[i][M],sum-dp[i+x][Math.max(M,x)]);
                    }
                }
            }
        }
        return dp[0][1];
    }

    public boolean Num292canWinNim(int n) {
        boolean[] dp = new boolean[n];
        if(n<=3)
            return true;
        dp[n-1]=true;
        dp[n-2] = true;
        dp[n-3] = true;
        for(int i = n-4;i>=0;i--)
        {
            dp[i] = !dp[i-1]||!dp[i-2]||dp[i-3];
        }
        return dp[0];
    }

    /**
     * pretty the same with Num292. All using || instead of &&.
     * @param N
     * @return
     */
    public boolean Num1025divisorGame(int N) {
        return N%2==0;
    }

    /**
     * similar to stone game I
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[nums.length][nums.length];
        for(int i=0;i<nums.length;i++)
        {
            dp[i][i] = nums[i];
        }

        for(int i=1;i<=n-1;i++)
        {
            for(int j=0;j+i<=n-1;j++)
            {
                dp[j][j+i] = Math.max(nums[j]-dp[j+1][j+i],nums[j+i]-dp[j][j+i-1]);
            }
        }
        return dp[0][n-1]>=0;
    }

    public int Num983mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        int i=0;
        for(int day = 0;day<=365;day++)
        {
            if(i>=days.length)
                break;
            if(day==days[i])
            {
                int oneday = day-1<0?0:dp[day-1];
                int sevenday = day-7<0?0:dp[day-7];
                int thirtyday = day-30<0?0:dp[day-30];
                dp[day] = Math.min(oneday+costs[0],Math.min(sevenday+costs[1],thirtyday+costs[2]));
                i++;
            }
            else
            {
                if(day==0)
                    dp[0] = 0;
                else dp[day]=dp[day-1];

            }
        }
        int lastday = days[days.length-1];
        return dp[lastday];
    }

    public int Num1504numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        int sum = 0;
        int now = 0;
        for(int i=0;i<m;i++)
        {
            now = 0;
            for(int j=0;j<n;j++)
            {
                if(mat[i][j]==1)
                    now++;
                else
                    now=0;
                dp[i][j] = now;
            }
        }
        int ans = 0,minx = Integer.MAX_VALUE;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                minx = Integer.MAX_VALUE;
                for(int k=i;k>=0;k--)
                {
                    minx = Math.min(minx,dp[k][j]);
                    ans+=minx;
                }
            }
        }
        return sum;
    }

    public int Num1143longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(),len2 = text2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i=0;i<len1+1;i++)
        {
            dp[i][0] = 0;
        }
        for(int j=0;j<len2+1;j++)
        {
            dp[0][j] = 0;
        }
        for(int i=1;i<len1+1;i++)
        {
            for(int j=1;j<len2+1;j++)
            {
                if(text1.charAt(i)==text2.charAt(j))
                {
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                dp[i][j] = Math.max(dp[i-1][j],Math.max(dp[i][j-1],dp[i][j]));
            }
        }
        return dp[len1][len2];
    }

    public int Num516longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];

        for(int i=0;i<n+1;i++)
        {
            dp[i][0] = 0;
            dp[0][i] = 0;
            dp[i][i] = 1;
        }
        dp[0][0] = 0;
        for(int i=1;i<n;i++)
        {
            if(s.charAt(i-1)==s.charAt(i))
                dp[i][i+1] = 2;
            else
                dp[i][i+1] = 1;
        }
        for(int len=2;len<n;len++)
        {
            for(int j=1;j<=n-len;j++)
            {
                int tail = j+len;
                if(s.charAt(j-1)==s.charAt(tail-1))
                {
                    dp[j][tail] = dp[j+1][tail-1]+2;
                }
                dp[j][tail] = Math.max(dp[j][tail],Math.max(dp[j+1][tail],dp[j][tail-1]));
            }
        }
        return dp[1][n];
    }

    /**
     * needs to be done again
     * @param books
     * @param shelf_width
     * @return
     */
    public int Num1105minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i=1;i<=n;i++)
        {
            int width = books[i-1][0];
            int height = books[i-1][1];
            dp[i] = dp[i-1]+height;
            for(int j=i-1;j>0&&width+books[j-1][0]<=shelf_width;j--)
            {
                height = Math.max(height,books[j-1][1]);
                width+=books[j-1][0];
                dp[i] = Math.min(dp[i],dp[j-1]+height);
            }
        }
        return dp[n];
    }

    public int Num413numberOfArithmeticSlices(int[] A) {
        int sum = 0;
        int n = A.length;
        if(n<=2)
            return sum;
        int[] dp = new int[n];
        for(int i=2;i<n;i++)
        {
            if(A[i]-A[i-1]==A[i-1]-A[i-2])
            {

                dp[i] = dp[i-1]+1;
            }
        }
        for(int i=0;i<n;i++)
            sum += dp[i];
        return sum;
    }

    public int Num1048longestStrChain(String[] words) {
        int n = words.length,max = 1;
        int[] dp = new int[n];
        dp[0] = 1;
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for(int i=1;i<n;i++)
        {
            dp[i]=1;
            for(int j=i-1;j>=0;j--)
            {
                if(Num1048ispredecessor(words[j],words[i]))
                    dp[i] = Math.max(dp[i],dp[j]+1);
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }

    public boolean Num1048ispredecessor(String s1,String s2)
    {
        if(s1.length()!=s2.length()-1)
            return false;
        if(s2.contains(s1))
            return true;
        int j=0,diff = 0;
        for(int i=0;i<s1.length();i++)
        {
            if(s1.charAt(i)!=s2.charAt(j))
            {
                if(diff==1)
                    return false;
                j++;
                i--;
                diff =1;
            }
            else
            {
                j++;
            }
        }
        return true;
    }

    public int Num750countCornerRectangles(int[][] grid) {
        Map<Integer, Integer> count = new HashMap();
        int ans = 0;
        for (int[] row: grid) {
            for (int c1 = 0; c1 < row.length; ++c1) if (row[c1] == 1) {
                for (int c2 = c1+1; c2 < row.length; ++c2) if (row[c2] == 1) {
                    int pos = c1 * 200 + c2;
                    int c = count.getOrDefault(pos, 0);
                    ans += c;
                    count.put(pos, c+1);
                }
            }
        }
        return ans;
    }

    public int Num1273deleteTreeNodes(int nodes, int[] parent, int[] value) {
        int[] f = new int[nodes];
        for (int i = nodes - 1; i > 0; i--) {
            value[parent[i]] += value[i];
            f[parent[i]] += value[i] == 0 ? 0 : f[i] + 1;
        }
        return value[0] == 0 ? 0 : f[0] + 1;
    }

    public int Num1027longestArithSeqLength(int[] A) {
        int hash[][] = new int[A.length][20000];
        int max = 0;
        for(int i = 1; i < A.length; i++)
            for(int j = i-1; j >=0; j--){
                int diff = A[i] - A[j] + 10000;
                int counttillnow = hash[j][diff];
                if(hash[i][diff] > counttillnow) continue;
                else {
                    hash[i][diff] = counttillnow + 1;
                    max = Math.max(max, counttillnow + 1);
                }
            }
        return max + 1;
    }

    public double Num813largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        double[][] dp = new double[K][n];
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
            dp[0][i] = sum / (i+1);
        }

        for (int k = 1; k < K; k++) {
            for (int i = k; i < n; i++) {
                sum = 0.0;
                for (int j = i; j > k-1; j--) {
                    sum += A[j];
                    dp[k][i] = Math.max(dp[k][i], sum / (i-j+1) + dp[k-1][j-1]);
                }
            }
        }
        return dp[K-1][n-1];

    }

    public int Num718findLength(int[] A, int[] B) {

    }


}
