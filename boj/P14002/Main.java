package boj.P14002;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * Main 설명 : 가장 긴 부분 수열 4
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/03/03
**/
public class Main {
    static Fs fs = new Fs();
    static class Fs {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws Exception {
        int n = fs.nInt();
        int[] arr = new int[n];
        int[] dp = new int[n];
        int[] index = new int[n];
        int maxD = -1;
        int maxI = -1;
        for(int i=0;i<n;i++) {
            arr[i] = fs.nInt();
            int r = 0;
            int k = i;
            for(int j=i-1;j>=0;j--) {
                if(arr[i] > arr[j] && dp[j] > r) {
                    r = dp[j];
                    k = j;
                }
            }
            index[i] = k;
            dp[i] = r + 1;
            if(maxD < dp[i]) {
                maxD = dp[i];
                maxI = i;
            }
        }

        System.out.println(maxD);

        Stack<Integer> stk = new Stack<>();
        while(index[maxI] != maxI) {
            stk.add(arr[maxI]);
            maxI = index[maxI];
        }
        stk.add(arr[maxI]);

        StringBuilder sb = new StringBuilder();
        while(!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }
        System.out.println(sb);

    }
}
