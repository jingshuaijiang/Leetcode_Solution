package com.company;

import java.util.*;
public class MovingAverage {
    List queue = new ArrayList<Integer>();
    int size;
    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        queue.add(val);
        int sum = 0;
        for(int i = Math.max(0,queue.size()-size);i<queue.size();++i)
        {
            sum+=(int)queue.get(i);
        }
        return sum *1.0 /Math.min(queue.size(),size);
    }
}
