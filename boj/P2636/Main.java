package boj.P2636;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main 설명 : 치즈
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/18
**/
public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[][] arr = new int[100][100];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                arr[i][j] = fs.nInt();
            }
        }

        int hour = 0;
        int lastCheese = 0;

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j] == 1) lastCheese++;
            }
        }
        if(lastCheese == 0) {
            System.out.println(0);
            System.out.println(0);
            return;
        }

        while(true) {
            hour++;
            int remain = oneHour();
            if(remain == 0) {
                break;
            }
            lastCheese = remain;
        }

        System.out.println(hour);
        System.out.println(lastCheese);


    }

    static int oneHour() {
        boolean[][] ch = new boolean[n][m];
        Queue<Node> q = new LinkedList<>();
        ch[0][0] = true;
        q.add(new Node(0,0));

        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(int i=0;i<4;i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(checkRange(nx,ny) && !ch[nx][ny]) {
                    ch[nx][ny] = true;
                    if(arr[nx][ny] == 0) {
                        q.add(new Node(nx,ny));
                    }
                }
            }
        }

        return removeCheeseAndCount(ch);

    }

    static int removeCheeseAndCount(boolean[][] ch) {
        int cnt = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++) {
                if(ch[i][j] && arr[i][j] == 1) {
                    arr[i][j] = 0;
                }
                else if(arr[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static boolean checkRange(int x,int y) {
        return x>=0 && x<n && y>=0 && y<m;
    }

    static class Node{
        int x,y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
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
