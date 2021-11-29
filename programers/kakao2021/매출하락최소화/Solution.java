package programers.kakao2021.매출하락최소화;


import java.util.ArrayList;

public class Solution {
    static Node[] nodes = new Node[300001];
    static int[][] dp = new int[300001][2];

    public int solution(int[] sales, int[][] links) {
        for(int i=1;i<=sales.length;i++) nodes[i] = new Node(i,sales[i-1]);
        for(int[] l : links){
            nodes[l[0]].child.add(nodes[l[1]]);
        }
        dfs(1);

        return Math.min(dp[1][0],dp[1][1]);
    }

    static void dfs(int idx){
        if(nodes[idx].child.size() == 0){
            dp[idx][0] = 0;
            dp[idx][1] = nodes[idx].amount;
            return;
        }

        for(Node c : nodes[idx].child){
            dfs(c.id);
        }
        int minimunAmount = 0;
        int minTmp = Integer.MAX_VALUE;
        int minTmpIdx = -1;
        boolean atLeastOneParticipantFlag = false;
        for(Node c : nodes[idx].child){
            if(dp[c.id][0] < dp[c.id][1]){
                minimunAmount += dp[c.id][0];
                if(minTmp > (dp[c.id][1]-dp[c.id][0])){
                    minTmpIdx = c.id;
                    minTmp = (dp[c.id][1]-dp[c.id][0]);
                }
            }else {
                atLeastOneParticipantFlag = true;
                minimunAmount += dp[c.id][1];
            }
        }
        dp[idx][1] = nodes[idx].amount + minimunAmount;
        if(!atLeastOneParticipantFlag) {
            dp[idx][0] = minimunAmount - dp[minTmpIdx][0] + dp[minTmpIdx][1];
        }else dp[idx][0] = minimunAmount;


    }

    static class Node{
        int id;
        int amount;
        ArrayList<Node> child = new ArrayList<>();

        public Node(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{5, 6, 5, 3, 4},
                new int[][]{
                        {2, 3}, {1, 4}, {2, 5}, {1, 2}
                }));
    }
}
