package boj.P13711;

import java.util.*;
import java.lang.*;
import java.io.*;

class Main{
    static Fs fs = new Fs();
    static HashMap<Integer,Integer> map = new HashMap<>();
    static int[] a = new int[100001];
    static int n;
    static int size = 0;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        for(int i=0;i<n;i++){
            map.put(fs.nInt(),i);
        }

        for(int i=0;i<n;i++){
            int next = map.get(fs.nInt()); // next is index

            if(size == 0 || (size>0 && a[size-1]<next)) a[size++] = next;
            else a[bnSearch(next)] = next;
        }

        System.out.println(size);
    }
    // return lower bound index
    static int bnSearch(int next){
        int low = 0 ;
        int high = size-1;
        int ret = low;
        while(low<=high){
            int mid = (low + high)/2;

            if(a[mid] >= next) {
                ret = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ret;

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
