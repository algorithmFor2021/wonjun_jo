package boj.P16947;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static ArrayList<Integer>[] adj = new ArrayList[3001];
    static boolean[] visited = new boolean[3001];
    static int[] dist = new int[3001];
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        for(int i=0;i<=n;i++) adj[i] = new ArrayList<>();
        for(int i=0;i<n;i++){
            int a = fs.nInt();
            int b = fs.nInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        cycleCheck(1,1,list);
        for(int i=1;i<=n;i++){
            if(dist[i]==-1) {
                dfs(i,0);
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dist[1]==-1?0:dist[1]);
        for(int i=2;i<=n;i++) sb.append(" " + (dist[i]==-1?0:dist[i]));
        System.out.println(sb);

    }
    static void cycleCheck(int prev,int cur, ArrayList<Integer> list){
        for(int next : adj[cur]){
            if(next == prev) continue;
            int idx = checkList(list,next);
            if(idx == -1){
                list.add(next);
                cycleCheck(cur,next,list);
                list.remove(list.size()-1);
            }
            else {
                for(int i= idx;i< list.size();i++){
                    dist[list.get(i)] = -1;
                }
            }
        }
    }
    static int checkList(ArrayList<Integer> list,int next){
        for(int i=0;i<list.size();i++){
            if(list.get(i) == next) return i;
        }
        return -1;
    }

    static void dfs(int x,int cnt){
        for(int next : adj[x]){
            if(!visited[next]){
                visited[next] = true;
                if(dist[next]==-1) dfs(next,0);
                else {
                    dist[next] = cnt+1;
                    dfs(next,cnt+1);
                }
            }
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
