package com.company;
import java.util.*;
public class Solution_pq {

    public int Num215findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t1-t2;
            }
        });
        for(int i=0;i<nums.length;i++)
        {
            pq.add(nums[i]);
            if(pq.size()>k)
                pq.poll();
        }
        return pq.poll();
    }

    public int Num414thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                if(t1== Integer.MIN_VALUE&&t2==Integer.MIN_VALUE)
                    return 0;
                if(t1==Integer.MIN_VALUE)
                    return -1;
                else if(t2==Integer.MIN_VALUE)
                    return 1;
                return t1-t2;
            }
        });
        int max = 0;
        for(int i=0;i<nums.length;i++)
        {
            if(!pq.contains(nums[i]))
                pq.add(nums[i]);
            max = Math.max(max,nums[i]);
            if(pq.size()>3)
                pq.poll();
        }

        if(pq.size()==3)
            return pq.poll();
        return max;

    }

    public int Num313nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int[] counter = new int[primes.length];
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0]-t2[0];
            }
        });
        for(int i=1;i<n;i++)
        {
            for(int j=0;j<counter.length;j++)
            {
                if(set.contains(dp[counter[j]]*primes[j]))
                    counter[j]++;
                pq.add(new int[]{dp[counter[j]]*primes[j],j});
            }
            int[] min = pq.poll();
            counter[min[1]]++;
            dp[i] = min[0];
            set.add(dp[i]);
            pq.clear();
        }
        return dp[n-1];
    }

    public List<List<Integer>> Num373kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0]+t1[1]-t2[0]-t2[1];
            }
        });
        List<List<Integer>> ans = new LinkedList<>();
        for(int num1:nums1)
        {
            for(int num2:nums2)
            {
                pq.add(new int[]{num1,num2});
            }
        }
        for(int i=0;i<k&&!pq.isEmpty();i++)
        {
            List<Integer> a = new LinkedList<>();
            int[] b = pq.poll();
            a.add(b[0]);
            a.add(b[1]);
            ans.add(a);
        }
        return ans;
    }


































}
