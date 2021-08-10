package boj.P15989;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int testCase;
    static int[] mod = new int[10003];
    static int[] dp = new int[10003];

    public static void main(String[] args) throws Exception{
        testCase = fs.nInt();

        for(int i=1;i<=5;i++) dp[i] = i;
        mod[0] = 1;
        mod[1] = 0;
        for(int i=2;i<=10000;i+=2){
            if(i%3 == 0) mod[i] += mod[i-2]+1;
            else mod[i] = mod[i-2];
            if((i+1)%3==0) mod[i+1] += mod[i-1]+1;
            else mod[i+1] = mod[i-2];
        }

        for(int i=6;i<=10000;i++){
            dp[i] = dp[i-1] + mod[i];
        }

        StringBuilder sb = new StringBuilder();
        while(testCase-->0) sb.append(dp[fs.nInt()]).append('\n');
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
