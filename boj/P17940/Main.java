package boj.P17940;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs= new Fs();
    static int n,m; // start = 0 - end = m
    static int[] company = new int[1001];
    static int[][] adj = new int[1001][1001];
    static int[] dist = new int[1001];
    static int[] trns = new int[1001];
    static final int MAX_VAL = 987654321;

    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        m = fs.nInt();

        for(int i=0;i<n;i++) {
            company[i] = fs.nInt();
            dist[i] = MAX_VAL;
            trns[i] = MAX_VAL;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                adj[i][j] = fs.nInt();
            }
        }

        solve();

        System.out.println(trns[m]+" "+dist[m]);


    }

    static void solve(){

        boolean[] visited = new boolean[1001];
        dist[0] = 0; // 시작 거리 0
        trns[0] = 0; // 시작 환승 0
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,0));

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            if(visited[cur.id]) continue;
            visited[cur.id] = true;

            ArrayList<Node> finds = findNext(cur.id);

            for(Node next : finds){
                if(trns[next.id] > trns[cur.id] + next.trans){
                    trns[next.id] = trns[cur.id] + next.trans;
                    dist[next.id] = dist[cur.id] + next.dist;
                    pq.add(new Node(next.id,dist[next.id],trns[next.id]));
                }
                else if(trns[next.id] == trns[cur.id] + next.trans){
                    if(dist[next.id] > dist[cur.id] + next.dist){
                        trns[next.id] = trns[cur.id] + next.trans;
                        dist[next.id] = dist[cur.id] + next.dist;
                        pq.add(new Node(next.id,dist[next.id],trns[next.id]));
                    }
                }
            }

        }


    }

    static ArrayList<Node> findNext(int cur){
        ArrayList<Node> ret = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(adj[cur][i] > 0){
                int t = company[cur]!=company[i]?1:0;
                ret.add(new Node(i,adj[cur][i],t));
            }
        }
        return ret;
    }


    static class Node implements Comparable<Node>{
        int id;
        int dist;
        int trans;

        public Node(int id, int dist,int trans) {
            this.id = id;
            this.dist = dist;
            this.trans = trans;
        }

        @Override
        public int compareTo(Node o) {
            if(this.trans != o.trans) return this.trans - o.trans;
            return this.dist - o.dist;
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        public String next() throws Exception{
            if(st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
