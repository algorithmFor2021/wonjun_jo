package boj.P1208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Main 설명 : 부분 수열의 합2
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/04/14
**/
public class Main {
    static Fs fs = new Fs();
    static int n;
    static int s;
    static int[] arr = new int[40];

    static ArrayList<Integer> left = new ArrayList<>();
    static HashMap<Integer,Long> lMap = new HashMap<>();
    static ArrayList<Integer> right = new ArrayList<>();
    static HashMap<Integer,Long> rMap = new HashMap<>();

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        s = fs.nInt();
        for(int i=0;i<n;i++) arr[i] = fs.nInt();

        makeArrayList(0,n/2,0,true);
        makeArrayList(n/2,n,0,false);

        Collections.sort(left);
        Collections.sort(right);

        long ans = 0;

        for(int l : left) {
            int target = s - l;
            if(rMap.containsKey(target)) {
                ans += (lMap.get(l) * rMap.get(target));
            }
        }


        System.out.println(s==0?ans-1:ans);

    }
    static void makeArrayList(int st,int ed,int sum,boolean isLeft) {
        if(st == ed) {
            if(isLeft) {
                if(lMap.containsKey(sum)) {
                    lMap.replace(sum, lMap.get(sum)+1);
                }else {
                    left.add(sum);
                    lMap.put(sum,1L);
                }
            }
            else {
                if(rMap.containsKey(sum)) {
                    rMap.replace(sum, rMap.get(sum)+1);
                }else {
                    rMap.put(sum,1L);
                    right.add(sum);
                }
            }
            return;
        }
        makeArrayList(st+1,ed,sum,isLeft);
        makeArrayList(st+1,ed,sum+arr[st],isLeft);
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
}
