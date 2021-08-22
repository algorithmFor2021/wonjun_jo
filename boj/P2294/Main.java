package boj.P2294;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,k;
    static int[] dp = new int[100001];
    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        k = fs.nInt();

        for(int i=0;i<=k;i++) dp[i] = -1;

        for(int i=0;i<n;i++){
            dp[fs.nInt()] = 1;
        }

        for(int i=1;i<=k;i++){
            if(dp[i]==1) continue;
            for(int j=i-1;j>=1;j--){
                if(dp[i-j] > 0 && dp[j] > 0){
                    if(dp[i] == -1) dp[i] = dp[i-j] + dp[j];
                    else dp[i] = Math.min(dp[i],dp[i-j] + dp[j]);
                }
            }
        }

        System.out.println(dp[k]);



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
