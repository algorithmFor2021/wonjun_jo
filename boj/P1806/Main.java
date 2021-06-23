package boj.P1806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,s;
    static int ans = Integer.MAX_VALUE;
    static int sum = 0;
    static int[] arr = new int[100001];
    public static void main(String[] args) throws Exception{
        n = fs.nInt(); s = fs.nInt();
        for(int i=0;i<n;i++) arr[i] = fs.nInt();
        int st = 0;
        int ed = 0;
        sum += arr[st];

        while(ed<n){
            if(sum >= s) ans = Math.min(ans,ed-st+1);
            if(sum  < s){
                ed++;
                if(ed< n) sum += arr[ed];
            }
            else{
                if(st == ed) {
                    st++;ed++;
                    if(ed < n) sum = arr[ed];
                }
                sum -= arr[st++];
            }
        }

        System.out.println(ans==Integer.MAX_VALUE?0:ans);

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