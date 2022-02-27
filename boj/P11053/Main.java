package boj.P11053;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        int[] arr = new int[n];
        int[] dp = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = fs.nInt();
        }

        int answer = 0;
        for(int i=0;i<n;i++){
            int ret = 0;
            for(int j=i-1;j>=0;j--){
                if(arr[j] < arr[i] ) {
                    ret = Math.max(dp[j],ret);
                }
            }
            dp[i] = ret + 1;
            answer = Math.max(dp[i],answer);
        }
        System.out.println(answer);

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
