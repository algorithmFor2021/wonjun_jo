package boj.P14889;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int k;
    static int[][] s = new int[21][21];
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited = new boolean[21];
    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        k = n/2;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                s[i][j] = fs.nInt();
            }
        }

        for(int i=0;i<1<<n;i++){
            if(Integer.bitCount(i) == k) {
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) visited[j+1] = true;
                    else visited[j+1] = false;
                }
                answer = Math.min(answer, sum(visited));
            }
        }

        System.out.println(answer);

    }
    static int sum(boolean[] visited){
        int teamA = 0;
        int teamB = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j) continue;
                // 같은 팀
                if(visited[i] && visited[i] == visited[j]){
                    teamA += s[i][j];
                }
                else if(!visited[i] && visited[i] == visited[j]){
                    teamB += s[i][j];
                }
            }
        }
        return Math.abs(teamA-teamB);
    }
    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
