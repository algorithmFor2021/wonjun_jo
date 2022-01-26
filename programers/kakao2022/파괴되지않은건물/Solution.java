package programers.kakao2022.파괴되지않은건물;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[][]{
                        {5,5,5,5,5}
                        ,{5,5,5,5,5}
                        ,{5,5,5,5,5}
                        ,{5,5,5,5,5}
                },
                new int[][]{
                        {1,0,0,3,4,4}
                        ,{1,2,0,2,3,2}
                        ,{2,1,0,3,1,2}
                        ,{1,0,1,3,3,1}
                }
        ));
    }

    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] temp = new int[n+1][m+1];

        for(int[] sk : skill){
            int val = sk[0]==1?-sk[5]:sk[5];
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];
            for(int i=r1;i<=r2;i++){
                temp[i][c1] += val;
                temp[i][c2+1] -= val;
            }
        }

        for(int i=0;i<n;i++){
            for(int j=1;j<m;j++){
                temp[i][j] = temp[i][j] + temp[i][j-1];
            }
        }
        int answer = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]+temp[i][j]>0) answer++;
            }
        }
        return answer;
    }
}
