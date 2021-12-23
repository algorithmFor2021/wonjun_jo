package programers.kakao2021.미로탈출;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    static int INF = 987654321;
    static int n;
    static int[][] adj = new int[1001][1001]; // 1~ 1000
    static int[] visited = new int[1001];
    static int[][] dist = new int[1001][3];
    static Set<Integer> traps;
    static int s,e;
    public int solution(int _n, int start, int end, int[][] roads, int[] _traps) {
        n = _n;
        traps = Arrays.stream(_traps).boxed().collect(Collectors.toSet());
        s = start;
        e = end;

        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                if(j < 3) dist[i][j] = INF;
                adj[i][j] = INF;
            }
        }

        for (int[] road : roads) {
            int p = road[0];
            int q = road[1];
            int s = road[2];
            adj[p][q] = Math.min(adj[p][q], s);
        }

        visited[s] = 1;
        dist[s][visited[s]] = 0;
        dfs(s);

        return Math.min(dist[e][0],Math.min(dist[e][1],dist[e][2]));
    }

    public static void dfs(int cur){
        if(traps.contains(cur)) revertRoad(cur);
        for(int i=1;i<=n;i++){
            if(adj[cur][i] != INF && visited[i]<2 && dist[i][visited[i]+1] > dist[cur][visited[cur]] + adj[cur][i]){
                dist[i][visited[i]+1] = dist[cur][visited[cur]] + adj[cur][i];
                visited[i] += 1;
                dfs(i);
                visited[i] -= 1;
            }
        }
        if(traps.contains(cur)) revertRoad(cur);
    }

    public static void revertRoad(int cur){
        for(int i=1;i<=n;i++){
            int temp = adj[i][cur];
            adj[i][cur] = adj[cur][i];
            adj[cur][i] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(4,1,4,new int[][]{
                {1,2,1},
                {3,2,1},
                {2,4,1}
        },new int[]{2,3}));
    }
}
