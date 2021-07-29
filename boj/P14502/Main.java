package boj.P14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 7 7
 * 2 0 0 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 0 0
 * 0 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 0 1 0 0 0 0 0
 */
public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[][] lab = new int[65][65];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer = 0;

    static ArrayList<Point> cand = new ArrayList<>(65); // max = 64

    public static void main(String[] args) throws Exception {
        input();
        dfs(0,0,new ArrayList<>());
        System.out.println(answer);
    }

    static int[][] getLab(){
        int[][] temp = new int[65][65];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                temp[i][j] = lab[i][j];
            }
        }
        return temp;
    }

    static int getSafeArea(){
        int ret = 0;
        int[][] temp = getLab();
        boolean[][] visited = new boolean[65][65];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(temp[i][j]==2 && !visited[i][j]){
                    visited[i][j] = true;
                    bfs(temp,visited,i,j);
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(temp[i][j]==0) ret++;
            }
        }
        return ret;
    }

    static void bfs(int[][] temp,boolean[][] visited,int x,int y){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x,y));

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx>=0 && nx<n && ny>=0 && ny<m && temp[nx][ny]==0 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    temp[nx][ny] = 2;
                    q.add(new Point(nx,ny));
                }
            }
        }
    }

    static void dfs(int idx,int cnt,ArrayList<Integer> idxList){
        if(idx > cand.size() || cnt > 3) return;
        if(cnt == 3){
            int[] x = new int[3];
            int[] y = new int[3];
            for(int i=0;i<3;i++){
                x[i] = cand.get(idxList.get(i)).x;
                y[i] = cand.get(idxList.get(i)).y;
                lab[x[i]][y[i]] = 1;
            }
            answer = Math.max(getSafeArea(),answer);
            for(int i=0;i<3;i++) lab[x[i]][y[i]] = 0;
            return;
        }

        idxList.add(idx);
        dfs(idx+1,cnt+1,idxList);
        idxList.remove(idxList.size()-1);
        dfs(idx+1,cnt,idxList);

    }

    static void input() throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                lab[i][j] = fs.nInt();
                if(lab[i][j] == 0) cand.add(new Point(i,j));
            }
        }
    }


    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Fs {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
