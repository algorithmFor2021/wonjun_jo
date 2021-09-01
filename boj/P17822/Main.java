package boj.P17822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int[][] arr = new int[51][51];
    static int n,m,testCase;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        m = fs.nInt();
        testCase = fs.nInt();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = fs.nInt();
            }
        }

        int x,d,k;
        for(int t =0;t<testCase;t++){
            x = fs.nInt();
            d = fs.nInt();
            k = fs.nInt();
            rotate(x,d,k);
            if(isRemainNumber()){
                if(!checking()){
                    averageCal();
                }
            }
        }

        System.out.println(getSum()[0]);

    }

    static void rotate(int x,int d,int k){
        // x배수 d방향 k만큼
        // 시계방향 = 오른쪽
        for(int i=x;i<=n;i+=x){
            int row = i-1;
            int[] temp = new int[m];

            if(d == 0) for(int j=0;j<m;j++) temp[j] = arr[row][getNum(j-k)];
            else for(int j=0;j<m;j++) temp[j] = arr[row][getNum(j+k)];

            for(int j=0;j<m;j++) arr[row][j] = temp[j];

        }

    }

    static boolean checking(){
        boolean flag = false;
        boolean[][] check = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

                for(int l=0;l<4;l++){
                    int ni = i + dx[l];
                    int nj = getNum(j + dy[l]);

                    if(ni>=0 && ni<n ){
                        if(arr[i][j]!=-1 && arr[i][j] == arr[ni][nj]){
                            flag = true;
                            check[i][j] = check[ni][nj] = true;
                        }
                    }
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(check[i][j]){
                    arr[i][j] = -1;
                }
            }
        }
        return flag;
    }

    static boolean isRemainNumber(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]>0) return true;
            }
        }
        return false;
    }
    static void averageCal(){
        int[] ret = getSum();
        double avg = (double)ret[0] / ret[1];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j] > 0 ) {
                    if ((double) arr[i][j] > avg) arr[i][j]--;
                    else if(arr[i][j] < avg) arr[i][j]++;
                }
            }
        }

    }

    static int[] getSum(){
        int sum = 0;
        int cnt = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j] > 0) {
                    cnt++;
                    sum += arr[i][j];
                }

            }
        }
        return new int[]{sum,cnt};
    }

    static int getNum(int x){
        if(x<0) return (m+x);
        else return x%m;
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
