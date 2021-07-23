package boj.P10422;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int testCase;
    static long[] dp = new long[5001];

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        dp[0] = 1L;
        dp[2] = 1L;
        for(int i=4;i<=5000;i+=2){
            for(int j=i-2;j>=0;j-=2){
                dp[i] += (dp[j] * dp[i-j-2]);
                dp[i] = dp[i]%1000000007;
            }
        }
        testCase = fs.nInt();

        while(testCase-- >0 ) sb.append(dp[fs.nInt()]).append('\n');

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
