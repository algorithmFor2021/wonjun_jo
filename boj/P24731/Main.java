package boj.P24731;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main 설명 : 정수론 문제?? 나중에 다시 풀기
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/03/25
**/
public class Main {
    static Fs fs = new Fs();
    static long MOD = 1000003;
    public static void main(String[] args) throws Exception {
        long n = fs.nInt();
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
