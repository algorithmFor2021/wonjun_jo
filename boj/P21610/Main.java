package boj.P21610;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,cmd;
    static int[][] arr = new int[51][51];
    static boolean[][] cloud;
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,-1,0,1,1,1,0,-1};



    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        cmd = fs.nInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = fs.nInt();
            }
        }

        int d,s;

        firstCloudInit();

        while(cmd-- > 0){
            d = fs.nInt();
            s = fs.nInt();

            cloudMove(d,s);
            rainning();
            waterCopy();
            makeCloud();

        }

        System.out.println(getSum());
    }

    static int getSum(){
        int ret = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                ret += arr[i][j];
            }
        }
        return ret;
    }

    static void cloudMove(int d,int s){
        boolean[][] nextCloud = new boolean[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int ni = i + dx[d-1]*(s%n);
                int nj = j + dy[d-1]*(s%n);


                ni = getNextLocation(ni);
                nj = getNextLocation(nj);

                nextCloud[ni][nj] = cloud[i][j];

            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                cloud[i][j] = nextCloud[i][j];
            }
        }
    }
    static void rainning(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(cloud[i][j]){
                    arr[i][j]++;
                }
            }
        }
    }

    static void waterCopy(){
        int[][] temp = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(cloud[i][j]) {
                    int chk = 0;
                    for (int k = 1; k <= 7; k += 2) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;

                        if (arr[ni][nj] > 0) chk++;

                    }
                    temp[i][j] += chk;
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] += temp[i][j];
            }
        }
    }
    static void makeCloud(){
        boolean[][] nextCloud = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]>=2 && !cloud[i][j]){
                    nextCloud[i][j] = true;
                    arr[i][j] -= 2;
                }
            }
        }
        cloud = nextCloud;
    }

    static int getNextLocation(int x){
        if(x < 0) return n - ( -x % n) ;
        return x%n;
    }


    static void firstCloudInit(){
        cloud = new boolean[n][n];
        for(int i=n-2;i<n;i++){
            for(int j=0;j<2;j++){
                cloud[i][j] = true;
            }
        }
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
