package boj.P15486;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[] dp = new int[1500001];
    static int[] t = new int[1500001];
    static int[] p = new int[1500001];

    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        for(int i=1;i<=n;i++){
            t[i] = fs.nInt();
            p[i] = fs.nInt();
            dp[i] = dp[i-1];
            for(int j=i;j>i-50;j--){
                if(j>=0 && j+t[j]-1 == i){
                    dp[i] = Math.max(dp[j-1]+p[j],dp[i]);
                }
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
