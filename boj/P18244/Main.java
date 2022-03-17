package boj.P18244;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int MOD = 1_000_000_007;
    static int[][][] dp = new int[101][10][5];
    public static void main(String[] args) throws Exception {
        int n = fs.nInt();

        // dp[i][j][k] = i = 길이 , j = 마지막수 , k = 0,1,2 -> 증가된 수 , 3,4 -> 감소된 수 체크
        for(int i=0;i<10;i++) dp[1][i][0] = 1;

        for(int i=2;i<=n;i++) {
            for(int j=0;j<=9;j++) {
                if(j < 9) {
                    dp[i][j][3] = (dp[i][j][3] + dp[i-1][j+1][0])%MOD;
                    dp[i][j][3] = (dp[i][j][3] + dp[i-1][j+1][1])%MOD;
                    dp[i][j][3] = (dp[i][j][3] + dp[i-1][j+1][2])%MOD;
                    dp[i][j][4] = (dp[i][j][4] + dp[i-1][j+1][3])%MOD;
                }
                if(j > 0) {
                    // 1개가 증가된 상태는 모든 감소된 상태에서 한개 증가시 가능함.
                    dp[i][j][1] = (dp[i][j][1] + dp[i-1][j-1][4])%MOD;
                    dp[i][j][1] = (dp[i][j][1] + dp[i-1][j-1][3])%MOD;
                    // 증가 상태에서 1개씩 증가
                    dp[i][j][1] = (dp[i][j][1] + dp[i-1][j-1][0])%MOD;
                    dp[i][j][2] = (dp[i][j][2] + dp[i-1][j-1][1])%MOD;
                }
            }
        }

        long sum = 0;
        for(int i=0;i<10;i++) {
            for(int j=0;j<5;j++) {
                sum = (sum + dp[n][i][j])%MOD;
            }
        }

        System.out.println(sum);
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
