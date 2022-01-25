package boj.P15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Main 설명 : 치킨배달
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/01/26
**/
public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int answer = Integer.MAX_VALUE;
    static ArrayList<int[]> house = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        input();
        dfs(0,0,new boolean[chicken.size()]);
        System.out.println(answer);
    }

    // chicken 에서 m개 선택하기
    static void dfs(int idx,int cnt,boolean[] ch){
        if(cnt == m){
            answer = Math.min(cal(ch),answer);
            return;
        }
        if(idx == chicken.size()) return;

        ch[idx] = true;
        dfs(idx+1,cnt+1,ch);
        ch[idx] = false;
        dfs(idx+1,cnt,ch);
    }

    static int cal(boolean[] ch){
        int ret = 0;
        for (int[] h : house) {
            int r = 9999;
            for(int j=0;j<chicken.size();j++){
                if(ch[j]){
                    int dist = Math.abs(chicken.get(j)[0] - h[0])
                            + Math.abs(chicken.get(j)[1] - h[1]);
                    r = Math.min(r, dist);
                }
            }
            ret += r;
        }
        return ret;
    }

    static void input() throws Exception{
        n = fs.nInt();
        m = fs.nInt();

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                int x = fs.nInt();
                if(x == 1) house.add(new int[]{i,j});
                if(x == 2) chicken.add(new int[]{i,j});
            }
        }
    }


    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }

}
