package boj.P1504;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n, e;
    static int[][] adj = new int[801][801];
    static long[][] dist = new long[3][801]; // 0 = start , 1 = mid1, 2 = mid2
    static int distNum = 0;
    static final long INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        e = fs.nInt();

        for(int i=1;i<=n;i++) {
            for(int j=0;j<3;j++) {
                dist[j][i] = INF;
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                adj[i][j] = (int)INF;
            }
        }

        int mid1 , mid2;
        for(int i=0;i<e;i++) {
            int a = fs.nInt();
            int b = fs.nInt();
            int v = fs.nInt();
            adj[a][b] = v;
            adj[b][a] = v;
        }
        mid1 = fs.nInt();
        mid2 = fs.nInt();
        dijkstra(1);
        dijkstra(mid1);
        dijkstra(mid2);

        // 1 -> 2 -> 3 -> n
        long cand1 = dist[0][mid1] + dist[1][mid2] + dist[2][n];

        // 1 -> 3 -> 2 -> n
        long cand2 = dist[0][mid2] + dist[2][mid1] + dist[1][n];
        System.out.println(Math.min(cand1,cand2)>=INF?-1:Math.min(cand1,cand2));
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        dist[distNum][start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            for(int next=1;next<=n;next++) {
                if(adj[cur.to][next] != INF) {
                    if(dist[distNum][next] > dist[distNum][cur.to] + adj[cur.to][next]) {
                        dist[distNum][next] = dist[distNum][cur.to] + adj[cur.to][next];
                        pq.add(new Node(next,dist[distNum][next]));
                    }
                }
            }
        }
        distNum++;
    }

    static class Node implements Comparable<Node>{
        int to;
        long dist;

        public Node(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return (int)(this.dist - o.dist);
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
