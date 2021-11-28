package programers.kakao2018.셔틀버스;


import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    static int START_TIME = 540;
    static ArrayList<Integer> intTimeTable = new ArrayList<>();

    public String solution(int n, int t, int m, String[] timetable) {
        for(String s : timetable) intTimeTable.add(timeToInt(s));
        return toStringTime(bnSearch(n,t,m));
    }

    public static String toStringTime(int ans){
        int h = ans / 60;
        int m = ans % 60;

        StringBuilder sb = new StringBuilder();
        if(h < 10) sb.append("0");
        sb.append(h).append(":");
        if(m < 10) sb.append("0");
        sb.append(m);
        return sb.toString();
    }

    public static int bnSearch(int n,int t,int m){
        int low = 0;
        int high = 540 + n*t;
        int answer = low;
        while(low <= high){
            int mid = (low + high) / 2;

            if(check(n,t,m,mid)){
                answer = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }

        }
        return answer;
    }

    static public boolean check(int n,int t,int m,int mid){
        ArrayList<Integer> tmp = new ArrayList<>(intTimeTable);
        tmp.add(mid);
        Collections.sort(tmp);

        int curTime = START_TIME;
        int curCust = 0;
        int maxTime = tmp.get(curCust);
        while(curTime <= START_TIME + t*(n-1)){
            for(int i=0;i<m;i++){
                if(curCust < tmp.size() && tmp.get(curCust) <= curTime){
                    maxTime = tmp.get(curCust);
                    curCust++;
                }
            }
            if(maxTime > mid) return true;
            if(maxTime == mid && curTime == START_TIME + t*(n-1)){
                for(int k=curCust;k<tmp.size();k++){
                    if(tmp.get(k) == mid) return false;
                }
                return true;
            }

            curTime += t;
        }

        return false;
    }

    public static int timeToInt(String s){
        int h = Integer.parseInt(s.substring(0,2));
        int m = Integer.parseInt(s.substring(3));

        return 60*h + m;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        //sol.solution(1,1,5,new String[]{"08:00", "08:01", "08:02", "08:03"});
        System.out.println(sol.solution(2,10,2,new String[]{"09:10", "09:09", "08:00"}));
    }
}
