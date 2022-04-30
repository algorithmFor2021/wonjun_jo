package boj.P1939;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int start , dest;
    static ArrayList<Node>[] adj = new ArrayList[100001];

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();

        for(int i=0;i<=n;i++) adj[i] = new ArrayList<>();

        for(int i=0;i<m;i++) {
            int a = fs.nInt();
            int b = fs.nInt();
            int val = fs.nInt();

            adj[a].add(new Node(b,val));
            adj[b].add(new Node(a,val));
        }

        start = fs.nInt();
        dest = fs.nInt();

        System.out.println(solve());

    }

    static int solve() {
        int low = 0;
        int high = 1000000000;
        int ans = low;

        while(low <= high) {
            int mid = (low + high) / 2;

            // mid 가 지나갈 수 있는가?
            if(check(mid)) {
                low = mid + 1;
                ans = mid;
            }else {
                high = mid - 1;
            }
        }

        return ans;
    }

    static boolean check(int dist) {
        boolean[] visit = new boolean[100001];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        boolean ret = false;

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == dest) {
                ret = true;
                break;
            }

            for(Node next : adj[cur]) {
                if(!visit[next.to] && next.dist >= dist) {
                    visit[next.to] = true;
                    q.add(next.to);
                }
            }
        }

        return ret;
    }

    static class Node{
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
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
