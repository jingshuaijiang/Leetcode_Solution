package com.company;
import java.util.*;
public class Solution_greedy {

    public int[][] Num406reconstructQueue(int[][] people) {
        int[][] ans = new int[people.length][2];
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                if(t1[0]==t2[0])
                    return t1[1]-t2[1];
                else
                    return t2[0]-t1[0];
            }
        });
        List<int[]> output = new LinkedList<>();
        for(int[] p:people)
        {
            output.add(p[1],p);
        }
        return output.toArray(ans);
    }
}
