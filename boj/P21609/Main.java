package boj.P21609;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Group group;
    static int[][] arr;
    static int score = 0;

    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        m = fs.nInt();
        arr = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = fs.nInt();
            }
        }

        while(findGrop()) {
            score += group.total*group.total;
            removeArr(group.baseX, group.baseY);
            gravity();
            turn90();
            gravity();
        }

        System.out.println(score);

    }

    static void gravity(){
        for(int j=0;j<n;j++){
            for(int i=n-1;i>=0;i--){
                if(arr[i][j]==-9){
                    for(int k=i-1;k>=0;k--){
                        if(arr[k][j]==-1) break;
                        if(arr[k][j]>=0){
                            arr[i][j] = arr[k][j];
                            arr[k][j] = -9;
                            break;
                        }
                    }
                }
            }
        }
    }
    static void turn90(){
        int[][] temp = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                temp[j][i] = arr[i][n-1-j];
            }
        }
        arr = temp;
    }

    // all -9
    static void removeArr(int x,int y){
        int baseColor = arr[x][y];
        boolean[][] ch = new boolean[n][n];
        ch[x][y] = true;
        arr[x][y] = -9;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        while(!q.isEmpty()){
            Pair p = q.poll();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(checkRange(nx,ny) && !ch[nx][ny]){
                    if(arr[nx][ny] == baseColor || arr[nx][ny] == 0) {
                        ch[nx][ny] = true;
                        arr[nx][ny] = -9;
                        q.add(new Pair(nx, ny));
                    }
                }
            }
        }
    }

    static boolean findGrop(){

        ArrayList<Group> glist = new ArrayList<>();
        boolean[][] ch = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]>0 && !ch[i][j]){
                    bfs(i,j,ch,glist);
                    rainbowCheckRelease(ch);
                }
            }
        }

        Collections.sort(glist);
        if(glist.size() > 0) {
            group = glist.get(0);
            return true;
        }
        return false;

    }

    static void rainbowCheckRelease(boolean[][] ch){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==0)
                    ch[i][j] = false;
            }
        }
    }

    static void bfs(int x,int y, boolean[][] ch,ArrayList<Group> glist){
        ch[x][y] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        int baseX = x;
        int baseY = y;
        int total = 1;
        int rainBowNum = 0;

        while(!q.isEmpty()){
            Pair p = q.poll();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 범위 및 방문체크
                if(checkRange(nx,ny) && !ch[nx][ny]){
                    // 같은 색 혹은 무지개색
                    if(arr[baseX][baseY] == arr[nx][ny] || arr[nx][ny] == 0){
                        ch[nx][ny] = true;
                        total++;
                        if(arr[nx][ny] == 0) rainBowNum++;
                        q.add(new Pair(nx,ny));
                    }
                }

            }

        }

        if(total >= 2){
            glist.add(new Group(baseX,baseY,total,rainBowNum));
        }

    }

    static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<n;
    }


    static class Pair{
        int x,y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static class Group implements Comparable<Group>{

        int baseX,baseY;
        int total;
        int rainBowNum;

        public Group(int baseX, int baseY, int total, int rainBowNum) {
            this.baseX = baseX;
            this.baseY = baseY;
            this.total = total;
            this.rainBowNum = rainBowNum;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "baseX=" + baseX +
                    ", baseY=" + baseY +
                    ", total=" + total +
                    ", rainBowNum=" + rainBowNum +
                    '}';
        }

        @Override
        public int compareTo(Group o) {
            if(this.total != o.total) return o.total - this.total;
            if(this.rainBowNum != o.rainBowNum) return o.rainBowNum - this.rainBowNum;
            if(this.baseX != o.baseX) return o.baseX - this.baseX;
            return o.baseY - this.baseY;
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
