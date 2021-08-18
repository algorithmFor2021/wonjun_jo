package boj.P1275;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,testCase;
    static long[] segTree = new long[400004];
    static long[] base = new long[100001];

    static void update(int node,int st,int ed,int target,int val){
        if(st > target || ed < target) return;
        if(st == ed) {
            segTree[node] = val;
            return;
        }
        update(node*2,st,(st+ed)/2,target,val);
        update(node*2+1,(st+ed)/2+1,ed,target,val);
        segTree[node] = segTree[node*2] + segTree[node*2+1];

    }

    static long init(int node,int st,int ed){
        if(st == ed) return segTree[node] = base[st];
        return segTree[node] = init(node*2,st,(st+ed)/2)
                + init(node*2+1,(st+ed)/2+1,ed);
    }
    static long query(int node,int st,int ed,int left,int right){
        if(st > right || ed < left) return 0;
        if(left<=st && ed<=right) return segTree[node];
        return query(node*2,st,(st+ed)/2,left,right)
                +query(node*2+1,(st+ed)/2+1,ed,left,right);
    }

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        testCase = fs.nInt();
        for(int i=0;i<n;i++) base[i] = fs.nInt();
        init(1,0,n-1);

        StringBuilder sb = new StringBuilder();
        int x,y,a,b;
        while(testCase-->0){
            x = fs.nInt()-1;
            y = fs.nInt()-1;
            a = fs.nInt()-1;
            b = fs.nInt();
            sb.append(query(1,0,n-1,Math.min(x,y),Math.max(x,y))).append('\n');
            update(1,0,n-1,a,b);
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
