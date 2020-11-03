package com.company;
import java.util.*;
public class HitCounter {

    Queue<Integer> hits;
    final int SECONDS = 300;
    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new LinkedList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        hits.offer(timestamp);
        while(!hits.isEmpty() && (timestamp - hits.peek()) >= 300) hits.poll();
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!hits.isEmpty() && (timestamp - hits.peek()) >= 300) hits.poll();
        return hits.size();
    }


}
