package boj.P4485;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[][] arr = new int[126][126];
    static int[][] dp = new int[126][126];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        int prob = 1;
        StringBuilder sb =new StringBuilder();
        while(true){
            n = fs.nInt();
            if(n == 0) break;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    arr[i][j] = fs.nInt();
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra();

            sb.append("Problem "+prob+": " +dp[n-1][n-1]+"\n");
            prob += 1;
        }

        System.out.println(sb);

    }
    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dp[0][0] = arr[0][0];
        pq.add(new Node(0,0,arr[0][0]));
        boolean[][] visited = new boolean[n+1][n+1];

        while(!pq.isEmpty()){
            Node node= pq.poll();

            if(visited[node.x][node.y]) continue;
            visited[node.x][node.y] = true;

            for(int i=0;i<4;i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx>=0 && nx<n && ny>=0 && ny<n
                    && dp[nx][ny] > dp[node.x][node.y] + arr[nx][ny]
                ){
                    dp[nx][ny] = dp[node.x][node.y] + arr[nx][ny];
                    pq.add(new Node(nx,ny,dp[nx][ny]));

                }
            }

        }

    }
    static class Node implements Comparable<Node>{
        int x,y;
        int val;

        public Node(int x,int y,int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
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
