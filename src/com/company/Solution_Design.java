package com.company;
import java.util.*;
import java.lang.Object;
import javafx.util.Pair;

public class Solution_Design {

    class Logger {
        HashMap<String,Integer> map;
        /** Initialize your data structure here. */
        public Logger() {
            map = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            if(map.get(message)==null||timestamp-map.get(message)>=10)
            {
                map.put(message,timestamp);
                return true;
            }
            else
                return false;
        }
    }

    class MovingAverage {
        int size;
        List<Integer> queue = new LinkedList<>();
        int sum = 0;
        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            this.size = size;
        }

        public double next(int val) {
            queue.add(val);
            if(queue.size()>size)
            {
                int head = queue.remove(0);
                sum-=head;
            }
            sum+=val;
            return (double)sum*1.0/queue.size();
        }
    }

    class HitCounter {

        int total_hits;
        Deque<Pair<Integer,Integer>> deque;
        /** Initialize your data structure here. */
        public HitCounter() {
            total_hits = 0;
            deque = new LinkedList<Pair<Integer,Integer>>();
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            if(deque.size()==0||deque.getLast().getKey()!=timestamp)
            {
                this.deque.add(new Pair<Integer,Integer>(timestamp,1));
            }
            else
            {
                int prevcount = deque.getLast().getValue();
                deque.removeLast();
                this.deque.add(new Pair<Integer,Integer>(timestamp,prevcount+1));
            }
            total_hits++;
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            while(!deque.isEmpty())
            {
                int diff = timestamp-deque.getFirst().getKey();
                if(diff>=300)
                {
                    this.total_hits-=this.deque.getFirst().getValue();
                    this.deque.removeFirst();
                }
                else
                    break;
            }
            return total_hits;
        }
    }

    public class ZigzagIterator {
        int total_num;
        int output_num;
        Deque<List<Integer>> vectors = new LinkedList<>();
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            if(v1.size()!=0)
                vectors.add(v1);
            if(v2.size()!=0)
                vectors.add(v2);
            total_num+=v1.size();
            total_num+=v2.size();
        }

        public int next() {
            if(vectors.size()==0)
                return 0;
            List<Integer> head = vectors.poll();
            output_num++;
            int cur = head.get(0);
            if(head.size()>1)
            {
                head.remove(0);
                vectors.add(head);
            }
            return cur;
        }

        public boolean hasNext() {
            return output_num<total_num;
        }
    }

    class PeekingIterator implements Iterator<Integer> {
        Iterator<Integer> iterator;
        Integer peeked_value = null;
        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;

        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if(peeked_value==null)
            {
                if(!iterator.hasNext())
                {
                    throw new NoSuchElementException();
                }
                peeked_value = iterator.next();
            }
            return peeked_value;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if(peeked_value!=null)
            {
                Integer toreturn = peeked_value;
                peeked_value = null;
                return toreturn;
            }
            if(!iterator.hasNext())
            {
                throw new NoSuchElementException();
            }
            return iterator.next();

        }

        @Override
        public boolean hasNext() {
            return peeked_value!=null||iterator.hasNext();
        }
    }

    class TwoSum {

        /** Initialize your data structure here. */
        public TwoSum() {

        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {

        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {

        }
    }

    class TicTacToe {
        int[] rows;
        int[] cols;
        int[] diagonal;
        int n;
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            rows = new int[n];
            cols = new int[n];
            this.n = n;
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            if(player==1)
            {
                rows[row]++;
                cols[col]++;
                if(row==col)
                {
                    diagonal[0]++;
                }
                if(row+col==n-1)
                {
                    diagonal[1]++;
                }
            }
            else
            {
                rows[row]--;
                cols[col]--;
                if(row==col)
                {
                    diagonal[0]--;
                }
                if(row+col==n-1)
                {
                    diagonal[1]--;
                }
            }
            int ans = 0;
            for(int i=0;i<n;i++)
            {
                if(rows[i]==-n||cols[i]==-n)
                {
                    ans = 2;
                    break;
                }
                if(rows[i]==n||cols[i]==n)
                {
                    ans = 1;
                    break;
                }
            }
            for(int i=0;i<2;i++)
            {
                if(diagonal[i]==-n)
                {
                    ans = 2;
                    break;
                }
                if(diagonal[i]==n)
                {
                    ans = 1;
                    break;
                }
            }
            return ans;
        }
    }

    class PhoneDirectory {

        /** Initialize your data structure here
         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
        public PhoneDirectory(int maxNumbers) {

        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        public int get() {

        }

        /** Check if a number is available or not. */
        public boolean check(int number) {

        }

        /** Recycle or release a number. */
        public void release(int number) {

        }
    }

    class Twitter {
        private class tweet {
            /**
             * 推文 id
             */
            private int id;

            /**
             * 发推文的时间戳
             */
            private int timestamp;
            private tweet next;

            public tweet(int id, int timestamp) {
                this.id = id;
                this.timestamp = timestamp;
            }
        }
        HashMap<Integer,HashSet<Integer>> following;
        HashMap<Integer,tweet> twitter;
        PriorityQueue<tweet> queue;
        int timestamp;

        /** Initialize your data structure here. */
        public Twitter() {
            this.following = new HashMap<>();
            this.twitter = new HashMap<Integer, tweet>();
            queue = new PriorityQueue<>(new Comparator<tweet>() {
                @Override
                public int compare(tweet t1, tweet t2) {
                    return t2.timestamp-t1.timestamp;
                }
            });
            timestamp = 0;
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            timestamp++;
            if(twitter.containsKey(userId))
            {
                tweet newhead = new tweet(tweetId,timestamp);
                tweet oldhead = twitter.get(userId);
                newhead.next = oldhead;
                twitter.put(userId,newhead);
            }
            else
            {
                twitter.put(userId,new tweet(tweetId,timestamp));
            }
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            queue.clear();
            if(twitter.containsKey(userId))
            {
                queue.offer(twitter.get(userId));
            }
            HashSet<Integer> followinglist = following.get(userId);
            if (followinglist != null && followinglist.size() > 0) {
                for (Integer followingId : followinglist) {
                    tweet tweet = twitter.get(followingId);
                    if (tweet != null) {
                        queue.offer(tweet);
                    }
                }
            }
            List<Integer> ans = new LinkedList<>();
            int count = 10;
            while(!queue.isEmpty()&&count<10)
            {
                tweet head = queue.poll();
                ans.add(head.id);
                if(head.next!=null)
                {
                    queue.offer(head.next);
                }
                count++;
            }
            return ans;

        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if(followeeId==followerId)
                return;
            HashSet<Integer> follow_list = following.get(followerId);
            if(follow_list==null)
            {
                HashSet<Integer> new_follow_list = new HashSet<>();
                new_follow_list.add(followeeId);
                following.put(followerId,new_follow_list);
            }
            else{
                if(follow_list.contains(followeeId))
                    return;
                follow_list.add(followeeId);
            }
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if(followeeId==followerId)
                return;
            HashSet<Integer> follow_list = following.get(followerId);
            if(follow_list==null)
            {
                return;
            }
            follow_list.remove(followeeId);
        }
    }

    class easyNumArray {

        int[] arrays;

        public NumArray(int[] nums) {
            int n = nums.length;
            arrays = new int[n+1];
            for(int i=0;i<nums.length;i++)
            {
                arrays[i+1] = arrays[i]+nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return arrays[j+1]-arrays[i];
        }
    }

    class easyNumMatrix {

        int question_num = 304;
        int[][] matrixs;

        public NumMatrix(int[][] matrix) {
            int n = matrix.length;
            if(n==0)
                return;
            int m = matrix[0].length;
            this.matrixs = new int[n+1][m+1];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    matrixs[i+1][j+1] = matrixs[i][j+1]+matrixs[i+1][j]-matrixs[i][j]+matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return matrixs[row2+1][col2+1]+matrixs[row1][col1]-matrixs[row2+1][col1]-matrixs[row1][col2+1];
        }
    }

    class NumArray {

        int question_num = 307;
        int[] tree;
        int n;
        public NumArray(int[] nums) {
            n = nums.length;
            tree = new int[n*2];
            for(int i=0;i<n;i++)
            {
                tree[i+n] = nums[i];
            }
            for(int i=n-1;i>=0;i--)
            {
                tree[i] = tree[2*i]+tree[2*i+1];
            }
        }

        public void update(int index, int val) {
            int curr_index = index+n;
            int diff = tree[curr_index]-val;
            while(curr_index>=0)
            {
                tree[curr_index]-=diff;
                curr_index/=2;
            }
        }

        public int sumRange(int left, int right) {
            int sum = 0;
            int l = left+n;
            int r = right+n;
            while(l<=r)
            {
                if(l%2==1)
                {
                    sum+=tree[l];
                    l++;
                }
                if(r%2==0)
                {
                    sum+=tree[r];
                    r--;
                }
                r/=2;
                l/=2;
            }
            return sum;
        }
    }


    class NumMatrix {

        int rows;
        int cols;
        int[][] bit;

        private int lsb(int n)
        {
            return n&(-n);
        }

        private void UpdateBIT(int r, int c, int val)
        {
            for(int i=r;i<=rows;i+=lsb(i))
            {
                for(int j=c;j<=cols;j+=lsb(j))
                {
                    this.bit[i][j] +=val;
                }
            }
        }

        private int queryBIT(int r, int c) {
            int sum = 0;
            // keep subtracting lsb(i) to i, lsb(j) to j and obtain the final sum as the sum of non-overlapping sub-rectangles
            // Using two nested for loops, one for the rows and one for the columns
            for (int i = r; i > 0; i -= lsb(i)) {
                for (int j = c; j > 0; j -= lsb(j)) {
                    sum += this.bit[i][j];
                }
            }
            return sum;
        }

        private void buildBIT(int[][] matrix) {
            for (int i = 1; i <= rows; ++i) {
                for (int j = 1; j <= cols; ++j) {
                    // call update function on each of the entries present in the matrix
                    int val = matrix[i - 1][j - 1];
                    UpdateBIT(i, j, val);
                }
            }
        }


        public NumMatrix(int[][] matrix) {
            rows = matrix.length;
            if (rows == 0) return;
            cols = matrix[0].length;
            bit = new int[rows + 1][];
            // Using 1 based indexing, hence resizing the bit array to (rows + 1, cols + 1)
            for (int i = 1; i <= rows; ++i)
                bit[i] = new int[cols + 1];
            buildBIT(matrix);
        }

        public void update(int row, int col, int val) {
            int old_val = sumRegion(row, col, row, col);
            // handling 1-based indexing
            row++; col++;
            int diff = val - old_val;
            UpdateBIT(row, col, diff);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            row1++; col1++; row2++; col2++;
            int a = queryBIT(row2, col2);
            int b = queryBIT(row1 - 1, col1 - 1);
            int c = queryBIT(row2, col1 - 1);
            int d = queryBIT(row1 - 1, col2);
            return (a + b) - (c + d);
        }
    }

    class BrowserHistory {

        public BrowserHistory(String homepage) {

        }

        public void visit(String url) {

        }

        public String back(int steps) {

        }

        public String forward(int steps) {

        }
    }














































}
