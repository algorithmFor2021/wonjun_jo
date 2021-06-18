package boj.P1647;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[] parent = new int[100001];
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static long ans = 0;
    static int edgeNum = 0;

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        for(int i=1;i<=n;i++) {
            parent[i] = i;
        }

        for(int i=0;i<m;i++){
            pq.add(new Node(fs.nInt(),fs.nInt(),fs.nInt()));
        }

        while(edgeNum != n-2){
            Node node = pq.poll();

            if(find(node.a) != find(node.b)){
                union(node.a,node.b);
                edgeNum++;
                ans += node.val;
            }


        }

        System.out.println(ans);

    }

    static int find(int x){
        if(parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int x,int y){
        int px = find(x);
        int py = find(y);
        parent[px] = py;
    }

    static class Node implements Comparable<Node>{
        int a,b;
        int val;

        public Node(int a,int b,int val){
            this.a = a;
            this.b = b;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return val - o.val;
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
