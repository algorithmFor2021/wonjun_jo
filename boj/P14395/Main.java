package boj.P14395;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static HashSet<Integer> set = new HashSet<>();
    static int s,t;
    static int MUL_MAX_NUMBER = 31700;
    static int ADD_MAX_NUMBER = 1000000000;

    public static void main(String[] args) throws Exception {
        s = fs.nInt();
        t = fs.nInt();

        if(s == t) {
            System.out.println(0);
            return;
        }


        set.add(s);
        Point start = new Point(s,"");
        Queue<Point> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()){
            Point p = q.poll();

            if(p.num == t){
                System.out.println(p.str);
                return;
            }

            if(p.num <= MUL_MAX_NUMBER){
                int next = p.num * p.num;
                if(!set.contains(next)) {
                    set.add(next);
                    q.add(new Point(next, p.str + "*"));
                }
            }
            if(p.num <= ADD_MAX_NUMBER){
                int next = p.num * 2;
                if(!set.contains(next)) {
                    set.add(next);
                    q.add(new Point(next, p.str + "+"));
                }
            }
            if(!set.contains(1)) {
                set.add(1);
                q.add(new Point(1,p.str+"/"));
            }

        }

        System.out.println(-1);


    }

    static class Point{
        int num;
        String str;

        public Point(int num,String str) {
            this.num = num;
            this.str = str;
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
