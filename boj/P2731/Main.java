package boj.P2731;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static String ans = "";
    static String s = "";
    static String[] piece;
    public static void main(String[] args)  throws Exception{
        int t = fs.nInt();
        while(t-- > 0) {
            s = fs.next();
            piece = new String[s.length()];
            for(int i=1;i<=s.length();i++) {
                piece[i-1] = s.substring(s.length()-i);
            }
            dfs(new StringBuilder());
            System.out.println(Long.parseLong(ans));
        }

    }

    static void dfs(StringBuilder sb) {
        int len = sb.length();
        if(len == s.length()) {
            ans = sb.toString();
            return;
        }

        String ret = piece[len];

        for(int i=0;i<10;i++) {
            String nextNum = String.valueOf((char) (i + '0')) + sb;
            BigDecimal bigNum  = new BigDecimal(nextNum);
            BigDecimal mulResult = bigNum.multiply(bigNum).multiply(bigNum);
            StringBuilder mod = new StringBuilder(mulResult.toString());
            while(mod.toString().length() < ret.length()) mod.insert(0, "0");
            String cmp = mod.substring(mod.length() - (len + 1));
            if(cmp.equals(ret)) {
                dfs(new StringBuilder(nextNum));
            }
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }

        public String next() throws Exception{
            while(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
