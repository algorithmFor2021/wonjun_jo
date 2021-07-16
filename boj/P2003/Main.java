package boj.P2003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[] arr = new int[10001];
    static int n,m;
    public static void main(String[] args) throws Exception{
        int answer = 0;
        n = fs.nInt();
        m = fs.nInt();
        for(int i=0;i<n;i++) arr[i] = fs.nInt();
        int st=0,ed=0;
        int sum = arr[0];
        while(st<n){
            if(sum == m) answer++;
            if(sum >= m){
                if(st==ed && ed+1 <n) sum+=arr[++ed];
                else sum -= arr[st++];
            }
            else{
                if(ed+1 >= n) break;
                sum += arr[++ed];
            }
        }

        System.out.println(answer);
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
