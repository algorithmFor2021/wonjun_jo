package programers.kakao2017.사단고음;

public class Solution {
    static int answer = 0;
    public int solution(int n) {
        make(n,0,0);
        return answer;
    }

    public void make(int num,int t,int p){
        if(num==1) {
            if(t*2 == p) {
                answer++;
            }
            return;
        }
        if((t+1)*2 <= p && num%3 == 0){
            make(num/3,t+1,p);
        }
        if(p+1 <= 38){
            make(num-1,t,p+1);
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution().solution(2147483647));
    }
}
