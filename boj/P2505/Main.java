package boj.P2505;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * Main 설명 : 두번 뒤집기
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/03/31
**/
public class Main {
    static Fs fs = new Fs();
    static int n;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        int[] arr= new int[n+1];
        for(int i=1;i<=n;i++) arr[i] = fs.nInt();


    }

    static boolean isOneReversed(int[] a) {
        int num = 0;
        for(int i=1;i<=n;i++) {

        }
        return true;
    }

    static boolean isRightCheck(int[] a) {
        for(int i=1;i<=n;i++) if(a[i]!=i) return false;
        return true;
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
