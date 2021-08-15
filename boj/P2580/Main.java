package boj.P2580;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[][] sdoku = new int[9][9];
    static int[][] answer = new int[9][9];

    public static void main(String[] args) throws Exception{
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                sdoku[i][j] = fs.nInt();
            }
        }

        dfs(0);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<9;i++){
            sb.append(answer[i][0]);
            for(int j=1;j<9;j++){
                sb.append(" ").append(answer[i][j]);
            }
            sb.append('\n');
        }

        System.out.print(sb);


    }
    // idx = 0 ~ 80
    static void dfs(int idx){
        if(idx == 81) {
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    answer[i][j] = sdoku[i][j];
                }
            }
            return;
        }
        int x = idx / 9;
        int y = idx % 9;
        if(sdoku[x][y] > 0) dfs(idx+1);
        else{
            for(int i=1;i<=9;i++){
                if(answer[0][0] == 0 && check(x,y,i)){
                    sdoku[x][y] = i;
                    dfs(idx + 1);
                    sdoku[x][y] = 0;
                }
            }
        }
    }

    static boolean check(int x,int y,int val){
        // row and col check
        for(int i=0;i<9;i++){
            if(sdoku[x][i] == val) return false;
            if(sdoku[i][y] == val) return false;
        }

        // box check
        int r = x/3*3;
        int c = y/3*3;

        for(int i=r;i<r+3;i++){
            for(int j=c;j<c+3;j++){
                if(sdoku[i][j] == val) return false;
            }
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
    }
}
