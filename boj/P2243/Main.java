package boj.P2243;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int MAX_VALUE = 1000000;
    static int[] segTree = new int[MAX_VALUE*4+1];

    public static void update(int node,int st,int ed,int target,int val){
        if(st > target || ed < target) return;
        if(st == ed){
            segTree[node] += val;
            return;
        }
        update(node*2,st,(st+ed)/2,target,val);
        update(node*2+1,(st+ed)/2+1,ed,target,val);
        segTree[node] = segTree[node*2] + segTree[node*2+1];
    }

    public static int query(int node,int st,int ed,int left,int right){
        if(st > right || ed < left) return 0;
        if(left<=st && ed <=right){
            return segTree[node];
        }
        return query(node*2,st,(st+ed)/2,left,right)
                + query(node*2+1,(st+ed)/2+1,ed,left,right);
    }

    public static int bnSearch(int k){
        int low = 1;
        int high = MAX_VALUE;
        int ret = -1;

        while(low<=high){
            int mid = (low+high)/2;
            int q = query(1,0,MAX_VALUE,1,mid);
            if(q >= k){
                ret = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws Exception {
        int testCase = fs.nInt();
        StringBuilder sb = new StringBuilder();
        while(testCase-->0){
            int cmp = fs.nInt();
            // 꺼내기
            if(cmp == 1){
                int k = fs.nInt();
                int mat = bnSearch(k);
                sb.append(mat).append('\n');
                update(1,0,MAX_VALUE,mat,-1);
            }
            // 추가하기
            else{
                int m = fs.nInt();
                int num = fs.nInt();
                update(1,0,MAX_VALUE,m,num);
            }
        }

        System.out.println(sb);



    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
