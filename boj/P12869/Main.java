package boj.P12869;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[][][] dp = new int[61][61][61];
    static int[] damage = new int[]{1,3,9};
    static int[] scv = new int[3];
    static int n;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        for(int i=0;i<n;i++) {
            scv[i] = fs.nInt();
        }

        for(int i=0;i<61;i++)
            for(int j=0;j<61;j++)
                for(int k=0;k<61;k++)
                    dp[i][j][k] = -1;

        dp[0][0][0] = 0;

        System.out.println(dfs(scv[0],scv[1],scv[2]));

    }
    static int dfs(int x,int y,int z){
        // 방문체크
        if(dp[x][y][z] != -1) return dp[x][y][z];

        dp[x][y][z] = Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    if(i!=j && j!=k && i!=k){
                        dp[x][y][z] = Math.min(
                                dp[x][y][z],
                                dfs(better(x-damage[i]),better(y-damage[j]),better(z-damage[k]))+1
                        );
                    }
                }
            }
        }

        return dp[x][y][z];
    }

    static int better(int x){
        return Math.max(x,0);
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
