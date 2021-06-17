package boj.P2169;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int INF = -100 * 1000 * 1001;
    static int[][] arr = new int[1001][1001];
    static int[][] dp = new int[1001][1001];

    static int n,m;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = fs.nInt();
                dp[i][j] = INF;
            }
        }

        // 1행
        dp[0][0] = arr[0][0];
        for(int j=1;j<m;j++) dp[0][j] = dp[0][j-1]+arr[0][j];

        for(int i=1;i<n;i++){
            int[] tmp = new int[m];
            tmp[0] = dp[i-1][0] + arr[i][0];
            for(int j=1;j<m;j++){
                tmp[j] = Math.max(tmp[j-1],dp[i-1][j]) + arr[i][j];
            }
            int[] tmp2 = new int[m];
            tmp2[m-1] = dp[i-1][m-1] + arr[i][m-1];
            for(int j=m-2;j>=0;j--){
                tmp2[j] = Math.max(tmp2[j+1],dp[i-1][j]) + arr[i][j];
            }
            for(int j=0;j<m;j++){
                dp[i][j] = Math.max(tmp[j],tmp2[j]);
            }
        }
        System.out.println(dp[n-1][m-1]);

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
