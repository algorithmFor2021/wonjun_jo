package boj.P5557;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static long[][] dp = new long[101][21];
    static int[] arr = new int[101];
    static int n,target;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        for(int i=1;i<n;i++) arr[i] = fs.nInt();
        target = fs.nInt();

        dp[1][arr[1]] = 1; // 1개의 수로 arr[1]을 만드는 경우의수 = 1

        for(int i=2;i<n;i++){
            for(int j=0;j<=20;j++){
                if(dp[i-1][j]>0){
                    if(j + arr[i] <= 20) dp[i][j+arr[i]] += dp[i-1][j];
                    if(j - arr[i] >= 0) dp[i][j-arr[i]] += dp[i-1][j];
                }
            }
        }

        System.out.println(dp[n-1][target]);
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
