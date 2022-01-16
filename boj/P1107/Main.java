package boj.P1107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static boolean[] notUseNum = new boolean[10];

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        int k = fs.nInt();
        for(int i=0;i<k;i++){
            notUseNum[fs.nInt()] = true;
        }
        int cur = 100;

        if(n == 100) {
            System.out.println(0);
            return;
        }

        for(int i=0;i<999999;i++){
            if(check(i)){
                if(Math.abs(n-i) < Math.abs(n-cur)){
                    cur = i;
                }
            }
        }
        int gap = Math.abs(n - cur);
        int len = cur==100?0:String.valueOf(cur).length();

        int ans = gap + len;
        ans = Math.min(ans,Math.abs(n-100));

        System.out.println(ans);


    }

    static boolean check(int num){
        do{
            if(notUseNum[num % 10]) return false;
            num = num/10;
        }while(num > 0);
        return true;
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
