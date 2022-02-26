package boj.P17834;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static ArrayList<Integer>[] adj = new ArrayList[50001];
    static int[] cnt = {0,0}; // black , white
    static boolean[] visit = new boolean[50001];
    static int[] color = new int[50001];
    static boolean flag = false;

    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        m = fs.nInt();
        for(int i=1;i<=n;i++) adj[i] = new ArrayList<>();
        for(int i=0;i<m;i++) {
            int a = fs.nInt();
            int b = fs.nInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        cnt[0]++;
        visit[1] = true;
        dfs(1,1);
        if(flag) System.out.println(0);
        else {
            long a = cnt[0];
            long b = cnt[1];
            System.out.println(a*b*2);
        }

    }

    // color 는 1 아니면 2
    static void dfs(int idx,int c) {
        int nextColor = 3 - c;
        for(int next : adj[idx]) {
            if(visit[next]) {
                if(color[next] == c) {
                    flag = true;
                    return;
                }
            }
            else {
                visit[next] = true;
                color[next] = nextColor;
                cnt[nextColor-1]++;
                dfs(next,nextColor);
            }
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
