package boj.P2498;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Main 설명 : 탑
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/10
**/
public class Main {
    static Fs fs = new Fs();
    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        Stack<Node> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();
        sb.append(0).append(" ");
        stk.add(new Node(fs.nInt(),1));

        for(int i=2;i<=n;i++) {
            int nextHeight = fs.nInt();

            while(!stk.isEmpty() && stk.peek().h < nextHeight) {
                stk.pop();
            }

            if(stk.isEmpty()) {
                sb.append(0);
            }else {
                sb.append(stk.peek().idx);
            }
            sb.append(" ");

            stk.add(new Node(nextHeight,i));

        }

        System.out.println(sb);
    }

    static class Node{
        int h;
        int idx;

        public Node(int h, int idx) {
            this.h = h;
            this.idx = idx;
        }
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
