package programers.kakao2020.외벽점검;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    static int[] w;
    static int[] d;
    static int totalRange;
    static int answer = -1;

    public int solution(int n, int[] weak, int[] dist) {
        d = sort(dist);
        w = weak;
        totalRange = n;

        for(int i=1;i<=dist.length;i++){
            dfs(i,0,new boolean[i],new ArrayList<>());
            if(answer != -1) break;
        }
        return answer;
    }

    public boolean isCovered(ArrayList<Integer> list){
        for(int st=0;st<w.length;st++){
            int[] tempWeak = new int[w.length];
            for(int j=st;j<st+w.length;j++){
                if(j >= w.length) tempWeak[j-st] = w[j%w.length] + totalRange;
                else tempWeak[j-st] = w[j%w.length];
            }

            int l =0;
            int curLoc = tempWeak[0] + list.get(l);
            for(int i=0;i<w.length;i++){
                if(tempWeak[i] > curLoc){
                    l += 1;
                    if(l >= list.size()) break;
                    curLoc = tempWeak[i] + list.get(l);
                }
            }

            if(l < list.size()) return true;
        }
        return false;
    }

    public void dfs(int total,int idx,boolean[] visited,ArrayList<Integer> list){
        if(idx == total){
            if(isCovered(list)) answer = total;
            return;
        }

        for(int i=0;i<total;i++){
            if(!visited[i]){
                visited[i] = true;
                list.add(d[i]);
                dfs(total,idx+1,visited,list);
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }

    }

    public int[] sort(int[] a){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<a.length;i++) list.add(a[i]);
        Collections.sort(list,Comparator.reverseOrder());
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                12,new int[]{1, 5, 6, 10},new int[]{1, 2, 3, 4}
        ));
    }
}
