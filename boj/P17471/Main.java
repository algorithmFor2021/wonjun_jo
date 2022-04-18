package boj.P17471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main 설명 : 게리맨더링
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/18
**/
public class Main {
    static Fs fs = new Fs();
    static int n;
    static int[] people = new int[11]; // 1~ 10
    static int answer = Integer.MAX_VALUE;
    static ArrayList<Integer>[] adj = new ArrayList[11];

    public static void main(String[] args) throws Exception{
        n = fs.nInt();

        for(int i=1;i<=n;i++) adj[i] = new ArrayList<>();

        for(int i=1;i<=n;i++) {
            people[i] = fs.nInt();
        }

        for(int i=1;i<=n;i++) {
            int adjCnt = fs.nInt();
            for(int j=0;j<adjCnt;j++) {
                adj[i].add(fs.nInt());
            }
        }

        // 1 부터 n 까지 k개 고르기 (k = 1~ n/2)
        dfs(0,0);

        System.out.println(answer==Integer.MAX_VALUE?-1:answer);

    }

    // idx = 1 ~ n
    static void dfs(int idx,int mask) {
        if(idx == n ) {
            if(Integer.bitCount(mask)<1 || Integer.bitCount(mask)>n/2) return;
            int gap = getPeopleGap(mask);
            if(gap >= 0) {
                answer = Math.min(answer,getPeopleGap(mask));
            }
            return;
        }

        mask += (1<<idx);
        dfs(idx+1,mask);
        mask -= (1<<idx);
        dfs(idx+1,mask);


    }

    static int getPeopleGap(int mask) {
        int[] numbering = new int[n+1];
        int idx = 1;
        for(int i=1;i<(1<<n);i<<=1) {
            if((i&mask) == i) {
                numbering[idx] = 1;
            }
            idx++;
        }

        boolean[] first = new boolean[n+1];
        int isFirst = 0;
        for(int i=1;i<=n;i++) {
            if(numbering[i]==1 && !first[i]) {
                isFirst++;
                first[i] = true;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while(!q.isEmpty()) {
                    int cur = q.poll();

                    for(int next : adj[cur]) {
                        if(numbering[next] == 1 && !first[next]) {
                            first[next] = true;
                            q.add(next);
                        }
                    }
                }
            }
        }

        if(isFirst != 1) return -1;


        int num = 1;
        for(int i=1;i<=n;i++) {
            if(numbering[i] == 0) {
                num++;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                numbering[i] = num;

                while(!q.isEmpty()) {
                    int cur = q.poll();

                    for(int next : adj[cur]) {
                        if(numbering[next] == 0) {
                            numbering[next] =  num;
                            q.add(next);
                        }
                    }

                }
            }
        }

        if(num != 2) return -1;

        int gap = 0;
        for(int i=1;i<=n;i++) {
            if(numbering[i] == 1) {
                gap += people[i];
            }
            else {
                gap -= people[i];
            }
        }
        return Math.abs(gap);

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
