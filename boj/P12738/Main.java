package boj.P12738;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//  백준 java 에서 속도 1등 !
public class Main {
    static Fs fs = new Fs();
    static int n;
    static int size = 0;
    static int[] arr = new int[1000001];

    public static void main(String[] args) throws Exception {
        n = fs.nInt();

        for(int i=0;i<n;i++) {
            int next = fs.nInt();
            if(size == 0 ||  (size>0 && arr[size-1] < next) ) arr[size++] = next;
            else arr[binarySearch(next)] = next;

        }
        System.out.println(size);

    }
    // return index of arr
    public static int binarySearch(int next){
        int low = 0;
        int high = size - 1;

        int ret = low;
        while(low <= high){
            int mid = (low + high ) / 2;

            if(arr[mid] >= next) {
                high = mid - 1;
                ret = mid;
            }
            else low = mid + 1;
        }

        return ret;
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
