package boj.P2812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int n,k;

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        k = fs.nInt();
        int r = n-k;
        char[] s = fs.next().toCharArray();
        Deque<Integer> q = new ArrayDeque<>();
        for(char c : s) {
            int a = c - '0';
            while(k>0 && !q.isEmpty() && q.peekLast() < a) {
                k--;
                q.pollLast();
            }

            q.addLast(a);

        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty() && sb.length() != r) {
            sb.append(q.pollFirst());
        }

        System.out.println(sb);
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        public String next() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
