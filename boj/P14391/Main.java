package boj.P14391;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n, m;
    static int[][] arr;
    static boolean[][] visit;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        input();
        dfs(0,0);
        System.out.println(answer);
    }

    static void dfs(int idx,int sum){
        if(idx == n*m) {
            answer = Math.max(answer,sum);
            return;
        }
        int x = idx / m ;
        int y = idx % m ;

        if(visit[x][y]) dfs(idx+1,sum);

        for(int i=0;i<4;i++) {
            int nx = x;
            int ny = y+i;
            if(checkRange(nx,ny) && checkVisitRange(x,y,nx,ny)) {
                visitRange(x,y,nx,ny,true);
                int next = getNum(x,y,nx,ny);
                dfs(idx+1,sum + next);
                visitRange(x,y,nx,ny,false);
            }

            if(i >= 1) {
                int nx2 = x+i;
                int ny2 = y;
                if(checkRange(nx2,ny2) && checkVisitRange(x,y,nx2,ny2)) {
                    visitRange(x,y,nx2,ny2,true);
                    int next = getNum(x,y,nx2,ny2);
                    dfs(idx+1,sum + next);
                    visitRange(x,y,nx2,ny2,false);
                }
            }
        }
    }

    static void visitRange(int x,int y,int nx,int ny, boolean flag) {
        if(x == nx) {
            for(int i=y;i<=ny;i++) {
                visit[x][i] = flag;
            }
        } else {
            for(int i =x;i<=nx;i++) {
                visit[i][y] = flag;
            }
        }
    }

    static int getNum(int x,int y,int nx,int ny){
        StringBuilder sb = new StringBuilder();
        if(x == nx) {
            for(int i=y;i<=ny;i++) {
                sb.append(arr[x][i]);
            }
        } else {
            for(int i=x;i<=nx;i++) {
                sb.append(arr[i][y]);
            }
        }

        return Integer.parseInt(sb.toString());
    }

    static boolean checkVisitRange(int x,int y,int nx,int ny) {
        if(x == nx) {
            for(int i=y;i<=ny;i++) {
                if(visit[x][i]) return false;
            }
        } else {
            for(int i=x;i<=nx;i++) {
                if(visit[i][y]) return false;
            }
        }
        return true;
    }
    static boolean checkRange(int x,int y) {
        return 0 <= x && x<n && 0<=y && y<m;
    }

    static void input() throws Exception {
        n = fs.nInt();
        m = fs.nInt();
        arr = new int[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] temp = fs.next().toCharArray();
            for (int j = 0; j < m; j++) {
                arr[i][j] = temp[j]-'0';
            }
        }
    }

    static class Fs {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception {
            return Integer.parseInt(next());
        }

        public String next() throws Exception {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}