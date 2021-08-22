package boj.P1028;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int r,c;
    static int[][] arr = new int[750][750];
    public static void main(String[] args) throws Exception{
        r = fs.nInt();
        c = fs.nInt();

        for(int i=0;i<r;i++){
            char[] temp = fs.next().toCharArray();
            for(int j=0;j<c;j++){
                arr[i][j] = temp[j]-'0';
            }
        }

        int low = 0;
        int high = (Math.min(r,c)-1)/2;

        for(int i= high;i>=low;i--){
            if(check(i)){
                System.out.println(i+1);
                return;
            }
        }
        System.out.println(0);
    }

    static boolean check(int k){
        for(int i=k;i<r-k;i++){
            for(int j=k;j<c-k;j++){
                if(diamondCheck(i,j,k)){
                    return true;
                }
            }
        }
        return false;
    }
    static boolean diamondCheck(int i,int j,int k){
        for(int x=0;x<=k;x++){
            if(arr[i-x][j-k+x]==0
                    || arr[i+x][j-k+x]==0
                    || arr[i+k-x][j+x]==0
                    || arr[i-k+x][j+x]==0) return false;
        }

        return true;
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
