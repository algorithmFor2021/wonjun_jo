package boj.P14003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        int[] origin = new int[n];
        int[] arr = new int[n];
        int[] dp = new int[n];
        int size = 0;
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<n;i++) {
            int next = fs.nInt();
            origin[i] = next;
            int low = 0;
            int high = size - 1;
            int ans = -1;
            while(low <= high) {
                int mid = (low + high) / 2;

                if(arr[mid] < next) {
                    low = mid + 1;
                }else {
                    high = mid - 1;
                    ans = mid;
                }
            }

            if(ans == -1) {
                arr[size] = next;
                dp[i] = size++;
            }else {
                arr[ans] = next;
                dp[i] = ans;
            }
        }

        sb.append(size).append('\n');
        size--;
        Stack<Integer> stk = new Stack<>();
        for(int i=n-1;i>=0;i--) {
            if(dp[i] == size) {
                size--;
                stk.add(origin[i]);
            }
        }

        while(!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);


    }

    static class Fs {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st=  new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
