package boj.P12015;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        int[] arr = new int[n];
        int arrSize = 1;
        arr[0] = fs.nInt();
        for(int i=1;i<n;i++){
            int next = fs.nInt();
            int low = 0;
            int high = arrSize-1;
            int mid = -1;
            int loc = -1;
            while(low <= high) {
                mid = (low + high) / 2;
                if(arr[mid] < next) {
                    low = mid + 1;
                }else {
                    high = mid - 1;
                    loc = mid;
                }
            }
            if(loc == -1) {
                arr[arrSize++] = next;
            }else {
                arr[loc] = next;
            }
        }

        System.out.println(arrSize);
    }
}
