package programers.kakao2022.양과늑대;

import java.util.*;

public class Solution {
    static int[] info;
    static int maxS = 0;
    static ArrayList<Integer>[] adj;
    public int solution(int[] _info, int[][] _edges) {
        info = _info;
        adj = new ArrayList[17]; // 0~16
        for(int i=0;i<=16;i++) adj[i] = new ArrayList<>();
        for(int [] e : _edges){
            adj[e[0]].add(e[1]);
        }

        dfs(1,0,0,new ArrayList<>());

        System.out.println(maxS);
        return maxS;
    }
    // 현재 상태에서 방문할 수 있는 모든 노드를 방문 하는 것이 목표
    public void dfs(int s, int w, int idx, List<Integer> next){
        maxS = Math.max(s,maxS);

        List<Integer> list = new ArrayList<>();
        // 내 자녀들은 다음에 방문 해야함
        list.addAll(adj[idx]);
        // 부모 노드"들" 에서 다음으로 방문해야 할 노드들도 방문해야 하기 때문에 추가
        list.addAll(next);
        // 현재노드는 제거
        list.remove(Integer.valueOf(idx));

        for(int l : list){
            int nextS = s + (info[l]==0?1:0);
            int nextW = w + (info[l]==1?1:0);
            if(nextS > nextW) dfs(nextS,nextW, l,list);
        }
    }
    public static void main(String[] args) {
        new Solution().solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1},
                new int[][]{
                        {0,1},{1,2}
                        ,{1,4},{0,8}
                        ,{8,7},{9,10}
                        ,{9,11},{4,3}
                        ,{6,5},{4,6},{8,9}}
                );
    }
}
