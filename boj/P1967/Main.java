package boj.P1967;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static ArrayList<Node>[] adj;
    static int[] dp = new int[10001];

    public static void main(String[] args) throws Exception{
        input();
        dfs(0,1);
        int answer = Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            answer = Math.max(dp[i],answer);
        }
        System.out.println(answer);
    }

    static int dfs(int prev,int cur){
        int goUpDist = 0;
        if(prev != 0){
            for(Node c : adj[prev]){
                if(c.id == cur){
                    goUpDist = c.dist;
                }
            }
        }

        if(adj[cur].size() == 0){
            return goUpDist;
        }

        ArrayList<Integer> distList = new ArrayList<>();
        for(Node c : adj[cur]){
            distList.add(dfs(cur,c.id));
        }

        if(distList.size() == 1) {
            dp[cur] = goUpDist + distList.get(0);
            return goUpDist + distList.get(0);
        }

        Collections.sort(distList,Collections.reverseOrder());
        dp[cur] = distList.get(0) + distList.get(1);
        return Math.max(distList.get(0),distList.get(1)) + goUpDist;

    }

    static void input() throws Exception{
        n = fs.nInt();
        adj = new ArrayList[n+1];
        for(int i=1;i<=n;i++) adj[i] = new ArrayList<>();

        for(int i=0;i<n-1;i++){
            int parent = fs.nInt();
            int child = fs.nInt();
            int val = fs.nInt();
            adj[parent].add(new Node(child,val));
        }
    }

    static class Node{
        int id;
        int dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }


    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
