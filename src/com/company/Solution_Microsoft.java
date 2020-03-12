package com.company;

import java.util.*;

public class Solution_Microsoft {
    public int Num509fib(int N) {
        if(N==0) return 0;
        if(N==1) return 1;
        return Num509fib(N-1)+Num509fib(N-2);
    }

    public String Num1185dayOfTheWeek(int day, int month, int year) {
        if(month==1)
        {
            month =13;
            year--;
        }
        if(month ==2)
        {
            month =14;
            year --;
        }
        int week = (day+2*month+3*(month+1)/5+year+year/4-year/100+year/400)%7;
        switch (week)
        {
            case 0:
                return "Monday";


            case 1:
                return "Tuesday";

            case 2:
                return "Wednesday";

            case 3:
                return "Thursday";

            case 4:
                return "Friday";

            case 5:
                return "Saturday";

            case 6:
                return "Sunday";

        }
        return null;
    }
    public int Num136singleNumber(int[] nums) {
        for(int i=1;i<=nums.length-1;i++)
        {
            nums[0] = nums[0]^nums[i];
        }
        return nums[0];
    }
    public boolean Num266canPermutePalindrome(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++)
        {
            if(map.containsKey(s.charAt(i)))
            {
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }
            else
            {
                map.put(s.charAt(i),1);
            }
        }
        int count=0;
        for(Integer value: map.values())
        {
            if(value%2 ==1)
            {
                count++;
                if(count>=2) return false;
            }
        }
        return true;
    }
    public List<String> Num412fizzBuzz(int n) {
        List<String> ans = new LinkedList<>();
        for(int i=1;i<=n;i++)
        {
            String current = "";
            if(i%3==0) current+="Fizz";
            if(i%5==0) current+="Buzz";
            if(current=="") current = String.valueOf(i);
            ans.add(current);
        }
        return ans;
    }
    public ListNode Num21mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val >=l2.val) {
            l2.next = Num21mergeTwoLists(l1, l2.next);
            return l2;
        }
        else
        {
            l1.next = Num21mergeTwoLists(l1.next,l2);
            return l1;
        }
    }
    public boolean Num217containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int x: nums)
        {
            if(set.contains(x)) return true;
            set.add(x);
        }
        return false;
    }
    /*Do it again*/
    public ListNode Num206reverseList(ListNode head) {
        if(head==null || head.next==null)
        {
            return head;
        }
        ListNode p = Num206reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
    public ListNode Num206itreverseList(ListNode head) {
        ListNode current = head;
        ListNode pre = null;
        while(current!=null)
        {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;

        }
        return pre;
    }
    public boolean Num252canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0] - t1[0];
            }
        });
        for(int i=0;i<intervals.length-1;i++)
        {
            if(intervals[i][1] > intervals[i+1][0])
                return false;

        }
        return true;
    }
    public int[] Num1twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public int Num268missingNumber(int[] nums) {
        int counter = nums.length;
        for(int i=0;i<nums.length;i++)
        {
            counter = counter^ i^nums[i];
        }
        return counter;
    }
    public int Num121maxProfit(int[] prices) {
        if(prices.length==0) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int profit = Integer.MIN_VALUE;
        for(int i=0;i<prices.length;i++)
        {
            if(prices[i] < min)
            {
                min = prices[i];
                max  = Integer.MIN_VALUE;
            }
            if(prices[i] > max)
            {
                max = prices[i];
            }
            if(max-min > profit) profit = max-min;
        }
        return profit;
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1,j=n-1,step = m+n-1;
        while(i>=0 && j>=0)
        {
            nums1[i--] = (nums1[i] < nums2[j]) ? nums2[j--] : nums1[i--];
        }
        System.arraycopy(nums2,0,nums1,0,j+1);
    }
    public boolean Num141hasCycle(ListNode head) {
        if(head ==null || head.next==null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow!= fast)
        {
            if(fast==null || slow ==null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    /*code should be optimized*/
    public int Num443compress(char[] chars) {
        if(chars.length==0) return 0;
        int pointer = -1;
        int count = 1;
        char pre = chars[0];
        for(int i=1;i<chars.length;i++)
        {
            if(chars[i]!=pre)
            {
                pointer+=1;
                chars[pointer] = pre;
                if(count ==1)
                {

                }
                else
                {

                    String count1= String.valueOf(count);
                    for(int j=0;j<count1.length();j++)
                    {
                        pointer+=1;
                        chars[pointer] = count1.charAt(j);
                    }
                }
                count=1;
                pre = chars[i];
            }
            else
            {
                count++;
            }

        }
        pointer+=1;
        chars[pointer] = pre;
        if(count==1)
        {

        }else
        {
            String count1= String.valueOf(count);
            for(int j=0;j<count1.length();j++)
            {
                pointer+=1;
                chars[pointer] = count1.charAt(j);
            }
        }
        return pointer+1;


    }

}
