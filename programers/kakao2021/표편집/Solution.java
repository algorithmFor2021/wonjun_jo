package programers.kakao2021.표편집;

import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    static int total;
    static int[] tree = new int[4000001];
    static Stack<Integer> revoke = new Stack<>();
    static StringTokenizer st;

    static int query(int node,int st,int ed,int left,int right){
        if(st > right || ed < left) return 0;
        if(left <= st && ed <= right) return tree[node];
        else{
            return query(node*2,st,(st+ed)/2,left,right) +
                    query(node*2+1,(st+ed)/2+1,ed,left,right);
        }
    }

    static void update(int node,int st,int ed,int target,int val){
        if(st > target || ed < target) return;
        if(st == ed){
            tree[node] = val;
            return;
        }

        update(node*2,st,(st+ed)/2,target,val);
        update(node*2+1,(st+ed)/2+1,ed,target,val);
        tree[node] = tree[node*2] + tree[node*2+1];
    }

    public String solution(int n, int k, String[] cmd) {
        total = n;
        for(String c : cmd){
            st = new StringTokenizer(c);
            String nextCmd = st.nextToken();
            if(nextCmd.equals("U")){
                int dist = Integer.parseInt(st.nextToken());
                k = bnSearch(k,0,k-1,dist);
            }else if(nextCmd.equals("D")){
                int dist = Integer.parseInt(st.nextToken());
                k = bnSearch(k,k+1,n-1,dist);
            }else if(nextCmd.equals("C")){
                revoke.add(k);
                update(1,0,n-1,k,1);
                // isLast
                if(k==n-1 || query(1,0,n-1,k+1,n-1) == n-1-k){
                    int next = bnSearch(k,0,k-1,0);
                    k = next;
                }
                else{
                    int next = bnSearch(k,k+1,n-1,0);
                    k = next;
                }
            }
            else{
                int top = revoke.pop();
                update(1,0,n-1,top,0);
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            if(query(1,0,n-1,i,i)==0){
                sb.append("O");
            }
            else{
                sb.append("X");
            }
        }

        return sb.toString();
    }

    public int bnSearch(int base,int low,int high,int dist){
        boolean isDown = base == (low-1);
        int ans = 0;
        while(low <= high){
            int mid = (low + high) / 2;

            int removedCnt = query(1,0,total-1, Math.min(base, mid), Math.max(base, mid));
            int ret = Math.abs(base-mid) - removedCnt;

            if(ret >= dist){
                if(isDown)high = mid - 1;
                else low = mid+1;
                ans = mid;
            }
            else{
                if(isDown)low = mid + 1;
                else high = mid - 1;
            }

        }

        // never call
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(20,0,new String[]{
                "D 15",
                "C",
                "C",
                "C",
                "C",
                "U 15",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C",
                "C"

        });
    }
}
