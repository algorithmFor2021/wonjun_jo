package boj.P1043;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[] parent;
    static int n,m;
    static int pCnt;
    static ArrayList<Integer>[] party = new ArrayList[51];

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();

        parent = new int[n+1];
        for(int i=1;i<=n;i++) {
            parent[i] = i;
        }
        for(int i=1;i<=m;i++) {
            party[i] = new ArrayList<>();
        }

        pCnt = fs.nInt();
        int g = 0;
        if(pCnt > 0) {
            g = fs.nInt();
        }
        for(int i=1;i<pCnt;i++) {
            int t = fs.nInt();
            union(g,t);
        }

        for(int i=1;i<=m;i++) {
            int partyNum = fs.nInt();
            if(partyNum >= 2) {
                int standard = fs.nInt();
                for(int j=1;j<partyNum;j++) {
                    int k = fs.nInt();
                    union(standard,k);
                    party[i].add(k);
                }
            }else {
                party[i].add(fs.nInt());
            }
        }


        if(g == 0) {
            System.out.println(m);
            return;
        }

        int ans = 0;
        for(int i=1;i<=m;i++) {
            if(find(party[i].get(0)) != find(g) ) {
                ans++;
            }
        }

        System.out.println(ans);



    }

    static int find(int x) {
        if(parent[x] == x ) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a,int b) {
        int pa = find(a);
        int pb = find(b);
        parent[pb] = pa;
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
