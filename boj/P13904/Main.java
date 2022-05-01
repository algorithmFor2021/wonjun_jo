package boj.P13904;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception{
        int n = fs.nInt();

        int[] score = new int[1001];
        ArrayList<Node> list = new ArrayList<>();

        for(int i=0;i<n;i++) {
            int d = fs.nInt();
            int w = fs.nInt();

            list.add(new Node(d,w));
        }

        Collections.sort(list);

        int answer = 0;
        for(Node node : list) {
            for(int i=node.day;i>=1;i--) {
                if(node.score > score[i]) {
                    score[i] = node.score;
                    break;
                }
            }
        }
        for(int i=1;i<=1000;i++) {
            answer += score[i];
        }

        System.out.println(answer);

    }

    static class Node implements Comparable<Node> {

        int day;
        int score;

        public Node(int day, int score) {
            this.day = day;
            this.score = score;
        }

        @Override
        public int compareTo(Node o) {
            return o.score - this.score;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "day=" + day +
                    ", score=" + score +
                    '}';
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt( )throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
