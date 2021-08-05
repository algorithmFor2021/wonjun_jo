package boj.P13460;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
7 7
#######
#.###R#
##....#
#..#.##
##.#OB#
##....#
#######
* */
public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static char[][] arr = new char[11][11];
    static int MAX_CNT = 10;
    static int[] red;
    static int[] blue;
    static int answer = 100;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        for(int i=0;i<n;i++){
            char[] temp = fs.next();
            for(int j =0;j<m;j++) arr[i][j] = temp[j];
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]=='R'){
                    red = new int[]{i,j};
                    arr[i][j]='.';
                }
                if(arr[i][j]=='B'){
                    blue = new int[]{i,j};
                    arr[i][j] = '.';
                }
            }
        }
        dfs(0,red,blue);
        System.out.println(answer>10?-1:answer);

    }

    static void dfs(int cnt,int[] r,int[] b){
        if(cnt > MAX_CNT) return;
        int ret = check(r,b);
        if(ret == -1) return;
        else if(ret == 1) {
            answer = Math.min(answer,cnt);
            return;
        }
        else{ // ret == 0
            for(int dir=0;dir<4;dir++){
                int[] nxt = move(dir,r,b);
                dfs(cnt+1,new int[]{nxt[0],nxt[1]},new int[]{nxt[2],nxt[3]});
            }

        }

    }
    static int[] move(int dir,int[] r,int[] b){
        int[] cur = {r[0],r[1],b[0],b[1]};
        boolean redIsMoved = true,blueIsMoved = true;
        while(redIsMoved || blueIsMoved){
            int[] next = {cur[0]+dx[dir],cur[1]+dy[dir],cur[2]+dx[dir],cur[3]+dy[dir]};
            redIsMoved = justOneMove(cur, next, 0, 1, 2, 3);
            blueIsMoved = justOneMove(cur, next, 2, 3, 0, 1);
        }
        return cur;
    }

    private static boolean justOneMove(int[] cur, int[] next, int i, int i2, int i3, int i4) {
        boolean isMoved;
        if (arr[next[i]][next[i2]] == '#') isMoved = false;
        else if (arr[next[i]][next[i2]] == 'O') {
            isMoved = false;
            cur[i] = next[i];
            cur[i2] = next[i2];
        } else if (next[i] == cur[i3] && next[i2] == cur[i4]) isMoved = false;
        else {
            isMoved = true;
            cur[i] = next[i];
            cur[i2] = next[i2];
        }
        return isMoved;
    }

    static int check(int[] r,int[] b){
        if(arr[b[0]][b[1]]=='O') return -1; // no more search
        else if(arr[r[0]][r[1]]=='O') return 1; // found
        else return 0; // more search
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        public char[] next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken().toCharArray();
        }
    }
}
