package boj.P9012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception {
        int tc = fs.nInt();
        StringBuilder sb = new StringBuilder();

        while(tc-->0) {
            char[] s = fs.next().toCharArray();

            Stack<Character> stk = new Stack<>();
            boolean flag  = true;

            for(char c : s) {
                if(c == '(') stk.add('(');
                else {
                    if(stk.isEmpty()) {
                        flag = false;
                        break;
                    }
                    else {
                        if(stk.peek() == '(') {
                            stk.pop();
                        }else {
                            flag = false;
                            break;
                        }
                    }
                }
            }

            if(!stk.isEmpty()) flag = false;

            sb.append(flag?"YES":"NO").append("\n");

        }

        System.out.println(sb);
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }

        public String next() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
