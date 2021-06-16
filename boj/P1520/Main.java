package boj.P1520;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[][] arr = new int[501][501];
    static int[][] dp = new int[501][501];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = fs.nInt();
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0,0));

    }
    static int dfs(int x,int y){
        if(dp[x][y]!= -1) return dp[x][y];
        else if(x==n-1 && y==m-1) return 1;
        else {
            dp[x][y] = 0;
            for(int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0 && nx < n && ny>=0 && ny <m && arr[nx][ny] < arr[x][y]){
                    dp[x][y] += dfs(nx,ny);
                }
            }
        }
        return dp[x][y];
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
