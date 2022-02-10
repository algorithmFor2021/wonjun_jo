package programers.위클리챌린지.이진변환반복하기;

import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {0,0};
        while(s.length()>1) {
            int oneCnt = 0;
            int zeroCnt = 0;
            for(char c : s.toCharArray()) {
                if(c=='1') {
                    oneCnt++;
                }else {
                    zeroCnt++;
                }
            }
            s = Integer.toBinaryString(oneCnt);
            answer[0]++;
            answer[1] += zeroCnt;
        }
        return answer;
    }
}