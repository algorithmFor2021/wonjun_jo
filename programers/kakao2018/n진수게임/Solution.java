package programers.kakao2018.n진수게임;

import java.util.*;

class Solution {
    static HashMap<Integer,String> parseMap = new HashMap<>();
    static Queue<String> q = new LinkedList<>();

    public static void initParse(){
        for(int i=0;i<10;i++) parseMap.put(i,String.valueOf(i));
        for(int i=10;i<16;i++) parseMap.put(i,String.valueOf((char)(55+i)));
    }

    public String solution(int n, int t, int m, int p) {
        int curTurn = 0;
        int curNum = 1;
        q.add("0");
        initParse();
        String answer = "";
        while(answer.length()<t){
            if(q.isEmpty()) make(curNum++,n);


            String next = q.poll();

            // myturn
            if(curTurn%m + 1 == p){
                answer += next;
            }

            curTurn++;
        }
        return answer;
    }

    public static void make(int num,int n){
        int t1,t2;
        StringBuilder s = new StringBuilder();
        while(num != 0){
            t1 = num / n ;
            t2 = num % n ;
            s.insert(0, parseMap.get(t2));
            num = t1;
        }
        for(int i=0;i<s.length();i++){
            q.add(s.substring(i,i+1));
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(2,4,2,1));
    }
}
