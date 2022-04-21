package boj.P1715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        int[] arr = new int[n];

        for(int i=0;i<n;i++) arr[i] = fs.nInt();

        Arrays.sort(arr);

        if(n == 1) {
            System.out.println(0);
            return;
        }

        int sum = 0;
        for(int i=0;i<n-1;i++) {
            sum += (arr[i] + arr[i+1]);
            arr[i+1] = (arr[i] + arr[i+1]);
        }

        System.out.println(sum);
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
