package boj.P23324;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int n,m,k;
    static long[] ans;

    static class Node{
        int to;
        int val;

        public Node(int to, int val) {
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        k = fs.nInt();
        ans = new long[2];

        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<m;i++) {
            int a = fs.nInt();
            int b = fs.nInt();
            int val = 0;
            if(i+1 == k) {
                val = 1;
            }
            adj.get(a).add(new Node(b,val));
            adj.get(b).add(new Node(a,val));
        }

        boolean[] visit = new boolean[n+1];
        int numFlag = 0;
        for(int i=1;i<=n;i++) {
            if(!visit[i]){
                visit[i] = true;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                ans[numFlag] += 1;
                while(!q.isEmpty()) {
                    int cur = q.poll();

                    for(Node next : adj.get(cur)) {
                        if(!visit[next.to] && next.val==0) {
                            visit[next.to] = true;
                            ans[numFlag] += 1;
                            q.add(next.to);
                        }
                    }
                }
                numFlag += 1;
            }
        }

        System.out.println(ans[0]*ans[1]);

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
