package boj.P10974;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static Fs fs = new Fs();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        dfs(1,new boolean[10],new ArrayList<>());
        System.out.print(sb.toString());
    }

    static void dfs(int idx,boolean[] visit, ArrayList<Integer> list){
        if(idx == n+1) {
            for(int l : list) {
                sb.append(l).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append('\n');
            return;
        }

        for(int i=1;i<=n;i++) {
            if(!visit[i]) {
                visit[i] = true;
                list.add(i);
                dfs(idx+1,visit,list);
                list.remove(Integer.valueOf(i));
                visit[i] = false;
            }
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
