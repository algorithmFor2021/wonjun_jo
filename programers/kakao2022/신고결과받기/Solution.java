package programers.kakao2022.신고결과받기;

import java.util.*;

class Solution {
    static HashMap<String,HashSet<String>> reportToId = new HashMap<>(); // 나를 신고한 사람
    static HashMap<String,HashSet<String>> idToReport = new HashMap<>(); // 내가 신고한 사람

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        for(String s : id_list) {
            reportToId.put(s,new HashSet());
            idToReport.put(s,new HashSet());
        }

        for(String s : report) {
            StringTokenizer st = new StringTokenizer(s);
            String f = st.nextToken();
            String t = st.nextToken();
            HashSet<String> tmpSet0 = reportToId.get(t);
            tmpSet0.add(f);
            reportToId.replace(t,tmpSet0);
            HashSet<String> tmpSet = idToReport.get(f);
            tmpSet.add(t);
            idToReport.replace(f,tmpSet);

        }

        int idx = 0;
        for(String s : id_list) {
            int r = 0;
            Iterator<String> it = idToReport.get(s).iterator();
            while(it.hasNext()) {
                String next = it.next();
                int num = reportToId.get(next).size();
                if(num >= k ) {
                    r++;
                }
            }
            answer[idx++] = r;
        }

        return answer;
    }
}