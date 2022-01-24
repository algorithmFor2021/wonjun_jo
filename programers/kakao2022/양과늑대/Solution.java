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
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0,0,0,list);

        System.out.println(maxS);
        return maxS;
    }
    // 현재 상태에서 방문할 수 있는 모든 노드를 방문 하는 것이 목표
    public void dfs(int s, int w, int idx, List<Integer> next){
        if(info[idx]==0) s+= 1;
        else w += 1;

        if(s <= w) return;

        maxS = Math.max(s,maxS);

        ArrayList<Integer> tmp = new ArrayList<>();
        // 이때까지
        tmp.addAll(next);
        tmp.addAll(adj[idx]);
        tmp.remove(Integer.valueOf(idx));
        System.out.println(tmp);
        for(int nxt : tmp){
            dfs(s,w,nxt,tmp);
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
