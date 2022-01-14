package boj.P11725;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Main 설명 : 트리의 부모 찾기
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/01/14
**/
public class Main {
    static Fs fs = new Fs();
    static int n;
    static List<Integer>[] adj = new ArrayList[100001];
    static boolean[] chk = new boolean[100001];
    static int[] parent = new int[100001];

    public static void main(String[] args) throws Exception{
        input();
        chk[1] = true;
        dfs(1);
        StringBuilder sb = new StringBuilder();
        for(int i=2;i<=n;i++){
            sb.append(parent[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int cur){
        for(int next : adj[cur]){
            if(!chk[next]){
                chk[next] = true;
                parent[next] = cur;
                dfs(next);
            }
        }
    }

    static void input() throws Exception{
        n = fs.nInt();
        for(int i=0;i<=n;i++) adj[i] = new ArrayList<>();
        for(int i=0;i<n-1;i++){
            int s = fs.nInt();
            int e = fs.nInt();
            adj[s].add(e);
            adj[e].add(s);
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
