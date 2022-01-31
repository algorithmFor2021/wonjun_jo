package boj.P2666;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main 설명 : 벽장문의 이동
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/01/31
**/

public class Main {
    static Fs fs = new Fs();
    static int n;
    static boolean[] door = new boolean[21];
    static int[] order;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        input();
        dfs(0,0,door);
        System.out.println(answer);
    }

    static void dfs(int idx,int sum,boolean[] door){
        if(idx == order.length){
            answer = Math.min(answer,sum);
            return;
        }
        int cur = order[idx];

        // cur open
        if(door[cur]) {
            dfs(idx + 1,sum,door);
        }
        else {
            int lmove = 0;
            int rmove = 0;
            boolean[] left = new boolean[n+1];
            boolean[] right = new boolean[n+1];
            for (int i = 1; i <= n; i++) {
                left[i] = door[i];
                right[i] = door[i];
            }
            for(int i=cur-1;i>=1;i--){
                if(door[i]){
                    left[i] = false;
                    left[cur] = true;
                    lmove = cur - i;
                    break;
                }
            }
            for(int i=cur+1;i<=n;i++){
                if(door[i]){
                    right[i] = false;
                    right[cur] = true;
                    rmove = i - cur;
                    break;
                }
            }
            if(lmove>0) dfs(idx+1,sum+lmove,left);
            if(rmove>0) dfs(idx+1,sum+rmove,right);
        }
    }

    static void input() throws Exception{
        n = fs.nInt();
        for(int i=0;i<2;i++) door[fs.nInt()] = true;
        int t = fs.nInt();
        order = new int[t];
        for(int i=0;i<t;i++){
            order[i]=fs.nInt();
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
