package com.company;
import java.util.*;

public class Solution_easyplayground {

    public int Num1614maxDepth(String s) {
        int depth = 0;
        int maxdepth = 0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='(')
            {
                depth++;
                maxdepth = Math.max(maxdepth,depth);
            }
            else if(s.charAt(i)==')')
                depth--;

        }
        return maxdepth;
    }
}

