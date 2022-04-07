package boj.P1799;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * Main 설명 : 비숍
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/07
**/
public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[][] arr = new int[10][10];
    static int wanswer = 0;
    static int banswer = 0;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = fs.nInt();
            }
        }

        dfs(0,0,new boolean[n][n],true);
        dfs(1,0,new boolean[n][n],false);

        System.out.println(wanswer + banswer);
    }

    static void dfs(int idx,int sum,boolean[][] visit,boolean isWhite) {
        if(isWhite){
            wanswer = Math.max(wanswer,sum);
        }else {
            banswer = Math.max(banswer,sum);
        }

        if(idx >= n*n) return;


        int x = idx / n;
        int y = idx % n;

        int nextX = (idx+2)/n;
        int nextY = (idx+2)%n;
        int nextIdx = idx+2;
        if(n%2 == 0 && x < nextX) {
            if(nextY == 0) nextIdx += 1;
            else nextIdx -= 1;
        }

        if(arr[x][y] == 1 && isPossible(x,y,visit)) {
            visit[x][y] = true;
            dfs(nextIdx,sum+1,visit,isWhite);
            visit[x][y] = false;
        }
        dfs(nextIdx,sum,visit,isWhite);
    }

    static boolean isPossible(int x,int y,boolean[][] visit) {
        int k = 1;
        for(int i=x-1;i>=0;i--) {
            int left = y - k;
            int right = y + k;
            if(left>=0 && visit[i][left]) return false;
            if(right<n && visit[i][right]) return false;
            k++;
        }
        return true;
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
