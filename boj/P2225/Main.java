package boj.P2225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[][] dp = new int[201][201];

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        // dp[i][j] = i개 더해서 j 만들기
        for(int i=1;i<=200;i++){
            dp[1][i] = 1;
            dp[i][1] = i;
        }

        for(int i=2;i<=200;i++){
            for(int j=2;j<=200;j++){
                dp[i][j] = (dp[i][j-1] + dp[i-1][j])%1000000000;
            }
        }

        System.out.println(dp[m][n]);


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
