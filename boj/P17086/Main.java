package boj.P17086;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[] dx = {-1,1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,-1,1,-1,1,-1,1};
    static int[][] dist = new int[51][51];

    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        m = fs.nInt();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dist[i][j] = -1;
            }
        }

        Queue<Point> q = new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(fs.nInt() == 1){
                    q.add(new Point(i,j));
                    dist[i][j] = 0;
                }
            }
        }

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int dir=0;dir<8;dir++){
                int nx = p.x + dx[dir];
                int ny = p.y + dy[dir];

                if(checkRange(nx,ny) && dist[nx][ny] == -1){
                    dist[nx][ny] = dist[p.x][p.y] + 1;
                    q.add(new Point(nx,ny));
                }
            }
        }

        int answer = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                answer = Math.max(answer,dist[i][j]);
            }
        }

        System.out.println(answer);

    }
    static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<m;
    }

    static class Point{
        int x,y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
