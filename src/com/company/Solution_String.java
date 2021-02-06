package com.company;
import java.util.*;

public class Solution_String {

    public String Num394decodeString(String s) {
        int len = s.length();
        if(len==0)
            return "";

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==']')
            {
                List<Character> str = new LinkedList<>();
                while(stack.peek()!='[')
                    str.add(stack.pop());
                stack.pop();
                int times = 1;
                int number = 0;
                while(!stack.isEmpty()&&Character.isDigit(stack.peek()))
                {
                    number = number+(stack.pop()-'0')*times;
                    times*=10;
                }
                while(number!=0)
                {
                    for(int j = str.size() - 1; j >= 0; j--)
                    {
                        stack.push(str.get(j));
                    }
                    number--;
                }
            }
            else
                stack.push(s.charAt(i));

        }
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);

    }


    public boolean Num1041isRobotBounded(String instructions) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0;
        int idx = 0;
        for (char i : instructions.toCharArray()) {
            if (i == 'L')
                idx = (idx + 3) % 4;
            else if (i == 'R')
                idx = (idx + 1) % 4;
            else {
                x += directions[idx][0];
                y += directions[idx][1];
            }
        }
        return (x == 0 && y == 0) || (idx != 0);
    }

    public List<Integer> Num438findAnagrams(String s, String p) {
        int n = p.length();
        List<Integer> ans = new LinkedList<>();
        int[] counter = new int[26];
        int[] counter1 = new int[26];
        for(int i=0;i<p.length();i++)
        {
            counter[p.charAt(i)-'a']++;
        }
        if(s.length()<p.length())
            return ans;
        for(int i=0;i<n;i++)
        {
            counter1[s.charAt(i)-'a']++;
        }
        if(Num438same(counter,counter1))
            ans.add(0);
        for(int i=n;i<s.length();i++)
        {
            counter1[s.charAt(i-n)-'a']--;
            counter1[s.charAt(i)-'a']++;
            if(Num438same(counter,counter1))
                ans.add(i-n+1);
        }
        return ans;

    }

    public boolean Num438same(int[] counter, int[] counter1)
    {
        for(int i=0;i<counter.length;i++)
        {
            if(counter[i]!=counter1[i])
                return false;
        }
        return true;
    }

    public boolean Num567checkInclusion(String s1, String s2) {
        int n = s1.length();
        int[] counter = new int[26];
        int[] counter1 = new int[26];
        for(int i=0;i<s1.length();i++)
        {
            counter[s1.charAt(i)-'a']++;
        }
        if(s2.length()<s1.length())
            return false;
        for(int i=0;i<n;i++)
        {
            counter1[s2.charAt(i)-'a']++;
        }
        if(Num567same(counter,counter1))
            return true;
        for(int i=n;i<s2.length();i++)
        {
            counter1[s2.charAt(i-n)-'a']--;
            counter1[s2.charAt(i)-'a']++;
            if(Num567same(counter,counter1))
                return true;
        }
        return false;
    }

    public boolean Num567same(int[] counter, int[] counter1)
    {
        for(int i=0;i<counter.length;i++)
        {
            if(counter[i]!=counter1[i])
                return false;
        }
        return true;
    }

    int Num1239max = 0;
    public int Num1239maxLength(List<String> arr) {
        int n = arr.size();
        int[] counter = new int[26];
        int[] visited = new int[n];
        for(int i=0;i<n;i++)
        {
            Num1239helper(arr,visited,counter,0,0);
        }
        return Num1239max;
    }

    public void Num1239helper(List<String> arr,int[] visited,int[] counter,int i,int size)
    {
        if(finished(counter)||i>=arr.size())
        {
            Num1239max = Math.max(Num1239max,size);
            return;
        }
        for(int j=i;j<arr.size();j++)
        {
            if(conflictself(counter,arr.get(j)))
            {
                continue;
            }
            if(notconflict(counter,arr.get(j)))
            {
                counter = add(counter,arr.get(j));
                visited[j]=1;
                Num1239max = Math.max(Num1239max,size+arr.get(j).length());
                Num1239helper(arr,visited,counter,j+1,size+arr.get(j).length());
                visited[j]=0;
                counter = minus(counter,arr.get(j));
            }
        }
    }



    public int[] add(int[] counter, String s)
    {
        for(int i=0;i<s.length();i++)
        {
            counter[s.charAt(i)-'a']++;

        }
        return counter;
    }

    public int[] minus(int[] counter, String s)
    {
        for(int i=0;i<s.length();i++)
        {
            counter[s.charAt(i)-'a']--;

        }
        return counter;
    }


    public boolean notconflict(int[] counter, String s)
    {
        for(int i=0;i<s.length();i++)
        {
            if(counter[s.charAt(i)-'a']!=0)
                return false;
        }
        return true;
    }
    public boolean finished(int[] counter)
    {
        for(int i=0;i<counter.length;i++)
        {
            if(counter[i]==0)
                return false;
        }
        return true;
    }

    public boolean conflictself(int[] counter, String s)
    {
        for(int i=0;i<s.length();i++)
        {
            if(counter[s.charAt(i)-'a']!=0)
                return true;
            counter[s.charAt(i)-'a']++;
        }
        return false;
    }

    public String Num767reorganizeString(String S) {
        char[] ans = new char[S.length()];
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<S.length();i++)
        {
            map.put(S.charAt(i),map.getOrDefault(S.charAt(i),0)+1);
        }
        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character t1, Character t2) {
                return map.get(t2)-map.get(t1);
            }
        });
        for(char key:map.keySet())
        {
            pq.add(key);
        }
        int index = 0;
        while(pq.size()>=2)
        {
            char maxchar = pq.poll();
            char max2char = pq.poll();
            ans[index] = maxchar;
            index++;
            ans[index] = max2char;
            index++;
            int num1 = map.get(maxchar)-1;
            if(num1>0)
            {
                map.put(maxchar,num1);
                pq.add(maxchar);
            }
            int num2 = map.get(max2char)-1;
            if(num2>0)
            {
                map.put(max2char,num2);
                pq.add(max2char);
            }
        }
        if(pq.size()>0)
        {
            char a = pq.poll();
            if(map.get(a)>1)
                return "";
            else if(map.get(a)==1)
                ans[S.length()-1] = a;
        }

        return new String(ans);
    }

    public int Num28strStr(String haystack, String needle) {

    }

    public int firstUniqChar(String s) {
        int[] counter = new int[26];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (counter[s.charAt(i) - 'a'] != 0) {
                counter[s.charAt(i) - 'a'] = -1;
            } else
                counter[s.charAt(i) - 'a'] = i + 1;
        }
        for(int i=0;i<26;i++)
        {
            if(counter[i]!=-1&&counter[i]!=0)
                min = Math.min(min,counter[i]);
        }
        return min==Integer.MAX_VALUE?-1:min;
    }

    public boolean Num383canConstruct(String ransomNote, String magazine) {
        int[] counter1 = new int[26];
        int[] counter2 = new int[26];
        for(int i=0;i<magazine.length();i++)
        {
            counter1[magazine.charAt(i)-'a']++;
        }
        for(int i=0;i<ransomNote.length();i++)
        {
            counter2[ransomNote.charAt(i)-'a']++;
            if(counter2[ransomNote.charAt(i)-'a']>counter1[ransomNote.charAt(i)-'a'])
                return false;
        }
        for(int i=0;i<26;i++)
        {
            if(counter1[i]<counter2[i])
                return false;
        }
        return true;
    }

    public String Num345reverseVowels(String s) {
        char[] newstring = new char[s.length()];
        int i=0,j = s.length()-1;
        while(i<=j)
        {
            while(i<=j&&notvowel(s.charAt(i)))
            {
                newstring[i] = s.charAt(i);
                i++;
            }
            while(j>=i&&notvowel(s.charAt(j)))
            {
                newstring[j] = s.charAt(j);
                j--;
            }
            if(i>j)
                break;
            newstring[i] = s.charAt(j);
            newstring[j] = s.charAt(i);
            i++;
            j--;
        }
        return new String(newstring);

    }

    public boolean notvowel(char c)
    {
        if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='A'||c=='E'||c=='I'||c=='O'||c=='U')
            return false;
        return true;
    }

    public boolean Num392isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i=0,j=0,len = 0;
        while(i<s.length()&&j<t.length())
        {
            while(j<t.length()&&t.charAt(j)!=s.charAt(i))
                j++;
            if(j>=t.length())
                return false;
            i++;
            j++;
            len++;
        }
        return len==s.length()?true:false;
    }

    public String Num151reverseWords(String s) {
        String ans = "";
        if(s.length()==0)
            return ans;
        int index = s.length()-1;
        while(index>=0)
        {
            while(index>=0&&s.charAt(index)==' ')
                index--;
            if(index<0)
                break;
            int endindex = index;
            while(index>=0&&s.charAt(index)!=' ')
            {
                index--;
            }
            ans+=s.substring(index+1,endindex+1)+" ";
        }
        if(ans.equals(""))
            return ans;
        return ans.substring(0,ans.length());
    }

    public List<String> Num22generateParenthesis(int n) {
        List<String> ans = new LinkedList<>();
        Num22helper("(",ans,n,1,1);
        return ans;
    }

    public void Num22helper(String current, List<String> ans, int n, int left, int right)
    {
        if(current.length()==2*n)
        {
            ans.add(current);
            return;
        }
        if(left<n)
        {
            Num22helper(current+"(",ans,n,left+1,right);
        }
        if(right<left)
            Num22helper(current+")",ans,n,left,right+1);
    }

    public void Num186reverseWords(char[] s) {
        reverse(s);
        reversewords(s);
    }

    public void reverse(char[] s)
    {
        int left = 0, right = s.length-1;
        while(left<right)
        {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public void reversewords(char[] s)
    {
        int left = 0, right = 0;
        while(left<s.length)
        {
            right = left;
            while(right<s.length&&s[right]!=' ')
            {
                right++;
            }
            right--;
            int i = left,j = right+1;
            while(i<right)
            {
                char temp = s[i];
                s[i] = s[right];
                s[right] = temp;
                i++;
                right--;
            }
            left = j+1;
        }
    }

    public String Num5longestPalindrome(String s) {
        if(s==null||s.length()==0)
            return s;
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int i=0;i<len;i++)
        {
            dp[i][i] = 1;
        }
        int max = Integer.MIN_VALUE, start = 0,end = 0;
        for(int i=0;i<len-1;i++)
        {
            if(s.charAt(i)==s.charAt(i+1))
                dp[i][i+1] = 2;
        }
        for(int l = 2;l < len;l++)
        {
            for(int j = 0;j<len-l;j++)
            {
                int e = l+j;
                if(s.charAt(j)==s.charAt(e)&&dp[j+1][e-1]!=0)
                {
                    dp[j][e] = dp[j+1][e-1]+2;
                    if(dp[j][e]>max)
                    {
                        max = dp[j][e];
                        start = j;
                        end = e+1;
                    }
                }
            }
        }
        return s.substring(start,end);

    }

    public int Num1689minPartitions(String n) {
        int ans = 0;
        for(int i=0;i<n.length();i++)
        {
            int current = Integer.valueOf(n.substring(i,i+1));
            ans = Math.max(ans,current);
        }
        return ans;
    }

    public int Num621leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        // 最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }

        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }

    public int numWays(String s) {
        char[] sc = s.toCharArray();
        int[] ones = new int[Math.min(sc.length, 6000)];
        int onesCount = 0;
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] == '1') {
                ones[onesCount] = i;
                onesCount++;
            }
        }
        if (onesCount % 3 != 0)  return 0;

        if (onesCount == 0) {
            return (int)(((long)(s.length() - 2) *
                    (long)(s.length() - 1) / 2L) % 1000000007L);
        }
        int end1 = ones[onesCount / 3 - 1];
        int start2 = ones[onesCount / 3];
        int end2 = ones[onesCount * 2 / 3 - 1];
        int start3 = ones[onesCount * 2 / 3];
        return (int)((long)(start2 - end1) *
                (long)(start3 - end2) % 1000000007L);

    }

    public int Num1578minCost(String s, int[] cost) {
        if(s.length()<=1)
            return 0;
        int i=0;
        int ans = 0;
        while(i<s.length()-1)
        {
            if(s.charAt(i)==s.charAt(i+1))
            {
                int j = i+1;
                while(j<s.length()&&s.charAt(j)==s.charAt(i))
                {
                    j++;
                }
                int max = 0,sum=0;
                for(int k=i;k<j;k++)
                {
                    sum+=cost[k];
                    max = Math.max(max,cost[k]);
                }
                ans = ans+sum-max;
                i=j;
            }
            else
                i++;
        }
        return ans;
    }

    public List<String> Num722removeComments(String[] source) {
        List<String> ans = new LinkedList<>();
        boolean inblock = false;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<source.length;i++)
        {
            char[] linecode = source[i].toCharArray();
            int j=0;
            if(!inblock)
                sb = new StringBuilder();
            while(j<linecode.length)
            {
                if(!inblock&&j+1<linecode.length&&linecode[j]=='/'&&linecode[j+1]=='*')
                {
                    inblock = true;
                    j++;
                }
                else if(inblock&&j+1<linecode.length&&linecode[j]=='*'&&linecode[j+1]=='/')
                {
                    inblock = false;
                    j++;
                }
                else if(!inblock&&j+1<linecode.length&&linecode[j]=='/'&&linecode[j+1]=='/')
                {
                    break;
                }
                else if(!inblock)
                {
                    sb.append(linecode[j]);
                }
                j++;
            }
            if(!inblock&&sb.length()>0)
            {
                ans.add(new String(sb));
            }
        }
        return ans;
    }

    public String Num468validIPAddress(String IP) {
        boolean ipv4 = IP.contains(".");
        if(ipv4)
        {
            String[] each_part = IP.split(".");
            if(each_part.length!=4)
                return "Neither";
            for(int i=0;i<each_part.length;i++)
            {
                if(each_part[i].length()==0||each_part[i].length()>3)
                    return "Neither";
                int num = Integer.valueOf(each_part[0]);
                if(num<0||num>255)
                    return "Neither";
                if(i!=0&&each_part[i].length()!=1&&each_part[i].charAt(0)=='0')
                    return "Neither";
                for(char ch:each_part[i].toCharArray())
                {
                    if(!Character.isDigit(ch))
                        return "Neither";
                }
            }
            return "IPv4";
        }else
        {
            String[] each_part = IP.split(":");
            String hexdigits = "0123456789abcdefABCDEF";
            if(each_part.length!=8)
                return "Neither";
            for(int i=0;i<each_part.length;i++)
            {
                if(each_part[i].length()<1||each_part[i].length()>4)
                    return "Neither";
                for (Character ch : each_part[i].toCharArray()) {
                    if (hexdigits.indexOf(ch) == -1) return "Neither";
                }
            }
                return "IPv6";
        }

    }

    public String Num1058minimizeError(String[] prices, int target) {
        int n = prices.length;
        double[] ceiling = new double[n];
        double[] flooring = new double[n];
        int floor_sum=0,ceil_sum = 0;
        for(int i=0;i<prices.length;i++)
        {
            double val = Double.valueOf(prices[i]);
            double floor_val = Math.floor(val);
            double ceil_val = Math.ceil(val);
            floor_sum+=floor_val;
            ceil_sum += ceil_val;
            ceiling[i] = (int)ceil_val-val;
            flooring[i] = val - (int)floor_val;
        }
        if(target<floor_sum||target>ceil_sum)
            return "-1";
        Arrays.sort(ceiling);
        int diff = ceil_sum-target;
        double ans = 0;
        for(int i=0;i<n;i++)
        {
            if(i<diff)
            {
                double dif = 1-ceiling[n-1-i];
                ans+=dif;
            }
            else
            {
                ans+=ceiling[n-1-i];
            }
        }
        return String.valueOf(ans);
    }
























































    public int Num1684countConsistentStrings(String allowed, String[] words) {

    }




































}
