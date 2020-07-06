package com.company;
import java.util.*;

public class NumArray {
    int[] narray;
    public NumArray(int[] nums) {
        narray = nums;
        for(int i=1;i<nums.length;i++)
        {
            narray[i] += narray[i-1];
        }
    }

    public int sumRange(int i, int j) {
        if(i==0)
            return narray[j];
        else
            return narray[j]-narray[i-1];
    }
}
