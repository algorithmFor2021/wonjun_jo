package boj.P2206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] arr = new int[1000][1000];
    static int[][][] dist = new int[2][1000][1000];

    public static void main(String[] args) throws Exception{
        input();
        init();
        bfs();
        System.out.println(getAnswer());
    }

    private static void bfs() throws Exception{
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0,0));
        dist[0][0][0] = 1;

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int dir = 0;dir<4;dir++){
                int nx = p.x + dx[dir];
                int ny = p.y + dy[dir];

                if(nx>=0 && nx < n && ny>=0 && ny<m ){
                    if(arr[nx][ny] == 0 && dist[p.k][nx][ny]==-1){
                        dist[p.k][nx][ny] = dist[p.k][p.x][p.y] + 1;
                        q.add(new Point(nx,ny,p.k));
                    }
                    if(arr[nx][ny] == 1 && p.k + 1 <= 1 && dist[p.k+1][nx][ny]==-1){
                        dist[p.k+1][nx][ny] = dist[p.k][p.x][p.y] + 1;
                        q.add(new Point(nx,ny,p.k+1));
                    }
                }
            }
        }
    }

    private static void init() throws Exception{
        for(int i=0;i<2;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<m;k++){
                    dist[i][j][k] = -1;
                }
            }
        }
    }

    private static void input() throws Exception {
        n = fs.nInt();
        m = fs.nInt();
        for(int i=0;i<n;i++){
            char[] temp = fs.next().toCharArray();
            for(int j=0;j<m;j++){
                arr[i][j] = temp[j]-'0';
            }
        }
    }

    static private int getAnswer(){
        if(dist[0][n-1][m-1] == -1 && dist[1][n-1][m-1] == -1) return -1;
        if(dist[0][n-1][m-1] != -1 && dist[1][n-1][m-1] != -1)
            return Math.min(dist[0][n-1][m-1],dist[1][n-1][m-1]);
        return Math.max(dist[0][n-1][m-1],dist[1][n-1][m-1]);
    }

    static class Point{
        int x,y;
        int k;

        public Point(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }


    static class Fs {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
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
