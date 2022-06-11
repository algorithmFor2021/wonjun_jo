package boj.P2660;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[][] dist = new int[51][51];
    static int n;
    static int INF = 987654321;

    public static void main(String[] args) throws Exception{
        n = fs.nInt();

        for(int i=0;i<51;i++) {
            for(int j=0;j<51;j++) {
                dist[i][j] = INF;
            }
        }

        while(true) {
            int a = fs.nInt();
            int b = fs.nInt();

            if(a == -1) break;

            dist[a][b] = 1;
            dist[b][a] = 1;

        }

        // k = 중간 경로
        for(int k=1;k<=n;k++) {
            for(int i=1;i<=n;i++) {
                for(int j=1;j<=n;j++) {
                    if(i != j && k != i && k != j)
                    dist[i][j] = Math.min(dist[i][j],dist[i][k] + dist[k][j]);
                }
            }
        }

        int[] ans = new int[51];
        int g = INF;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(dist[i][j] != INF) {
                    ans[i] = Math.max(ans[i],dist[i][j]);
                }
            }
            g = Math.min(g,ans[i]);
        }

        for(int i=1;i<=n;i++) {
            if(ans[i] == g) {
                list.add(i);
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for(int l : list) sb.append(l).append(" ");
        System.out.println(g+" "+list.size());
        System.out.println(sb);
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
