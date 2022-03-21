package boj.P1074;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main 설명 :  Z
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/03/21
**/
public class Main {
    static Fs fs = new Fs();
    static int cnt = -1;
    static int answer;
    static int r,c;

    public static void main(String[] args) throws Exception{
        int size = fs.nInt();
        r = fs.nInt();
        c = fs.nInt();

        divRecur(size,0,0);

        System.out.println(answer);

    }

    static void divRecur(int size,int x,int y) {
        if(size == 1) {
            for(int i=x;i<x+2;i++){
                for(int j=y;j<y+2;j++){
                    cnt++;
                    if(i==r && j==c) answer = cnt;
                }
            }
            return;
        }

        int next = 1 <<  (size - 1);
        int[] dx = {0,0,next,next};
        int[] dy = {0,next,0,next};

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int maxX = nx + next;
            int maxY = ny + next;
            if(nx <= r && r < maxX && ny<=c && c < maxY) {
                divRecur(size - 1, nx, ny);
            }else {
                cnt += (next * next);
            }
        }

    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
