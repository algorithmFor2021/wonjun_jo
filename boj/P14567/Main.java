package boj.P14567;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs  = new Fs();
    static ArrayList<Integer>[] arr;
    static StringBuilder sb= new StringBuilder();
    static int[] degree = new int[1001];
    static int[] answer = new int[1001];
    static int v,m;
    public static void main(String[] args) throws Exception{
        v = fs.nInt();
        m = fs.nInt();
        arr = new ArrayList[v+1];
        for(int i=1;i<=v;i++) arr[i] = new ArrayList<>();

        for(int i =0;i<m;i++){
            int first = fs.nInt();
            int next = fs.nInt();

            arr[first].add(next);
            degree[next]++;
        }

        topologySort();

        for(int i=1;i<=v-1;i++) {
            sb.append(answer[i]+1).append(" ");
        }
        sb.append(answer[v]+1);
        System.out.println(sb.toString());



    }

    static void topologySort(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=v;i++) {
            if(degree[i]==0) q.add(i);
        }

        while(!q.isEmpty()){
            int subject = q.poll();

            for(int next : arr[subject]){
                answer[next] = answer[subject]+1;
                if(--degree[next]==0){
                    q.add(next);
                }
            }

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
