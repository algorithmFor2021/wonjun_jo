package boj.P1766;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[] degree = new int[32001];
    static ArrayList<Integer>[] a;

    public static void main(String[] args) throws Exception{
        input();
        System.out.print(topology());
    }

    static StringBuilder topology(){
        StringBuilder sb= new StringBuilder();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i=1;i<=n;i++){
            if(degree[i]==0) q.add(i);
        }

        while(!q.isEmpty()){

            int id = q.poll();

            sb.append(id).append(" ");

            for(int conn : a[id]){
                if(--degree[conn]==0) q.add(conn);
            }
        }

        return sb;
    }

    static void input() throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        a = new ArrayList[n+1];
        for(int i=1;i<=n;i++) a[i] = new ArrayList<>();
        for(int i=0;i<m;i++){
            int fr = fs.nInt();
            int to = fs.nInt();
            a[fr].add(to);
            degree[to] += 1;
        }
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
