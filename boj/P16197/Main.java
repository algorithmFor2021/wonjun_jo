package boj.P16197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int a,b,c,d;
    static char[][] arr = new char[21][21];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer = -1;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();

        boolean flag = false;
        for(int i=0;i<n;i++) {
            char[] temp = fs.nToCharArray();
            for(int j=0;j<m;j++){
                if(temp[j] == 'o'){
                    if(flag) {c=i;d=j;}
                    else {
                        a=i;b=j;
                        flag = true;
                    }
                    arr[i][j] = '.';
                }
                else arr[i][j] = temp[j];
            }
        }

        dfs(a,b,c,d,0);
        System.out.println(answer);

    }

    static void dfs(int x1,int y1,int x2,int y2,int depth){
        if(depth == 11) return;
        if(isAnswer(x1,y1,x2,y2)) {
                answerUpdate(depth);
                return;
        }
        if(isStop(x1,y1,x2,y2)) return;

        for(int i=0;i<4;i++){
            int nextX1 = x1 + dx[i];
            int nextY1 = y1 + dy[i];
            int nextX2 = x2 + dx[i];
            int nextY2 = y2 + dy[i];
            if(isWall(nextX1,nextY1)) {nextX1 = x1;nextY1 = y1;}
            if(isWall(nextX2,nextY2)) {nextX2 = x2;nextY2 = y2;}
            dfs(nextX1,nextY1,nextX2,nextY2,depth+1);
        }

    }

    static void answerUpdate(int depth){
        if(answer == -1) answer = depth;
        else answer = Math.min(answer,depth);
    }

    static boolean isStop(int x1,int y1,int x2,int y2){
        if(!checkRange(x1,y1) && !checkRange(x2,y2)) return true;
        return false;
    }

    static boolean isAnswer(int x1,int y1,int x2,int y2){
        // 동전1 out
        if(!checkRange(x1,y1) && checkRange(x2,y2)) return true;
        // 동전2 out
        if(checkRange(x1,y1) && !checkRange(x2,y2)) return true;

        return false;
    }

    static boolean checkRange(int x,int y){
        if(x<0 || x>=n || y<0 || y>=m) return false;
        return true;
    }

    static boolean isWall(int x,int y){
        if(checkRange(x,y) && arr[x][y]=='#') return true;
        return false;
    }
    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        public char[] nToCharArray() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken().toCharArray();
        }
    }
}
