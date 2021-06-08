package boj.P1034;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m,k;
    static int[] zeroCnt = new int[51];
    static long[] nums = new long[51];
    static int ans = 0;
    public static void main(String[] args) throws Exception{

        n = fs.nInt();
        m = fs.nInt();
        for(int i=0;i<n;i++){
            char[] cArr = fs.next().toCharArray();
            long tmp = 1;
            for(int j=m-1;j>=0;j--){
                if(cArr[j]=='0') zeroCnt[i]++;
                else nums[i] += tmp;
                tmp <<= 1;
            }
        }
        k = fs.nInt();
        for(int i=0;i<n;i++){
            int cnt = 0;
            for(int j=0;j<n;j++)
                if(check(i) && nums[j] == nums[i])
                    cnt++;

            ans = Math.max(cnt,ans);
        }

        System.out.println(ans);

    }
    public static boolean check(int i){
        if(zeroCnt[i]<=k && k%2==0 && zeroCnt[i]%2==0) return true;
        else if(zeroCnt[i]<=k && k%2==1 && zeroCnt[i]%2==1) return true;
        else return false;
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception {
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
