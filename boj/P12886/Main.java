package boj.P12886;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int sum;
    static boolean[][] ch = new boolean[1501][1501];
    static int answer = 0;
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception{
        int a,b,c;
        a = fs.nInt();
        b = fs.nInt();
        c = fs.nInt();

        sum = a + b + c;

        ch[a][b] = true;
        dfs(a,b);
        System.out.println(answer);


    }
    public static void dfs(int a,int b){
        int c = sum - a - b;
        if(a == b && b == c ){
            answer = 1;
            return;
        }
        go(a,b);
        go(b,c);
        go(a,c);

    }

    private static void go(int a, int b) {
        if(answer != 1) {
            int small = Math.min(a, b);
            int big = Math.max(a, b);
            if (!ch[small * 2][big - small] || !ch[big - small][small * 2]) {
                ch[small * 2][big - small] = ch[big - small][small * 2] = true;
                dfs(small * 2, big - small);
            }
        }
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
