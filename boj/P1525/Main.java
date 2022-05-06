package boj.P1525;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static String str = "";
    static HashMap<String,Integer> visit = new HashMap<>();
    static String cmp = "123456780";
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                str += String.valueOf(fs.nInt());
            }
        }

        dfs(0,str);


        System.out.println(visit.get(cmp));
    }

    static void dfs(int move , String cur) {

        int pos = cur.indexOf('0');
        int x = pos / 3;
        int y = pos % 3;

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >=0 && ny >=0 && nx <3 && ny < 3) {
                int n = nx*3 + ny;
                char ch = cur.charAt(n);
                String next = cur;
                next = next.replace('0','9');
                next = next.replace(ch,'0');
                next = next.replace('9',ch);

                if(!visit.containsKey(next)) {
                    visit.put(next,move+1);
                    dfs(move + 1, next);
                }
            }

        }

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
