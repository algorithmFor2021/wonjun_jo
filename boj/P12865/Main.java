package boj.P12865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static int[] w = new int[101];
    static int[] d = new int[100001];
    static int[] v = new int[101];
    static Fs fs = new Fs();

    public static void main(String[] args)  throws Exception{

        n = fs.nInt();
        k = fs.nInt();
        for(int i=1;i<=n;i++){
            w[i] = fs.nInt();
            v[i] = fs.nInt();
        }
        for(int i=1; i<=n; i++){
            for(int j=k; j>=1; j--){
                if(w[i] <= j){
                    d[j] = Math.max(d[j], d[j-w[i]] + v[i]);
                }
            }
        }
        System.out.println(d[k]);
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
