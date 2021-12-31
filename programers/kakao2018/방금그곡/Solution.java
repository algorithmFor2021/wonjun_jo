package programers.kakao2018.방금그곡;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    static HashMap<String,String> map = new HashMap<>();
    static StringBuilder transM = new StringBuilder();
    public String solution(String m, String[] musicinfos) {
        map.put("C#","a");map.put("D#","b");map.put("F#","c");map.put("G#","d");map.put("A#","e");
        ArrayList<Node> list = new ArrayList<>();
        transM = translate(m.toCharArray());
        int id = 0;
        for(String music : musicinfos){
            Node nd = new Node(music,m,id++);
            if(nd.containsYn) list.add(nd);
        }
        Collections.sort(list);
        return list.size()==0?"(None)":list.get(0).name;
    }

    static class Node implements Comparable<Node>{
        int id; // 2순위
        int duration; // 우선 1순위
        String name;
        boolean containsYn;

        public Node(String f,String comp,int id){
            this.id = id;
            StringTokenizer stzr = new StringTokenizer(f,",");
            String temp = stzr.nextToken();
            int h = Integer.parseInt(temp.substring(0,2));
            int m = Integer.parseInt(temp.substring(3,5));
            int st = 60*h + m;
            temp = stzr.nextToken();
            h = Integer.parseInt(temp.substring(0,2));
            m = Integer.parseInt(temp.substring(3,5));
            duration = 60*h + m - st;
            name = stzr.nextToken();
            temp = stzr.nextToken();
            StringBuilder transTemp = translate(temp.toCharArray());
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<duration;i++) sb.append(transTemp.charAt(i%transTemp.length()));
            containsYn = sb.toString().contains(transM.toString());
        }

        @Override
        public int compareTo(Node o) {
            if(this.duration != o.duration) return o.duration - this.duration;
            return this.id - o.id;
        }
    }

    public static StringBuilder translate(char[] clist){
        StringBuilder transTemp = new StringBuilder();
        for(int i=0;i<clist.length-1;i++){
            if(clist[i]=='#') continue;
            else if(clist[i+1]=='#') transTemp.append(map.get(String.valueOf(clist[i]) + clist[i + 1]));
            else transTemp.append(clist[i]);
        }
        if(clist[clist.length-1] != '#') transTemp.append(clist[clist.length-1]);
        return transTemp;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().solution("CC#BCC#BCC#BCC#B",
        new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}
        ));
    }


}
