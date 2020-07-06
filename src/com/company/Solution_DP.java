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



}
