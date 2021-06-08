package boj.P1038;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        n = fs.nInt();

        dfs(0,10);

        for(int i=0;i<n;i++){
            pq.poll();
            if(pq.size() ==0 ) break;
        }
        System.out.println(pq.size()>0?pq.poll():-1);
    }
    // max = 9876543210
    static void dfs(long sum,int cur){
        if(cur == 0 ) return;
        for(int i=0;i<cur;i++){
            pq.add(sum+i);
            dfs((sum+i)*10,i);
        }

    }
    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception {
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
