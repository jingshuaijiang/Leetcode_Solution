package com.company;

import java.util.*;
public class MovingAverage {
    List<Integer> queue;
    int size;
    public MovingAverage(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    public double next(int val) {
        queue.add(val);
        if(queue.size()<this.size)
        {
            int sum = 0;
            for(int i=0;i<queue.size();i++)
            {
                sum+=queue.get(i);
            }
            return sum*1.0/queue.size();
        }
        else
        {
            int sum = 0;
            for(int i=queue.size()-1;i>queue.size()-1-size;i--)
            {
                sum+=queue.get(i);
            }
            return sum*1.0/size;
        }
    }
}
