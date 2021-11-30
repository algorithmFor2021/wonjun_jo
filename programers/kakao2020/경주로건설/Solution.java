package programers.kakao2020.경주로건설;

public class Solution {
    static int n;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] b;
    static int[][][] payArr = new int[25][25][4];

    public int solution(int[][] board) {
        n = board.length;
        b = board;
        for(int i=0;i<25;i++){
            for(int j=0;j<25;j++){
                for(int k=0;k<4;k++) {
                    payArr[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        payArr[0][0][0] = 0;
        payArr[0][0][2] = 0;
        dfs(0,0,0,0);
        dfs(0,0,2,0);

        return Math.min(payArr[n-1][n-1][0],Math.min(payArr[n-1][n-1][1],
            Math.min(payArr[n-1][n-1][2],payArr[n-1][n-1][3])
        ));
    }

    public static void dfs(int x,int y,int dir,int sum){
        if(x == n-1 && y == n-1){
            return;
        }

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            int nextPay = 100 + (dir==i?0:500);

            if(checkPossible(nx,ny) && payArr[nx][ny][i] > sum + nextPay){
                payArr[nx][ny][i] = sum + nextPay;
                sum += nextPay;
                dfs(nx,ny,i,sum);
                sum -= nextPay;
            }
        }
    }

    public static boolean checkPossible(int x,int y){
        return x>=0 && x<n && y>=0 && y<n && b[x][y]==0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                {0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}
        }));
    }
}
