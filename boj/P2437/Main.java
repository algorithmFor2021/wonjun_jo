package boj.P2437;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int n;
    static int ans = 0;
    static ArrayList<Integer> list = new ArrayList<>(1001);
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        for(int i=0;i<n;i++) list.add(fs.nInt());
        Collections.sort(list);
        for(int l : list){
            if(ans + 1 >= l) ans += l;
            else break;
        }
        System.out.println(ans + 1);
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
