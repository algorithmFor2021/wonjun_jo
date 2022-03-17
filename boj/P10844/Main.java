package boj.P10844;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * Main 설명 : 쉬운 계단 수
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/03/17
**/

public class Main {
    static Fs fs = new Fs();
    static int MOD = 1_000_000_000;
    static int[][] dp = new int[101][10];

    public static void main(String[] args) throws Exception {
        int n = fs.nInt();

        /**
         *  dp[i][j] -> i= 길이 , j = 마지막으로 끝난 수
         *
         *  dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]
         *
         */
        for(int i=1;i<=9;i++) dp[1][i] = 1; // 0 으로 시작하는 수는 계단 수가 아님.

        for(int i=2;i<=100;i++) {
            for(int j=0;j<=9;j++) {
                dp[i][j] = (dp[i][j] + (
                        (j>0 ? dp[i-1][j-1] : 0) +
                                (j<9 ? dp[i-1][j+1] : 0)
                        )%MOD )%MOD;
            }
        }

        long sum = 0;
        for(int i=0;i<10;i++) sum = (sum + dp[n][i])%MOD;

        System.out.println(sum);



    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st=  new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
