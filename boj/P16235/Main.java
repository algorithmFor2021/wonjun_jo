package boj.P16235;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    static Fs fs = new Fs();
    static int n,m,k;
    static int[][] A = new int[11][11];
    static int[][] food = new int[11][11];
    static int[] dx = {-1,-1,1,1,0,0,1,-1};
    static int[] dy = {-1,1,-1,1,1,-1,0,0};
    static ArrayList<Integer>[][] trees = new ArrayList[11][11];

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        m = fs.nInt();
        k = fs.nInt();

        arrayAInit();
        treesInit();
        while(k-- > 0) go();

        System.out.println(getAnswer());


    }

    private static void treesInit() throws Exception {
        int x,y,age;
        for(int i=0;i<m;i++){
            x = fs.nInt()-1;
            y = fs.nInt()-1;
            age = fs.nInt();
            trees[x][y].add(age);
        }
    }

    private static void arrayAInit() throws Exception {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                A[i][j] = fs.nInt();
                food[i][j] = 5; // 초기 모든 땅에는 양분이 5만큼 들어있음
                trees[i][j] = new ArrayList<>();
            }
        }
    }

    static void go(){
        springAndSummerAndWinter();
        fall();
    }

    static void springAndSummerAndWinter(){
        int[][] dead = new int[11][11];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){

                Collections.sort(trees[i][j]);
                ArrayList<Integer> temp = new ArrayList<>();
                for(int age : trees[i][j]){
                    if( age <= food[i][j]) {
                        temp.add(age+1);
                        food[i][j] -= age;
                    }
                    else dead[i][j] += age/2;
                }
                trees[i][j] = temp;

            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                food[i][j] += (dead[i][j] + A[i][j]);
            }
        }
    }

    static void fall(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int age : trees[i][j]){
                    if(age%5 == 0){
                        for(int k=0;k<8;k++){
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if(checkRange(nx,ny)){
                                trees[nx][ny].add(1);
                            }
                        }
                    }
                }
            }
        }
    }

    static int getAnswer(){
        int ret = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++){
                ret += trees[i][j].size();
            }
        }
        return ret;
    }

    static boolean checkRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<n;
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
