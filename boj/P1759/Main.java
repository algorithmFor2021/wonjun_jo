package boj.P1759;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static StringBuilder answer = new StringBuilder();
    static Set<Character> set = new HashSet<>();
    static char[] ch = new char[15];
    static int l,c;

    public static void main(String[] args) throws Exception{
        input();
        dfs(0,0,new boolean[15]);
        System.out.print(answer);
    }

    static void dfs(int idx,int cnt,boolean[] visit){
        if(idx == c){
            StringBuilder s = new StringBuilder();
            for(int i=0;i<c;i++) if(visit[i]) s.append(ch[i]);
            if(s.length()!= l || !check(s.toString())) return;
            answer.append(s.toString()).append('\n');
        }else{
            visit[idx] = true;
            dfs(idx+1,cnt+1,visit);
            visit[idx] = false;
            dfs(idx+1,cnt,visit);
        }
    }

    static boolean check(String s){
        int sum = 0;
        for(int i=0;i<s.length();i++){
            if(set.contains(s.charAt(i))){
                sum += 1;
            }
        }
        return sum >= 1 && l - sum >= 2;
    }

    static void input() throws Exception{
        l = fs.nInt();
        c = fs.nInt();
        int idx = 0;
        for(char cr  : fs.nextLine().toCharArray()) if(cr != ' ') ch[idx++] = cr;
        ArrayList<Character> sortinglist = new ArrayList<>();
        for(int i=0;i<c;i++) sortinglist.add(ch[i]);
        Collections.sort(sortinglist);
        for(int i=0;i<c;i++) ch[i] = sortinglist.get(i);
        set.addAll(Arrays.asList('a','e','i','o','u'));
    }




    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public String nextLine() throws Exception{
            return br.readLine();
        }

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }
    }
}
