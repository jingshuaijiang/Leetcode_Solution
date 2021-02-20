package com.company;
import java.util.*;
public class Solution_Backtracking {

    public List<List<Integer>> Num78subsets(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> current = new LinkedList<>();
        Num78helper(ans,current,0,nums);
        return ans;
    }

    public void Num78helper(List<List<Integer>> ans, List<Integer> current, int i, int[] nums)
    {
        if(i>=nums.length)
        {
            ans.add(new LinkedList<>(current));
            return;
        }
        Num78helper(ans,current,i+1,nums);
        current.add(nums[i]);
        Num78helper(ans,current,i+1,nums);
        current.remove(current.size()-1);
        return;
    }

    public List<List<Integer>> Num90subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> current = new LinkedList<>();
        Arrays.sort(nums);
        Num90helper(ans,current,0,nums);
        return ans;
    }

    public void Num90helper(List<List<Integer>> ans, List<Integer> current, int start,int[] nums)
    {
        ans.add(new LinkedList(current));
        for(int i=start;i<nums.length;i++)
        {
            if(i>start&&nums[i]==nums[i-1])
                continue;
            current.add(nums[i]);
            Num90helper(ans,current,i+1,nums);
            current.remove(current.size()-1);
        }
    }

    public List<List<Integer>> Num77combine(int n, int k) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> current = new LinkedList<>();
        Num77helper(ans,current,n,k,0,1);
        return ans;
    }

    public void Num77helper(List<List<Integer>> ans, List<Integer> current, int n, int k, int temp, int start)
    {
        if(temp>=k)
        {
            ans.add(new LinkedList<>(current));
            return;
        }
        for(int i=start;i<=n;i++)
        {
            current.add(i);
            Num77helper(ans,current,n,k,temp+1,i+1);
            current.remove(current.size()-1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> nums = new LinkedList<>();
        Num39helper(candidates,target,ans,nums,0);
        return ans;
    }

    public void Num39helper(int[] arrays,int tar,List<List<Integer>> ans,List<Integer> nums,int startindex)
    {
        if(tar==0)
        {
            ans.add(new LinkedList<>(nums));
            return;
        }
        for(int i=startindex;i<arrays.length;i++)
        {
            if(tar-arrays[i]>=0)
            {
                nums.add(arrays[i]);
                Num39helper(arrays,tar-arrays[i],ans,nums,i);
                nums.remove(nums.size()-1);
            }
        }
    }

    public List<List<Integer>> Num40combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> nums = new LinkedList<>();
        Arrays.sort(candidates);
        Num40helper(candidates,target,ans,nums,0);
        return ans;
    }

    public void Num40helper(int[] arrays,int tar,List<List<Integer>> ans,List<Integer> nums,int startindex)
    {
        if(tar==0)
        {
            ans.add(new LinkedList<>(nums));
            return;
        }
        for(int i=startindex;i<arrays.length;i++)
        {
            if(i>startindex&&arrays[i]==arrays[i-1])
                continue;
            if(tar-arrays[i]>=0)
            {
                nums.add(arrays[i]);
                Num40helper(arrays,tar-arrays[i],ans,nums,i+1);
                nums.remove(nums.size()-1);
            }
            else
                break;
        }
    }

    protected void backtrack(int remain, int k,
                             LinkedList<Integer> comb, int next_start,
                             List<List<Integer>> results) {

        if (remain == 0 && comb.size() == k) {
            // Note: it's important to make a deep copy here,
            // Otherwise the combination would be reverted in other branch of backtracking.
            results.add(new ArrayList<Integer>(comb));
            return;
        } else if (remain < 0 || comb.size() == k) {
            // Exceed the scope, no need to explore further.
            return;
        }

        // Iterate through the reduced list of candidates.
        for (int i = next_start; i < 9; ++i) {
            comb.add(i + 1);
            this.backtrack(remain - i - 1, k, comb, i + 1, results);
            comb.removeLast();
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        LinkedList<Integer> comb = new LinkedList<Integer>();

        this.backtrack(n, k, comb, 0, results);
        return results;
    }

    public int Num337combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int combSum = 1; combSum < target + 1; ++combSum) {
            for (int num : nums) {
                if (combSum - num >= 0)
                    dp[combSum] += dp[combSum - num];
                // minor optimizaton, early stopping
                // else
                //     break;
            }
        }
        return dp[target];
    }

    public void Num31nextPermutation(int[] nums) {
        int i = nums.length-2;
        while(i>=0&&nums[i+1]<=nums[i])
        {
            i--;
        }
        if(i>=0)
        {
            int j = nums.length-1;
            while(j>=0&&nums[j]<=nums[i])
                j--;
            swap(nums,i,j);
        }
        reverse(nums,i+1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    HashMap<Integer,List<List<Integer>>> map = new HashMap<>();
    public List<List<Integer>> Num254getFactors(int n) {
        List<List<Integer>> ans = new LinkedList<>();
        int sqrt = (int)Math.sqrt(n);
        for(int i=2;i<=sqrt;i++)
        {
            if(n%i==0)
            {
                List<List<Integer>> last = new LinkedList<>();
                if(!map.containsKey(n/i))
                {
                    last = getFactors(n/i);
                }
                last = map.get(n/i);
                for(List<Integer> combi:last)
                {
                    List<Integer> current = new LinkedList<>();
                    current.add(i);
                    int keep = 0;
                    for(int num:combi)
                    {
                        if(num<i)
                        {
                            keep=1;
                            break;
                        }
                        current.add(num);
                    }
                    if(keep==0)
                        ans.add(current);
                }

                List<Integer> current = new LinkedList<>();
                current.add(i);
                current.add(n/i);
                ans.add(current);

            }
        }
        map.put(n,ans);
        return ans;
    }

    public String Num60getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for(int i=1;i<n;i++)
        {
            factorial[i] = factorial[i-1]*i;
        }
        --k;
        int[] visited = new int[n+1];
        Arrays.fill(visited,1);
        StringBuilder sb = new StringBuilder();
        for(int j=1;j<=n;j++)
        {
            int order = k/factorial[n-j]+1;
            for(int p=1;p<=n;p++)
            {
                order-=visited[p];
                if(order==0)
                {
                    visited[p] = 0;
                    sb.append(p);
                    break;
                }
            }
            k%=factorial[n-j];
        }
        return sb.toString();
    }

    public List<String> Num320generateAbbreviations(String word) {
        List<String> ans = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        HashSet<String> set = new HashSet<>();
        Num320helper(ans,0,sb,word,set);
        return ans;
    }

    public void Num320helper(List<String> ans, int index, StringBuilder sb, String word, HashSet<String> set)
    {
        if(index>=word.length()&&!set.contains(sb.toString()))
        {
            ans.add(sb.toString());
            set.add(sb.toString());
            return ;
        }
        for(int len=1;len<=word.length()-index;len++)
        {
            int end = sb.length();

            if((end>0&&!Character.isDigit(sb.charAt(end-1)))||end==0)
            {
                sb.append(len);
                Num320helper(ans,index+len,sb,word,set);
                sb.delete(end,sb.length());
            }

            sb.append(word.substring(index,index+len));
            Num320helper(ans,index+len,sb,word,set);
            sb.delete(end,sb.length());
        }
    }

    public List<String> Num93restoreIpAddresses(String s) {
        List<String> ans = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        Num93helper(ans,sb,0,0,s,0);
        return ans;
    }

    public void Num93helper(List<String> ans, StringBuilder sb, int index, int k, String s, int dots)
    {
        if(index==s.length()&&dots==3)
        {
            ans.add(sb.toString());
            return ;
        }
        int end = sb.length();
        if(k<2)
        {
            Num93helper(ans,sb,index+1,k+1,s,dots);
        }
        if(valid(s,index,k))
        {
            sb.append(s.substring(index,index+k));
            if(dots<3)
            {
                sb.append('.');
                dots+=1;
            }
            k=0;
            Num93helper(ans,sb,index+1,k,s,dots);
            sb.setLength(end);
        }
    }

    public boolean valid(String s, int index, int k)
    {
        String sub = s.substring(index,index+k);
        if(sub.length()>1&&sub.charAt(0)=='0')
            return false;
        int current = Integer.valueOf(sub);
        if(current>=0&&current<=255)
            return true;
        return false;
    }

    public int Num351numberOfPatterns(int m, int n) {
        int ans = 0;
        for(int i=1;i<=9;i++)
        {
            num351helper(i);
        }
        for(int i=m;i<=n;i++)
        {
            ans+=Num351nums[i];
        }
        return ans;
    }
    int[] Num351nums = new int[10];

    public void num351helper(int i)
    {
        int[] visited = new int[10];
        visited[1]=1;
        int corner = Num351patterhelper(visited,1,i-1);
        visited[1]=0;
        visited[2]=1;
        int line = Num351patterhelper(visited,2,i-1);
        visited[2]=0;
        visited[5] = 1;
        int center = Num351patterhelper(visited,5,i-1);
        visited[5] = 0;
        Num351nums[i] = 4*corner+4*line+center;
    }

    public  int Num351patterhelper(int[] visited,int last,int left)
    {
        if(left==0)
            return 1;
        int sum = 0;
        for(int i=1;i<=9;i++)
        {
            if(isvalid(visited,last,i))
            {
                visited[i] = 1;
                sum+=Num351patterhelper(visited,i,left-1);
                visited[i]=0;
            }
        }
        return sum;
    }

    public boolean isvalid(int[] visited,int last,int current)
    {
        if(visited[current]==1)
            return false;
        if ((current + last) % 2 == 1)
            return true;
        // indexes are at both end of the diagonals for example 0,0, and 8,8
        int mid = (current + last)/2;
        if (mid == 4)
            return visited[mid]==1;
        // adjacent cells on diagonal  - for example 0,0 and 1,0 or 2,0 and //1,1
        if ((current%3 != last%3) && (current/3 != last/3)) {
            return true;
        }
        // all other cells which are not adjacent
        return visited[mid]==1;
    }

    public List<String> Num282addOperators(String num, int target) {
        List<String> ans = new LinkedList<>();
        Num282helper(ans,num,0,target,0,0,"");
        return ans;
    }

    public void Num282helper(List<String> ans, String num, int index, int target, long sum, long previousnum, String expression)
    {
        if(index==num.length())
        {
            if(sum==target)
            {
                ans.add(expression);
                return;
            }
        }
        for(int i=index;i<num.length();i++)
        {
            if(i!=index&&num.charAt(index)=='0')
                break;
            long cur = Long.parseLong(num.substring(index,i+1));
            if(index==0)
            {
                Num282helper(ans,num,i+1,target,cur,cur,expression+cur);
            }
            else{
                Num282helper(ans,num,i+1,target,sum+cur,cur,expression+"+"+cur);
                Num282helper(ans,num,i+1,target,sum-cur,-cur,expression+"-"+cur);
                Num282helper(ans,num,i+1,target,sum-previousnum+previousnum*cur,previousnum*cur,expression+"*"+cur);
            }
        }
    }

    HashMap<Character,String> c2s = new HashMap<>();
    HashMap<String,Character> s2c = new HashMap<>();
    public boolean Num291wordPatternMatch(String pattern, String s) {
        return Num291helper(pattern,s,0,0);
    }

    public boolean Num291helper(String pattern,String s,int pindex, int sindex)
    {
        if(pindex==pattern.length()&&sindex==s.length())
            return true;
        if((pindex==pattern.length()&&sindex<s.length())||(sindex==s.length()&&pindex<pattern.length()))
            return false;
        char c = pattern.charAt(pindex);
        if(c2s.containsKey(c))
        {
            String value = c2s.get(c);
            if(s.length()-sindex<value.length())
                return false;
            if(s.substring(sindex,sindex+value.length()).equals(value))
            {
                if(Num291helper(pattern,s,pindex+1,sindex+value.length()))
                    return true;
            }
            return false;
        }else
        {
            for(int i = sindex+1;i<=s.length();i++)
            {
                String value = s.substring(sindex,i);
                if(s2c.containsKey(value))
                    continue;
                s2c.put(value,c);
                c2s.put(c,value);
                if(Num291helper(pattern,s,pindex+1,i))
                    return true;
                s2c.remove(value);
                c2s.remove(c);
            }
        }
        return false;
    }




































}
