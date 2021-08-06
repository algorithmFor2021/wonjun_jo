package boj.P17143;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
* 첫째 줄에 격자판의 크기 R, C와 상어의 수 M이 주어진다. (2 ≤ R, C ≤ 100, 0 ≤ M ≤ R×C)

둘째 줄부터 M개의 줄에 상어의 정보가 주어진다.
* 상어의 정보는 다섯 정수 r, c, s, d, z
* (1 ≤ r ≤ R, 1 ≤ c ≤ C, 0 ≤ s ≤ 1000, 1 ≤ d ≤ 4, 1 ≤ z ≤ 10000) 로 이루어져 있다.
* (r, c)는 상어의 위치, s는 속력, d는 이동 방향, z는 크기이다. d가 1인 경우는 위,
* 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미한다
*
4 6 8
4 1 3 3 8
1 3 5 2 9
2 4 8 4 1
4 5 0 1 4
3 3 1 2 7
1 5 8 4 3
3 6 2 1 2
2 2 2 3 5
* */
// 낚시왕
public class Main {
    static Fs fs = new Fs();
    static int r,c,m;
    static ArrayList[][] shArr = new ArrayList[101][101];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Shark[] sh = new Shark[10001];

    public static void main(String[] args) throws Exception{
        r = fs.nInt(); c= fs.nInt(); m= fs.nInt();

        for(int i=0;i<101;i++)for(int j=0;j<101;j++) shArr[i][j] = new ArrayList();

        // (x,y) s = speed , d= direction, z= size
        int x,y,s,d,z;
        for(int i=0;i<m;i++){
            x = fs.nInt();y = fs.nInt();s = fs.nInt();d = fs.nInt();z = fs.nInt();
            if(d==1 || d==2) sh[i] = new Shark(i,x,y,s%((r-1)*2),d-1,z);
            else sh[i] = new Shark(i,x,y,s%((c-1)*2),d-1,z);
            shArr[x][y].add(z);
        }

        for(int col=1;col<=m;col++){
            findShark(col);

        }

    }

    static void findShark(int col){
        for(int i=1;i<=r;i++){
            if(shArr[i][col].size() > 0) {
                for(int j=0;j<m;j++){
                    if(sh[j].x == i && sh[j].y == col){
                        sh[j].x = -1;
                        sh[j].y = -1;
                        return;
                    }
                }
            }
        }
    }

    static class Shark{
        int id;
        int x,y;
        int speed;
        int dir;
        int size;

        public boolean isDead(){
            return x==-1;
        }

        private boolean isBound(){
            if(dir < 2) return x==1 || x==r;
            else return y==1 || y==c;
        }


        public void move(){
            int dist = this.speed;
            while(dist-->0){
                if(isBound()){
                    if(dir%2==0) dir++;
                    else dir--;
                }
                x += dx[dir];
                y += dy[dir];
            }
        }

        public String toString(){
            return "번호: "+id+" 위치: "+x+","+y+" 초당거리: "+speed+" 크기: "+size+" 방향: "+dir;
        }

        public Shark(int id, int x, int y, int speed, int dir, int size) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
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
