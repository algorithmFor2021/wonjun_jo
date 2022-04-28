package boj.P1167;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static ArrayList<Node>[] adj;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        adj = new ArrayList[n+1];
        for(int i=0;i<=n;i++) adj[i] = new ArrayList<>();

        for(int i=1;i<=n;i++) {
            int num = fs.nInt();

            while(true) {
                int to = fs.nInt();
                if(to == -1) break;
                int dist = fs.nInt();

                adj[num].add(new Node(to,dist));
            }
        }

        boolean[] visit = new boolean[100001];
        visit[1] = true;
        dfs(1,visit,0);

        System.out.println(answer);


    }

    static int dfs(int start,boolean[] visit,int parentDist) {

        ArrayList<Integer> comp = new ArrayList<>();
        for(Node next : adj[start]) {
            if(!visit[next.to]) {
                visit[next.to] = true;
                comp.add(dfs(next.to,visit,next.dist));
            }
        }


        if(comp.size() == 0 ) {
            answer = Math.max(answer,parentDist);
            return parentDist;
        } else if(comp.size() == 1){
            answer = Math.max(answer,comp.get(0));
            return comp.get(0) + parentDist;
        }else {
            comp.sort(Comparator.reverseOrder());
            answer = Math.max(answer,comp.get(0) + comp.get(1));
            return comp.get(0) + parentDist;
        }

    }


    static class Node {
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", dist=" + dist +
                    '}';
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
