package boj.P16964;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static Fs fs = new Fs();
    static int n;
    static ArrayList<Integer>[] adj = new ArrayList[100001];
    static boolean[] ch = new boolean[100001];
    public static void main(String[] args) throws Exception{
        for(int i=0;i<100001;i++) adj[i] = new ArrayList<>();
        n = fs.nInt();
        int a,b;
        for(int i=0;i<n-1;i++){
            a = fs.nInt();
            b = fs.nInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] cmp = new int[n+1];
        for(int i=0;i<n;i++){
            cmp[i] = fs.nInt();
        }

        if(cmp[0] != 1) {
            System.out.println(0);
            return;
        }
        Stack<HashSet<Integer>> stk = new Stack<>();
        HashSet<Integer> startSet = makeSet(1);
        ch[1] = true;
        if(!startSet.isEmpty()) stk.add(startSet);

        for(int i=1;i<n;i++){
            if(!stk.isEmpty()){
                HashSet<Integer> curSet = stk.peek();

                if(!curSet.contains(cmp[i])){
                    System.out.println(0);
                    return;
                }
                ch[cmp[i]] = true;
                curSet.remove(cmp[i]);

                if(curSet.isEmpty()) stk.pop();

                HashSet<Integer> child = makeSet(cmp[i]);
                if(child.size()>0) stk.add(child);

            }
            else {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);

    }

    static HashSet<Integer> makeSet(int x){
        HashSet<Integer> ret = new HashSet<>();
        for(int child : adj[x]){
            if(!ch[child]){
                ret.add(child);
            }
        }
        return ret;
    }


    static class Fs {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
