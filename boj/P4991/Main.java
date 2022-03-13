package boj.P4991;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};
    static final int INF = 987654321;
    static char[][] board;
    static Point[] points;
    static int[][] adj;
    static int pSize;
    static int w,h;
    static int answer;

    public static void main(String[] args) throws Exception{

        while(true){
            w = fs.nInt();
            h = fs.nInt();

            if(w==0 && h==0) return;

            input();
            calDistanceAll();
            dfs(1,new boolean[10],new ArrayList<>());
            System.out.println(answer==INF ? -1 : answer);

        }
    }


    private static void dfs(int idx, boolean[] visit, ArrayList<Integer> order) {
        if(idx == pSize) {
            int cur = 0;
            int dist = 0;
            for(int o : order) {
                if(adj[cur][o] == INF) {
                    return;
                }
                dist += adj[cur][o];
                cur = o;
            }
            answer = Math.min(answer,dist);
            return;
        }
        for(int i=1;i<pSize;i++) {
            if(!visit[i]) {
                visit[i] = true;
                order.add(i);
                dfs(idx + 1,visit,order);
                order.remove(order.size()-1);
                visit[i] = false;
            }
        }
    }


    private static void input() throws Exception {
        board = new char[h][w];
        points = new Point[11];
        adj = new int[10][10];
        pSize = 1;
        answer = INF;

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                adj[i][j] = INF;
            }
        }

        for(int i=0;i<h;i++) {
            char[] temp = fs.next().toCharArray();
            for(int j=0;j<w;j++) {
                if(temp[j] == '*') {
                    points[pSize] = new Point(i,j);
                    board[i][j] = (char)(pSize+'0');
                    pSize += 1;
                    continue;
                }
                if(temp[j] == 'o') {
                    points[0] = new Point(i,j); // start 지점
                    board[i][j] = '0';
                    continue;
                }
                board[i][j] = temp[j];
            }
        }
    }

    static void calDistanceAll() {

        for(int i=0;i<pSize;i++) {
            int[][] visit = new int[20][20];

            for(int j=0;j<20;j++){
                for(int k=0;k<20;k++){
                    visit[j][k] = -1;
                }
            }

            Queue<Point> q = new LinkedList<>();
            visit[points[i].x][points[i].y] = 0;
            q.add(new Point(points[i].x,points[i].y));

            while(!q.isEmpty()) {
                Point cur = q.poll();

                for(int j=0;j<4;j++) {
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];

                    if(nx>=0 && nx<h && ny>=0 && ny <w && board[nx][ny]!='x' && visit[nx][ny] == -1) {
                        visit[nx][ny] = visit[cur.x][cur.y] + 1;
                        if(board[nx][ny] != '.') {
                            int num = (board[nx][ny]-'0');
                            adj[i][num] = visit[nx][ny];
                        }
                        q.add(new Point(nx,ny));
                    }
                }
            }
        }
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }
    }
}
