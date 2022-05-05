package programers.kakao2020.수식최대화;


import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * Solution 설명 :
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/05/05
**/
public class Solution {
    char[][] operator = {
            {'-','+','*'},
            {'+','-','*'},
            {'*','+','-'},
            {'+','*','-'},
            {'-','*','+'},
            {'*','-','+'},
    };

    public long solution(String expression) {
        long answer = Long.MIN_VALUE;
        Calculator calculator = new Calculator(expression);
        for(char[] op : operator) {
            long ans = calculator.calculate(op);
            answer = Math.max(ans,answer);
        }
        return answer;
    }


    static class Calculator {

        Stack<String> stk;

        // 처음 ex 에는 음수가 없음
        Calculator(String ex) {
            StringTokenizer st = new StringTokenizer(ex,"-+*",true);
            this.stk = new Stack<>();
            while(st.hasMoreTokens()) this.stk.add(st.nextToken());
        }

        long calculate(char[] op) {
            Stack<String> ret = new Stack<>();
            Iterator<String> it = this.stk.iterator();

            for(char o : op) {
                Stack<String> tmp = new Stack<>();
                while(it.hasNext()) {
                    String next = it.next();
                    if(!tmp.isEmpty() && !isOperator(next) && tmp.peek().equals(String.valueOf(o))) {
                        tmp.pop();
                        String a = tmp.pop();
                        String b = next;
                        next = cal(a , b , o);
                    }
                    tmp.add(next);
                }
                ret = (Stack<String>) tmp.clone();
                it = ret.iterator();
            }

            return Math.abs(Long.parseLong(ret.peek()));
        }

        boolean isOperator(String c) {
            return c.equals("-") || c.equals("+") || c.equals("*");
        }

        String cal(String a,String b,char op) {
            if(op == '-') {
                return String.valueOf(Long.parseLong(a) - Long.parseLong(b));
            }
            else if(op == '+') {
                return String.valueOf(Long.parseLong(a) + Long.parseLong(b));
            }
            else {
                return String.valueOf(Long.parseLong(a) * Long.parseLong(b));
            }
        }

    }

    public static void main(String[] args) throws Exception{
        System.out.println(new Solution().solution("100-200*300-500+20"));
    }


}
