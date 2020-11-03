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

    int Num1254nums = 0;
    int Num1254bound = -1;
    public int Num1254closedIsland(int[][] grid) {
        for(int i=0;i< grid.length;i++)
        {
            for(int j = 0;j<grid[0].length;j++)
            {
                if(grid[i][j]==0)
                {
                    Num1254helper(grid,i,j,2);
                    if(Num1254bound==-1)
                        Num1254nums++;
                    Num1254bound = -1;
                }
            }
        }
        return Num1254nums;

    }

    public void Num1254helper(int[][] grid,int i, int j,int fullfilnum)
    {
        int n = grid.length;
        int m = grid[0].length;
        if(0<=i&&i<n&&0<=j&&j<m)
        {
            if(grid[i][j]==0)
            {
                if(i==0||i==n-1 ||j==0 ||j==m-1)
                    Num1254bound = -2;
                grid[i][j] = fullfilnum;
                Num1254helper(grid,i+1,j,fullfilnum);
                Num1254helper(grid,i-1,j,fullfilnum);
                Num1254helper(grid,i,j-1,fullfilnum);
                Num1254helper(grid,i,j+1,fullfilnum);
            }
        }
    }

    public void Num130solve(char[][] board) {
        if(board.length==0||board[0].length==0)
            return;
        int n = board.length;
        int m = board[0].length;
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j< board[0].length;j++)
            {
                if(i==0||i==n-1||j==0||j==m-1)
                {
                    if(board[i][j]=='O')
                    {
                        Num130helper(board,i,j,n,m);
                    }
                }
            }
        }
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j< board[0].length;j++) {
                if(board[i][j]=='O')
                    board[i][j]='X';
            }
        }

        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j< board[0].length;j++) {
                if(board[i][j]=='1')
                    board[i][j]='O';
            }
        }

    }

    public void Num130helper(char[][] board,int i, int j,int n,int m)
    {
        if(i<0||i>=n||j<0||j>=m)
            return;
        if(board[i][j]=='O')
        {
            board[i][j] = '1';
            Num130helper(board,i+1,j,n,m);
            Num130helper(board,i-1,j,n,m);
            Num130helper(board,i,j+1,n,m);
            Num130helper(board,i,j-1,n,m);
        }
        return;
    }

//    public void Num286wallsAndGates(int[][] rooms) {
//        if(rooms.length==0||rooms[0].length==0)
//            return ;
//        int n = rooms.length;
//        int m = rooms[0].length;
//        for(int i=0;i<n;i++)
//        {
//            for(int j=0;j<m;j++)
//            {
//                if(rooms[i][j]==2147483647)
//                {
//                    Num286helper(rooms,i,j);
//                }
//            }
//        }
//
//    }

//    public int Num286helper(int[][] rooms,int i, int j)
//    {
//        if(i<0||i>rooms.length-1||j<0||j>rooms.length-1)
//        {
//            return -1;
//        }
//        if(rooms[i][j]==-1)
//            return -1;
//        if(rooms[i][j]==0)
//            return 0;
//        else if(rooms[i][j]==2147483647)
//        {
//            rooms[i][j] = -2;
//            int up = Num286helper(rooms,i-1,j);
//            int down = Num286helper(rooms,i+1,j);
//            int left = Num286helper(rooms,i,j-1);
//            int right = Num286helper(rooms,i,j+1);
//            int finval = Integer.MAX_VALUE;
//            finval = up ==-1? finval:Math.min(finval,up+1);
//            finval = down==-1? finval:Math.min(finval,down+1);
//            finval = left == -1?finval:Math.min(finval,left+1);
//            finval = right == -1? finval:Math.min(finval,right+1);
//            rooms[i][j] = finval;
//            return finval;
//        }
//        return -1;
//    }


    public int Num127ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int step = 0;
        while(!queue.isEmpty())
        {
            int number = queue.size();
            step+=1;
            for(int i=0;i<number;i++)
            {
                String popedword = queue.poll();
                if(popedword.equals(endWord))
                    return step;
                for(int j=0;j<wordList.size();)
                {
                    if(Num127helper(popedword,wordList.get(j)))
                    {
                        queue.add(wordList.get(j));
                        wordList.remove(j);
                    }
                    else
                    {
                        j++;
                    }
                }
            }
        }
        return 0;
    }

    public boolean Num127helper(String word1,String word2)
    {
        int diff= 0 ;
        for(int i=0;i<word1.length();i++)
        {
            if(word1.charAt(i)!=word2.charAt(i))
                diff++;
        }
        return diff==1;
    }


    public List<List<String>> Num51solveNQueens(int n) {
        List<List<String>> ans = new LinkedList<>();
        if(n==0)
            return ans;
        int[] poscounter = new int[n];
        for(int i=0;i<n;i++)
        {
            poscounter[0] = i;
            Num51helper(n,poscounter,ans,1);
        }
        return ans;
    }

    public void Num51helper(int n, int[] poscounter, List<List<String>> ans,int depth)
    {
        if(depth==n)
        {
            List<String> solstr = new LinkedList<>();
            for(int i=0;i<n;i++)
            {
                String curline = "";
                for(int j=0;j<n;j++)
                {
                    if(poscounter[i]==j)
                    {
                        curline+="Q";
                    }
                    else
                        curline+=".";
                }
                solstr.add(curline);
            }
            ans.add(solstr);
            return;
        }
        int[] curpos = new int[n];
        for(int i=0;i<depth;i++)
        {
            int colnum = poscounter[i];
            curpos[colnum] = 1;
            int k = depth-i;

            if(colnum-k>=0)
                curpos[colnum-k] = 1;
            if(colnum+k<n)
                curpos[colnum+k] = 1;
        }

        for(int i=0;i<n;i++)
        {
            if(curpos[i]==0)
            {
                poscounter[depth] = i;
                Num51helper(n,poscounter,ans,depth+1);
                poscounter[depth] = 0;
            }
        }
    }



    public List<List<String>> Num126findLadders(String beginWord, String endWord, List<String> wordList) {

    }



    public void Num289gameOfLife(int[][] board) {

    }

    public int Num959regionsBySlashes(String[] grid) {

    }

    public int Num980uniquePathsIII(int[][] grid) {

    }































}
