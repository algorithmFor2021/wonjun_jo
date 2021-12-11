package boj.P2589;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n,m;
    static char[][] board = new char[51][51];
    static int answer = 0;

    public static void main(String[] args) throws Exception{
        input();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j] == 'L'){

                    answer = Math.max(getMaxDistance(i,j),answer);
                }
            }
        }

        System.out.println(answer);
    }
    static void input() throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        for(int i=0;i<n;i++){
            char[] temp = fs.next().toCharArray();
            for(int j=0;j<m;j++){
                board[i][j] = temp[j];
            }
        }
    }

    static int getMaxDistance(int x,int y){
        int ret = 0;
        Queue<Point> q = new LinkedList<>();
        boolean[][] ch = new boolean[51][51];
        q.add(new Point(x,y,0));
        ch[x][y] = true;

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(checkRange(nx,ny) && !ch[nx][ny]){
                    ch[nx][ny] = true;
                    Point next = new Point(nx,ny,p.cnt+1);
                    ret = Math.max(ret,next.cnt);
                    q.add(next);
                }
            }
        }
        return ret;
    }

    static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<m && board[x][y]=='L';
    }

    static class Point{
        int x,y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");


        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }
    }
}
