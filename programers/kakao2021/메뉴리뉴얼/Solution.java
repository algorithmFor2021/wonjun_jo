package programers.kakao2021.메뉴리뉴얼;
import java.util.*;

public class Solution {
    static int[] words = new int[20];
    static int[] courseMax = new int[11];
    static List<Integer>[] courseList = new ArrayList[11];

    public String[] solution(String[] orders, int[] course) {
        for(int i=0;i<11;i++) courseList[i] = new ArrayList<>();
        makeWords(orders);
        for(int c : course) {
            for(int i=1;i<1<<26;i++) {
                if(Integer.bitCount(i) == c) {
                    int mask = i;
                    int cnt = 0;
                    for(int w : words) if ((mask & w) == mask) cnt++;
                    if(cnt < 2) continue;
                    if(courseMax[c] <= cnt) {
                        if(courseMax[c] < cnt) courseList[c] = new ArrayList();
                        courseMax[c] = cnt;
                        courseList[c].add(mask);
                    }
                }
            }
        }
        return makeAns();
    }

    public String[] makeAns() {
        List<String> ret = new ArrayList<>();
        for(List<Integer> l : courseList) for(int i : l) ret.add(makeString(i));
        Collections.sort(ret, String::compareTo);
        return ret.toArray(new String[0]);
    }

    public String makeString(int a) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for(int i=1;i<1<<26;i<<=1) {
            if((a&i)>0) sb.append((char)('A'+idx));
            idx++;
        }
        return sb.toString();
    }

    public void makeWords(String[] orders) {
        int idx = 0;
        for(String o : orders) {
            int n = 0;
            for(char c : o.toCharArray()) n += 1<<(c-'A');
            words[idx++] = n;
        }
    }

    public static void main(String[] args) {
        for(String s : new Solution().solution(new String[]{
                "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"
        },new int[]{2,3,4})){
            System.out.println(s);
        }
    }

}
