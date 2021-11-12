package programers.kakao2019.blockgame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int n;
    static int[][] board;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static HashSet<Integer> set = new HashSet<>();
    static HashSet<Integer>[] removeDepends = new HashSet[201];

    public int solution(int[][] b){
        n = b.length;
        board = b;
        boolean[][] ch = new boolean[n][n];

        for(int i=0;i<201;i++){
            removeDepends[i] = new HashSet<>();
        }

        int maxNumber = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]>0 && !ch[i][j]){
                    maxNumber = Math.max(board[i][j],maxNumber);
                    set.add(board[i][j]);
                    bfs(i,j,ch);
                }
            }
        }
        boolean[] removeCheck = new boolean[201];
        int answer = 0;
        while(true){
            int target = -1;
            for(int i=1;i<=maxNumber;i++){
                if(set.contains(i) && removeDepends[i].size() == 0 && !removeCheck[i]){
                    answer++;
                    removeCheck[i] = true;
                    target = i;
                    break;
                }
            }
            if(target == -1) break;

            for(int i=1;i<=maxNumber;i++){
                removeDepends[i].remove(target);
            }
        }

        return answer;
    }

    private static void bfs(int x,int y,boolean[][] ch){
        Point maxPoint = new Point(Integer.MIN_VALUE,Integer.MIN_VALUE);
        Point minPoint = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);

        int curNumber = board[x][y];
        Queue<Point> q = new LinkedList<>();
        ch[x][y] = true;
        Point strt = new Point(x,y);
        q.add(strt);
        upateMinMaxPoint(minPoint,maxPoint,strt);

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(checkRange(nx,ny) && !ch[nx][ny] && board[nx][ny] == curNumber){
                    Point next = new Point(nx,ny);
                    q.add(next);
                    upateMinMaxPoint(minPoint,maxPoint,next);
                    ch[nx][ny] = true;
                }
            }
        }

        updateRemoveDepends(minPoint,maxPoint,curNumber);

    }

    private static void updateRemoveDepends(Point min,Point max,int curNumber){
        for(int i=min.x;i<=max.x;i++){
            for(int j=min.y;j<=max.y;j++){
                // 비어있는 칸
                if(board[i][j] != curNumber){
                    // 위로 가보기
                    for(int k=i;k>=0;k--){
                        if(board[k][j] == curNumber){
                            removeDepends[curNumber].clear();
                            removeDepends[curNumber].add(-1);
                            return;
                        }
                        if(board[k][j] > 0){
                            removeDepends[curNumber].add(board[k][j]);
                        }
                    }
                }
            }
        }
    }

    private static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<n;
    }

    private static void upateMinMaxPoint(Point min,Point max,Point cur){
        min.x = Math.min(min.x,cur.x);
        min.y = Math.min(min.y,cur.y);
        max.x = Math.max(max.x,cur.x);
        max.y = Math.max(max.y,cur.y);
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int r = sol.solution(new int[][]{{0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,4,0,0,0},
                                    {0,0,0,0,0,4,4,0,0,0},
                                    {0,0,0,0,3,0,4,0,0,0},
                                    {0,0,0,2,3,0,0,0,5,5},
                                    {1,2,2,2,3,3,0,0,0,5},
                                    {1,1,1,0,0,0,0,0,0,5}});

        System.out.println(r);
    }
}
