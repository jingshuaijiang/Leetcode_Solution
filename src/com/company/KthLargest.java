package com.company;

import java.util.*;

/*code for Leetcode Num703*/

public class KthLargest {

    List list = new ArrayList<Integer>();
    int k;
    public KthLargest(int k, int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++)
        {
            list.add(0,nums[i]);
        }

        this.k = k;
    }

    public int add(int val) {
        int i=0;
        while(i<list.size())
        {
            if((int)list.get(i)> val)
            {
                i++;
                continue;
            }
            else
            {
                break;
            }

        }
        list.add(i,val);
        return (int)list.get(k-1);
    }
}
