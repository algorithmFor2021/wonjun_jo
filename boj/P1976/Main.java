package boj.P1976;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Main 설명 :
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/28
**/
public class Main {
    static Fs fs = new Fs();
    static int[][] adj = new int[201][201];
    static int n;
    static int m;
    static boolean[] visit = new boolean[201];
    static ArrayList<HashSet<Integer>> comp = new ArrayList<>();
    static ArrayList<Integer> tripPlan = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                adj[i][j] = fs.nInt();
            }
        }

        for(int i=0;i<m;i++) tripPlan.add(fs.nInt());

        for(int i=1;i<=n;i++) {
            if(!visit[i]) {
                comp.add(makeSet(i));
            }
        }

        boolean answer = false;
        for(HashSet<Integer> set : comp) {
            boolean temp = true;

            for(int plan : tripPlan) {
                if(!set.contains(plan)) {
                    temp = false;
                }
            }

            if (temp) {
                answer = true;
                break;
            }
        }

        System.out.println(answer?"YES":"NO");


    }

    static HashSet<Integer> makeSet(int start) {
        HashSet<Integer> ret = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        ret.add(start);
        visit[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int i=1;i<=n;i++) {
                if(adj[cur][i]==1 && !visit[i]) {
                    visit[i] = true;
                    ret.add(i);
                    q.add(i);
                }
            }

        }

        return ret;

    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
