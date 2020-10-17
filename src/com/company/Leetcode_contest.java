package com.company;
import java.util.*;

public class Leetcode_contest {

    public int minOperations(String[] logs) {
        int ans = 0;
        for(int i=0;i<logs.length;i++)
        {
            switch (logs[i])
            {
                case("../"):
                    ans--;
                    break;
                case("./")   :
                    break;
                default:
                    ans++;
                    break;
            }
        }
        return ans;
    }
}
