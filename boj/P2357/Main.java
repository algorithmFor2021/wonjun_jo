package boj.P2357;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[] maxSegTree = new int[100_000*4+1];
    static int[] minSegTree = new int[100_000*4+1];
    static int[] base = new int[100_000+1];

    public static int maxQuery(int node,int st,int ed,int left,int right){
        if(st > right || ed < left) return 0;
        if(left <= st && ed <= right) return maxSegTree[node];
        return Math.max(maxQuery(node*2,st,(st+ed)/2,left,right),
                    maxQuery(node*2+1,(st+ed)/2+1,ed,left,right)
                );

    }
    public static int minQuery(int node,int st,int ed,int left,int right){
        if(st > right || ed < left) return Integer.MAX_VALUE;
        if(left <= st && ed <= right) return minSegTree[node];
        return Math.min(minQuery(node*2,st,(st+ed)/2,left,right),
                minQuery(node*2+1,(st+ed)/2+1,ed,left,right)
        );

    }

    public static int maxInit(int node,int st,int ed){
        if(st == ed) return maxSegTree[node] = base[st];
        else return maxSegTree[node] = Math.max(maxInit(node*2,st,(st+ed)/2),
                maxInit(node*2+1,(st+ed)/2+1,ed));
    }

    public static int minInit(int node,int st,int ed){
        if(st == ed) return minSegTree[node] = base[st];
        else return minSegTree[node] = Math.min(minInit(node*2,st,(st+ed)/2),
                minInit(node*2+1,(st+ed)/2+1,ed));
    }

    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        int m = fs.nInt();
        for(int i=0;i<n;i++) base[i] = fs.nInt();

        maxInit(1,0,n-1);
        minInit(1,0,n-1);

        StringBuilder sb = new StringBuilder();
        int a,b;
        for(int i=0;i<m;i++) {
            a = fs.nInt()-1;
            b = fs.nInt()-1;
            sb.append(minQuery(1,0,n-1,a,b))
                    .append(" ")
                    .append(maxQuery(1,0,n-1,a,b))
                    .append("\n")
            ;
        }

        System.out.println(sb);
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
