package boj.P1068;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int startNum = -1;
    static int removeNum = -1;
    static int answer = 0;
    static Node[] nodes = new Node[50];
    public static void main(String[] args) throws Exception {
        input();
        findLeaf(startNum);
        System.out.println(answer);
    }

    static void findLeaf(int idx){
        if(idx == removeNum) return;

        if(nodes[idx].child.size() == 0){
            answer++;
            return;
        }

        if(nodes[idx].child.size() == 1 && nodes[idx].child.get(0).id ==removeNum) {
            answer++;
            return;
        }

        for(Node child : nodes[idx].child){
            findLeaf(child.id);
        }

    }

    static void input() throws Exception{
        /**
          5
          -1 0 0 1 1
          2
         */
        n = fs.nInt();
        for(int i=0;i<n;i++) nodes[i] = new Node(i);
        for(int i=0;i<n;i++){
            int t = fs.nInt();
            if(t != -1) nodes[t].child.add(nodes[i]);
            else startNum = i;
        }
        removeNum = fs.nInt();
    }

    static class Node{
        int id;
        List<Node> child = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    // FastScanner
    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");


        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }
    }
}
