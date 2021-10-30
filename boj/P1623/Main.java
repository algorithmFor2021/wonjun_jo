package boj.P1623;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[] nalary = new int[200001];
    static int[][] dp = new int[200001][2];
    static StringBuilder sb = new StringBuilder();
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        input();
        dfs(1);
        sb.append(dp[1][1]).append(" ").append(dp[1][0]).append('\n');
        makeAnswer(1);
        makeAnswer(0);

        System.out.print(sb);

    }

    static void input() throws Exception{

        n = fs.nInt();
        nodes = new Node[n+1];
        for(int i=1;i<=n;i++){
            nalary[i] = fs.nInt();
            nodes[i] = new Node(i);
        }
        nodes[1].parent = -1;
        for(int i=2;i<=n;i++){
            int p = fs.nInt();
            nodes[i].parent = p;
            nodes[p].child.add(nodes[i]);
        }
    }

    static void dfs(int idx){

        int clen = nodes[idx].child.size();

        // leaf node
        if(clen == 0 ) {
            dp[idx][0] = 0;
            dp[idx][1] = nalary[idx];
            return;
        }

        for(int i=0;i<clen;i++) dfs(nodes[idx].child.get(i).id);

        dp[idx][1] = nalary[idx];
        for(int i=0;i<clen;i++){
            int childId = nodes[idx].child.get(i).id;
            dp[idx][0] += Math.max(dp[childId][0],dp[childId][1]);
            dp[idx][1] += dp[childId][0];
        }

    }

    static void makeAnswer(int participate){
        ArrayList<Integer> trace = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1,participate});
        while(!q.isEmpty()){
            int[] p = q.poll();

            if(p[1] == 1) {
                trace.add(p[0]);
                for(Node child : nodes[p[0]].child){
                    q.add(new int[]{child.id,0});
                }
            }
            else{
                for(Node child : nodes[p[0]].child){
                    if(dp[child.id][0] <= dp[child.id][1]) q.add(new int[]{child.id,1});
                    else q.add(new int[]{child.id,0});
                }
            }
        }

        Collections.sort(trace);
        for(int t : trace){
            sb.append(t).append(" ");
        }
        sb.append(-1).append("\n");

    }

    static class Node{
        int id;
        int parent;
        ArrayList<Node> child = new ArrayList<>();

        public Node(int id) {
            this.id = id;
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
