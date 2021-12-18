package programers.도둑질;

public class Solution {
    int[] dp1 = new int[1000001];
    int[] dp2 = new int[1000001];
    int n;
    public int solution(int[] money) {
        n = money.length;
        dp1[0] = money[0];
        dp1[1] = 0;
        dp1[2] = dp1[0] + money[2];
        dp2[0] = 0;
        dp2[1] = money[1];
        dp2[2] = money[2];

        for(int i=3;i<n;i++){
            dp1[i] = Math.max(Math.max(dp1[i-3] + money[i],dp1[i-2] + money[i]),dp1[i-1]);
            dp2[i] = Math.max(Math.max(dp2[i-3] + money[i],dp2[i-2] + money[i]),dp2[i-1]);
        }

        return Math.max(dp1[n-2],dp2[n-1]);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{1,2,3,1}));
    }
}
