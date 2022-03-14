package programers.kakao2021.메뉴리뉴얼;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    static List<Character> menu = new ArrayList<>();
    static String[] orders;
    static int[] courseMax = new int[11];
    static List<String>[] result = new ArrayList[11];

    public String[] solution(String[] _orders, int[] course) {
        orders = _orders;
        for(int i=0;i<11;i++) result[i] = new ArrayList<>();
        menu = String.join("", _orders)
                .chars()
                .mapToObj(c -> (char) c)
                .distinct().collect(Collectors.toList());

        Arrays.stream(course)
                .forEach(c -> {
                    dfs(0,c,new ArrayList<>());
                });

        List<String> ans = new ArrayList<>();
        for(List<String> l : result) ans.addAll(l);
        ans.sort(String::compareTo);
        return ans.toArray(new String[0]);

    }
    // course = 코스 개수
    public static void dfs(int idx, int course,List<Character> list) {
        if(list.size() == course) {
            int num = 0;
            for(String o : orders) if(check(o,list)) num++;
            if(num < 2) return;

            StringBuilder sb = new StringBuilder();
            Collections.sort(list);
            for(char c : list) sb.append(c);

            if(num >= courseMax[course]) {
                if (num > courseMax[course]) result[course] = new ArrayList<>();
                courseMax[course] = num;
                result[course].add(sb.toString());
            }
            return;
        }
        if(idx == menu.size()) return;

        list.add(menu.get(idx));
        dfs(idx+1,course,list);
        list.remove(list.size()-1);
        dfs(idx+1,course,list);

    }

    public static boolean check(String s,List<Character> list) {
        int r = 0;
        for(char l : list) {
            for(char ss : s.toCharArray()) {
                if(l == ss) r++;
            }
        }
        return r == list.size();
    }



    public static void main(String[] args) {
        for(String s : new Solution().solution(new String[]{
                "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"
        },new int[]{2,3,4})){
            System.out.println(s);
        }
    }

}
