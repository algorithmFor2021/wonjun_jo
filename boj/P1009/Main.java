package boj.P1009;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        int t = fs.nInt();
        while(t-- > 0) {
            sb.append(solve(fs.nInt(),fs.nInt())).append('\n');
        }
        System.out.println(sb);
    }

    static int solve(int a,int b){
        int answer = a;
        for(int i=0;i<b-1;i++){
            answer *= a;
            answer %= 10;
            System.out.println(answer);
        }
        return answer==0?10:answer;
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
