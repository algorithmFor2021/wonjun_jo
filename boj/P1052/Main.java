package boj.P1052;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,k;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        k = fs.nInt();

        int x = n;
        for(int i=0;i<k-1;i++){
            x -= find(x);
        }
        System.out.println(getAnswer(x));

    }
    static int find(int x){
        int ret = 1;
        while(ret < x){
            ret <<= 1;
        }
        ret >>= 1;
        return ret;
    }

    static int getAnswer(int x){
        // x보다 큰 2의 최소제곱수를 찾는다
        int ret = 1;
        while(ret < x){
            ret <<= 1;
        }
        return ret - x;
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
