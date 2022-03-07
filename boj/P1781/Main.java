package boj.P1781;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Main 설명 :
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/03/07
**/
public class Main {
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        ArrayList<Node> list = new ArrayList<>();
        for(int i=0;i<n;i++) {
            list.add(new Node(fs.nInt(),fs.nInt()));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Collections.sort(list);
        int answer = 0;
        for(Node node : list) {
            if(node.deadLine > pq.size()) {
                pq.add(node.ramen);
            }
            else {
                if(pq.isEmpty()) continue;
                int peek = pq.peek();
                if(peek < node.ramen) {
                    pq.poll();
                    pq.add(node.ramen);
                }
            }
        }
        while(!pq.isEmpty()) answer += pq.poll();
        System.out.println(answer);
    }
    static class Node implements Comparable<Node>{
        int deadLine;
        int ramen;

        public Node(int deadLine, int ramen) {
            this.deadLine = deadLine;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Node o) {
            if(this.deadLine != o.deadLine) return this.deadLine - o.deadLine;
            return o.ramen - this.ramen;
        }
    }
    static class Fs {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
