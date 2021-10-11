package boj.P1600;

import boj.MainTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs= new Fs();
    static int[][][] d = new int[200][200][31];
    static int k;
    static int[][] arr = new int[200][200];
    static int w,h;
    static int[] dx = {-1,1,0,0,-1,-2,-2,-1,1,2,2,1};
    static int[] dy = {0,0,-1,1,-2,-1,1,2,2,1,-1,-2};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        k = fs.nInt();
        w = fs.nInt();
        h = fs.nInt();

        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                arr[i][j] = fs.nInt();
                for(int l=0;l<=k;l++) d[i][j][l]= - 1;
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0,0));
        d[0][0][0] = 0;

        while(!q.isEmpty()){
            Point p = q.poll();

            int nextDirRange = p.k < k ? 12 : 4;

            for(int i=0;i<nextDirRange;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int nz = i >=4 ? p.k+1 : p.k;

                if(checkRange(nx,ny,nz)) {
                    d[nx][ny][nz] = d[p.x][p.y][p.k] + 1;
                    q.add(new Point(nx, ny, nz));
                }
            }

        }

        for(int i=0;i<=k;i++){
            if(d[h-1][w-1][i] != -1) answer = Math.min(answer,d[h-1][w-1][i]);
        }

        System.out.println(answer==Integer.MAX_VALUE?-1:answer);



    }

    static boolean checkRange(int x,int y,int z){
        return x>=0 && x<h && y>=0 && y<w && arr[x][y]==0 && d[x][y][z]==-1;
    }

    static class Point{
        int x,y,k;

        public Point(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }


    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        public String next() throws Exception{
            if(st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
