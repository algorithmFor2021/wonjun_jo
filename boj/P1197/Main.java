package boj.P1197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int v;
    static int e;
    static ArrayList<Edge> list = new ArrayList<>();
    static int[] parent = new int[10001];

    public static void main(String[] args) throws Exception{
        input();
        Collections.sort(list);
        int cnt = 0;
        int answer = 0;
        for(Edge e : list){
            int p1 = find(e.st);
            int p2 = find(e.ed);
            if(p1 == p2 ) continue;
            parent[p1] = p2;
            cnt++;
            answer += e.dist;
            if(cnt == v-1) break;
        }

        System.out.println(answer);

    }

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void input() throws Exception {
        v = fs.nInt();
        e = fs.nInt();
        for(int i=1;i<=v;i++){
            parent[i] = i;
        }
        for(int i=0;i<e;i++){
            list.add(new Edge(fs.nInt(),fs.nInt(),fs.nInt()));
        }
    }

    static class Edge implements Comparable<Edge>{
        int st,ed;
        int dist;

        public Edge(int st, int ed, int dist) {
            this.st = st;
            this.ed = ed;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    static class Fs{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
