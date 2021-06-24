package boj.P1922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[] parent = new int[1001];
    static long ans = 0;
    static ArrayList<Node> nodes = new ArrayList<>(100001);
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        for(int i=1;i<=n;i++) parent[i] = i;
        for(int i=0;i<m;i++){
            nodes.add(new Node(fs.nInt(),fs.nInt(),fs.nInt()));
        }
        Collections.sort(nodes);
        int cnt = 0;
        for(Node node : nodes){
            int px = find(node.x);
            int py = find(node.y);

            if(px != py){
                cnt++;
                parent[px] = py;
                ans += node.val;
            }

            if(cnt == n-1) break;
        }

        System.out.println(ans);


    }
    static int find(int x){
        if(parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    static class Node implements Comparable<Node>{
        int x,y;
        int val;
        public Node(int x,int y,int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
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
