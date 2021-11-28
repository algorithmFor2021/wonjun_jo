package programers.kakao2020.보석쇼핑;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    static HashMap<String,Integer> map = new HashMap<>();
    static int totalSize = 0;
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        totalSize = new HashSet<>(Arrays.asList(gems)).size();
        int ansSt = 0;
        int ansEd = gems.length-1;
        int st = 0;
        int ed = 0;
        for(;ed<gems.length;ed++){
            if(!map.containsKey(gems[ed])) map.put(gems[ed],1);
            else map.replace(gems[ed],map.get(gems[ed])+1);

            if(map.keySet().size() == totalSize){
                while(map.get(gems[st]) >= 2){
                    map.replace(gems[st],map.get(gems[st])-1);
                    st++;
                }
                if(ansEd - ansSt > ed - st) {
                    ansEd = ed;
                    ansSt = st;
                }
            }
        }
        answer[0] = ansSt+1;
        answer[1] = ansEd+1;
        return answer;
    }
    public static void main(String[] args) {
        for(int a : new Solution()
                .solution(new String[]
                        {"a", "b", "b", "c", "d", "a", "b", "c", "d"})){
            System.out.println(a);
        }
    }
}
