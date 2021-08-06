package boj.P14442;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
6 4 2
0100
1110
1000
0000
0111
0000
* */
public class Main {
    static Fs fs = new Fs();
    static int n,m,k;
    static int[][][] dist = new int[11][1000][1000];
    static int[][] map = new int[1000][1000];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int INF = 1000*1000 + 100;

    public static void main(String[] args) throws Exception{
        for(int i=0;i<11;i++){
            for(int j=0;j<1000;j++){
                for(int m=0;m<1000;m++){
                    dist[i][j][m] = -1;
                }
            }
        }

        n = fs.nInt();
        m = fs.nInt();
        k = fs.nInt();

        for(int i=0;i<n;i++){
            char[] temp = fs.next().toCharArray();
            for(int j=0;j<m;j++){
                map[i][j] = temp[j]-'0';
            }
        }

        bfs();

        int answer = INF;
        for(int i=0;i<=k;i++){
            int cmp = dist[i][n-1][m-1];
            if(cmp != -1){
                answer = Math.min(answer,cmp);
            }
        }

        System.out.println(answer==INF?-1:answer);


    }

    static void bfs(){
        Node st = new Node(0,0,0);
        Queue<Node> q = new LinkedList<>();
        dist[0][0][0] = 1;
        q.add(st);
        while(!q.isEmpty()){
            Node node=  q.poll();

            for(int i=0;i<4;i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(checkRange(nx,ny)){
                    // wall
                    if(node.k+1 <= k && map[nx][ny]==1 && dist[node.k+1][nx][ny]==-1){
                        dist[node.k+1][nx][ny] = dist[node.k][node.x][node.y] + 1;
                        q.add(new Node(nx,ny,node.k+1));
                    }
                    // not wall
                    if(map[nx][ny]==0 && dist[node.k][nx][ny]==-1){
                        dist[node.k][nx][ny] = dist[node.k][node.x][node.y] + 1;
                        q.add(new Node(nx,ny,node.k));
                    }
                }
            }
        }
    }

    static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<m;
    }


    static class Node{
        int x,y,k;

        public Node(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
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
