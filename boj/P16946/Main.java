package boj.P16946;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int grpNum = 2;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static HashMap<Integer,Integer> map = new HashMap<>();
    static int[][] arr = new int[1001][1001];

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        for(int i=0;i<n;i++){
            char[] temp = fs.next().toCharArray();
            for(int j=0;j<m;j++){
                arr[i][j] = temp[j]-'0';
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]==0){
                    bfs(i,j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]==1){
                    int ret = 1;
                    Set<Integer> grpSet = new HashSet<>();
                    for(int k=0;k<4;k++){
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        if(checkRange(ni,nj) && arr[ni][nj]>1 && !grpSet.contains(arr[ni][nj])){
                            grpSet.add(arr[ni][nj]);
                            ret += map.get(arr[ni][nj]);
                        }
                    }
                    sb.append(ret%10);
                }
                else sb.append(0);
            }
            sb.append('\n');
        }

        System.out.println(sb);



    }
    static void bfs(int x,int y){
        int grpCnt = 0;
        arr[x][y] = grpNum;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x,y));

        while(!q.isEmpty()){
            Point p = q.poll();

            grpCnt++;

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(checkRange(nx,ny) && arr[nx][ny]==0){
                    arr[nx][ny] = grpNum;
                    q.add(new Point(nx,ny));
                }

            }
        }
        map.put(grpNum,grpCnt);
        grpNum++;
    }

    static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<m;
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
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
