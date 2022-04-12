package boj.P1655;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main 설명 : 가운데를 말해요
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/12
**/
public class Main {
    static Fs fs = new Fs();
    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(n, (o1,o2)->o1-o2);
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(n, (o1,o2)->o2-o1);
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<n;i++) {
            int next = fs.nInt();

            if(maxPQ.isEmpty()) maxPQ.add(next);
            else if(minPQ.isEmpty()) {
                if(next < maxPQ.peek()) {
                    minPQ.add(maxPQ.poll());
                    maxPQ.add(next);
                }else {
                    minPQ.add(next);
                }
            }
            else {
                if(maxPQ.peek() < next) {
                    minPQ.add(next);
                }else {
                    maxPQ.add(next);
                }
            }

            // 균형 잡아주기
            if(Math.abs(minPQ.size() - maxPQ.size()) == 2 ) {
                if(minPQ.size() > maxPQ.size()) {
                    maxPQ.add(minPQ.poll());
                }else {
                    minPQ.add(maxPQ.poll());
                }
            }

            if(minPQ.size() <= maxPQ.size()) sb.append(maxPQ.peek());
            else sb.append(minPQ.peek());

            sb.append("\n");
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
    }
}
