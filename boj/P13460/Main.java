package boj.P13460;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
5 5
#####
#..B#
#.#.#
#RO.#
#####
* */
public class Main {
    static Fs fs = new Fs();
    static final int MAX_TIMES = 10;
    static int n,m;
    static char[][] arr = new char[11][11];
    static int[] red ;
    static int[] blue ;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();

        for(int i=0;i<n;i++){
            int j =0;
            for(char c : fs.next()) {
                arr[i][j] = c;
                if(c == 'R') {
                    red = new int[]{i,j};
                    arr[i][j] = '.';
                }
                if(c == 'B') {
                    blue = new int[]{i,j};
                    arr[i][j] = '.';
                }
                j++;
            }
        }
        int[] tempRed = Arrays.copyOfRange(red, 0, 2);
        int[] tempBlue = Arrays.copyOfRange(blue,0,2);
        System.out.println(move(3,tempRed,tempBlue));
        printBall(tempRed,tempBlue);
    }

    static void dfs(int cnt,int[] tempRed,int[] tempBlue){
        if(cnt > MAX_TIMES) return;
        for(int i=0;i<4;i++){
            if(     move(i,
                    Arrays.copyOfRange(tempRed, 0, 2),
                    Arrays.copyOfRange(tempBlue, 0, 2))){
            }
        }
    }

    // 모든 구슬 dir방향으로 이동시킴
    static boolean move(int dir,int[] red,int[] blue){
        boolean redFlag = false,blueFlag = false;
        while(true){
            red[0] += dx[dir];
            red[1] += dy[dir];
            blue[0] += dx[dir];
            blue[1] += dy[dir];
            char nRed = arr[red[0]][red[1]];
            char nBlue = arr[blue[0]][blue[1]];

            if(nRed != '.'){
                if(nRed == '#'){
                    red[0] -= dx[dir];
                    red[1] -= dy[dir];
                }
                redFlag = true;
            }

            if(nBlue != '.'){
                if(nBlue == '#') {
                    blue[0] -= dx[dir];
                    blue[1] -= dy[dir];
                }
                blueFlag = true;
            }

            if(redFlag && blueFlag) break;
        }

        if(arr[red[0]][red[1]] == 'O' && arr[blue[0]][blue[1]]=='.'){
            return true;
        }else return false;

    }

    static void printBall(int[] red,int[] blue) {
        System.out.println("red : "+red[0] + " , " + red[1]);
        System.out.println("blue : "+blue[0] + " , "  +blue[1]);
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
