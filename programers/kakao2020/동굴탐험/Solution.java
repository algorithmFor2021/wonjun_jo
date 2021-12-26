package programers.kakao2020.동굴탐험;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static int n;
    static int[][] path;
    static int[][] order;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static int[] prior;
    static int[] hang;
    static boolean[] visited;

    public boolean solution(int _n, int[][] _path, int[][] _order) {
        this.n = _n;
        this.path = _path;
        this.order = _order;
        prior = new int[200002];
        hang = new int[200002];
        visited = new boolean[200002];

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int[] p : path){
            adj.get(p[0]).add(p[1]);
            adj.get(p[1]).add(p[0]);
        }
        for(int[] o : order){
            prior[o[1]] = o[0];
        }

        if(prior[0] != 0) return false;

        visited[0] = true;
        dfs(0);
        for(int i=0;i<n;i++){
            if(!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public void dfs(int idx){
        for(int next : adj.get(idx)){
            if(!visited[next]){
                // 다음 방에 방문하기 전에 우선순위가 되는 방을 방문하지 않았다면 달아둔다. 다시 dfs 하기 위함
                if(!visited[prior[next]]){
                    //System.out.println("hanginng == " + next);
                    hang[prior[next]] = next;
                }
                else{
                    //System.out.println(idx + " go next -> " + next);
                    visited[next] = true;
                    dfs(next);
                }
            }
        }
        // 내번호로 달아둔게 있다면?
        if(hang[idx] != 0){
            //System.out.println(idx + " to restart -> " + hang[idx]);
            visited[hang[idx]] = true;
            dfs(hang[idx]);
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution().solution(9,new int[][]{
                {0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}
        },new int[][]{
                {4,1},{8,5},{6,7}
        }));

    }
}
