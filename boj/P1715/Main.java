package boj.P1715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0;i<n;i++) pq.add(fs.nInt());

        int sum = 0;

        while(!pq.isEmpty()) {
            if(pq.size() == 1) break;
            else {
                int a = pq.poll();
                int b = pq.poll();
                sum += (a + b);
                pq.add(a+b);
            }
        }

        System.out.println(sum);
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
