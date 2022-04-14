package boj.P2473;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static long[] arr;
    static long diff = Long.MAX_VALUE;
    static long[] ans = new long[3];
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        arr = new long[n];

        for(int i=0;i<n;i++) {
            arr[i] = fs.nInt();
        }

        Arrays.sort(arr);


        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(j+1 == n ) continue;
                long target = -(arr[i] + arr[j]);

                int low = j+1;
                int high = n-1;

                int ansIdx = low;
                while(low <= high) {
                    int mid = (low + high) / 2;

                    if(Math.abs(arr[ansIdx]-target) > Math.abs(arr[mid]-target) ) {
                        ansIdx = mid;
                    }

                    if(arr[mid] < target) {
                        low = mid + 1;
                    }else if(arr[mid] > target) {
                        high = mid - 1;
                    }else {
                        ansIdx = mid;
                        break;
                    }
                }

                if(diff > Math.abs(arr[i]+arr[j]+arr[ansIdx])) {
                    diff = Math.abs(arr[i]+arr[j]+arr[ansIdx]);
                    ans = new long[]{arr[i],arr[j],arr[ansIdx]};
                }

            }
        }

        System.out.println(ans[0] +" "+ ans[1]+" "+ ans[2]);



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
