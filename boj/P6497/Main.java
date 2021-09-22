package boj.P6497;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[] parent = new int[200001];
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        while(true) {
            int ans = 0;
            int n = fs.nInt();
            int m = fs.nInt();

            if(n==0 && m==0) break;

            for (int i = 0; i < n; i++) parent[i] = i;
            edges = new ArrayList<>();
            int a, b, d;
            int total = 0;
            for (int i = 0; i < m; i++) {
                a = fs.nInt();
                b = fs.nInt();
                d = fs.nInt();
                total += d;
                edges.add(new Edge(a, b, d));
            }

            Collections.sort(edges);

            int len = 0;

            for (Edge e : edges) {
                if (len == n - 1) break;
                else {
                    int p1 = find(e.a);
                    int p2 = find(e.b);

                    if (p1 != p2) {
                        parent[p1] = p2;
                        ans += e.d;
                        len++;
                    }
                }
            }
            sb.append(total-ans).append('\n');
        }

        System.out.println(sb);


    }

    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static class Edge implements Comparable<Edge>{
        int a,b,d;

        public Edge(int a, int b, int d) {
            this.a = a;
            this.b = b;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "a=" + a +
                    ", b=" + b +
                    ", d=" + d +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return this.d - o.d;
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
