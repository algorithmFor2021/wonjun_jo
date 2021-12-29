package boj.P1937;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[][] arr = new int[501][501];
    static int[][] dp = new int[501][501];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        input();
        System.out.println(solve());
    }

    static int solve(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(dp[i][j]==0){
                    dp[i][j] = dfs(i,j);
                }
            }
        }
        int r = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                r = Math.max(r,dp[i][j]);
            }
        }
        return r;
    }

    static int dfs(int x,int y){
        int r = 1;
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(checkRange(nx,ny) && arr[nx][ny] > arr[x][y]){
                if(dp[nx][ny] > 0) r = Math.max(r,dp[nx][ny]+1);
                else r = Math.max(r,dfs(nx,ny)+1);
            }
        }
        return dp[x][y] = r;
    }
    static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<n ;
    }

    static void input() throws Exception{
        n =fs.nInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = fs.nInt();
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
