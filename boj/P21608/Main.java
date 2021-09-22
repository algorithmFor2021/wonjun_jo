package boj.P21608;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[][] arr;
    static int n;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static HashSet<Integer>[] f;

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        arr = new int[n][n];
        f = new HashSet[n*n+1];
        for(int i=0;i<n*n+1;i++) f[i] = new HashSet<>();


        for(int i=0;i<n*n;i++){
            int t = fs.nInt();
            for(int j=0;j<4;j++) f[t].add(fs.nInt());
            getLoc(t,f[t]);
        }

        System.out.println(getAns());

    }

    static int getAns(){
        int ans = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int ret = 0;
                for(int k=0;k<4;k++){
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;

                    if(f[arr[i][j]].contains(arr[ni][nj])) ret++;
                }
                if(ret != 0){
                    ans += (int)Math.pow(10,ret-1);
                }
            }
        }

        return ans;
    }

    static void getLoc(int t,HashSet<Integer> f){
        int[][] temp = new int[n][n];
        int[][] temp2 = new int[n][n];

        int maxT1 = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==0) {
                    int t1 = 0;
                    int t2 = 0;
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;

                        if (f.contains(arr[ni][nj])) t1++;
                        if (arr[ni][nj] == 0) t2++;
                    }
                    maxT1 = Math.max(t1, maxT1);
                    temp[i][j] = t1;
                    temp2[i][j] = t2;
                }
            }
        }

        int[] ret = {-1,-1};
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==0 && temp[i][j]==maxT1){
                    if(ret[0] == -1){
                        ret[0] = i;
                        ret[1] = j;
                        continue;
                    }
                    if(temp2[i][j] > temp2[ret[0]][ret[1]]){
                        ret[0] = i;
                        ret[1] = j;
                    }

                }
            }
        }

        arr[ret[0]][ret[1]] = t;


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
