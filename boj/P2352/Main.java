package boj.P2352;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Main 설명 : 반도체 설계
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/27
**/

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[] arr = new int[40000];
    static int size = 0;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();

        for(int i=0;i<n;i++) {
            int next = fs.nInt();

            if(size == 0) {
                arr[size++] = next;
            }
            else {
                bnSearch(next);
            }
        }

        System.out.println(size);

    }

    static void bnSearch(int next) {
        // next보다 큰 수 중에 가장 작은 수
        int low = 0;
        int high = size;
        int ret = -1;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(arr[mid] > next) {
                high = mid - 1;
                ret = mid;
            }else {
                low = mid + 1;
            }
        }

        if(ret == -1) {
            arr[size++] = next;
        }else {
            arr[ret] = next;
        }
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
