package programers.입국심사;

public class Solution {
    static long MAX_TIME = 1_000_000_000_000_000_000L;
    public long solution(int n, int[] times) {
        long answer = 0;

        long low = 0;
        long high = MAX_TIME;
        while(low <= high){
            long mid = (low + high) / 2;

            if(check(times,mid,n)){
                answer = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }

        }

        return answer;
    }

    public boolean check(int[] times , long mid, int n){
        long ret = 0;
        for(int t : times){
            ret += mid / (long)t;
        }
        return ret >= (long)n;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(6,new int[]{7,10}));
    }
}
