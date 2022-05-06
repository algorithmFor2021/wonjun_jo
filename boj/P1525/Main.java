package boj.P1525;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static Fs fs = new Fs();
    static int[][] arr = new int[3][3];
    static HashSet<String> visit = new HashSet<>();
    static String cmp = "123456780";
    static long answer = Long.MAX_VALUE;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                arr[i][j] = fs.nInt();
            }
        }

        dfs(0);

        System.out.println(visit.size());

        List<String> collect = visit.stream().collect(Collectors.toList());
        Collections.sort(collect);

        for(String s : collect) {
            System.out.println(s);
        }
    }

    static void dfs(int move) {

        String s = toStringHashCode();
        if(visit.contains(s)) {
            System.out.println(s);
            return;
        }

        visit.add(s);
        if(s.equals(cmp)) {
            answer = Math.min(move,answer);
            return;
        }

        if(move > 100) return;

        int[] pos = findBlank();

        for(int i=0;i<4;i++) {
            int nx = pos[0] + dx[i];
            int ny = pos[1] + dy[i];

            if(checkRange(nx,ny)) {
                swap(nx,ny,pos[0],pos[1]);
                dfs(move + 1);
                swap(nx,ny,pos[0],pos[1]);
            }
        }

    }

    static boolean checkRange(int x,int y) {
        return x>=0 && x<3 && y>=0 && y<3;
    }

    static void swap(int x,int y,int nx,int ny) {
        int temp = arr[nx][ny];
        arr[nx][ny] = arr[x][y];
        arr[x][y] = temp;
    }

    static int[] findBlank() {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(arr[i][j] == 0) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    static String toStringHashCode() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                sb.append(arr[i][j]);
            }
        }
        return sb.toString();
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
         }
    }
}
