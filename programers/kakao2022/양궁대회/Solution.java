package programers.kakao2022.양궁대회;

import java.util.*;

class Solution {
    static int n;
    static int[] info;
    static int[] answer;
    static int ansVal;

    public int[] solution(int _n, int[] _info) {
        n = _n;
        info = _info;
        ansVal = -1;
        answer = new int[]{-1};
        dfs(0,new int[11],0);
        return answer;
    }

    // 11개 중에 n개 선택하기
    public void dfs(int idx,int[] lion,int sum){
        if(sum == n){
            int gap = check(lion);
            if(gap > 0 && gap >= ansVal){
                if(ansVal != -1 && gap == ansVal){
                    for(int i=10;i>=0;i--){
                        if(answer[i] < lion[i]){
                            ansVal = gap;
                            answer = Arrays.copyOfRange(lion, 0, lion.length);
                            break;
                        }
                        if(answer[i] > lion[i]) break;
                    }
                }
                else {
                    ansVal = gap;
                    answer = Arrays.copyOfRange(lion, 0, lion.length);
                }
            }
            return;
        }
        if(idx == 11) return;

        for(int i=0;i<=n;i++){
            if(sum + i <= n){
                lion[idx] = i;
                dfs(idx+1,lion,sum+i);
                lion[idx] = 0;
            }
        }

    }

    public int check(int[] lion){
        int a = 0;
        int b = 0;
        for(int i=0;i<11;i++){
            int score = 10-i;
            if(info[i]==0 && lion[i]==0) continue;
            if(info[i] >= lion[i]) a += score;
            else b += score;
        }
        return b - a;
    }

    public static void main(String[] args) {
        int[] ret = new Solution().solution(5,new int[]{2,1,1,1,0,0,0,0,0,0,0});
        for(int r : ret){
            System.out.println(r);
        }
    }
}
