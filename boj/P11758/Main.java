package boj.P11758;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main 설명 : CCW
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/12
**/
public class Main {
    static Fs fs = new Fs();
    public static void main(String[] args) throws Exception{
        System.out.println(ccw(fs.nInt(),fs.nInt(),fs.nInt(),fs.nInt(),fs.nInt(),fs.nInt()));
    }

    static int ccw(int x1,int y1,int x2,int y2,int x3,int y3) {
        return Integer.compare(x1*y2+x2*y3+x3*y1 - y1*x2-y2*x3-y3*x1, 0);
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
