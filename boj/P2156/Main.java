package boj.P2156;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main 설명 : 포도주 시식
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/01/04
**/
public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[] arr = new int[10001];
    static int[] dp = new int[10001];
    public static void main(String[] args) throws Exception{
        input();
        dp[0] = arr[0];
        dp[1] = arr[1]+arr[0];
        dp[2] = Math.max(arr[1]+arr[0],
                Math.max(arr[1]+arr[2],arr[0]+arr[2]));

        for(int i=3;i<n;i++){
            // 마시는 경우
            dp[i] = Math.max(
                    arr[i]+dp[i-2],arr[i]+arr[i-1]+dp[i-3]
            );
            // 안마시는 경우
            dp[i] = Math.max(dp[i],dp[i-1]);
        }
        System.out.println(dp[n-1]);
    }

    static void input() throws Exception{
        n =fs.nInt();
        for(int i=0;i<n;i++){
            arr[i] = fs.nInt();
        }
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
