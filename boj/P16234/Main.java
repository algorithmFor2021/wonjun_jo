package boj.P16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int[][] arr = new int[101][101];
    static int l, r , n;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int ans = 0;

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        l = fs.nInt();
        r = fs.nInt();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = fs.nInt();
            }
        }

        while(markingAndCheck()){}

        System.out.println(ans);

    }
    static boolean markingAndCheck(){
        int mark = 1;
        int[][] visited = new int[n+1][n+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j]==0){
                    visited[i][j] = mark;
                    dfs(i,j,mark++,visited);
                }
            }
        }
        if(mark > n*n) return false;

        HashMap<Integer, int[]> map = new HashMap<>();
        for(int i=1;i<mark;i++) map.put(i, new int[]{0,0});

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int[] tmp = map.get(visited[i][j]);
                tmp[0] += arr[i][j];
                tmp[1]++;
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = map.get(visited[i][j])[0] / map.get(visited[i][j])[1];
            }
        }
        ans++;
        return true;
    }



    static void dfs(int x,int y,int mark,int[][] visited){
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(checkRange(nx,ny)) {
                int dif = Math.abs(arr[nx][ny] - arr[x][y]);
                if (visited[nx][ny] != mark && dif >= l && dif <= r) {
                    visited[nx][ny] = mark;
                    dfs(nx, ny, mark, visited);
                }
            }
        }

    }

    static boolean checkRange(int x,int y){
        if(x<0 || x>=n || y<0 || y>=n) return false;
        return true;
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
