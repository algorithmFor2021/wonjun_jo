package boj.P16198;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static ArrayList<Integer> arr = new ArrayList<>();
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        for(int i=0;i<n;i++) arr.add(fs.nInt());
        dfs(0);
        System.out.println(answer);

    }

    static void dfs(int sum){
        if(arr.size()==2) answer = Math.max(answer, sum);
        else{
            for(int i=1;i<arr.size()-1;i++){
                int temp = arr.get(i-1)*arr.get(i+1);
                int a = arr.remove(i);
                dfs(sum + temp);
                arr.add(i,a);
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

        public char[] nToCharArray() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken().toCharArray();
        }
    }
}
