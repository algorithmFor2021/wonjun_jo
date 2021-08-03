package boj.P9663;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static boolean[][] chess = new boolean[15][15];
    static boolean[] colCh = new boolean[15];
    static long answer = 0;
    static int n;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        dfs(0);
        System.out.println(answer);
    }
    static void dfs(int r){
        if(r == n) {
            answer++;
            return;
        }

        for(int c = 0; c<n;c++){
            if(check(r,c)){
                colCh[c] = true;
                chess[r][c]=true;
                dfs(r+1);
                colCh[c] = false;
                chess[r][c]=false;
            }
        }
    }

    static boolean check(int r,int c){
        if(colCh[c]) return false;
        for(int i=1;i<=r && r-i>=0 ;i++){
            if(c-i>=0 && chess[r-i][c-i]) return false;
            if(c+i<n && chess[r-i][c+i]) return false;
        }
        return true;
    }
    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st=  new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
