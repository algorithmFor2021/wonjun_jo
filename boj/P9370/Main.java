package boj.P9370;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static final int INF = 1_000_000_000;
    static int[][] adj = new int[2001][2001];
    static int n,m,t,s,g,h;
    static int testCase;


    public static void main(String[] args) throws Exception{
        testCase = fs.nInt();
        StringBuilder sb = new StringBuilder();

        while(testCase-- > 0){

            clear();

            n = fs.nInt();
            m = fs.nInt();
            t = fs.nInt();
            s = fs.nInt();
            g = fs.nInt();
            h = fs.nInt();

            for(int i=0;i<m;i++){
                int a = fs.nInt();
                int b = fs.nInt();
                int d = fs.nInt();

                adj[a][b] = d*2;
                adj[b][a] = d*2;

            }

            adj[h][g] = adj[g][h] = adj[h][g] - 1;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(s,0));
            int[] dist = new int[2001];
            for(int i=0;i<=n;i++) dist[i] = INF;
            boolean[] visited = new boolean[2001];
            dist[s] = 0;

            while(!pq.isEmpty()){
                Node node = pq.poll();

                if(visited[node.to]) continue;
                visited[node.to] = true;

                int cur = node.to;

                for(int i=1;i<=n;i++){
                    if(dist[i] > dist[cur] + adj[cur][i]){
                        dist[i] = dist[cur] + adj[cur][i];
                        pq.add(new Node(i,dist[i]));
                    }
                }
            }

            ArrayList<Integer> ans = new ArrayList<>();
            for(int i=0;i<t;i++) ans.add(fs.nInt());
            Collections.sort(ans);

            for(int i=0;i<t;i++){
                int dest = ans.get(i);
                if(dist[dest]%2==1) sb.append(dest).append(" ");
            }
            sb.append('\n');
        }

        System.out.print(sb);

    }
    // clear All
    static void clear(){
        for(int i=0;i<=2000;i++){
            for(int j=0;j<=2000;j++){
                adj[i][j] = INF;
            }
        }
    }

    static class Node implements Comparable<Node>{
        int to,time;

        public Node(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
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
