package boj.P16928;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static Fs fs = new Fs();
    static boolean[] visited = new boolean[101];
    static HashMap<Integer,Integer> cross = new HashMap<>();

    public static void main(String[] args) throws Exception{
        int n=fs.nInt(),m=fs.nInt();
        for(int i=0;i<n+m;i++){
            int from =fs.nInt();
            int to = fs.nInt();
            cross.put(from,to);
        }


        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1,0));
        visited[1] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.loc == 100) {
                System.out.println(node.cnt);
                break;
            }

            for(int dice = 1; dice <= 6 ;dice++){
                int next = node.loc + dice;
                if(cross.containsKey(next)) next = cross.get(next);
                if(checkRange(next) && !visited[next]){
                    visited[next] = true;
                    q.add(new Node(next,node.cnt+1));
                }
            }
        }


    }
    static boolean checkRange(int x){
        if(x<1 || x>100) return false;
        return true;
    }

    static class Node{
        int loc;
        int cnt;

        public Node(int loc, int cnt) {
            this.loc = loc;
            this.cnt = cnt;
        }
    }



    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
