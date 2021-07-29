package boj.P16929;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static char[][] arr = new char[51][51];
    static boolean[][] visited = new boolean[51][51];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean answer = false;

    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        m =fs.nInt();
        for(int i=0;i<n;i++){
            char[] temp = fs.next().toCharArray();
            if (m >= 0) System.arraycopy(temp, 0, arr[i], 0, m);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(answer) break;
                dfs(i,j,i,j,i,j,new boolean[n][m]);
            }
        }

        System.out.println(answer?"Yes":"No");

    }

    static void dfs(int prevX,int prevY, int curX,int curY,int targetX,int targetY,boolean[][] ch){
        for(int i=0;i<4;i++){
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];

            if(checkRange(nextX,nextY) && !ch[nextX][nextY] && arr[curX][curY] == arr[nextX][nextY]){
                ch[nextX][nextY] = true;
                if(nextX != prevX && nextY != prevY && nextX == targetX && nextY == targetY){
                    answer = true;
                    return;
                }
                dfs(curX,curY,nextX,nextY,targetX,targetY,ch);

                ch[nextX][nextY] = false;
            }
        }
    }

    static boolean checkRange(int x,int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public void stCheck() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
        }

        public int nInt() throws Exception{
            stCheck();
            return Integer.parseInt(st.nextToken());
        }

        public String next() throws Exception{
            stCheck();
            return st.nextToken();
        }
    }
}