package programers.조이스틱;

public class Solution {

    static int l;
    static int answer = Integer.MAX_VALUE;

    public int solution(String name) {
        char[] arr = name.toCharArray();
        l = arr.length;
        boolean[] visit = new boolean[l];
        visit[0] = true;
        dfs(0,getCharMove(arr[0]),visit,arr);

        return answer;
    }

    static void dfs(int idx,int move,boolean[] visit,char[] arr) {
        if(l==1) {
            answer = move;
            return;
        }
        int[] right = move(idx,true,visit,arr);
        int[] left = move(idx,false,visit,arr);

        if(right[0] == -1 || left[0] == -1) {
            answer = Math.min(move,answer);
            return;
        }

        visit[right[0]] = true;
        dfs(right[0],move + right[1],visit,arr);
        visit[right[0]] = false;
        visit[left[0]] = true;
        dfs(left[0],move + left[1],visit,arr);
        visit[left[0]] = false;

    }

    // {인덱스 , 이동 횟수}
    static int[] move(int cur,boolean isRight,boolean[] visit,char[] arr) {
        int move = 1;
        int idx = -1;
        int start = isRight ? getNextIndex(cur+1) : getNextIndex(cur-1);
        for(int i=start;i!=cur;i = isRight?getNextIndex(i+1):getNextIndex(i-1)){
            if(arr[i] != 'A' && !visit[i]) {
                idx = i;
                break;
            }
            move++;
        }
        return idx==-1?new int[]{-1,-1} :
                new int[]{idx,move+getCharMove(arr[idx])};
    }

    static int getNextIndex(int x) {
        if(x>=l) return x%l;
        else if(x<0) {
            int absMod = Math.abs(x)%l;
            return l - absMod;
        }
        return x;
    }

    static public int getCharMove(char c) {
        return Math.min(26-(c-'A'),c-'A');
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("AAA"));
    }
}
