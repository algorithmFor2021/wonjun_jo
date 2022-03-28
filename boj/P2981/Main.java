package boj.P2981;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * Main 설명 : 검문
 * @author jwonjun
 * @version 1.0.0
 * 작성일 : 2022/03/29
**/
public class Main {
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception{
        int n = fs.nInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = fs.nInt();
        }

        // 오름차순 정렬
        Arrays.sort(arr);

        /**
         *
         * A = aM + r , B = bM + r , C = cM + r
         * 일때 , A-B = (a-b)M
         * B-C = (b-c)M
         * 이때 M 은 A-B 와 B-C의 최대 공약수임. (단 , M != 1)
         *
         */

        int v = arr[1] - arr[0];
        for(int i=2;i<n;i++){
            v = gcd(v, arr[i]-arr[i-1]);
        }

        System.out.println(make(v));



    }

    static StringBuilder make(int v) {
        StringBuilder sb = new StringBuilder();

        for(int i=2;i<=(int)Math.sqrt(v);i++) {
            if(v%i==0) sb.append(i).append(" ");
        }
        sb.append(v).append("\n");

        return sb;
    }

    static int gcd(int a,int b) {
        if(a%b==0) return b;
        return gcd(b,a%b);
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
