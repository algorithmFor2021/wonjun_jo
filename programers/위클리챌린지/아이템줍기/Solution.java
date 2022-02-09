package programers.위클리챌린지.아이템줍기;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Solution 설명 : 아이템 줍기
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/02/10
**/
public class Solution {
    static int[][] arr = new int[102][102];
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        setRectangle(rectangle);
        removeDot(rectangle);
        arr[characterY*2][characterX*2] = 2;
        arr[itemY*2][itemX*2] = 3;
        go(characterY*2,characterX*2,0);
        return answer/2;
    }

    static void go(int x,int y,int sum) {
        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=2 && nx<=100 && ny>=2 && ny<=100 && arr[nx][ny]==1) {
                arr[nx][ny] = 2;
                go(nx,ny,sum+1);
            }

            if(arr[nx][ny] == 3) {
                answer = Math.min(answer,sum+1);
            }
        }
    }

    public void removeDot(int[][] rectangle) {
        for(int i=1;i<=100;i++) {
            for(int j=1;j<=100;j++){
                if(arr[i][j]==0) continue;
                boolean flag = false;
                for(int[] r : rectangle) {
                    if(r[0]*2< j && r[2]*2 > j && r[1]*2 < i && r[3]*2 > i) {
                        flag = true;
                        break;
                    }
                }
                if(flag) arr[i][j] = 0;
            }
        }
    }

    public void setRectangle(int[][] rectangle){
        for(int[] rec : rectangle) {
            for(int i=rec[0]*2;i<=rec[2]*2;i++) {
                arr[rec[1]*2][i] = 1;
                arr[rec[3]*2][i] = 1;
            }
            for(int i=rec[1]*2;i<=rec[3]*2;i++) {
                arr[i][rec[0]*2] = 1;
                arr[i][rec[2]*2] = 1;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                {1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}
        },9,7,6,1));
    }
}
