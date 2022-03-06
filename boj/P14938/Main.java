package boj.P14938;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[] items = new int[101];
    static int[][] adj = new int[101][101];
    static int INF  = 987654321;
    static int n,m,r;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        r = fs.nInt();
        for(int i=1;i<=n;i++) {
            items[i] = fs.nInt();
        }
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(i==j) continue;
                adj[i][j] = INF;
            }
        }
        for(int i=0;i<r;i++) {
            int a = fs.nInt();
            int b = fs.nInt();

            adj[a][b] = fs.nInt();
            adj[b][a] = adj[a][b];
        }

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                for(int k=1;k<=n;k++) {
                    if(i!=j && j!=k && k!=i && adj[j][i]!=INF && adj[i][k]!=INF) {
                        adj[j][k] = Math.min(adj[j][i] + adj[i][k], adj[j][k]);
                    }
                }
            }
        }

        int answer = 0;
        for(int i=1;i<=n;i++) {
            int s = 0;
            for(int j=1;j<=n;j++){
                if(adj[i][j] <= m) {
                    s += items[j];
                }
            }
            answer = Math.max(answer,s);
        }
        System.out.println(answer);

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
