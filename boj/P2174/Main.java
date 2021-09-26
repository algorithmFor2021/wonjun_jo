package boj.P2174;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int A,B;
    static int n,m;
    static int[][] arr;
    static Robot[] robot;
    static int[] dx = {0,-1,0,1}; // E N W S +1 하면 L -1 하면 R
    static int[] dy = {1,0,-1,0};
    static HashMap<String,Integer> dirMap = new HashMap<>();
    public static void main(String[] args) throws Exception {

        dirMap.put("E",0);
        dirMap.put("N",1);
        dirMap.put("W",2);
        dirMap.put("S",3);

        A = fs.nInt();
        B = fs.nInt();
        n = fs.nInt();
        m = fs.nInt();

        arr = new int[B+1][A+1];
        robot = new Robot[n+1];

        int x,y;
        String dir;
        for(int i=1;i<=n;i++){
            x = fs.nInt();
            y = fs.nInt();
            dir = fs.next();

            arr[B+1-y][x] = i;
            robot[i] = new Robot(i,B+1-y,x,dirMap.get(dir));
        }

        for(int i=0;i<m;i++){
            int robotNum = fs.nInt();
            String cmd = fs.next();
            int cnt = fs.nInt();

            for(int j=0;j<cnt;j++){
                String ret = start(robotNum,cmd);
                if(!ret.equals("OK\n")) {
                    System.out.println(ret);
                    return;
                }
            }
        }

        System.out.println("OK");

    }
    static void print(){
        for(int i=1;i<=B;i++){
            for(int j=1;j<=A;j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static String start(int rn, String cmd){
        if(cmd.equals("L")) {
            robot[rn].turnL();
            return "OK\n";
        }
        else if(cmd.equals("R")) {
            robot[rn].turnR();
            return "OK\n";
        }
        else return robot[rn].go();
    }

    static class Robot{
        int id;
        int x,y;
        int dir;

        public Robot(int id, int x, int y, int dir) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void turnL(){
            this.dir = (this.dir+1)%4;
        }
        public void turnR(){
            this.dir = this.dir==0?3:this.dir-1;
        }

        public String go(){
            int nx = x + dx[this.dir];
            int ny = y + dy[this.dir];

            if(nx<1 || nx>B || ny<1 || ny>A) return "Robot "+this.id+" crashes into the wall\n";

            if(arr[nx][ny]!=0){
                return "Robot "+this.id+" crashes into robot "+arr[nx][ny]+"\n";
            }

            arr[this.x][this.y] = 0;
            arr[nx][ny] = this.id;
            this.x = nx;
            this.y = ny;
            return "OK\n";
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
