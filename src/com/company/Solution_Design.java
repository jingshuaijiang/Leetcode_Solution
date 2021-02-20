package com.company;
import java.util.*;
import java.lang.Object;
import javafx.util.Pair;
import org.w3c.dom.html.HTMLDOMImplementation;

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
        public class history
        {
            String url;
            private history next;
            private history before;

            public history(String url)
            {
                this.url = url;
            }
        }

        history head,current;

        public BrowserHistory(String homepage) {
            head = new history("This is the head");
            history homepage1 = new history(homepage);
            head.next = homepage1;
            homepage1.before = head;
            current = homepage1;
        }

        public void visit(String url) {
            if(current.next!=null)
            {
                current.next.before = null;
                current.next = null;
            }
            history newpage = new history(url);
            current.next = newpage;
            newpage.before = current;
            current = current.next;
        }

        public String back(int steps) {
            while(current.before!=null&&steps>=0)
            {
                current = current.before;
                steps--;
            }
            current = current.next;
            return current.url;
        }

        public String forward(int steps) {
            while(current.next!=null&&steps>0)
            {
                current = current.next;
                steps--;
            }
            return current.url;
        }
    }

    class UndergroundSystem {
        private HashMap<Integer,Pair<Integer,String>> checkin;
        private HashMap<String,Pair<Double,Double>> timetable;

        public UndergroundSystem() {
            checkin = new HashMap<Integer, Pair<Integer, String>>();
            timetable = new HashMap<String, Pair<Double, Double>>();

        }

        public void checkIn(int id, String stationName, int t) {
            checkin.put(id,new Pair<>(t,stationName));
        }

        public void checkOut(int id, String stationName, int t) {
            Pair<Integer,String> info = checkin.get(id);
            String startstation = info.getKey();
            int time = info.getValue();
            String key = startstation+"##"+stationName;
            if(timetable.containsKey(key))
            {
                Pair<Double, Double> currentones = timetable.get(key);

            }
            checkin.remove(id);
        }

        public double getAverageTime(String startStation, String endStation) {
            String key = startStation+"##"+endStation;
            Pair<Double, Double> currentones = timetable.get(key);
            return currentones.getKey()/currentones.getValue();
        }
    }

    class RandomizedSet {

        /** Initialize your data structure here. */
        HashMap<Integer,Integer> setmap;
        List<Integer> set;
        public RandomizedSet() {
            set = new ArrayList<>();
            setmap = new HashMap<Integer,Integer>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            int position = set.size();
            if(setmap.containsKey(val))
                return false;
            setmap.put(val,position-1);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!setmap.containsKey(val))
                return false;
            int index = setmap.get(val);
            int last = set.get(set.size()-1);
            set.set(index,last);
            set.remove(set.size()-1);
            setmap.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return set.get(rand.nextInt(set.size()));
        }
    }


    class SummaryRanges {

        private class Node {

            int left, right;
            Node next;

            public Node(int left, int right, Node next) {
                this.left = left;
                this.right = right;
                this.next = next;
            }
        }
        private TreeMap<Integer,Node> treeMap;
        private Node dummy;
        private int count;
        /** Initialize your data structure here. */
        public SummaryRanges() {
            this.treeMap = new TreeMap<>();
            dummy = new Node(-2,-2,null);
            treeMap.put(-2,dummy);
            count = 0;
        }

        public void addNum(int val) {
            Node pre = treeMap.lowerEntry(val).getValue();
            Node cur = pre.next;
            if(cur!=null&&cur.left<=val)
                return;
            boolean isnull = cur==null;
            if(cur==null)
            {
                cur = new Node(val+2,val+2,null);
            }
            if(pre.right+1==val)
            {
                if(val+1==cur.left)
                {
                    treeMap.remove(pre.right);
                    treeMap.remove(cur.right);
                    pre.right = cur.right;
                    pre.next = cur.next;
                    treeMap.put(cur.right,pre);
                    count--;
                }
                else
                {
                    treeMap.remove(pre.right);
                    pre.right++;
                    treeMap.put(pre.right,pre);
                }
            }else if(val+1==cur.left)
                cur.left--;
            else
            {
                Node node = new Node(val,val,isnull?null:cur);
                treeMap.put(val,node);
                count++;
                pre.next = node;
            }
        }

        public int[][] getIntervals() {
            int[][] res = new int[count][2];
            Node node = dummy.next;
            int id = 0;
            while(node!=null)
            {
                res[id++] = new int[]{node.left,node.right};
                node = node.next;
            }
            return res;
        }
    }

    class RangeModule {
        private class Interval
        {
            private int left;
            private int right;
            private Interval next;
            public Interval(int left, int right, Interval next)
            {
                this.left = left;
                this.right = right;
                this.next = next;
            }
        }
        TreeMap<Integer,Interval> map;
        Interval dummy;

        public RangeModule() {
            map = new TreeMap<>();
            dummy = new Interval(-1,-1,null);
            map.put(-1,dummy);
        }

        public void addRange(int left, int right) {
            Interval prev = map.lowerEntry(left).getValue();
            Interval cur = prev.next;
            while(cur!=null&&cur.left<=right)
            {
                right = Math.max(cur.right,right);
                left = Math.min(cur.left,left);
                map.remove(cur.right);
                cur = cur.next;
            }
            prev.next = new Interval(left,right,cur);
            map.put(right, prev.next);
        }

        public boolean queryRange(int left, int right) {
            Interval itv = map.lowerEntry(left).getValue().next;
            if(itv == null)
                return false;
            return itv.left<=left&&itv.right>=right;
        }

        public void removeRange(int left, int right) {
            Interval pre = map.floorEntry(left).getValue();
            Interval cur = pre.next;
            while (cur!=null&&cur.left<right)
            {
                map.remove(cur.right);
                if(cur.left<left)
                {
                    pre.next = new Interval(cur.left,left,null);
                    map.put(left,pre.next);
                    pre = pre.next;
                }
                if(cur.right>right)
                {
                    pre.next = new Interval(right,cur.right,null);
                    map.put(cur.right,pre.next);
                    pre = pre.next;
                }
                cur = cur.next;
            }
            pre.next = cur;
        }
    }

    class MedianFinder {

        /** initialize your data structure here. */
        public MedianFinder() {

        }

        public void addNum(int num) {

        }

        public double findMedian() {

        }
    }

    class RandomizedCollection {

        ArrayList<Integer> lst;
        HashMap<Integer,Set<Integer>> idx;
        java.util.Random rand = new java.util.Random();
        /** Initialize your data structure here. */
        public RandomizedCollection() {
            lst = new ArrayList<>();
            idx = new HashMap<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            if(!idx.containsKey(val))
            {
                idx.put(val,new HashSet<Integer>());
            }
            idx.get(val).add(lst.size());
            lst.add(val);
            return idx.get(val).size()==1;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if(!idx.containsKey(val)||idx.get(val).size()==0)
                return false;
            int remove_index = idx.get(val).iterator().next();
            idx.get(val).remove(remove_index);
            int last = lst.get(lst.size()-1);
            lst.set(remove_index,last);
            idx.get(last).remove(lst.size()-1);
            idx.get(last).add(remove_index);

            lst.remove(lst.size()-1);
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return lst.get(rand.nextInt(lst.size()));
        }
    }

    class LFUCache {

        HashMap<Integer,cachenode> cache;
        HashMap<Integer,DoublyLinkedList> frequencymap;
        int capacity;
        int min;
        public LFUCache(int capacity) {
            cache = new HashMap<>(capacity);
            frequencymap = new HashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            cachenode node = cache.get(key);
            if(node==null)
                return -1;
            updatefreq(node);
            return node.value;
        }

        public void put(int key, int value) {
            if(capacity==0)
                return;
            cachenode node = cache.get(key);
            if(node!=null)
            {
                node.value = value;
                updatefreq(node);
            }
            else
            {
                if(cache.size()==capacity)
                {
                    DoublyLinkedList dll = frequencymap.get(min);
                    cache.remove(dll.tail.pre.key);
                    dll.removenode(dll.tail.pre);
                }
                cachenode newnode = new cachenode(key,value);
                cache.put(key,newnode);
                DoublyLinkedList dll = frequencymap.get(1);
                if(dll==null)
                {
                    dll = new DoublyLinkedList();
                    frequencymap.put(1,dll);
                }
                dll.addnode(newnode);
                min = 1;
            }
        }

        public void updatefreq(cachenode node)
        {
            int freq = node.frequency;
            DoublyLinkedList dll = frequencymap.get(freq);
            dll.removenode(node);
            if(freq==min&&dll.head.next==dll.tail)
            {
                min = freq+1;
            }
            node.frequency++;
            DoublyLinkedList dll2 = frequencymap.get(freq+1);
            if(dll2==null)
            {
                dll2 = new DoublyLinkedList();
                frequencymap.put(freq+1,dll2);
            }
            dll2.addnode(node);

        }
    }

    class cachenode
    {
        int key;
        int value;
        int frequency = 1;
        cachenode pre,next;

        public cachenode()
        {
        }

        public cachenode(int key,int value)
        {
            this.key = key;
            this.value = value;
        }
    }

    class DoublyLinkedList
    {
        cachenode head;
        cachenode tail;
        public DoublyLinkedList() {
            head = new cachenode();
            tail = new cachenode();
            head.next = tail;
            tail.pre = head;
        }

        public void removenode(cachenode node)
        {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        public void addnode(cachenode node)
        {
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            node.pre = head;
        }
    }


    class Trie {
        TrieNode root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode node = root;
            for(int i=0;i<word.length();i++)
            {
                char current = word.charAt(i);
                if(node.containsKey(current))
                {
                    node = node.get(current);
                }
                else
                {
                    TrieNode newnode = new TrieNode();
                    node.put(current,newnode);
                    node = node.get(current);
                }
            }
            node.setEnd();
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode node = root;
            for(int i=0;i<word.length();i++)
            {
                char current = word.charAt(i);
                if(node.containsKey(current))
                {
                    node = node.get(current);
                }
                else
                {
                    return false;
                }
            }
            return node.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for(int i=0;i<prefix.length();i++)
            {
                char current = prefix.charAt(i);
                if(node.containsKey(current))
                {
                    node = node.get(current);
                }
                else
                {
                    return false;
                }
            }
            return true;
        }
    }

    class TrieNode
    {
        private TrieNode[] children;

        final int R = 26;
        boolean isEnd;
        public TrieNode(){
            children = new TrieNode[26];
        }

        public boolean containsKey(char ch)
        {
            return children[ch-'a']!=null;
        }

        public TrieNode get(char ch)
        {
            return children[ch-'a'];
        }

        public void put(char ch,TrieNode node)
        {
            children[ch-'a'] = node;
        }

        public void setEnd()
        {
            isEnd = true;
        }

        public boolean isEnd()
        {
            return isEnd;
        }
    }














































}
