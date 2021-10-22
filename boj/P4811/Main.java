package boj.P4811;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static long[][] dp = new long[31][31];
    public static void main(String[] args) throws Exception{
        for(int i=0;i<=30;i++) dp[0][i] = 1;

        for(int i=1;i<=30;i++){
            for(int j=i;j<=30;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        while(true) {
            int n = fs.nInt();
            if(n == 0) break;
            sb.append(dp[n][n]).append('\n');
        }

        System.out.println(sb);

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
