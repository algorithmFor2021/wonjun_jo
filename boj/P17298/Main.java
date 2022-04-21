package boj.P17298;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Main 설명 : 오큰수 ..
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/19
**/
public class Main {
    static Fs  fs =  new Fs();
    static int[] arr = new int[1000001];
    static int[] answer = new int[1000001];
    public static void main(String[] args) throws Exception{
        int n = fs.nInt();
        Stack<Integer> indexStack = new Stack<>();


        for(int i=0;i<n;i++){
            arr[i] = fs.nInt();

            while(!indexStack.isEmpty() && arr[indexStack.peek()] < arr[i]) {
                // 현재 값이 스택의 탑보다 크다면 오큰수는 arr[i] 임
                int topIndex = indexStack.pop();
                answer[topIndex] = arr[i];

            }

            indexStack.add(i);

        }
        StringBuilder realAns = new StringBuilder();
        for(int i=0;i<n;i++) {
            if(answer[i] == 0) realAns.append(-1).append(" ");
            else realAns.append(answer[i]).append(" ");
        }

        realAns.append("\n");

        System.out.println(realAns);

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
