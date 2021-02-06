package com.company;
import java.util.*;
import java.math.BigInteger;


public class Solution_Math {

    public boolean isUgly(int num) {
        while (num>0){
            if(num == 1||num == 2 || num == 3 || num == 5){
                return true;
            }else if (num%2 == 0){
                num/=2;
            }else if(num%3 == 0){
                num/=3;
            }else if(num%5 == 0){
                num/=5;
            }else{
                return false;
            }
        }

        return false;
    }

    public double Num1344angleClock(int hour, int minutes) {

        double minhand = (minutes - 0.0) / 60 * 360;
        double hourhand = (hour * 30.0 + minutes / 60.0 * 30) % 360;
        return Math.min(Math.abs(minhand - hourhand), 360 - Math.abs(minhand - hourhand));
    }

    public int Num829consecutiveNumbersSum(int N) {
        int res = 1;
        for (int i = 2; i < Math.sqrt(2 * N); ++i) {
            if ((N - i * (i - 1) / 2) % i == 0) ++res;
        }
        return res;
    }

    public int Num258addDigits(int num) {
        if(num==0)
            return 0;
        if(num%9==0)
            return 9;
        return num%9;
    }

    public String Num67addBinary(String a, String b) {
         BigInteger x = new BigInteger(a,2);
         BigInteger y = new BigInteger(b,2);
         BigInteger zero = new BigInteger("0",2);
         BigInteger ans,carry;
         while(y.compareTo(zero)!=0)
         {
             ans = x.xor(y);
             carry = x.and(y).shiftLeft(1);
             x = ans;
             y = carry;
         }
         return x.toString(2);
    }

    public int Num7reverse(int x) {
        int ans = 0;
        while(x!=0)
        {
            int left = x%10;
            x/=10;
            if(ans>Integer.MAX_VALUE/10||(ans==Integer.MAX_VALUE/10&&left>7))
                return 0;
            if(ans<Integer.MIN_VALUE/10||(ans==Integer.MIN_VALUE/10&&left<-8))
                return 0;
            ans = ans*10+left;
        }
        return ans;
    }

    public int compareVersion(String version1, String version2) {
        int n = version1.length(), m = version2.length();

        int i = 0;
        int j = 0;

        while (i < n || j < m) {
            // 用v1,v2来计算每一个块中版本号的大小
            int v1 = 0;
            int v2 = 0;

            // 若当前的字符不是分隔符，则计算
            while (i < n && version1.charAt(i) != '.') {
                v1 = v1 * 10 + version1.charAt(i) - '0';
                i++;
            }

            while (j < m && version2.charAt(j) != '.') {
                v2 = v2 * 10 + version2.charAt(j) - '0';
                j++;
            }
            // 判断当前块中的版本号是否一致
            if (v1 != v2) {
                if (v1 > v2) {
                    return 1;
                }
                return -1;
            }
            // 跳过分隔符
            i++;
            j++;
        }

        // 全部比较完了，没有不等的则返回0
        return 0;
    }

    public int Num8myAtoi(String s) {
        int ans = 0;
        int index = 0;
        boolean neg = false;
        int n = s.length();
        while(index<n&&s.charAt(index)==' ')
            index++;
        if(index==n)
            return ans;
        if(s.charAt(index)=='-')
        {
            neg = true;
            index++;
        }
        else if(s.charAt(index)=='+')
        {
            index++;
        }
        while(index<n&&Character.isDigit(s.charAt(index)))
        {
            int current = s.charAt(index)-'0';
            if(neg)
            {
                if(ans>Integer.MAX_VALUE/10||(ans==Integer.MAX_VALUE/10&&current>8))
                    return Integer.MIN_VALUE;
                else
                    ans = ans*10+current;
            }
            else
            {
                if(ans>Integer.MAX_VALUE/10||(ans==Integer.MAX_VALUE/10&&current>7))
                    return Integer.MAX_VALUE;
                else
                    ans = ans*10+current;
            }
            index++;
        }
        return neg?-ans:ans;

    }

