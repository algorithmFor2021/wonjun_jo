package boj.P19539;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// java11 백준 속도 1등!
public class Main {
    static Fs fs = new Fs();
    static int n;
    static long sum = 0L;
    static long cnt = 0L;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        for(int i=0;i<n;i++){
            int next = fs.nInt();
            sum += next;
            cnt += next/2;
        }
        if (sum % 3 == 0) {
            if(sum/3 <= cnt) System.out.println("YES");
            else System.out.println("NO");
        }
        else System.out.println("NO");

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
