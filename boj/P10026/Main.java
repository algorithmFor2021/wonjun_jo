package boj.P10026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int ans1 =0,ans2 = 0;

    public static void main(String[] args) throws Exception{
        n  = fs.nInt();
        char[][] map1 = new char[101][101];
        char[][] map2 = new char[101][101];
        boolean[][] ch1 = new boolean[101][101];
        boolean[][] ch2 = new boolean[101][101];

        for(int i=0;i<n;i++){
            int j = 0;
            for(char c :  fs.next().toCharArray()){
                map1[i][j] = c;
                if(c == 'R' || c=='G') map2[i][j] = 'R';
                else map2[i][j] = 'B';
                j++;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!ch1[i][j]){
                    ch1[i][j] = true;
                    dfs(map1,ch1,i,j);
                    ans1++;
                }
                if(!ch2[i][j]){
                    ch2[i][j] = true;
                    dfs(map2,ch2,i,j);
                    ans2++;
                }
            }
        }
        System.out.println(ans1 + " " + ans2);
    }
    static void dfs(char[][] map,boolean[][] ch,int x,int y){
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(checkRange(nx,ny) && !ch[nx][ny] && map[x][y]==map[nx][ny]){
                ch[nx][ny] = true;
                dfs(map,ch,nx,ny);
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

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
