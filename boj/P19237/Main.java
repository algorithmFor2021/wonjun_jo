package boj.P19237;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static HashMap<String,ArrayList<Integer>> dirMap = new HashMap<>();
    static int n,m,k; // 격자 , 상어수 , 냄새
    static int[][] shark = new int[21][21];
    static int[][] smell = new int[21][21];

    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,-1,1};

    static int time = 1;

    static Point[] sharkLoc;

    public static void main(String[] args) throws Exception{
        n = fs.nInt(); m= fs.nInt(); k= fs.nInt();

        sharkLoc = new Point[m+1];
        for(int i=0;i<=m;i++){
            sharkLoc[i]= new Point(-1,-1,-1);
        }

        // 상어 배열 초기화
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                shark[i][j] = fs.nInt();
                if(shark[i][j] > 0){
                    sharkLoc[shark[i][j]] = new Point(shark[i][j],i,j);
                }
            }
        }

        // 냄새 배열 초기화
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(shark[i][j] >0) smell[i][j] = k;
            }
        }

        // 각 상어의 처음 방향
        for(int i=1;i<=m;i++){
            sharkLoc[i].dir = fs.nInt();
        }

        // 방향 우선순위
        for(int i=1;i<=m;i++){
            for(int j=1;j<=4;j++){
                String s = i + "_" + j;
                dirMap.put(s,new ArrayList<>());
                for(int k=1;k<=4;k++){
                    dirMap.get(s).add(fs.nInt());
                }
            }
        }


        while(time <= 1000){
            // 상어 이동하기
            Point[] nextLoc = moveShark();
            goShark(nextLoc);
            sharkLoc = nextLoc;
            smellErase();
            smelling();
            if(check()) break;
            time++;
        }


        System.out.println(time>1000?-1:time);


    }
    static boolean check(){
        int ret = 0;
        for(int i=1;i<=m;i++){
            if(sharkLoc[i].num != -1) ret++;
        }
        if(ret == 1) return true;
        else return false;
    }
    static void smelling(){
        for(int i=1;i<=m;i++){
            Point p = sharkLoc[i];
            if(p.num != -1){
                smell[p.x][p.y] = k;
                shark[p.x][p.y] = p.num;
            }
        }

    }
    static void smellErase(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(smell[i][j] > 0){
                    smell[i][j]--;
                    if(smell[i][j] ==0){
                        shark[i][j] = 0;
                    }
                }
            }
        }
    }

    static void goShark(Point[] nextLoc){
        // 다음샤크 위치 시키기
        for(int i=1;i<=m;i++){
            Point p = nextLoc[i];
            if(p.num == -1) continue;
            // 다음 위치에 상어없는 경우
            if(shark[p.x][p.y]==0 || shark[p.x][p.y] == p.num){
                shark[p.x][p.y] = p.num;
            }
            //다음 위치의 상어가 나보다 힘쌤
            else if(shark[p.x][p.y] < p.num){
                nextLoc[i].num = -1;
            }
            else if (shark[p.x][p.y] > p.num){
                nextLoc[shark[p.x][p.y]].num = -1;
                shark[p.x][p.y] = p.num;
            }
        }
    }
    static Point[] moveShark(){
        Point[] nextLoc = new Point[m+1];
        for(int i=1;i<=m;i++) nextLoc[i] = new Point(-1,-1,-1);
        for(int i=1;i<=m;i++){
            Point p = sharkLoc[i];
            if(p.num == -1) continue;
            boolean flag = false;
            int curX = p.x;
            int curY = p.y;


            // 냄새 없는 칸
            String s = p.num + "_" + p.dir;

            for(int d : dirMap.get(s)){
                int nx = curX + dx[d];
                int ny = curY + dy[d];

                if(checkRange(nx,ny) && smell[nx][ny] == 0){
                    nextLoc[p.num] = new Point(p.num,nx,ny);
                    nextLoc[p.num].dir = d;
                    flag = true;
                    break;
                }
            }

            // 자신의 냄새칸
            if(flag == false){
                for(int d : dirMap.get(s)){
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];

                    if(checkRange(nx,ny) && shark[nx][ny] == p.num){
                        nextLoc[p.num] = new Point(p.num,nx,ny);
                        nextLoc[p.num].dir = d;
                        break;
                    }
                }
            }


        }

        return nextLoc;
    }

    static boolean checkRange(int x,int y){
        if(x>=0 && x<n && y>=0 && y<n ) return true;
        else return false;
    }

    static class Point{
        int num;
        int dir;
        int x,y;

        public Point(int num,int x,int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return num + " : " + x + "," + y;
        }

    }

    static void printShark(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(shark[i][j]);
            }
            System.out.println();
        }
    }

    static void printSmell(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(smell[i][j]);
            }
            System.out.println();
        }
    }
    static void printDir(){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"위");
        map.put(2,"아래");
        map.put(3,"왼쪽");
        map.put(4,"오른쪽");

        for(int i=1;i<=m;i++){
            Point p = sharkLoc[i];
            if(p.num == -1) {
                System.out.println(p.num + " 번상어 die");
                continue;
            }
            String s = p.num + "_" +p.dir;
            String ss = "";
            for(int d : dirMap.get(s)){
                ss += map.get(d) + " ";
            }
            System.out.println(p.num + "번 상어 : " + map.get(p.dir)+"방향 보고있음 next방향: " + ss);

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
