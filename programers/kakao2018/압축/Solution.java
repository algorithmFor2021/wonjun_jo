package programers.kakao2018.압축;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    static HashMap<String,Integer> wordToId = new HashMap<>();
    public int[] solution(String msg) {
        int maxNumber = 27;
        ArrayList<Integer> ret = new ArrayList<>();
        init();
        for(int i=0;i<msg.length();){
            int j=i+1;
            int temp = -1;
            String subMsg = "";
            for(;j<=msg.length();j++){
                subMsg = msg.substring(i,j);
                if(wordToId.containsKey(subMsg)){
                    temp = wordToId.get(subMsg);
                }
                else break;
            }
            ret.add(temp);
            if(!wordToId.containsKey(subMsg)) {
                wordToId.put(subMsg, maxNumber);
                maxNumber++;
            }
            i = j - 1;
        }
        int[] answer = new int[ret.size()];
        for(int i=0;i<answer.length;i++){
            answer[i] = ret.get(i);
        }

        return answer;
    }

    public static void init(){
        for(int i=65;i<65+26;i++){
            wordToId.put(String.valueOf((char)i),i-64);
        }
    }

    public static void main(String[] args) {
        printArary(new Solution().solution("TOBEORNOTTOBEORTOBEORNOT"));
    }

    public static void printArary(int[] a){
        System.out.print("[ ");
        for(int i : a){
            System.out.print(i+",");
        }
        System.out.println(" ]");
    }

}
