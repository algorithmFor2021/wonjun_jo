package boj.P1697;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,k;
    static int[] times = new int[100001];

    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        k = fs.nInt();

        for(int i=0;i<=100000;i++) times[i] = Integer.MAX_VALUE;
        times[n] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == k){
                break;
            }

            int left = cur -1;
            int right = cur + 1;
            int right2 = cur*2;

            int nextCnt = times[cur] + 1;

            if(check(left,nextCnt)){
                times[left] = nextCnt;
                q.add(left);
            }
            if(check(right,nextCnt)){
                times[right] = nextCnt;
                q.add(right);
            }
            if(check(right2,nextCnt)){
                times[right2] = nextCnt;
                q.add(right2);
            }

        }

        System.out.println(times[k]);


    }


    static boolean check(int next,int nextCnt){
        if(next<0 || next>100000 || times[next] <= nextCnt) return false;
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
