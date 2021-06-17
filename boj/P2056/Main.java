package boj.P2056;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static Fs fs =new Fs();
    static int n;
    static int curTime = 0; // max = 100 * 10000
    static ArrayList<Integer>[] arr = new ArrayList[10001];
    static int[] degree = new int[10001];
    static int[] times =  new int[10001];

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        for(int i=0;i<=n;i++) arr[i] = new ArrayList<>();
        for(int i=1;i<=n;i++){
            times[i] = fs.nInt();
            int prevNum = fs.nInt();
            degree[i] += prevNum;
            for(int j=0;j<prevNum;j++){
                int prev  = fs.nInt();
                arr[prev].add(i);
            }
        }

        while(curTime < 210000000){
            int cnt = 0;
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int i=1;i<=n;i++) {
                if(times[i]==0) cnt++;
                else if(degree[i] == 0 && times[i]!=0) {
                    times[i]--;
                    if(times[i] == 0) tmp.add(i);
                }
            }
            for(int t : tmp){
                for(int a : arr[t]){
                    degree[a]--;
                }
            }
            if(cnt==n) break;
            curTime++;
        }

        System.out.println(curTime);
    }

    static class Fs {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
