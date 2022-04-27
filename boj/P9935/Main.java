package boj.P9935;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Main 설명 : 문자열 폭발
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/28
**/
public class Main {
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception{
        char[] str = fs.next().toCharArray();
        char[] mask = fs.next().toCharArray();

        char[] rMask = new char[mask.length];
        for(int i=mask.length-1;i>=0;i--) {
            rMask[mask.length-1-i] = mask[i];
        }

        Stack<Character> stk = new Stack<>();
        Stack<Character> temp;
        for(char s : str) {
            stk.add(s);

            temp = new Stack<>();
            boolean flag = false;
            for(char r : rMask) {
                if(r == stk.peek()) {
                    temp.add(stk.pop());
                }else {
                    flag = true;
                    break;
                }
            }

            if(flag) {
                while(!temp.isEmpty()) stk.add(temp.pop());
            }

        }

        Iterator<Character> it = stk.iterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()) {
            sb.append(it.next());
        }

        System.out.println(sb.length()==0?"FRULA":sb);

    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

    }
}
