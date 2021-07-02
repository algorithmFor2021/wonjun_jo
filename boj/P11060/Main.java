package boj.P11060;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[] dp = new int[1001];
    static int[] arr = new int[1001];
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        for(int i=0;i<n;i++) arr[i] = fs.nInt();
        for(int i=0;i<n;i++) dp[i] = -1;
        dp[0] = 0;
        for(int i=0;i<n;i++){
            if(arr[i]==0 || dp[i] == -1) continue;
            for(int j=i+1;j<=i+arr[i];j++){
                if(j<n){
                    if(dp[j] == -1) dp[j] = dp[i]+1;
                    else dp[j] = Math.min(dp[j],dp[i] + 1);
                }
            }
        }

        System.out.println(dp[n-1]);


    }
    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int  nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
