package boj.P20055;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,l;
    static int[][] arr = new int[101][101];
    static int answer = 0;

    public static void main(String[] args) throws Exception{
        input();
        solve();
        System.out.println(answer);
    }

    private static int solve(){
        for(int i=0;i<n;i++){
            if(checkIfPossible(i,true)) answer++;
            if(checkIfPossible(i,false)) answer++;
        }
        return answer;
    }

    private static boolean checkIfPossible(int k,boolean isRow){
        int[] temp = new int[n];
        boolean[] ch = new boolean[n];
        if(isRow) for(int i=0;i<n;i++) temp[i] = arr[k][i];
        else for(int i=0;i<n;i++) temp[i] = arr[i][k];

        for(int i=0;i<n-1;i++) {
            int dif = temp[i] - temp[i+1];
            if(Math.abs(dif) > 1) return false;
            if(dif == 1 && !rangeCheck(temp,ch,i+1,i+l)) return false;
            if(dif == -1 && !rangeCheck(temp,ch,i-l+1,i)) return false;
        }
        return true;

    }

    private static boolean rangeCheck(int[] temp,boolean[] ch,int s,int e){
        // 경사로 놓을 공간은 있는지
        if(s<0 || s>=n || e<0 || e>=n) return false;
        // 이미 경사로 놓여있는지
        for(int i=s;i<=e;i++){if(ch[i]) return false;}
        // 경사로놓을 곳의 높이는 서로 다 같은지
        for(int i=s;i<e;i++) if(temp[i] != temp[i+1]) return false;
        // 경사로 놓고 체크해두기
        for(int i=s;i<=e;i++) ch[i] = true;

        return true;
    }



    private static void input() throws Exception {
        n = fs.nextInt();
        l = fs.nextInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = fs.nextInt();
            }
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws Exception{
            return Integer.parseInt(next());
        }
    }
}
