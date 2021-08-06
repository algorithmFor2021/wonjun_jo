package boj.P13460;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    static void dfs(int cnt, int[] r, int[] b){
        if(cnt > MAX_CNT) return;
        int flag = check(r,b);
        if(flag == 1) {
            answer = Math.min(answer,cnt);
            return;
        }
        else if(flag == 0){
            for(int dir=0;dir<4;dir++){
                int[] nxt = move(dir,r,b);
                dfs(cnt + 1, new int[]{nxt[0],nxt[1]},new int[]{nxt[2],nxt[3]});
            }
        }


    }
    static int[] move(int dir,int[] r,int[] b){
        int[] cur = {r[0],r[1],b[0],b[1]};
        boolean redNotMoved=false,blueNotMoved=false;

        while(true){
            int[] nxt = Arrays.copyOfRange(cur,0,4);
            if(cur[0]!=-1){
                nxt[0] += dx[dir];
                nxt[1] += dy[dir];
            }
            if(cur[2]!=-1){
                nxt[2] += dx[dir];
                nxt[3] += dy[dir];
            }
            // 현재위치가 탈출구임
            if(nxt[0]==-1) redNotMoved = true;
            // 다음위치가 벽이거나 이미 구슬이 있거나 탈출구인경우
            else if(arr[nxt[0]][nxt[1]]=='#' || arr[nxt[0]][nxt[1]]=='O' || (nxt[0]==cur[2] && nxt[1]==cur[3])){
                // 탈출구인경우
                if(arr[nxt[0]][nxt[1]]=='O'){
                    cur[0]=-1;cur[1]=-1;
                }
                redNotMoved = true;
            }
            else {
                cur[0] = nxt[0];cur[1]=nxt[1];
                redNotMoved = false; // 다음위치에 움직일수 있는 경우
            }
            if(nxt[2]==-1) blueNotMoved = true;
            // 다음위치가 벽이거나 이미 구슬이 있거나 탈출구인경우
            else if(arr[nxt[2]][nxt[3]]=='#' || arr[nxt[2]][nxt[3]]=='O' || (nxt[2]==cur[0] && nxt[3]==cur[1])){
                // 탈출구인경우
                if(arr[nxt[2]][nxt[3]]=='O'){
                    cur[2]=-1;cur[3]=-1;
                }
                blueNotMoved = true;
            }else{
                cur[2] = nxt[2];cur[3]=nxt[3];
                blueNotMoved = false;
            }
            if(redNotMoved && blueNotMoved) break;
        }

        return cur;
    }

    static int check(int[] r,int[] b){
        if(b[0] == -1) return -1;
        else if(r[0] == -1) return 1;
        else return 0;
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
