package programers.kakao2022.사라지는발판;

import java.util.*;
/**
 * Solution 설명 : 사라지는 발판
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/02/02
**/
public class Solution {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n;
    static int m;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                {1,1,1}
                ,{1,1,1}
                ,{1,1,1}
        },new int[]{1,0},new int[]{1,2}));
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        dfs(board,aloc[0],aloc[1],bloc[0],bloc[1],0);
        return answer;
    }

    Pair dfs(int[][] board,int ax,int ay,int bx,int by,int idx){

        if(ax==bx && ay==by){
            answer = Math.max(answer,idx);
            if(idx%2==0){
                return new Pair(1,0);
            }
            return new Pair(0,1);
        }

        boolean lastFlag = true;
        Pair ret = new Pair(0,0);
        int _x = (idx%2==0?ax:bx);
        int _y = (idx%2==0?ay:by);
        for(int i=0;i<4;i++){
            int nx = dx[i] + _x;
            int ny = dy[i] + _y;
            if(nextPossible(board,nx,ny)){
                lastFlag = false;
                board[_x][_y] = 0;
                if(idx%2==0) {
                    ret.add(dfs(board,nx,ny,bx,by,idx+1));
                }else{
                    ret.add(dfs(board,ax,ay,nx,ny,idx+1));
                }
                board[_x][_y] = 1;
            }
        }
        if(lastFlag){
            // Bwin
            if(idx%2==0){
                return new Pair(0,1);
            }else{
                return new Pair(1,0);
            }
        }else{
            if(ret.aWin ==0 || ret.bWin == 0){
                answer = Math.max(answer,idx);
            }
            return ret;
        }

    }

    static class Pair{
        int aWin;
        int bWin;

        public Pair(int aWin, int bWin) {
            this.aWin = aWin;
            this.bWin = bWin;
        }

        public void add(Pair p){
            this.aWin += p.aWin;
            this.bWin += p.bWin;
        }
    }
    boolean nextPossible(int[][] board,int x,int y){
        return x>=0 && x<n && y>=0 && y<m && board[x][y]==1;
    }

}
