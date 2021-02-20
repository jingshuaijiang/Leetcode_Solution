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
        int maxlength = 0;
        int a = A.length;
        int b = B.length;
        int[][] dp = new int[a+1][b+1];
        for(int i=1;i<=a;i++)
        {
            for(int j=1;j<=b;j++)
            {
                if(A[i-1]==B[j-1])
                {
                    dp[i][j] = dp[i-1][j-1]+1;
                    maxlength = Math.max(maxlength,dp[i][j]);
                }
                else
                    dp[i][j] = 0;
            }
        }
        return maxlength;
    }

    public int Num1055shortestWay(String source, String target) {
        int[] count = new int[26];
        for(int i=0;i<source.length();i++)
        {
            count[source.charAt(i)-'a']++;
        }
        for(int i=0;i<target.length();i++)
        {
            if(count[target.charAt(i)-'a']<=0)
                return -1;
        }
        int[] dp = new int[target.length()+1];
        for(int i=1;i<=target.length();i++)
        {
            dp[i] = Integer.MAX_VALUE;
            for(int len = 0;len<source.length();len++)
            {
                if(i-len<1||!Num1055ISasubstring(source,target,i-len-1,i-1))
                    break;
                if(Num1055ISasubstring(source,target,i-len-1,i-1))
                {
                    dp[i] = Math.min(dp[i-len-1]+1,dp[i]);
                }
            }
        }
        return dp[target.length()];
    }

    public boolean Num1055ISasubstring(String source,String target,int start,int end)
    {
        int i = 0,j = start;
        while(i<source.length()&&j<=end)
        {
            while(i<source.length()&&j<=end&&source.charAt(i)!=target.charAt(j))
            {
                i++;
            }
            if(i==source.length()&&j<=end)
                return false;
            else
            {
                i++;j++;
            }
        }
        if(i==source.length()&&j<=end)
            return false;
        return true;
    }

    public int Num712minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=1;i<=n;i++)
        {
            dp[i][0] = dp[i-1][0]+s1.charAt(i-1)-'a'+97;
        }
        for(int j=1;j<=m;j++)
        {
            dp[0][j] = dp[0][j-1]+s2.charAt(j-1)-'a'+97;
        }
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(s1.charAt(i-1)==s2.charAt(j-1))
                {
                    dp[i][j] = dp[i-1][j-1];
                }
                else
                {
                    int ascs1 = s1.charAt(i-1)-'a'+97;
                    int ascs2 = s2.charAt(j-1)-'a'+97;
                    dp[i][j] = Math.min(dp[i-1][j]+ascs1,dp[i][j-1]+ascs2);
                }
            }
        }
        return dp[n][m];
    }

    public int Num1155numRollsToTarget(int d, int f, int target) {
        int MOD = 1000000007;
        int[][] dp = new int[31][1001];
        int min = Math.min(f, target);
        for (int i = 1; i <= min; i++) {
            dp[1][i] = 1;
        }
        int targetMax = d * f;
        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= targetMax; j++) {
                for (int k = 1; j - k >= 0 && k <= f; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                }
            }
        }
        return dp[d][target];

    }

    public int Num935knightDialer(int N) {
        int[][] dp = new int[N][10];
        int mod = 1000000007;
        for(int i=0;i<10;i++)
        {
            dp[0][i] = 1;
        }

        for(int i=1;i<N;i++)
        {
            dp[i][0] = (dp[i-1][4]+dp[i-1][6])%mod;
            dp[i][1] = (dp[i-1][6]+dp[i-1][8])%mod;
            dp[i][2] = (dp[i-1][7]+dp[i-1][9])%mod;
            dp[i][3] = (dp[i-1][4]+dp[i-1][8])%mod;

            dp[i][4] = (((dp[i-1][3]+dp[i-1][9])%mod)+dp[i-1][0])%mod;
            dp[i][6] = (((dp[i-1][1]+dp[i-1][7])%mod)+dp[i-1][0])%mod;

            dp[i][7] = (dp[i-1][2]+dp[i-1][6])%mod;
            dp[i][8] = (dp[i-1][1]+dp[i-1][3])%mod;
            dp[i][9] = (dp[i-1][2]+dp[i-1][4])%mod;
        }
        int ans = 0;
        for(int i=0;i<10;i++)
        {
            ans = (ans + dp[N-1][i])%mod;
        }
        return ans;
    }

    public double knightProbability(int N, int K, int r, int c) {
        double[][] dp = new double[N][N];
        dp[r][c] = 1;
        int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
        for(int i=0;i<K;i++)
        {
            double[][] current = new double[N][N];
            for(int row=0;row<N;row++)
            {
                for(int col = 0;col<N;col++)
                {
                    if(dp[row][col]>0)
                    {
                        for(int k=0;k<8;k++)
                        {
                            int nr = row+dr[k];
                            int nc = col+dc[k];
                            if(0<=nr&&nr<N&&0<=nc&&nc<N)
                            {
                                current[nr][nc] += dp[row][col]/8.0;
                            }
                        }
                    }
                }
            }
            dp = current;
        }
        double ans=0;
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                ans+=dp[i][j];
            }
        }
        return ans;
    }

    public int Num85maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] h = new int[m];
        int[] left = new int[m];
        int[] right = new int[m];
        int maxrect = 0;
        Arrays.fill(right,m);
        for(int i=0;i<n;i++)
        {
            int curleft = 0;
            int curright = n;
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j]=='1')
                    h[j]++;
                else
                    h[j]=0;
            }

            for(int j=0;j<m;j++)
            {
                if(matrix[i][j]=='1')
                {
                    left[j] = Math.max(left[j],curleft);
                }
                else
                {
                    left[j] = 0;
                    curleft = j+1;
                }
            }
            for(int j=m-1;j>=0;j--)
            {
                if(matrix[i][j]=='1')
                {
                    right[j] = Math.min(right[j],curright);
                }
                else
                {
                    right[j] = 0;
                    curright = j;
                }
            }
            for(int j=0;j<m;j++)
            {
                maxrect = Math.max(maxrect,(right[j]-left[j])*h[j]);
            }
        }
        return maxrect;
    }

    public int Num91numDecodings(String s) {

        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i=2;i<=s.length();i++)
        {
            dp[i] = Integer.valueOf(s.charAt(i-1)+"")>0?dp[i-1]:0;
            String twodigit = s.substring(i-2,i);
            if(Integer.valueOf(twodigit)<=26&&Integer.valueOf(twodigit)>9)
            {
                dp[i] += dp[i-2];
            }

        }
        return dp[s.length()];
    }

    public int Num646findLongestChain(int[][] pairs) {
        Arrays.sort(pairs,(a,b)->a[1]-b[1]);
        int curr = Integer.MIN_VALUE, ans = 0;
        for(int[] pair:pairs)
        {
            if(curr< pair[0])
            {
                curr = pair[1];
                ans++;
            }
        }
        return ans;
    }

    public int Num343integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i=2;i<=n;i++)
        {
            for(int j = i-1;j>=1;j--)
            {
                dp[i] = Math.max(dp[i],dp[j]*(i-j));
                dp[i] = Math.max(dp[i],j*(i-j));
            }
        }
        return dp[n];
    }

    public int Num494findTargetSumWays(int[] nums, int S) {
        if(S>1000||S<-1000)
            return 0;
        int n = nums.length;
        int[][] dp = new int[n][2*n+1];
        if(nums[0]==0)
            dp[0][1000] = 2;
        else
        {
            dp[0][nums[0]+1000] = 1;
            dp[0][1000-nums[0]] = 1;
        }
        for(int i=1;i<n;i++)
        {
            for(int j=0;j<2*n+1;j++)
            {
                if(dp[i-1][j]>0)
                {
                    int mj = j-nums[i];
                    int pj = j+nums[i];
                    if(mj>=0)
                        dp[i][mj]+=dp[i-1][j];
                    if(pj<=2*n)
                        dp[i][pj]+=dp[i-1][j];
                }
            }
        }
        return dp[n-1][S];
    }

    public int Num740deleteAndEarn(int[] nums) {
        int[] count = new int[10001];
        for(int num:nums)
        {
            count[num]++;
        }
        int ppre = count[1];
        int pre = Math.max(count[1],2*count[2]);
        for(int i=3;i<10001;i++)
        {
            int curr = Math.max(pre,ppre+i*count[i]);
            ppre = pre;
            pre = curr;
        }
        return pre;
    }

    public int Num300lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public boolean Num416canPartition(int[] nums) {
        int sum = 0;
        for(int i=0;i<nums.length;i++)
        {
            sum+=nums[i];
        }
        if(sum%2==1)
            return false;
        int target = sum/2;
        boolean[][] dp = new boolean[nums.length][20001];
        dp[0][0] = true;
        dp[0][nums[0]] = true;
        for(int i=1;i<nums.length;i++)
        {
            for(int j=0;j<20001;j++)
            {
                if(dp[i-1][j])
                {
                    dp[i][j] = true;
                    dp[i][j+nums[i]] = true;
                    if(dp[i][target])
                        return true;
                }
            }
        }
        return false;
    }

    public int Num1223dieSimulator(int n, int[] rollMax) {
        int mod = (int) Math.pow(10,9)+7;
        int[][][] dp = new int[n][7][16];
        for(int i=1;i<7;i++)
        {
            dp[0][i][1] = 1;
        }
        for(int i=1;i<n;i++)
        {
            int[] prev = new int[7];
            int sum = 0;
            int ppp = 0;
            for(int j=1;j<7;j++)
            {
                for(int k=1;k<16;k++)
                {
                    prev[j]=(prev[j]+dp[i-1][j][k]%mod)%mod;
                }
                sum+=prev[j]%mod;
            }

            for(int j=1;j<7;j++)
            {
                for(int p = 1;p<7;p++)
                {
                    if(p!=j)
                        dp[i][j][1]=(dp[i][j][1]+ prev[p]%mod)%mod;
                    if(dp[i][j][1]<0)
                        ppp++;
                }
                for(int k=2;k<16;k++)
                {
                    if(k<=rollMax[j-1])
                        dp[i][j][k] = dp[i-1][j][k-1]%mod;
                    if(dp[i][j][k]<0)
                        ppp++;
                }
            }
        }
        int ans = 0;
        int ppp = 0;
        for(int j=1;j<7;j++)
        {
            for(int k=1;k<16;k++)
            {
                ans=(ans+dp[n-1][j][k]%mod)%mod;
                if(ans<0)
                    ppp++;
            }
        }
        return ans%mod;
    }

    public int Num361maxKilledEnemies(char[][] grid) {
        int n = grid.length;
        if(n==0)
            return 0;
        int m = grid[0].length;
        int[][] dpleft =new int[n+2][m+2];
        int[][] dpright = new int[n+2][m+2];
        int[][] dpup = new int[n+2][m+2];
        int[][] dpdown = new int[n+2][m+2];
        for(int i=1;i<=n;i++)
        {
            int leftnum = 0;
            for(int j=1;j<=m;j++)
            {
                if(grid[i-1][j-1]=='W')
                    leftnum = 0;
                else if(grid[i-1][j-1]=='E')
                    leftnum++;
                else
                    dpleft[i][j] = leftnum;
            }
            int rightnum = 0;
            for(int j=m;j>=1;j--)
            {
                if(grid[i-1][j-1]=='W')
                    rightnum = 0;
                else if(grid[i-1][j-1]=='E')
                    rightnum++;
                else
                    dpright[i][j] = rightnum;
            }
        }
        for(int j=1;j<=m;j++)
        {
            int upnum = 0;
            for(int i=1;i<=n;i++)
            {
                if(grid[i-1][j-1]=='W')
                    upnum = 0;
                else if(grid[i-1][j-1]=='E')
                    upnum++;
                else
                    dpup[i][j] = upnum;
            }
            int downnum = 0;
            for(int i=n;i>=1;i--)
            {
                if(grid[i-1][j-1]=='W')
                    downnum = 0;
                else if(grid[i-1][j-1]=='E')
                    downnum++;
                else
                    dpdown[i][j] = downnum;
            }
        }
        int max = 0;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(grid[i-1][j-1]=='0')
                    max = Math.max(max,dpup[i][j]+dpdown[i][j]+dpleft[i][j]+dpright[i][j]);
            }
        }
        return max;
    }

    public int Num978maxTurbulenceSize(int[] A) {
        int[][] dp = new int[2][A.length+1];

        for(int i=0;i<A.length;i++) {
            dp[0][i] = dp[1][i] = 1;
        }

        for(int i=1;i<A.length;i++) {
            if(A[i] < A[i-1]) {
                dp[0][i] = Math.max(dp[0][i], dp[1][i-1] + 1);
            }
            else if(A[i] > A[i-1]) {
                dp[1][i] = Math.max(dp[1][i], dp[0][i-1] + 1);
            }
        }

        int ans = -1;
        for(int i=0;i<A.length;i++) {
            ans = Math.max(ans, Math.max(dp[0][i], dp[1][i]));
        }

        return ans;

    }

    public int Num264nthUglyNumber(int n) {
        if(n==1)
            return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0,p3 = 0,p5=0;
        for(int i=1;i<n;i++)
        {
            dp[i] = Math.min(dp[p2]*2,Math.min(dp[p3]*3,dp[p5]*5));
            if(dp[i] == dp[p2]*2) p2++;
            if(dp[i] == dp[p3]*3) p3++;
            if(dp[i] == dp[p5]*5) p5++;
        }
        return dp[n-1];
    }

    public int Num221maximalSquare(char[][] matrix) {
        int max = 0;
        int n = matrix.length;
        if(n==0)
            return 0;
        int m = matrix[0].length;
        int[][] dp = new int[n+1][m+1];
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(matrix[i-1][j-1]=='0')
                    dp[i][j] = 0;
                else
                {
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max*max;
    }
    int[] Num357ans = new int[9];
    public int Num357countNumbersWithUniqueDigits(int n) {
        Num357ans[0] = 0;
        Num357ans[1] = 10;
        Num357ans[2] = 81;
        int anns = 0;
        for(int i=3;i<=8;i++)
        {
            Num357ans[i] = Num357ans[i-1]*(11-i);
        }
        if(n==0)
            return 1;
        else
        {
            for(int i=0;i<=n;i++)
            {
                anns+=Num357ans[i];
            }
        }
        return anns;
    }

    public int Num873lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Map<Integer,Integer> index = new HashMap<>();
        for(int i=0;i<N;i++)
        {
            index.put(A[i],i);
        }
        Map<Integer,Integer> len = new HashMap<>();
        int max = 0;
        for(int i=0;i<N;i++)
        {
            for(int j=i-1;j>=0;j--)
            {
                int k = A[i]-A[j];
                int inde = index.getOrDefault(k,-1);
                if(inde>=0&&inde<j)
                {
                    int cand = len.getOrDefault(inde*N+j,2)+1;
                    len.put(j*N+i,cand);
                    max = Math.max(max,cand);
                }
            }
        }
        return max>=3?max:0;
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

    public int Num265minCostII(int[][] costs) {
        if(costs.length==0)
            return 0;
        int k = costs[0].length;
        int n = costs.length;
        for(int i=1;i<n;i++)
        {
            int min = -1,secondmin = -1;
            for(int j=0;j<k;j++)
            {
                int cost = costs[i-1][j];
                if(min==-1||cost<costs[i-1][min])
                {
                    secondmin = min;
                    min = j;
                }
                else if(secondmin==-1||cost<costs[i-1][secondmin])
                    secondmin = j;
            }
            for(int j=0;j<k;j++)
            {
                if(j==min)
                {
                    costs[i][j] += costs[i-1][secondmin];
                }
                else
                    costs[i][j] += costs[i-1][min];
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int j=0;j<k;j++)
        {
            ans = Math.min(ans,costs[n-1][j]);
        }
        return ans;
    }

    public int Num1335minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        if(d>len)
            return -1;
        int[][] dp = new int[len][d+1];
        for(int i=0;i<len;i++)
        {
            dp[i][1] = i==0 ? jobDifficulty[0]:Math.max(jobDifficulty[i],dp[i-1][1]);
        }
        for(int k=2;k<=d;k++)
        {
            for(int i=k-1;i<len;i++)
            {
                dp[i][k] = Integer.MAX_VALUE;
                int localMax = jobDifficulty[i];
                for(int s=i-1;s>=k-2;s--)
                {
                    dp[i][k] = Math.min(dp[i][k],dp[s][k-1]+localMax);
                    localMax = Math.max(localMax,jobDifficulty[s]);
                }
            }
        }
        return dp[len-1][d];
    }

    public int Num1478minDistance(int[] houses, int k) {
        int len = houses.length;
        Arrays.sort(houses);
        if(k>=len)
            return 0;
        int[][] distances = new int[len][len];
        for(int i=len-2;i>=0;i--)
        {
            for(int j=i+1;j<len;j++)
            {
                distances[i][j] = distances[i+1][j-1] + houses[j]-houses[i];
            }
        }
        int[][] dp = new int[len][k+1];
        for(int i=0;i<len;i++)
        {
            Arrays.fill(dp[i],Integer.MAX_VALUE/2);
        }
        for(int i=0;i<len;i++)
        {
            dp[i][1] = distances[0][i];
            for(int j=2;j<=k&&j<=i+1;j++)
            {
                for(int i0=0;i0<i;i0++)
                {
                    dp[i][j] = Math.min(dp[i][j],dp[i0][j-1]+distances[i0+1][i]);
                }
            }
        }
        return dp[len-1][k];
    }

    public int Num1235jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int jobs = startTime.length;
        int[][] jobinfo = new int[jobs][3];
        int[] dp = new int[jobs+1];
        for(int i=0;i<jobs;i++)
        {
            jobinfo[i][0] = startTime[i];
            jobinfo[i][1] = endTime[i];
            jobinfo[i][2] = profit[i];
        }
        Arrays.sort(jobinfo, new Comparator<int[]>() {
            @Override
            public int compare(int[] job1, int[] job2) {
                return job1[1]-job2[1];
            }
        });

        for(int i=0;i<jobs;i++)
        {
            dp[i+1] = Math.max(dp[i],jobinfo[i][2]);
            for(int j=i-1;j>=0;j--)
            {
                if(jobinfo[j][1]<=jobinfo[i][0])
                {
                    dp[i+1] = Math.max(dp[i+1],dp[j+1]+jobinfo[i][2]);
                    break;
                }
            }
        }
        return dp[jobs];
    }

    public int Num1043maxSumAfterPartitioning(int[] arr, int k) {
        int len = arr.length;
        int[][] dp = new int[len+1][k+2];
        for(int i=0;i<arr.length;i++)
        {
            for(int j=1;j<=k&&i-j>=0;j++)
            {
                dp[i+1][j] = Integer.MIN_VALUE;
                int maxnum = Maxinrange(arr,i,j);
                for(int p=1;p<=k;p++)
                {
                    dp[i+1][j] = Math.max(dp[i+1][j],dp[i+1-j][p]+maxnum*j);
                }
            }
        }
        int ans = 0;
        for(int p=1;p<=k;p++ )
        {
            ans = Math.max(ans,dp[len][p]);
        }
        return ans;
    }

    public int Maxinrange(int[] arr, int end, int nums)
    {
        int max = 0;
        for(int i=end-nums;i<=end;i++)
        {
            max = Math.max(max,arr[i]);
        }
        return max;
    }

    public int Num1043againmaxSumAfterPartitioning(int[] arr, int k) {
        int len = arr.length;
        int[] dp = new int[len+1];
        for(int i=0;i<arr.length;i++)
        {
            dp[i+1] = Integer.MIN_VALUE;
            for(int j=1;j<=k&&i-j>=0;j++)
            {
                int maxnum = Maxinrange(arr,i,j);
                dp[i+1] = Math.max(dp[i+1],dp[i+1-j]+maxnum*j);
            }
        }
        return dp[len];
    }

    public boolean Num523checkSubarraySum(int[] nums, int k) {
        if(k==0)
            return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++)
        {
            dp[i][i] = nums[i]%k;
        }
        for(int i=0;i<n-1;i++)
        {
            dp[i][i+1] = (nums[i]%k+nums[i+1]%k)%k;
            if(dp[i][i+1]==0)
                return true;
        }
        for(int len = 2; len<n; len++)
        {
            for(int i=0;i+len<n;i++)
            {
                int j = i+len;
                dp[i][j] = (dp[i+1][j-1]+nums[i]%k+nums[j]%k)%k;
                if(dp[i][j]%k==0)
                    return true;
            }
        }
        return false;
    }

    public boolean Num10isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for(int i=2;i<=m;i+=2)
        {
            if(p.charAt(i-1)=='*')
            {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.')
                    dp[i][j] = dp[i-1][j-1];
                else if(p.charAt(j-1)=='*')
                {
                    if(s.charAt(i-1)!=p.charAt(j-2)&&p.charAt(j-2)!='.')
                    {
                        dp[i][j] = dp[i][j-2];
                    }
                    else
                    {
                        dp[i][j] = dp[i][j-1] || dp[i][j-2] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[n][m];
    }


    public int Num650minSteps(int n) {
        int ans = 0,d = 2;
        while(n>1)
        {
            while(n%d==0)
            {
                ans+=d;
                n/=d;
            }
            d++;
        }
        return ans;
    }

    public double Num799champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row+1][query_row+1];
        dp[0][0] = poured;
        for(int i=1;i<=query_row;i++)
        {
            for(int j=0;j<=i;j++)
            {
                if(j==0)
                {
                    dp[i][0] = dp[i-1][0]>1?(dp[i-1][0]-1)/2:0;
                }
                else if(j==i)
                {
                    dp[i][j] = dp[i-1][j-1]>1?(dp[i-1][j-1]-1)/2:0;
                }
                else
                {
                    double a = dp[i-1][j-1]>1?(dp[i-1][j-1]-1)/2:0;
                    double b = dp[i-1][j]>1?(dp[i-1][j]-1)/2:0;
                    dp[i][j] =  a+b ;
                }
            }
        }
        return dp[query_row][query_glass]>1?1:dp[query_row][query_glass];
    }

    public int Num516againlongestPalindromeSubseq(String s) {
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

    public int Num1682longestPalindromeSubseq(String s) {
        int n = s.length();
        int ans = 0;
        // state
        int[][][] f = new int[n][n][26];
        // function
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 2) {
                    if (s.charAt(i) == s.charAt(j)) {
                        f[i][j][s.charAt(i) - 'a'] = 2;
                        ans = Math.max(ans, 2);
                    }
                } else {
                    for (int k = 0; k < 26; k++) {
                        if (s.charAt(i) == s.charAt(j) && s.charAt(i) - 'a' != k) {
                            f[i][j][s.charAt(i) - 'a'] = Math.max(f[i][j][s.charAt(i) - 'a'], f[i + 1][j - 1][k] + 2);
                            ans = Math.max(ans, f[i][j][s.charAt(i) - 'a']);
                        }
                        f[i][j][k] = Math.max(Math.max(f[i + 1][j][k], f[i][j - 1][k]), Math.max(f[i + 1][j - 1][k], f[i][j][k]));
                    }
                }
            }
        }
        // answer
        return ans;
    }

    public List<String> Num140wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for(String word:wordDict)
        {
            set.add(word);
        }
        HashMap<String,List<String>> map = new HashMap<>();
        Num140helper(map,s,set);
        return map.get(s);
    }

    public List<String> Num140helper(HashMap<String,List<String>> map, String s, HashSet<String> set)
    {
        if(s.equals(""))
        {
            LinkedList<String> ans = new LinkedList<>();
            return ans;
        }

        if(map.containsKey(s))
            return map.get(s);
        List<String> ans = new LinkedList<>();
        map.put(s,ans);
        for(int i=1;i<=s.length();i++)
        {
            String word = s.substring(0,i);
            if(set.contains(word))
            {
                List<String> postfixes = Num140helper(map,s.substring(i),set);
                for(String postfix:postfixes)
                {
                    String news = word+" "+postfix;
                    ans.add(news);
                }
                if(postfixes.size()==0&&s.substring(i).equals(""))
                    ans.add(word);
            }
        }
        return map.get(s);

    }

    public List<String> Num282addOperators(String num, int target) {

    }

    public int Num115numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++)
        {
            dp[i][0] = 1;
        }
        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                dp[j][i] = dp[j-1][i];
                if(s.charAt(j-1)==t.charAt(i-1))
                {
                    dp[j][i]+=dp[j-1][i-1];
                }
            }
        }
        return dp[n][m];
    }

    public int Num1444ways(String[] pizza, int k) {
        int mod  = 1000000007;
        int row = pizza.length;
        int col = pizza[0].length();
        int[][][] dp = new int[row][col][k+1];
        dp[0][0][1] = 1;
        for(int x = 2;x<=k;x++)
        {
            for(int i=0;i<row;i++)
            {
                for(int j=0;j<col;j++)
                {
                    if(dp[i][j][x]==0)
                        continue;
                    for(int z = i+1;z<row;z++)
                    {
                        if(hasA(num,i,j,z-1,col-1)&&hasA(num,z,j,row-1,col-1))
                        {
                            dp[z][j][x]+=dp[i][j][x-1];
                            dp[z][j][x]%=mod;
                        }
                    }
                    for(int z=j+1;z<col;z++)
                    {
                        if(hasA(num,i,j,ro-1,z-1)&&hasA(num,i,z,row-1,col-1))
                        {
                            dp[i][z][x]+=dp[i][j][x-1];
                            dp[i][z][x]%=mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                ans+=dp[i][j][k];
                ans%=mod;
            }
        }
        return ans;
    }

    public boolean hasA(int[][] num,int sr,int sc,int er,int ec){
        int num1=0,num2=0,num3=0,res;
        if(sr!=0 && sc!=0) num1=num[sr-1][sc-1];
        if(sr!=0) num2=num[sr-1][ec];
        if(sc!=0) num3=num[er][sc-1];
        return num[er][ec]-num2-num3+num1>0;
    }

    public boolean Num44isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i=1;i<=n;i++)
        {
            if(p.charAt(i-1)=='*')
            {
                dp[0][i]=true;
            }
            else
                break;
        }
        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(p.charAt(j-1)=='?'||p.charAt(j-1)==s.charAt(i-1))
                {
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p.charAt(j-1)=='*')
                {
                    dp[i][j] = dp[i-1][j]||dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }

    public boolean test()
    {
        String a = "";
        a.substring()
    }






























































    public int Num1262maxSumDivThree(int[] nums) {

    }




































}
