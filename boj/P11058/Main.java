package boj.P11058;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static long[] dp = new long[101];
    static int n;
    public static void main(String[] args) throws Exception{
        n =fs.nInt();
        for(int i=1;i<=6;i++) dp[i] = i;
        for(int i=7;i<=n;i++){
            long k = 2;
            for(int j=i-3;j>=1;j--){
                dp[i] = Math.max(dp[j]*k++,dp[i]);
            }
        }

        System.out.println(dp[n]);
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
