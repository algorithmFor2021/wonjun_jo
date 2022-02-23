package boj.P10819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[] arr = new int[10];
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        input();
        dfs(0,new boolean[10],new ArrayList<>());
        System.out.println(answer);
    }

    static void input() throws Exception{
        n = fs.nInt();
        for(int i=0;i<n;i++){
            arr[i] = fs.nInt();
        }
    }

    static void dfs(int idx , boolean[] visit , ArrayList<Integer> list) {
        if(idx == n) {
            answer = Math.max(answer, cal(list));
            return;
        }

        for(int i=0;i<n;i++) {
            if(!visit[i]) {
                visit[i] = true;
                list.add(arr[i]);
                dfs(idx+1,visit,list);
                list.remove(list.size()-1);
                visit[i] = false;
            }
        }
    }

    static int cal(ArrayList<Integer> list) {
        int ret = 0;
        for(int i=0;i<list.size()-1;i++) {
            ret += Math.abs(list.get(i)-list.get(i+1));
        }
        return ret;
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
