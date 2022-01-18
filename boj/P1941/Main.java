package boj.P1941;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static char[][] arr = new char[5][5];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static final int SIZE = 25;
    static int answer = 0;
    public static void main(String[] args) throws Exception{
        input();
        combination(-1,0,new boolean[SIZE]);
        System.out.println(answer);
    }

    static void combination(int x,int cnt,boolean[] ch){
        if(cnt == 7){
            if(check(ch)) answer++;
            return;
        }
        for(int i=x+1;i<SIZE;i++){
            ch[i] = true;
            combination(i,cnt+1,ch);
            ch[i] = false;
        }
    }

    static boolean check(boolean[] ch){
        Pair first = null;
        for(int i=0;i<SIZE;i++){
            if(ch[i]) {
                first = new Pair(i);
                break;
            }
        }
        int[] carr = new int[100];
        carr[arr[first.x][first.y]] = 1;

        boolean[][] vis = new boolean[SIZE/5][SIZE/5];
        vis[first.x][first.y] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(first);
        while(!q.isEmpty()){
            Pair p = q.poll();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                Pair next = new Pair(nx,ny);
                if(checkRange(nx,ny) && ch[next.getNum()] && !vis[nx][ny]){
                    vis[nx][ny] = true;
                    carr[arr[next.x][next.y]]+= 1;
                    q.add(next);
                }
            }
        }

        return carr['S']+carr['Y']==7 && carr['S'] >=4;

    }

    static boolean checkRange(int x,int y){
        return x>=0 && x<SIZE/5 && y>=0 && y<SIZE/5;
    }


    static void input() throws Exception{
        for(int i=0;i<SIZE/5;i++){
            char[] temp = fs.next().toCharArray();
            System.arraycopy(temp, 0, arr[i], 0, SIZE/5);
        }
    }

    static class Pair{
        int x,y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair(int num) {
            this.x = num / 5;
            this.y = num % 5;
        }

        public int getNum(){
            return this.x*5 + this.y;
        }


    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

    }
}
