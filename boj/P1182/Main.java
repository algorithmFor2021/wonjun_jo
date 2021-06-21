package boj.P1182;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int n,s;
    static int[] arr = new int[21];
    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        s = fs.nInt();
        for(int i=0;i<n;i++) arr[i] = fs.nInt();
        // 공집합 제거
        if(s==0) System.out.println(dfs(0, 0) - 1);
        else System.out.println(dfs(0, 0));
    }

    static public int dfs(int idx, int sum){
        if(idx == n && sum == s) return 1;
        else{
            if(idx == n ) return 0;
            return dfs(idx+1,sum+arr[idx]) + dfs(idx+1,sum);
        }

    }

    static class Fs{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
