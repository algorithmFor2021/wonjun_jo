package boj.P11437;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
/**
 * Main 설명 :  LCA2
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/03/28
**/
public class Main {
    static Fs fs = new Fs();
    static Node[] nodes = new Node[50001];
    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        for(int i=0;i<=n;i++) {
            nodes[i] = new Node(i);
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<n-1;i++) {
            int a = fs.nInt();
            int b = fs.nInt();

            nodes[a].child.add(new Node(b));
            nodes[b].child.add(new Node(a));
        }

        boolean[] v = new boolean[50001];
        v[1] = true;
        makeParent(1,v);

        int t = fs.nInt();

        while(t-->0) {
            int c1 = fs.nInt();
            int c2 = fs.nInt();

            int ans = nodes[c2].findMinParent(nodes[c1].getParentSet());

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void makeParent(int cur,boolean[] visit) {
        for(Node next : nodes[cur].child) {
            if(!visit[next.id]) {
                nodes[next.id].parent = cur;
                visit[next.id] =true;
                makeParent(next.id,visit);
            }
        }
    }


    static class Node{
        int id;
        int parent;
        ArrayList<Node> child = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public HashSet<Integer> getParentSet() {
            int curParent = id;
            HashSet<Integer> set = new HashSet<>();
            while(curParent != 0) {
                set.add(curParent);
                curParent = nodes[curParent].parent;
            }
            return set;
        }

        public int findMinParent(HashSet<Integer> parentSet) {
            int curParent = id;
            while(curParent != 0) {
                if(parentSet.contains(curParent)) return curParent;
                curParent = nodes[curParent].parent;
            }
            return 1;
        }
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
