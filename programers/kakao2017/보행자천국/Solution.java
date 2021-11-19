package programers.kakao2017.보행자천국;

public class Solution {
    int MOD = 20170805;
    int[][] dp = new int[502][502];
    int[][] arr = new int[502][502];

    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i+1][j+1] = cityMap[i][j];
            }
        }

        dp[0][1] = 1;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(arr[i][j] == 1) dp[i][j] = 0;
                else {
                    int left = j-1;
                    while(arr[i][left]==2) left--;
                    int up = i-1;
                    while(arr[up][j]==2) up--;
                    dp[i][j] = (dp[i][left] + dp[up][j])%MOD;
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(3,6,new int[][]{
                {0, 2, 0, 0, 0, 2},
                {0, 0, 2, 0, 1, 0},
                {1, 0, 0, 2, 2, 0}
        });
    }
}
