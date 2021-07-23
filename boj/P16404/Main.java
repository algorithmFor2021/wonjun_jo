package boj.P16404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


class Main{

    static int n,m;

    static int[] S;
    static int[] E;
    static int cnt = 0;

    static long[] tree;
    static long[] lazy;

    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    static Fs fs = new Fs();

    public static void updateLazy(int node,int st,int ed){
        if(lazy[node]!=0){
            tree[node] += (ed-st+1)*lazy[node];

            if(st!=ed){
                lazy[node*2] += lazy[node];
                lazy[node*2+1] += lazy[node];
            }
        }
        lazy[node] = 0;
    }

    public static void update(int node,int st ,int ed,int left,int right,long val){
        updateLazy(node,st,ed);
        if(st > right || ed < left) return;
        if(left<= st && ed <= right) {
            tree[node] += (ed-st+1)*val;

            if(st != ed){
                lazy[node*2] += val;
                lazy[node*2 + 1 ] += val;
            }
            return;
        }

        update(node*2,st,(st+ed)/2,left,right,val);
        update(node*2+1,(st+ed)/2+1,ed,left,right,val);
        tree[node] = tree[node*2] + tree[node*2+1];
    }

    public static long query(int node,int st ,int ed,int left,int right){
        updateLazy(node,st,ed);
        if(st > right || ed < left) return 0;
        if(left <= st && ed <= right) {
            return tree[node];
        }
        return query(node*2,st,(st+ed)/2,left,right)
                + query(node*2+1,(st+ed)/2+1,ed,left,right);
    }

    public static void dfsNumbering(int idx){
        S[idx] = ++cnt;
        for(int next : adj.get(idx)){
            dfsNumbering(next);
        }
        E[idx] = cnt;
    }

    public static void main(String[] args) throws IOException{
        n = fs.nInt();
        m = fs.nInt();

        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());
        S = new int[n+1];
        E = new int[n+1];

        for(int i=1;i<=n;i++){
            int parent = fs.nInt();
            if(parent != - 1 ) adj.get(parent).add(i);
        }


        // tree 의 구간화
        dfsNumbering(1);

        int size = 1;
        while(size < n ){
            size <<= 1;
        }size<<=1;

        tree = new long[size];
        lazy = new long[size];

        StringBuilder sb = new StringBuilder();

        // 처음 모든 잔액은 0원 이므로 init 없음

        while(m-->0){
            int cond = fs.nInt();
            int target = fs.nInt();
            if(cond == 1){ // update
                long val = fs.nLong();
                update(1,1,n,S[target],E[target],val);
            }
            else{ // print
                sb.append(query(1,1,n,S[target],S[target])).append('\n');
            }
        }

        System.out.println(sb);





    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws IOException {
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
        public long nLong() throws IOException {
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Long.parseLong(st.nextToken());
        }
    }
}
