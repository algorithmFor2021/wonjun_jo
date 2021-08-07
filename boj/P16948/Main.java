package boj.P16948;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[] dx = {-2,-2,0,0,2,2};
    static int[] dy = {-1,1,-2,2,-1,1};
    static int[] t = {0,0};
    static boolean[][] ch = new boolean[200][200];
    static int answer = -1;

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        Point st = new Point(fs.nInt(), fs.nInt(), 0);
        t[0] = fs.nInt(); t[1] = fs.nInt();

        Queue<Point> q = new LinkedList<>();
        q.add(st);
        ch[st.x][st.y] = true;

        while(!q.isEmpty()){
            Point p = q.poll();

            if(p.x == t[0] && p.y==t[1]){
                answer = p.cnt;
                break;
            }

            for(int i=0;i<6;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(checkRange(nx,ny) && !ch[nx][ny]){
                    ch[nx][ny] = true;
                    q.add(new Point(nx,ny,p.cnt+1));
                }

            }

        }

        System.out.println(answer);
    }

    static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<n;
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

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
