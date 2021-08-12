package boj.P16940;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static ArrayList<Integer>[] adj = new ArrayList[100001];
    static Queue<HashSet<Integer>> qOfSet = new LinkedList<>();

    public static void main(String[] args) throws Exception{
            n = fs.nInt();
            for(int i=0;i<=100000;i++) adj[i] = new ArrayList<>();

            int a,b;
            for(int i=0;i<n-1;i++){
                a = fs.nInt();
                b = fs.nInt();
                adj[a].add(b);
                adj[b].add(a);
            }
            int[] cmp = new int[n+1];
            for(int i=0;i<n;i++) cmp[i] = fs.nInt();

            HashSet<Integer> startHashSet = new HashSet<>();
            startHashSet.add(1);
            qOfSet.add(startHashSet);
            boolean[] ch  = new boolean[100001];
            ch[1] = true;

            for(int i=0;i<n;i++){
                if(!qOfSet.isEmpty()){
                    HashSet<Integer> set = qOfSet.peek();

                    if(!set.contains(cmp[i])){
                        System.out.println(0);
                        return;
                    }
                    else{
                        set.remove(cmp[i]);
                        if(set.isEmpty()) qOfSet.poll();
                        HashSet<Integer> nextSet = new HashSet<>();
                        for (int next : adj[cmp[i]]) {
                            if (!ch[next]) {
                                ch[next] = true;
                                nextSet.add(next);
                            }
                        }
                        if(nextSet.size()>0) qOfSet.add(nextSet);
                    }
                }
                else {
                    System.out.println(0);
                    return;
                }
            }

        System.out.println(1);
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
