package com.company;
import java.util.*;

public class Solution_String {

    public String Num394decodeString(String s) {
        int len = s.length();
        if(len==0)
            return "";

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==']')
            {
                List<Character> str = new LinkedList<>();
                while(stack.peek()!='[')
                    str.add(stack.pop());
                stack.pop();
                int times = 1;
                int number = 0;
                while(!stack.isEmpty()&&Character.isDigit(stack.peek()))
                {
                    number = number+(stack.pop()-'0')*times;
                    times*=10;
                }
                while(number!=0)
                {
                    for(int j = str.size() - 1; j >= 0; j--)
                    {
                        stack.push(str.get(j));
                    }
                    number--;
                }
            }
            else
                stack.push(s.charAt(i));

        }
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);

    }


    public boolean Num1041isRobotBounded(String instructions) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0;
        int idx = 0;
        for (char i : instructions.toCharArray()) {
            if (i == 'L')
                idx = (idx + 3) % 4;
            else if (i == 'R')
                idx = (idx + 1) % 4;
            else {
                x += directions[idx][0];
                y += directions[idx][1];
            }
        }
        return (x == 0 && y == 0) || (idx != 0);
    }

    public List<Integer> Num438findAnagrams(String s, String p) {
        int n = p.length();
        List<Integer> ans = new LinkedList<>();
        int[] counter = new int[26];
        int[] counter1 = new int[26];
        for(int i=0;i<p.length();i++)
        {
            counter[p.charAt(i)-'a']++;
        }
        if(s.length()<p.length())
            return ans;
        for(int i=0;i<n;i++)
        {
            counter1[s.charAt(i)-'a']++;
        }
        if(Num438same(counter,counter1))
            ans.add(0);
        for(int i=n;i<s.length();i++)
        {
            counter1[s.charAt(i-n)-'a']--;
            counter1[s.charAt(i)-'a']++;
            if(Num438same(counter,counter1))
                ans.add(i-n+1);
        }
        return ans;

    }

    public boolean Num438same(int[] counter, int[] counter1)
    {
        for(int i=0;i<counter.length;i++)
        {
            if(counter[i]!=counter1[i])
                return false;
        }
        return true;
    }

    public boolean Num567checkInclusion(String s1, String s2) {
        int n = s1.length();
        int[] counter = new int[26];
        int[] counter1 = new int[26];
        for(int i=0;i<s1.length();i++)
        {
            counter[s1.charAt(i)-'a']++;
        }
        if(s2.length()<s1.length())
            return false;
        for(int i=0;i<n;i++)
        {
            counter1[s2.charAt(i)-'a']++;
        }
        if(Num567same(counter,counter1))
            return true;
        for(int i=n;i<s2.length();i++)
        {
            counter1[s2.charAt(i-n)-'a']--;
            counter1[s2.charAt(i)-'a']++;
            if(Num567same(counter,counter1))
                return true;
        }
        return false;
    }

    public boolean Num567same(int[] counter, int[] counter1)
    {
        for(int i=0;i<counter.length;i++)
        {
            if(counter[i]!=counter1[i])
                return false;
        }
        return true;
    }

    public int Num1239maxLength(List<String> arr) {
        
    }



































}
