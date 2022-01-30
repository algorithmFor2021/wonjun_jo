package programers.kakao2022.사라지는발판;

import java.util.ArrayList;

public class Solution {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n;
    static int m;
    static ArrayList<int[]> awin = new ArrayList<>();
    static ArrayList<int[]> bwin = new ArrayList<>();

    public static void main(String[] args) {
        new Solution().solution(new int[][]{
                {1,1,1}
                ,{1,1,1}
                ,{1,1,1}
        },new int[]{1,0},new int[]{1,2});
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        dfs(board,aloc,bloc,0);
        return 0;
    }

    void dfs(int[][] board,int[] aloc,int[] bloc,int order){
        if(order%2==0){
            if(!nextPossible(board,aloc[0],aloc[1])) return;
            for(int i=0;i<4;i++){
                int[] nextLoc = new int[]{aloc[0] + dx[i],aloc[1] + dy[i]};
                if(nextPossible(board,nextLoc[0],nextLoc[1])){
                    board[aloc[0]][aloc[1]] = 0;
                    dfs(board,nextLoc,bloc,order+1);
                    board[aloc[0]][aloc[1]] = 1;
                }
            }
        }else{
            if(!nextPossible(board,bloc[0],bloc[1])) return;
            for(int i=0;i<4;i++){
                int[] nextLoc = new int[]{bloc[0] + dx[i],bloc[1] + dy[i]};
                if(nextPossible(board,nextLoc[0],nextLoc[1])){
                    board[bloc[0]][bloc[1]] = 0;
                    dfs(board,aloc,nextLoc,order+1);
                    board[bloc[0]][bloc[1]] = 1;
                }
            }
        }
    }

    boolean nextPossible(int[][] board,int x,int y){
        return x>=0 && x<n && y>=0 && y<m && board[x][y]==1;
    }

}
