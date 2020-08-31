package com.company;
import java.util.*;

public class Solution_DFS {

    public List<List<Integer>> Num39combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> nums = new LinkedList<>();
        Num39helper(candidates,target,ans,nums,0);
        return ans;
    }

    public void Num39helper(int[] arrays,int tar,List<List<Integer>> ans,List<Integer> nums,int startindex)
    {
        if(tar==0)
        {
            List<Integer> finalnumlist = new LinkedList<>(nums);
            ans.add(finalnumlist);
            return;
        }

        for(int i=startindex;i<arrays.length;i++)
        {
            if(tar-arrays[i]>=0)
            {
                nums.add(arrays[i]);
                Num39helper(arrays, tar-arrays[i], ans, nums,i);
                nums.remove(nums.size()-1);
            }
        }

    }

    public List<List<Integer>> Num77combine(int n, int k) {
        int[] counter = new int[n];
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> comb = new LinkedList<>();
        Num77helper(n,k,counter,ans,comb,1,0);
        return ans;
    }

    public void Num77helper(int n,int k,int[] counter,List<List<Integer>> ans,List<Integer> comb,int index,int startindex)
    {
        if(index>k)
        {
            List<Integer> addlist = new LinkedList<>(comb);
            ans.add(addlist);
            return;
        }
        for(int i=startindex;i<counter.length;i++)
        {
            if(counter[i]==0)
            {
                comb.add(i+1);
                counter[i]=1;
                Num77helper(n,k,counter,ans,comb,index+1,i);
                counter[i]=0;
                comb.remove(comb.size()-1);
            }
        }
    }

    public List<List<Integer>> Num254getFactors(int n) {

    }

    public List<String> Num17letterCombinations(String digits) {
        List<String> ans = new LinkedList<>();
        for(int i=0;i<digits.length();i++)
        {
            if(digits.charAt(i)>'9'||digits.charAt(i)<'2')
                return ans;
        }
        if(digits.length()<=0)
            return ans;
        HashMap<Character,String> digitmap = new HashMap<>();
        digitmap.put('2',"abc");
        digitmap.put('3',"def");
        digitmap.put('4',"ghi");
        digitmap.put('5',"jkl");
        digitmap.put('6',"mno");
        digitmap.put('7',"pqrs");
        digitmap.put('8',"tuv");
        digitmap.put('9',"wxyz");
        Num17helper(digits,digitmap,ans,0,"");
        return ans;
    }

    public void Num17helper(String digits,HashMap<Character,String> digitmap,List<String> ans,int index,String curstr)
    {
        if(index==digits.length())
        {
            ans.add(curstr);
            return;
        }
        String current = digitmap.get(digits.charAt(index));
        for(int i=0;i<current.length();i++)
        {
            curstr+=current.charAt(i);
            Num17helper(digits,digitmap,ans,index+1,curstr);
            curstr = curstr.substring(0,curstr.length()-1);
        }
    }

    public void Num289gameOfLife(int[][] board) {

    }

    public int Num959regionsBySlashes(String[] grid) {

    }
}
