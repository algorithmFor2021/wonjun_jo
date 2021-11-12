package boj.P20055;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,k;
    static int[] durable;
    static boolean[] robot;

    public static void main(String[] args) throws Exception{
        input();
        System.out.println(solve());
    }

    private static int solve(){
        int cnt = 1;
        while(true) {
            rotate();
            move();
            putRobot();
            if(checkExit()) break;
            cnt++;
        }
        return cnt;
    }

    private static boolean checkExit(){
        int tmp = 0;
        for(int i=0;i<2*n;i++){
            if(durable[i] == 0){
                tmp += 1;
            }
        }
        return tmp >= k;
    }

    private static void putRobot(){
        if(durable[0] >  0) {
            robot[0] = true;
            durable[0] -= 1;
        }
    }

    private static void move(){
        for(int i=n-2;i>=0;i--){
            if(i==n-2 && robot[i]){
                if(durable[i+1] > 0) {
                    durable[i+1] -= 1;
                    robot[i] = false;
                }
            }
            else{
                if(robot[i] && durable[i+1]>0 && !robot[i+1]){
                    robot[i+1] = true;
                    robot[i] = false;
                    durable[i+1] -= 1;
                }
            }
        }
    }

    private static void rotate(){
        for(int i=n-2;i>=0;i--){
            if(i==n-2 && robot[i]) robot[i] = false;
            else{
                if(robot[i]) {
                    robot[i] = false;
                    robot[i+1] = true;
                }
            }
        }

        int[] nextDurable = new int[2*n];
        for(int i=0;i<2*n;i++){
            nextDurable[(i+1)%(2*n)] = durable[i];
        }
        durable = nextDurable;
    }

    private static void input() throws Exception {
        n = fs.nextInt();
        k = fs.nextInt();
        durable = new int[2*n];
        robot = new boolean[n];
        for(int i=0;i<2*n;i++) durable[i] = fs.nextInt();
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        public int nextInt() throws Exception{
            return Integer.parseInt(next());
        }
    }
}
