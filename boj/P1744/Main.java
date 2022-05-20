package boj.P1744;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Main 설명 : 마지막 알고리즘! 수묶기
 * @author jowonjun
 * @version 1.0.0
 * 작성일 : 2022/05/18
**/
public class Main {
    static int n;
    static Fs fs = new Fs();
    public static void main(String[] args) throws  Exception {
        n = fs.nInt();

        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0;i<n;i++) list.add(fs.nInt());

        Collections.sort(list);

        boolean isZeroExists = false;

        for(int a : list) {
            if(a == 0) {
                isZeroExists = true;
                break;
            }
        }

        int ans = 0;
        int mIndex = list.size();
        for(int i=list.size()-1;i>0 && list.get(i)>1 && list.get(i-1)>1 ;i -= 2) {
            ans += (list.get(i)*list.get(i-1));
            mIndex = i-1;
        }
        mIndex--;

        int m = 0 ;
        for(int i = mIndex;i>=0;i--) {
            if(list.get(i) > 0) m += list.get(i);
        }


        int kIndex = -1;
        for(int i=0;i<list.size()-1 && list.get(i)<0 && list.get(i+1)<0 ;i += 2) {
            ans += (list.get(i)*list.get(i+1));
            kIndex = i+1;
        }
        kIndex++;

        int k = kIndex<list.size() && list.get(kIndex)<0 ? list.get(kIndex) : 0;

        if(isZeroExists) k = 0;

        ans += (m + k);

        System.out.println(ans);

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
