package com.company;
import javax.print.DocFlavor;
import java.util.*;
public class Solution_Graph {

    public boolean Num207canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length==0)
            return true;
        HashMap<Integer,GNode> map = new HashMap<>();
        for(int[] relation:prerequisites)
        {
            GNode preCourse = Num207createnewnode(map,relation[1]);
            GNode nextCourse = Num207createnewnode(map,relation[0]);
            preCourse.outNode.add(relation[0]);
            nextCourse.inDegree+=1;
        }
        LinkedList<Integer> nodelist = new LinkedList<>();
        for(Integer k : map.keySet())
        {
            GNode node = map.get(k);
            if(node.inDegree==0)
                nodelist.add(k);
        }
        int total = prerequisites.length;
        while(nodelist.size()!=0)
        {
            int curr_course_id = nodelist.pop();
            for(int next : map.get(curr_course_id).outNode)
            {
                GNode node = map.get(next);
                node.inDegree--;
                if(node.inDegree==0)
                    nodelist.add(next);
                total--;
            }
        }
        return total==0?true:false;
    }

    protected GNode Num207createnewnode(HashMap<Integer,GNode> map, Integer course)
    {
        if(map.containsKey(course))
            return map.get(course);
        else
        {
            GNode nnode = new GNode();
            map.put(course,nnode);
            return nnode;
        }
    }

    public int[] Num210findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        int[] in = new int[numCourses];
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for(int[] relation: prerequisites)
        {
            int pre = relation[1];
            int next =relation[0];
            List<Integer> outlist = map.getOrDefault(pre,new LinkedList<>());
            outlist.add(next);
            map.put(pre,outlist);
            in[next]++;
        }
        LinkedList<Integer> courseids = new LinkedList<>();
        for(int i=0;i<numCourses;i++)
        {
            if(in[i]==0)
                courseids.add(i);
        }
        int total = prerequisites.length;
        int index = 0;
        while(courseids.size()!=0)
        {
            int courseid = courseids.pop();
            ans[index] = courseid;
            if(map.containsKey(courseid))
            {
                List<Integer> adjlist = map.get(courseid);
                for(int i=0;i<adjlist.size();i++)
                {
                    in[adjlist.get(i)]--;
                    total--;
                    if(in[adjlist.get(i)]==0)
                        courseids.add(adjlist.get(i));
                }
            }
            index++;
        }
        if(total!=0)
            return new int[0];
        return ans;
    }

    HashMap<Node,Node> map = new HashMap<>();
    public Node Num133_dfs_cloneGraph(Node node) {
        if(node==null)
            return null;
        if(map.containsKey(node))
            return map.get(node);
        Node curnode = new Node(node.val,new ArrayList<>());
        map.put(node,curnode);
        for(Node neighbour:node.neighbors)
        {
            curnode.neighbors.add(Num133_dfs_cloneGraph(neighbour));
        }
        return curnode;
    }

    public Node Num133_bfs_cloneGraph(Node node) {
        if(node==null)
            return null;
        HashMap<Node,Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty())
        {
            Node curr = queue.poll();
            for(Node neighbour: curr.neighbors)
            {
                if(!visited.containsKey(neighbour))
                {
                    Node newnode = new Node(neighbour.val,new ArrayList<>());
                    visited.put(neighbour,newnode);
                    queue.add(neighbour);
                }
                visited.get(curr).neighbors.add(visited.get(neighbour));
            }
        }
        return visited.get(node);
    }

    public String Num269alienOrder(String[] words) {
        if(words.length==0)
            return null;
        List<String[]> relations = Num269helper(words);
        HashMap<String,Integer> counter = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                counter.put(String.valueOf(c), 0);
            }
        }
        HashMap<String,List<String>> map = new HashMap<>();
        for(int i=0;i<relations.size();i++)
        {
            String pre = relations.get(i)[0];
            String next = relations.get(i)[1];
            counter.put(next,counter.getOrDefault(next,0)+1);
            List<String> followings = map.getOrDefault(pre,new LinkedList<>());
            followings.add(next);
            map.put(pre,followings);
        }
        LinkedList<String> stringlist = new LinkedList<>();
        int total = relations.size();
        for(String key:counter.keySet())
        {
            if(counter.get(key)==0)
                stringlist.add(key);
        }
        String ans = "";
        while(stringlist.size()!=0)
        {
            String cur = stringlist.poll();
            if(map.containsKey(cur))
            {
                for(String next:map.get(cur))
                {
                    int indegree = counter.get(next);
                    indegree--;
                    total--;
                    if(indegree==0)
                        stringlist.add(next);
                    counter.put(next,indegree);
                }
            }
            ans+=cur;
        }
        if(total==0)
            return ans;
        return "";

    }

    public List<String[]> Num269helper(String[] words)
    {
        List<String[]> ans = new LinkedList<>();
        for(int i=0;i<words.length-1;i++)
        {

            if(words[i].length()>words[i+1].length()&&words[i].startsWith(words[i+1]))
            {
                String[] a = new String[]{"a","b"};
                String[] b = new String[]{"b","a"};
                ans.add(a);
                ans.add(b);
                break;
            }
            int length = Math.min(words[i].length(),words[i+1].length());
            for(int j = 0;j<length;j++)
            {
                if(words[i].charAt(j)!=words[i+1].charAt(j))
                {
                    String[] relation = new String[2];
                    relation[0] = String.valueOf(words[i].charAt(j));
                    relation[1] = String.valueOf(words[i+1].charAt(j));
                    ans.add(relation);
                    break;
                }
            }

        }
        return ans;
    }

    public double[] Num399calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] ans = new double[queries.size()];
        HashMap<String,HashMap<String,Double>> graph = new HashMap<>();
        for(int i=0;i<equations.size();i++)
        {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0),divisor = equation.get(1);
            double quotient = values[i];
            if(!graph.containsKey(dividend))
                graph.put(dividend,new HashMap<>());
            if(!graph.containsKey(divisor))
                graph.put(divisor,new HashMap<>());
            graph.get(dividend).put(divisor,quotient);
            graph.get(divisor).put(dividend,1/quotient);
        }

        for(int i=0;i<queries.size();i++)
        {
            List<String> query = queries.get(i);
            String dividend = query.get(0),divisor = query.get(1);
            if(!graph.containsKey(dividend)||!graph.containsKey(divisor))
                ans[i] = -1.0;
            else if(dividend==divisor)
                ans[i] = 1.0;
            else
            {
                HashSet<String> visited = new HashSet<>();
                ans[i] = Num399helper(graph,dividend,divisor,1,visited);
            }
        }
        return ans;
    }

    public double Num399helper(HashMap<String,HashMap<String,Double>> graph,String currNode, String targetNode,double accProduct, Set<String> visited)
    {
        visited.add(currNode);
        double ret = -1;
        Map<String,Double> neightbours = graph.get(currNode);
        if(neightbours.containsKey(targetNode))
            ret = accProduct*neightbours.get(targetNode);
        else {
            for(String neighbour:neightbours.keySet())
            {
                if(visited.contains(neighbour))
                    continue;
                ret = Num399helper(graph,neighbour,targetNode,accProduct*neightbours.get(neighbour),visited);
                if(ret!=-1)
                    break;
            }
        }
        visited.remove(currNode);
        return ret;
    }

    public List<Integer> Num310findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if(n==1)
        {
            ans.add(0);
            return ans;
        }
        int[] degree = new int[n];
        List<List<Integer>> map = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            map.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++)
        {
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            if(degree[i]==1)
                queue.add(i);
        }
        int number = n;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            if(number==size&&size<=2)
            {
                for(int k:queue)
                {
                    ans.add(k);
                }
                break;
            }
            for(int i=0;i<size;i++)
            {
                int curr = queue.poll();
                for(int adj:map.get(curr))
                {
                    degree[adj]--;
                    if(degree[adj]==1)
                        queue.add(adj);
                }
            }
            number-=size;
        }
        return ans;
    }

    public int Num149maxPoints(int[][] points) {
        if(points==null||points.length==0)
            return 0;
        HashMap<int[],Integer> map = new HashMap<>();
        int res = 0;
        for(int i=0;i<points.length;i++)
        {
            int samecount = 0;
            int max = 0;
            for(int j = i+1;j<points.length;j++)
            {
                int dy = points[i][1]-points[j][1];
                int dx = points[i][0]- points[j][0];
                if(dx==0&&dy==0)
                    samecount++;
                else
                {
                    int[] slope = Num149helper(dy,dx);
                    map.put(slope,map.getOrDefault(slope,0)+1);
                    max = Math.max(max,map.get(slope));
                }
            }
            res = Math.max(res,samecount+max+1);
            map.clear();;
        }
        return res;
    }

    public int[] Num149helper(int dy, int dx)
    {
        int[] ans = new int[2];
        if(dy==0)
        {
            ans[0] = 0;
            ans[1] = 1;
            return ans;
        }
        if(dx==0)
        {
            ans[1] = 0;
            ans[0] = 1;
            return ans;
        }
        int biggest_common = Num149helperhelper(dy,dx);
        ans[0] = dy/biggest_common;
        ans[1] = dx/biggest_common;
        return ans;
    }

    public int Num149helperhelper(int m, int n)
    {
        if(n==0)
            return m;
        return Num149helperhelper(n,m%n);
    }

    public List<Integer> Num1557findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> ans = new ArrayList<>();
        if(edges==null||edges.size()==0)
            return ans;
        int[] indegree = new int[n];
        for(int i=0;i<edges.size();i++)
        {
            indegree[edges.get(i).get(1)]++;
        }
        for(int i=0;i<n;i++)
        {
            if(indegree[i]==0)
                ans.add(i);
        }
        return ans;
    }

    public List<List<Integer>> Num797allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        if(graph==null||graph.length==0)
            return ans;
        List<Integer> path = new ArrayList<>();
        Num797helper(graph,0,ans,path);
        return ans;
    }

    public void Num797helper(int[][] graph,int curr_node,List<List<Integer>> ans,List<Integer> path)
    {
        if(curr_node==graph.length-1)
        {
            path.add(curr_node);
            List<Integer> newlist = new LinkedList<>(path);
            ans.add(newlist);
            path.remove(path.size()-1);
            return;
        }
        path.add(curr_node);
        for(int next:graph[curr_node])
        {
            Num797helper(graph,next,ans,path);
        }
        path.remove(path.size()-1);
        return;
    }

    public int Num1135minimumCost(int N, int[][] connections) {
        Arrays.sort(connections, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[2]-t2[2];
            }
        });
        int[] ans = new int[2];
        int[] unions = new int[N+1];
        for(int i=1;i<=N;i++)
        {
            unions[i]=i;
        }
        for(int i=0;i<connections.length;i++)
        {
            unions(connections[i][0],connections[i][1],unions,ans,connections[i]);
        }
        if(ans[1]==N-1)
            return ans[0];
        return -1;

    }

    public int find(int x, int unions[])
    {
        if(unions[x]==x)
            return x;
        return find(unions[x],unions);
    }

    public void unions(int x, int y,int unions[],int[] ans,int[] connection)
    {
        int xset = find(x,unions);
        int yset = find(y,unions);
        if(xset!=yset)
        {
            unions[xset] = yset;
            ans[0]+=connection[2];
            ans[1]++;
        }
    }

    public int[] Num684findRedundantConnection(int[][] edges) {
        int n = edges.length-1;
        int[] unions = new int[n+2];
        for(int i=1;i<=n;i++)
        {
            unions[i]= i;
        }
        for(int i=0;i<edges.length;i++)
        {
            if(Num684unions(edges[i][0],edges[i][1],unions))
                return edges[i];
        }
        return null;
    }

    public int Num684find(int x,int[] unions)
    {
        if(unions[x]==x)
            return x;
        else
            return find(unions[x],unions);
    }

    public boolean Num684unions(int x,int y,int[] unions)
    {
        int xset = Num684find(x,unions);
        int yset = Num684find(y,unions);
        if(xset!=yset)
        {
            unions[xset] = yset;
            return false;
        }
        return true;
    }

    public boolean Num841canVisitAllRooms(List<List<Integer>> rooms) {
        int total_room_number = rooms.size();
        int[] visited = new int[total_room_number];
        Num841DFS(rooms,0,visited);
        for(int i=0;i<total_room_number;i++)
        {
            if(visited[i]==0)
                return false;
        }
        return true;
    }

    public void Num841DFS(List<List<Integer>> rooms, int current_room,int[] visited)
    {
        if(visited[current_room]>0)
            return;
        visited[current_room]++;
        for(int next:rooms.get(current_room))
        {
            if(visited[next]>0)
                continue;
            Num841DFS(rooms,next,visited);
        }
    }

    public int Num1387getKth(int lo, int hi, int k) {
        int[] dp = new int[1000000];
        int[][] ans = new int[hi-lo+1][2];
        dp[2] = 1;
        for(int i=lo;i<=hi;i++)
        {
            int power = Num1387power(i,dp);
            ans[i-lo][0] = i;
            ans[i-lo][1] = power;
        }
        Arrays.sort(ans, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1] - t2[1];
            }
        });
        for(int i=0;i<ans.length;i++)
        {
            if(i==k-1)
                return ans[i][0];
        }
        return -1;
    }

    public int Num1387power(int x,int[] dp)
    {
        if(dp[x]!=0)
            return dp[x];
        else
        {
            if(x%2==0)
                dp[x] = Num1387power(x/2,dp);
            else
                dp[x] = Num1387power(x*3+1,dp);
            return dp[x];
        }
    }

    public int Num1136minimumSemesters(int N, int[][] relations) {
        int edge_nums = relations.length;
        int[] indegree = new int[N+1];
        HashMap<Integer,List<Integer>> graph = new HashMap<>();
        for(int[] relation: relations)
        {
            indegree[relation[1]]++;
            if(!graph.containsKey(relation[0]))
                graph.put(relation[0],new ArrayList<>());
            List<Integer> next = graph.get(relation[0]);
            next.add(relation[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<indegree.length;i++)
        {
            if(indegree[i]==0)
                queue.add(i);
        }
        if(queue.size()==0)
            return -1;
        int count = 0;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            count++;
            for(int i=0;i<size;i++)
            {
                int curr_course = queue.poll();
                if(graph.containsKey(curr_course))
                {
                    for(int next:graph.get(curr_course))
                    {
                        indegree[next]--;
                        edge_nums--;
                        if(indegree[next]==0)
                            queue.add(next);
                    }
                }
            }
        }
        if(edge_nums!=0)
            return -1;
        return count;
    }

    public int Num323countComponents(int n, int[][] edges) {
        int[] unions = new int[n];
        for(int i=0;i<unions.length;i++)
        {
            unions[i] = i;
        }
        for(int i=0;i<edges.length;i++)
        {
            Num323unions(edges[i][0],edges[i][1],unions);
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<unions.length;i++)
        {
            unions[i] = Num323find(i,unions);
            set.add(unions[i]);
        }
        return set.size();
    }

    public int Num323find(int x,int[] unions)
    {
        if(unions[x]==x)
            return x;
        return Num323find(unions[x],unions);
    }

    public void Num323unions(int x, int y, int[] unions)
    {
        int xset = Num323find(x,unions);
        int yset = Num323find(y,unions);
        if(xset!=yset)
            unions[xset] = yset;
    }

    public boolean Num785isBipartite(int[][] graph) {
        int[] unions = new int[graph.length];
        for(int i=0;i<graph.length;i++)
        {
            if(unions[i]==0)
                unions[i] = -1;
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            while(!stack.isEmpty())
            {
                int node = stack.pop();
                int curr = -unions[node];
                for(int next:graph[node])
                {
                    if(unions[next]==0)
                    {
                        unions[next] = curr;
                        stack.push(next);
                    }
                    else if(unions[next]!=curr)
                        return false;
                }
            }
        }
        return true;
    }

    public boolean canReach(int[] arr, int start) {
        int[] visited = new int[arr.length];
        int[] jump = new int[]{-1,1};
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty())
        {
            int curr = stack.pop();
            visited[curr] = 1;
            if(arr[curr]==0)
                return true;
            for(int i=0;i<jump.length;i++)
            {
                int next = curr+jump[i]*arr[curr];
                if(next<arr.length&&0<=next)
                {
                    if(visited[next]==0)
                        stack.push(next);
                }
            }

        }
        return false;
    }

    public int Num1162maxDistance(int[][] grid) {
        if(grid==null||grid.length==0)
            return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<grid.length;i++)
        {
            for(int j = 0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                    queue.add(new int[]{i,j});
            }
        }
        if(queue.size()==0||queue.size()==(grid.length*grid[0].length))
            return -1;
        int step = -1;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for(int i=0;i<size;i++)
            {
                int[] cur = queue.poll();
                for (int[] dir : dirs){
                    int nr = dir[0] + cur[0];
                    int nc = dir[1] + cur[1];
                    if (0 <= nr && nr < rows && 0 <= nc && nc < cols && grid[nr][nc] == 0){
                        queue.offer(new int[]{nr, nc});
                        grid[nr][nc] = 1;
                    }
                }
            }
            step++;
        }
        return step;
    }


    int Num1102_total_max = 0;
    int Num1102_path_min = Integer.MAX_VALUE;
    public int Num1102maximumMinimumPath(int[][] A) {
        if(A==null||A.length==0)
            return 0;
        boolean[][] visited = new boolean[A.length][A[0].length];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t2[2]-t1[2];
            }
        });
        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        pq.add(new int[]{0,0,A[0][0]});
        while(!pq.isEmpty())
        {
            int[] curr = pq.poll();
            int row = curr[0];
            int col = curr[1];
            if(row==A.length-1&&col==A[0].length-1)
                return curr[2];
            for (int[] dir : directions) {
                if (row + dir[0] >= A.length || row + dir[0] < 0 ||
                        col + dir[1] >= A[0].length || col + dir[1] < 0) continue;
                int nrow = row + dir[0];
                int ncol = col + dir[1];
                if(visited[nrow][ncol])
                    continue;
                else
                {
                    visited[nrow][ncol] = true;
                    pq.add(new int[]{nrow,ncol,Math.min(curr[2],A[nrow][ncol])});
                }
            }

        }
        return -1;
    }

    public int Num743networkDelayTime(int[][] times, int N, int K) {
        HashMap<Integer,List<int[]>> graph = new HashMap<>();
        for(int[] edge:times)
        {
            if(!graph.containsKey(edge[0]))
                graph.put(edge[0],new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
        }
        HashMap<Integer,Integer> dist = new HashMap<>();
        boolean[] seen = new boolean[N+1];
        for(int i=1;i<=N;i++)
        {
            dist.put(i,Integer.MAX_VALUE);
        }
        dist.put(K,0);
        while(true)
        {
            int canNode = -1;
            int canDist = Integer.MAX_VALUE;
            for(int i=1;i<=N;i++)
            {
                if(!seen[i] && dist.get(i)<canDist)
                {
                    canDist = dist.get(i);
                    canNode = i;
                }
            }
            if(canNode<0)
                break;
            seen[canNode] = true;
            if(graph.containsKey(canNode))
            {
                for(int[] next:graph.get(canNode))
                {
                    dist.put(next[0],Math.min(dist.get(next[0]),dist.get(canNode)+next[1]));
                }
            }
        }
        int ans = 0;
        for(int dis:dist.values())
        {
            if(dis==Integer.MAX_VALUE)
                return -1;
            ans = Math.max(ans,dis);
        }
        return ans;
    }

    public int Num787dijstrafindCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        HashMap<Integer,List<int[]>> graph = new HashMap<>();
        for(int[] flight:flights)
        {
            if(!graph.containsKey(flight[0]))
                graph.put(flight[0],new ArrayList<>());
            graph.get(flight[0]).add(new int[]{flight[1],flight[2]});
        }
        PriorityQueue<int[]> dist = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1]-t2[1];
            }
        });

        int[] dist_final = new int[n];
        int[] stops = new int[n];
        Arrays.fill(dist_final,Integer.MAX_VALUE);
        Arrays.fill(stops,Integer.MAX_VALUE);
        dist_final[src]=0;
        stops[src]=0;
        for(int i=0;i<n;i++)
        {
            if(i!=src)
                dist.offer(new int[]{n+1,Integer.MAX_VALUE,i});
            else
                dist.offer(new int[]{0,0,src});
        }
        while(!dist.isEmpty())
        {
            int[] curr = dist.poll();
            int stop = curr[0];
            int cost = curr[1];
            int curr_node = curr[2];

            if(curr_node == dst)
                return curr[1];
            if(stop==K+1)
                continue;
            if(graph.containsKey(curr_node))
            {
                for(int[] next:graph.get(curr_node))
                {
                    int next_node = next[0];
                    int weight = next[1];
                    if(weight+curr[1]<dist_final[next_node])
                    {
                        dist_final[next_node] = weight+curr[1];
                        dist.offer(new int[]{curr[0]+1,weight+curr[1],next_node});
                    }
                    else if(stop<stops[next_node])
                    {
                        dist.offer(new int[]{curr[0]+1,weight+curr[1],next_node});
                        stops[next_node] = stop+1;
                    }

                }
            }
        }
        return dist_final[dst]==Integer.MAX_VALUE?-1:dist_final[dst];
    }

    public int Num787Bellman_ford_findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] distance = new int[2][n];
        Arrays.fill(distance[0],Integer.MAX_VALUE);
        Arrays.fill(distance[1],Integer.MAX_VALUE);
        distance[0][src] = distance[1][src] = 0;
        for(int ite = 0;ite<=K;ite++)
        {
            for(int[] edge:flights)
            {
                int s = edge[0];
                int d = edge[1];
                int w = edge[2];
                int du = distance[1-ite&1][s];
                int dv = distance[ite&1][d];
                if(du==Integer.MAX_VALUE)
                    continue;
                if(du+w<dv)
                    distance[ite&1][d] = du+w;
            }
        }
        return distance[K&1][dst]<Integer.MAX_VALUE?distance[K&1][dst]:-1;
    }
    int Num1376maxtime = 0;
    public int Num1376numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if(n<=1)
            return 0;
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<manager.length;i++)
        {
            if(manager[i]==-1)
                continue;
            if(!map.containsKey(manager[i]))
                map.put(manager[i],new ArrayList<>());
            map.get(manager[i]).add(i);
        }

        Num1376helper(map,0,headID,informTime);
        return Num1376maxtime;
    }

    public void Num1376helper(HashMap<Integer,List<Integer>> map,int currtime,int currentID,int[] informTime)
    {
        if(!map.containsKey(currentID))
        {
            Num1376maxtime = Math.max(Num1376maxtime,currtime);
            return;
        }
        List<Integer> sub = map.get(currentID);
        for(int i=0;i<sub.size();i++)
        {
            Num1376helper(map,currtime+informTime[currentID],sub.get(i),informTime);
        }
    }

    public boolean Num356isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashMap<int[],Integer> map = new HashMap<>();
        for(int i=0;i<points.length;i++)
        {
            max = Math.max(max,points[i][0]);
            min = Math.min(min,points[i][0]);
            map.put(points[i],map.getOrDefault(points[i],0)+1);
        }
        double possibleline = (max+min)/2;
        for(int i=0;i<points.length;i++)
        {
            int otherside = max+min-points[i][0];
            int[] curr = new int[]{otherside,points[i][1]};
            if(!map.containsKey(otherside)||map.get(curr)<1)
                return false;
            map.put(curr,map.get(curr)-1);
        }
        return true;
    }

    public int Num223computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        long zero = 0;
        long X = Math.max(zero, Math.min(C, G) - Math.max(A, E));
        long Y = Math.max(zero, Math.min(D, H) - Math.max(B, F));
        return (int) ((C - A) * (D - B) + (G - E) * (H - F) - X * Y);
    }

    public boolean Num335isSelfCrossing(int[] x) {
        if(x.length<4)
            return false;
        for(int i=3;i<x.length;i++)
        {
            if(x[i]>=x[i-2]&&x[i-1]<=x[i-3])
                return true;
            if(i>3&&x[i-1] == x[i-3] && x[i-4] + x[i] >= x[i-2])
                return true;
            if(i > 4 && (x[i-3]-x[i-5] <= x[i-1]&&x[i-1] <= x[i-3]) && (x[i-2]-x[i-4] <= x[i]&&x[i] <= x[i-2]) && x[i-2] > x[i-4])
            return true;
        }
        return false;
    }

    public boolean NUm391isRectangleCover(int[][] rectangles) {
        int left=Integer.MAX_VALUE,low = Integer.MAX_VALUE,high = Integer.MIN_VALUE,right = Integer.MIN_VALUE;
        int areasum = 0;
        HashMap<String,Integer> map = new HashMap<>();
        for(int[] rec:rectangles)
        {
            left = Math.min(left,rec[0]);
            low = Math.min(low,rec[1]);
            high = Math.max(high,rec[3]);
            right = Math.max(right,rec[2]);
            areasum += (rec[3]-rec[1])*(rec[2]-rec[0]);
            String bottomleft = rec[0]+"#"+rec[1];
            String topleft = rec[0]+"#"+rec[3];
            String topright = rec[2] + "#"+rec[3];
            String bottomright = rec[2]+"#"+rec[1];
            map.put(bottomleft,map.getOrDefault(bottomleft,0)+1);            map.put(bottomleft,map.getOrDefault(bottomleft,0)+1);
            map.put(topleft,map.getOrDefault(topleft,0)+1);
            map.put(bottomright,map.getOrDefault(bottomright,0)+1);
            map.put(topright,map.getOrDefault(topright,0)+1);

        }
        String bottomleft = left+"#"+low;
        String topleft = left+"#"+high;
        String topright = right + "#"+high;
        String bottomright = right+"#"+low;
        if(areasum!=(high-low)*(right-left))
            return false;
        for(String key:map.keySet())
        {
            if(map.get(key)%2==1&&!(key.equals(bottomleft)||key.equals(bottomright)||key.equals(topleft)||key.equals(topright)))
                return false;
        }
        return map.get(bottomleft)==1&&map.get(topleft)==1&&map.get(topright)==1&&map.get(bottomright)==1;
    }

    

















































}
