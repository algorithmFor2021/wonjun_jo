package boj.P1938;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[][] arr = new int[51][51];
    static boolean[][][] visit = new boolean[51][51][2];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        Wood st = new Wood();
        Wood ed = new Wood();

        int stidx = 0;
        int edidx = 0;
        for(int i=0;i<n;i++) {
            char[] temp = fs.next().toCharArray();
            int j =0;
            for(char c : temp) {
                if(c == 'B') {
                    st.setPair(stidx++,i,j);
                    arr[i][j++] = 0;
                }else if(c == 'E') {
                    ed.setPair(edidx++,i,j);
                    arr[i][j++] = 0;
                }else {
                    arr[i][j++] = c - '0';
                }
            }
        }

        st.visitHere();
        Queue<Wood> q = new LinkedList<>();
        q.add(st);

        while(!q.isEmpty()) {
            Wood w = q.poll();

            if(w.isArrive(ed)){
                System.out.println(w.cnt);
                return;
            }

            Wood[] next = w.go();
            for(Wood nx : next) {
                if(nx.isValid()) {
                    nx.visitHere();
                    q.add(nx);
                }
            }

            Wood rotate = w.rotate();
            if(rotate.isValid()) {
                rotate.visitHere();
                q.add(rotate);
            }

        }

        System.out.println(0);

    }

    static class Wood {
        Pair[] pairs;
        int cnt;

        public Wood(){pairs = new Pair[3];}

        public boolean isPairValid() {
            for(Pair p : pairs) {
                if(!checkRange(p)) return false;
            }
            return true;
        }

        public void setPair(int idx,int x,int y){
            if(pairs == null ) pairs = new Pair[3];
            pairs[idx] = new Pair(x,y);
        }

        public boolean isArrive(Wood w){
            for(int i=0;i<3;i++){
                if(pairs[i].x != w.pairs[i].x) return false;
                if(pairs[i].y != w.pairs[i].y) return false;
            }
            return true;
        }

        public void visitHere() {
            visit[pairs[0].x][pairs[0].y][isUp()?0:1] = true;
        }

        public boolean isVisit() {
            return visit[pairs[0].x][pairs[0].y][isUp()?0:1];
        }

        public boolean isValid() {
            return pairs != null && !isVisit();
        }

        public boolean isUp() {
            return pairs[0].y == pairs[1].y;
        }

        public Wood[] go(){
            Wood[] woods = new Wood[4];
            for(int i=0;i<4;i++) {
                woods[i] = new Wood();
                for(int j=0;j<3;j++) {
                    woods[i].setPair(j,pairs[j].x + dx[i],pairs[j].y + dy[i]);
                }
                if(!woods[i].isPairValid()) {
                    woods[i].pairs = null;
                }
                woods[i].cnt = this.cnt + 1;
            }
            return woods;
        }

        public Wood rotate() {
            int lx,ly;
            if(isUp()) {
                lx = pairs[0].x;
                ly = pairs[0].y- 1;
            }else {
                lx = pairs[0].x -1;
                ly = pairs[0].y;
            }
            for(int i =lx;i<lx+3;i++){
                for(int j=ly;j<ly+3;j++){
                    if(i<0 || i >=n || j<0 || j>=n || arr[i][j]==1) return new Wood(null,-1);
                }
            }

            if(isUp()) {
                return new Wood(new Pair[]{
                        new Pair(pairs[0].x+1,pairs[0].y-1),
                        pairs[1],
                        new Pair(pairs[2].x-1,pairs[2].y+1)
                },this.cnt + 1);
            }else {
                return new Wood(new Pair[]{
                        new Pair(pairs[0].x-1,pairs[0].y+1),
                        pairs[1],
                        new Pair(pairs[2].x+1,pairs[2].y-1)
                },this.cnt + 1);
            }

        }

        public Wood(Pair[] pairs,int cnt) {
            this.pairs = pairs;
            this.cnt = cnt;
        }
    }

    static class Pair {
        int x,y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static private boolean checkRange(Pair p) {
        return p.x >= 0 && p.x < n && p.y >= 0 && p.y < n && arr[p.x][p.y] != 1;
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");


        public String next() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }
    }
}
