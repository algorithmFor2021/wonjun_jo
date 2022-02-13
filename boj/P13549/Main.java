package boj.P13549;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[] visited = new int[200002];

    public static void main(String[] args) throws Exception{
        int st = fs.nInt();
        int ed = fs.nInt();

        for(int i=0;i<=200001;i++) visited[i] = Integer.MAX_VALUE;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(st,0));
        visited[st] = 0;

        while(!q.isEmpty()) {
            Pair cur = q.poll();

            // 순간이동
            if(cur.loc > 0) {
                int t = cur.loc*2;
                while(t <= ed*2) {
                    if(visited[t] < cur.cnt) break;
                    visited[t] = cur.cnt;
                    q.add(new Pair(t,cur.cnt));
                    t *= 2;
                }
            }

            if(cur.loc <= ed && visited[cur.loc+1] > cur.cnt+1) {
                visited[cur.loc+ 1] = cur.cnt+1;
                q.add(new Pair(cur.loc+1,cur.cnt+1));
            }
            if(cur.loc > 0 && visited[cur.loc-1] > cur.cnt+1) {
                visited[cur.loc-1] = cur.cnt + 1;
                q.add(new Pair(cur.loc-1,cur.cnt+1));
            }
        }

        System.out.println(visited[ed]);
    }
    static class Pair{
        int loc;
        int cnt;

        public Pair(int loc, int cnt) {
            this.loc = loc;
            this.cnt = cnt;
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }

        public String next() throws Exception{
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
