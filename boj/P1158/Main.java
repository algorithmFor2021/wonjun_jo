package boj.P1158;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        int k = fs.nInt();

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++) q.add(i);

        int cnt = 0;
        while(!q.isEmpty()) {
            cnt = 0;
            while(cnt != k-1) {
                q.add(q.poll());
                cnt++;
            }

            sb.append(q.poll()).append(", ");

        }

        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);

        sb.append(">");

        System.out.println(sb);
    }
    static class Fs {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