    public int Num204countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime,true);
        for(int i=2;i*i<n;i++)
        {
            if(!isPrime[i])
                continue;
            for(int j = i*i;j<n;j++)
            {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for(int i=2;i<n;i++)
        {
            if(isPrime[i])
                count++;
        }
        return count;
    }

    public int Num69mySqrt(int x) {
        if(x<2)
            return x;
        long num;
        int mid,left = 2,right = x/2;
        while(left<=right)
        {
            mid = left + (right-left)/2;
            num = (long)mid * mid;
            if(num>x)
                right = mid - 1;
            else if(num<x)
                left = mid + 1;
            else
                return mid;
        }
        return right;
    }

    public double Num50myPow(double x, int n) {
        if(n==0)
            return 1;
        double half = Num50myPow(x,n/2);
        if(n%2==0)
            return half*half;
        else
            return half*half*x;
    }

    public String Num43multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0"))
            return "0";
        int n = num1.length();
        int m = num2.length();
        int[] sum = new int[m+n+1];

        for(int i=m-1;i>=0;i--)
        {
            int multi_carrieer = 0;
            int start = n+1+i;
            int add_carrier = 0;
            int single_mul = num2.charAt(i)-'0';
            for(int j=n-1;j>=0;j--)
            {
                int num1_num = num1.charAt(j)-'0';
                int digit = 0;
                int p = single_mul*num1_num+multi_carrieer;
                if(p>=10)
                {
                    multi_carrieer = p/10;
                    digit = p%10;
                }
                else
                {
                    digit = p;
                    multi_carrieer = 0;
                }
                int sum_current = sum[start]+digit+add_carrier;
                if(sum_current>=10)
                {
                    sum[start] = sum_current%10;
                    add_carrier = sum_current/10;
                }
                else
                {
                    sum[start] = sum_current;
                    add_carrier = 0;
                }
                start--;
            }

            while(add_carrier!=0||multi_carrieer!=0)
            {
                int cur = add_carrier+multi_carrieer+sum[start];
                if(cur>=10)
                {
                    add_carrier = cur/=10;
                    sum[start] = cur%10;
                    start--;
                }
                else
                {
                    sum[start] = cur;
                    add_carrier=0;
                    multi_carrieer=0;
                    break;
                }
            }

        }
        int index = 0;
        while(sum[index]==0)
            index++;
        StringBuilder sb = new StringBuilder();
        for(int i=index;i<=m+n;i++)
        {
            sb.append(sum[i]);

        }
        return sb.toString();
    }

    public int Num29divide(int dividend, int divisor) {
        if(dividend==Integer.MIN_VALUE&&divisor==-1)
            return Integer.MAX_VALUE;
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        int res = 0;
        while(a-b>0)
        {
            int x = 0;
            while((a-(b<<1<<x)>=0))
                x++;
            res+=1<<x;
            a-=b<<x;
        }
        return res;
    }

    public boolean Num365canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        return z % gcd(x, y) == 0;
    }

    public int gcd(int x, int y) {
        int remainder = x % y;
        while (remainder != 0) {
            x = y;
            y = remainder;
            remainder = x % y;
        }
        return y;
    }

    public List<List<Integer>> Num18fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> current = new LinkedList<>();
        Arrays.sort(nums);
        Num18ksumhelper(nums,target,0,4,ans,current);
        return ans;
    }

    public void Num18ksumhelper(int[] nums, int target, int start, int k, List<List<Integer>> ans, List<Integer> current)
    {
        if(start==nums.length&&k!=0)
            return;
        if(k==2)
        {
            Num18twosum(nums,target,start,ans,current);
            return;
        }
        for(int i=start;i<nums.length;i++)
        {
            if(i==start||nums[i]!=nums[i-1])
            {
                current.add(nums[i]);
                Num18ksumhelper(nums,target-nums[i],i+1,k-1,ans,current);
                current.remove(current.size()-1);
            }
        }
    }

    public void Num18twosum(int[] nums, int target, int start, List<List<Integer>> ans, List<Integer> current)
    {
        if(start>=nums.length-1)
            return;
        int left = start,right = nums.length-1;
        while(left<=right)
        {
            if(nums[left]+nums[right]==target)
            {
                current.add(nums[left]);
                current.add(nums[right]);
                ans.add(new LinkedList<>(current));
                current.remove(current.size()-1);
                current.remove(current.size()-1);
            }
            else if(nums[left]+nums[right]>target)
            {
                right--;
            }
            else {
                left++;
            }
        }
        return;
    }







































}
