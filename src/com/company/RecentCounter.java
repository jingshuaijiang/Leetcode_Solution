package com.company;
import java.util.*;

public class RecentCounter {
    LinkedList<Integer> llist;

    public RecentCounter() {
        llist = new LinkedList<>();
    }

    public int ping(int t) {
        llist.add(t);
        while(t-llist.peek() >3000)
            llist.remove();
        return llist.size();
    }
}
