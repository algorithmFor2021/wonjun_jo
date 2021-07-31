package boj.P17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static Fs fs = new Fs();
    static int row,col,t;
    static int[][] arr = new int[1001][1001];
    static Point[] cleaner = new Point[2];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        row = fs.nInt();
        col = fs.nInt();
        t = fs.nInt();
        for(int i=0;i<2;i++) cleaner[i] = new Point(-1,-1);

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                arr[i][j] = fs.nInt();
                if(arr[i][j]  == -1){
                    for(int k=0;k<2;k++){
                        if(cleaner[k].x == -1){
                            cleaner[k].x = i;
                            cleaner[k].y = j;
                            break;
                        }
                    }
                }
            }
        }

        while(t-->0) {
            spread();
            moveDust();
        }
        int cnt = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(arr[i][j] >0) cnt += arr[i][j];
            }
        }

        System.out.println(cnt);



    }
    static void moveDust(){
        int[][] nArr = new int[1001][1001];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                nArr[i][j] = arr[i][j];
            }
        }
        int up = cleaner[0].x;
        int down = cleaner[1].x;
        // 반시계
        for(int i=1;i<col;i++){
            nArr[0][i-1] = arr[0][i];
            nArr[row-1][i-1] = arr[row-1][i];
            nArr[up][i] = arr[up][i-1];
            nArr[down][i] = arr[down][i-1];
        }

        for(int i=1;i<=up;i++){
            nArr[i][0] = arr[i-1][0];
        }
        for(int i=row-2;i>=down;i--){
            nArr[i][0] = arr[i+1][0];
        }
        for(int i=up-1;i>=0;i--){
            nArr[i][col-1] = arr[i+1][col-1];
        }
        for(int i=down+1;i<row;i++){
            nArr[i][col-1] = arr[i-1][col-1];
        }

        arr = nArr;

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(arr[i][j] == -1) arr[i][j] = 0;
            }
        }

        arr[cleaner[0].x][0] = -1;
        arr[cleaner[1].x][0] = -1;

    }

    static void spread(){
        int[][] nArr = new int[1001][1001];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(arr[i][j] >0){
                    int cnt = 0;
                    for(int k=0;k<4;k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(checkRange(nx,ny)){
                            cnt++;
                            nArr[nx][ny] += arr[i][j]/5;
                        }
                    }
                    arr[i][j] -= arr[i][j]/5 * cnt;
                }
            }
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(arr[i][j] > 0) {
                    nArr[i][j] += arr[i][j];
                }
            }
        }

        nArr[cleaner[0].x][0] = -1;
        nArr[cleaner[0].x][0] = -1;

        arr = nArr;


    }

    static boolean checkRange(int x,int y){
        return x>=0 && x<row && y>=0 && y<col && arr[x][y]!=-1;
    }


    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x=" + x +
                    ", y=" + y +
                    '}';
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
