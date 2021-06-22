package boj.P1062;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int n,k;
    static ArrayList<Integer> list = new ArrayList<>();
    static int ans = 0;
    public static void main(String[] args) throws Exception{
        n = fs.nInt(); k = fs.nInt();
        for(int i=0;i<n;i++) {
            int word = getWord(fs.next());
            list.add(word);
        }
        //  b d e f g h j k l m  o  p  q  r  s  u  v  w  x  y  z
        //  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
        if(k<5) System.out.println(ans);
        else{
            k -= 5;
            // 21개 선택지 중에 k개를 선택할거임
            for(int mask=0;mask<1<<21;mask++){
                if(Integer.bitCount(mask) == k){
                    ans = Math.max(ans,check(mask));
                }
            }
            System.out.println(ans);
        }
    }
    static int check(int mask){
        int ret = 0;
        for(int l : list ) {
            if((l & mask) == l) ret++;
        }
        return ret;
    }
    static int getWord(String s){
        int ret = 0;
        for(char c: s.toCharArray()){
            if(c == 'a' || c=='n' || c=='t' || c=='i' || c=='c') continue;
            if(c <= 'c') ret |= 1<<(c - 'a'-1);
            else if(c <= 'i') ret |= 1<<(c-'a'-2);
            else if(c <= 'n') ret |= 1<<(c-'a'-3);
            else if(c <= 't') ret |= 1<<(c-'a'-4);
            else ret |= 1<<(c-'a'-5);
        }
        return ret;
    }
    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
