package programers.kakao2022.k진수에서소수개수;

import java.util.StringTokenizer;

public class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringTokenizer st = getNum(n,k);
        while(st.hasMoreTokens()){
            String next = st.nextToken();
            if(isPrime(next)) answer++;
        }
        System.out.println(answer);
        return answer;
    }

    public boolean isPrime(String _n){
        long n = Long.parseLong(_n);
        if(n < 2) return false;
        for(int i=2;i<=(int)Math.sqrt(n);i++){
            if(n%i==0) return false;
        }
        return true;
    }

    public StringTokenizer getNum(int n, int k){
        long tmp = n;
        StringBuilder sb = new StringBuilder();
        while(tmp > 0){
            sb.append(tmp%k);
            tmp = tmp / k ;
        }

        return new StringTokenizer(sb.reverse().toString(),"0");
    }

    public static void main(String[] args) {
        new Solution().solution(370,10);
    }


}
