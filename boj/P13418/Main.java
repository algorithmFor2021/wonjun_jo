package boj.P13418;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n,m;
    static int[] parent1 = new int[1001];
    static int[] parent2 = new int[1001];


    public static void main(String[] args) throws Exception{

        n = fs.nInt();
        m = fs.nInt();
        ArrayList<Edge> maxList = new ArrayList<>();
        ArrayList<Edge> minList = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            parent1[i] = i;
            parent2[i] = i;
        }

        parent1[0] = 1;
        parent2[0] = 1;
        int ret1 = 0;
        int ret2 = 0;
        int a,b,d;
        for(int i=0;i<=m;i++){
            a = fs.nInt();
            b = fs.nInt();
            d = fs.nInt();

            if((a==0 && b==1) || (a==1 && b==0)){
                ret1 += 1 - d;
                ret2 += 1 - d;
                continue;
            }

            maxList.add(new Edge(a,b,d));
            minList.add(new Edge(a,b,d));
        }

        Collections.sort(maxList,(o1,o2)->o1.d - o2.d);
        Collections.sort(minList,(o1,o2)->o2.d - o1.d);


        int nCnt = 0;
        for(Edge e : maxList){
            int p1 = find(e.a,true);
            int p2 = find(e.b,true);


            if(p1 != p2){
                parent1[p1] = p2;
                ret1 += 1-e.d;
                nCnt++;
                if(nCnt == n)break;
            }
        }

        nCnt = 0;
        for(Edge e : minList){
            int p1 = find(e.a,false);
            int p2 = find(e.b,false);

            if (p1 != p2) {
                parent2[p1] = p2;
                ret2 += 1 - e.d;
                nCnt++;
                if(nCnt == n)break;
            }
        }

        System.out.println(ret1*ret1 - ret2*ret2);

    }

    static int find(int x,boolean isP1){
        if(isP1){
            if(x==parent1[x]) return x;
            else return parent1[x] = find(parent1[x],isP1);
        }
        else{
            if(x==parent2[x]) return x;
            else return parent2[x] = find(parent2[x],isP1);
        }
    }


    static class Edge {
        int a,b,d;

        public Edge(int a, int b, int d) {
            this.a = a;
            this.b = b;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "a=" + a +
                    ", b=" + b +
                    ", d=" + d +
                    '}';
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st=  new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
