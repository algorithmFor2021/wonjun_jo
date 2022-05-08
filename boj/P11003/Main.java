package boj.P11003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        int l = fs.nInt();
        int[] arr = new int[n];
        StringBuilder sb = new StringBuilder();

        Deque<Node> dq = new ArrayDeque<>();

        for(int i=0;i<n;i++) {
            arr[i] = fs.nInt();
        }

        for(int i=0;i<n;i++) {
            while(!dq.isEmpty() && dq.peekFirst().idx < i-l+1) {
                dq.pollFirst();
            }
            while(!dq.isEmpty() && dq.peekLast().data > arr[i]) {
                dq.pollLast();
            }

            dq.addLast(new Node(arr[i],i));

            sb.append(dq.peekFirst().data).append(" ");
        }
        System.out.println(sb);
    }

    static class Node{
        int data;
        int idx;

        public Node(int data, int idx) {
            this.data = data;
            this.idx = idx;
        }
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
