package boj.P12094;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[][] strtArr;
    static int tmpMax;
    static int answer;

    public static void main(String[] args) throws Exception{
        input();
        dfs(0,strtArr);
        System.out.println(answer);

    }

    static void dfs(int idx,int[][] arr){
        if(idx  == 10) {
            setMaxLastMember(arr);
            return;
        }

        for(Direction dir : Direction.values()){
            tmpMax = 0;
            int[][] next = move(arr, dir);
            answer = Math.max(answer,tmpMax);
            if(isPossible(arr,next,idx)){
                dfs(idx+1,next);
            }
        }

    }
    static void setMaxLastMember(int[][] arr){
        int max = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                max = Math.max(arr[i][j],max);
            }
        }
        answer = Math.max(answer,max);
    }

    static boolean isPossible(int[][] a,int[][] b,int idx){
        int remain = 10 - idx;
        if(tmpMax * (int)Math.pow(2,remain) <= answer) return false;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(a[i][j] != b[i][j]) return true;
            }
        }
        return false;
    }


    static int[][] move(int[][] arr,Direction dir){
        int[][] ret = new int[n][n];
        switch (dir){
            case UP:
                for(int c=0;c<n;c++) {
                    int k = 0;
                    for (int r = 0; r < n; r++) {
                        if (arr[r][c] != 0) {
                            if (ret[k][c] == arr[r][c]) ret[k++][c] = arr[r][c] * 2;
                            else {
                                if (ret[k][c] == 0) ret[k][c] = arr[r][c];
                                else ret[++k][c] = arr[r][c];
                            }
                        }
                    }
                    for (int r = 0; r < n; r++) {
                        if (ret[r][c] != 0) {
                            tmpMax = Math.max(ret[r][c], tmpMax);
                        }
                    }
                }
                break;
            case DOWN:
                for(int c=0;c<n;c++) {
                    int k = n - 1;
                    for (int r = n - 1; r >= 0; r--) {
                        if (arr[r][c] != 0) {
                            if (ret[k][c] == arr[r][c]) ret[k--][c] = arr[r][c] * 2;
                            else {
                                if (ret[k][c] == 0) ret[k][c] = arr[r][c];
                                else ret[--k][c] = arr[r][c];
                            }
                        }
                    }
                    for (int r = 0; r < n; r++) {
                        if (ret[r][c] != 0) {
                            tmpMax = Math.max(ret[r][c], tmpMax);
                        }
                    }
                }
                break;
            case LEFT:
                for(int r=0;r<n;r++) {
                    int k = 0;
                    for (int c = 0; c < n; c++) {
                        if (arr[r][c] != 0) {
                            if (ret[r][k] == arr[r][c]) ret[r][k++] = arr[r][c] * 2;
                            else {
                                if (ret[r][k] == 0) ret[r][k] = arr[r][c];
                                else ret[r][++k] = arr[r][c];
                            }
                        }
                    }
                    for (int c = 0; c < n; c++) {
                        if (ret[r][c] != 0) {
                            tmpMax = Math.max(ret[r][c], tmpMax);
                        }
                    }
                }
                break;
            case RIGHT:
                for(int r=0;r<n;r++) {
                    int k = n - 1;
                    for (int c = n - 1; c >= 0; c--) {
                        if (arr[r][c] != 0) {
                            if (ret[r][k] == arr[r][c]) ret[r][k--] = arr[r][c] * 2;
                            else {
                                if (ret[r][k] == 0) ret[r][k] = arr[r][c];
                                else ret[r][--k] = arr[r][c];
                            }
                        }
                    }
                    for (int c = 0; c < n; c++) {
                        if (ret[r][c] != 0) {
                            tmpMax = Math.max(ret[r][c], tmpMax);
                        }
                    }
                }
                break;
        }


        return ret;
    }

    static void printArray(int[][] arr){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


    static void input() throws Exception{
        n = fs.nInt();
        strtArr = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                strtArr[i][j] = fs.nInt();
            }
        }
    }

    public enum Direction{
        UP,DOWN,LEFT,RIGHT
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
