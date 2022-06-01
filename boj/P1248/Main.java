package boj.P1248;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static ArrayList<Integer> list = new ArrayList<>();
    static char[][] s = new char[11][11]; // 1 ~ 10

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        String str = fs.next();
        int k = 0;
        for(int i=1;i<=n;i++) {
            for(int j=i;j<=n;j++) {
                s[i][j] = str.charAt(k++);
            }
        }

        solve(0);

    }

    static void solve(int idx ) {
        if(idx == n ) {
            for(int l : list) {
                System.out.print(l+" ");
            }
            System.exit(0);
        }

        for(int i=-10;i<=10;i++) {
            if(check(i)) {
                list.add(i);
                solve(idx + 1);
                list.remove(list.size()-1);
            }
        }
    }

    // list 에 num 을 추가해도 되는지 체크
    static boolean check(int num) {

        int sum = 0;
        for(int i=list.size()+1;i>=1;i--) {
            if(i == list.size()+1) sum += num;
            else sum += list.get(i-1);

            if(s[i][list.size()+1] == '+' && sum <=0) return false;
            if(s[i][list.size()+1] == '-' && sum >=0) return false;
            if(s[i][list.size()+1] == '0' && sum !=0) return false;
        }

        return true;
    }


    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }
    }
}
