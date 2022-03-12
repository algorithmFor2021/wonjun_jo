package boj.P1854;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static Fs fs = new Fs();
    static int n,m,k;
    static ArrayList<Node>[] adj = new ArrayList[1001];
    static PriorityQueue<Integer>[] distQueue = new PriorityQueue[1001];
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws Exception{
        input();
        kthDijkstra();
        makeAns();
    }

    static void kthDijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0)); // 1까지 가는데 0
        distQueue[1].add(0);

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            for(Node next : adj[cur.to]) {
                // 갱신이 되었을 경우에만 pq 에 다음을 넣음
                if(distQueue[next.to].size() < k  || distQueue[next.to].peek() > cur.dist + next.dist) {
                    if(distQueue[next.to].size() == k) distQueue[next.to].poll();
                    distQueue[next.to].add(cur.dist + next.dist);
                    pq.add(new Node(next.to,cur.dist + next.dist));
                }
            }

        }
    }

    static void makeAns() {
        for(int i=1;i<=n;i++) {
            if(distQueue[i].size() < k) answer.append(-1).append('\n');
            else answer.append(distQueue[i].peek()).append('\n');
        }

        System.out.println(answer);
    }



    static void input() throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        k = fs.nInt();

        for(int i=0;i<=n;i++) {
            adj[i] = new ArrayList<>();
            distQueue[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        for(int i=0;i<m;i++) {
            int a = fs.nInt();
            int b = fs.nInt();
            int c = fs.nInt();
            // a -> b 까지 가는데 c
            adj[a].add(new Node(b,c));
        }

    }

    static class Node implements Comparable<Node>{
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
