package boj.P1562;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int MOD = 1000000000;
    static int[][][] dp = new int[101][10][1<<10];
    public static void main(String[] args) throws Exception{

        n = fs.nInt();
        System.out.println(calc());

    }
    private static long calc() {
        long sum=0;
        int i, j, k, bit;

        for(i=1; i<10; i++) dp[1][i][1<<i] = 1;

        for (i=2; i<=n; i++)
            for (j=0; j<=9; j++)
                for(k=0; k<1024; k++) {
                    bit = k | (1<<j);
                    dp[i][j][bit] = (dp[i][j][bit] +
                            ((0<j ? dp[i-1][j-1][k] : 0) +
                                    (j<9 ? dp[i-1][j+1][k] : 0))%MOD
                    )%MOD;
                }
        for (i=0; i<10; i++) sum = (sum + dp[n][i][1024-1])%MOD;
        return sum;
    }


    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st= new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
