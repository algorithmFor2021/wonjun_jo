package programers.kakao2022.사라지는발판;

import java.util.*;

class Solution{
    static int n,m;
    static int[][] board;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int solution(int[][] _board, int[] aloc, int[] bloc) {
        board = _board;
        n = board.length;
        m = board[0].length;

        return dfs(aloc[0],aloc[1],bloc[0],bloc[1],0).cnt;
    }

    // 나의 위치 : x1,y1
    // 상대 위치 : x2,y2
    static Pair dfs(int x1,int y1,int x2,int y2,int idx){
        boolean winFlag = false;
        int minCnt = 99999;
        int maxCnt = 0;

        if(!check(x1,y1)) return new Pair(false,idx);
        if(x1==x2 && y1==y2) return new Pair(true,idx+1);


        for(int i=0;i<4;i++){
            int nx = x1 + dx[i];
            int ny = y1 + dy[i];

            if(checkRange(nx,ny)){
                board[x1][y1] = 0;
                Pair p = dfs(x2,y2,nx,ny,idx+1);
                // 상대가 진 경우 == 내가 이긴 경우
                if(!p.flag) {
                    winFlag = true;
                    minCnt = Math.min(minCnt,p.cnt);
                }
                // 상대가 이긴 경우
                else{
                    maxCnt = Math.max(maxCnt,p.cnt);
                }
                board[x1][y1] = 1;
            }
        }

        if(winFlag) return new Pair(true,minCnt);
        else return new Pair(false,maxCnt);
    }
    static boolean check(int curx,int cury){
        for(int i=0;i<4;i++){
            int nx = curx + dx[i];
            int ny = cury + dy[i];
            if(checkRange(nx,ny)) return true;
        }
        return false;
    }

    static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<m && board[x][y]==1;
    }

    static class Pair{
        boolean flag;
        int cnt;

        public Pair(boolean flag, int cnt) {
            this.flag = flag;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "flag=" + flag +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[][]{{1,1,1},{1,1,1},{1,1,1}}
                ,new int[]{1,0}
                ,new int[]{1,2}
        ));
    }


}