package boj.P9465;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    public static void main(String[] args) throws Exception{
        int t = fs.nInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0){
            int n = fs.nInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for(int i=0;i<2;i++){
                for(int j=0;j<n;j++){
                    if(i==0) a[j] = fs.nInt();
                    else b[j] = fs.nInt();
                }
            }
            int[][] dp = new int[n][2];
            dp[0][0] = a[0];
            dp[0][1] = b[0];
            if(n>=2) {
                dp[1][0] = dp[0][1] + a[1];
                dp[1][1] = dp[0][0] + b[1];
            }

            for(int i=2;i<n;i++){
                dp[i][0] = Math.max(dp[i-1][1] + a[i],Math.max(dp[i-2][0] + a[i],dp[i-2][1] + a[i]));
                dp[i][1] = Math.max(dp[i-1][0] + b[i],Math.max(dp[i-2][1] + b[i],dp[i-2][0] + b[i]));
            }

            sb.append(Math.max(dp[n-1][0],dp[n-1][1])).append("\n");

        }
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
