package boj.P3190;

/* package whatever; // don't place package name! */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    static int curDir = 2;
    static int time = 0;
    static int[][] board;
    static int n;
    static HashMap<Integer,String> dirMap = new HashMap<>();


    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        board = new int[n+1][n+1];
        board[1][1] = 1;
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(1,1));
        int appleNum = fs.nInt();
        for(int i=0;i<appleNum;i++) {
            board[fs.nInt()][fs.nInt()] = 2;
        }
        int dirNum = fs.nInt();
        for(int i=0;i<dirNum;i++) {
            dirMap.put(fs.nInt(), fs.next());
        }

        while(time < 2100000000) {
            if(dirMap.containsKey(time)) {
                if(dirMap.get(time).equals("D")) {
                    curDir = (curDir + 1) % 4;
                }else {
                    curDir = (curDir + 3) % 4;
                }
            }

            time++;
            Point head = q.peek();
            int nx = head.x + dx[curDir];
            int ny = head.y + dy[curDir];

            // 벽에 부딪힘
            if(nx<1 || nx >n || ny<1 || ny>n ) break;
            // 몸에 부딪힘
            if(board[nx][ny] == 1) break;

            // 사과 먹지 않으면 꼬리 짧아짐
            if(board[nx][ny]!=2) {
                Point last = q.pollLast();
                board[last.x][last.y] = 0;
            }
            board[nx][ny] = 1;
            q.addFirst(new Point(nx,ny));
        }

        System.out.println(time);

    }
    static class Point{
        int x,y;
        public Point(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
