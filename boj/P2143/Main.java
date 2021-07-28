package boj.P2143;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static int T;
    static int aSize,bSize;
    static long answer =0;
    static int[] A = new int[1001];
    static int[] B = new int[1001];
    static HashMap<Integer,Long> aMap = new HashMap<>(1_000_001);
    static HashMap<Integer,Long> bMap = new HashMap<>(1_000_001);

    public static void main(String[] args) throws Exception{
        T = fs.nInt();
        aSize = fs.nInt();
        for(int i=0;i<aSize;i++) A[i] = fs.nInt();
        bSize = fs.nInt();
        for(int i=0;i<bSize;i++) B[i] = fs.nInt();

        int[] aSum = new int[1001];
        int[] bSum = new int[1001];

        int temp1 = 0;
        int temp2 = 0;
        int maxLen = Math.max(aSize,bSize);

        for(int i=0;i<maxLen;i++){
            if(i<aSize) {
                temp1 += A[i];
                aSum[i] += temp1;
                addMap('A',temp1);
            }
            if(i<bSize) {
                temp2 += B[i];
                bSum[i] += temp2;
                addMap('B',temp2);
            }
        }
        for(int i=0;i<aSize;i++){
            for(int j=i+1;j<aSize;j++){
                aSum[j] -= A[i];
                addMap('A',aSum[j]);
            }
        }
        for(int i=0;i<bSize;i++){
            for(int j=i+1;j<bSize;j++){
                bSum[j] -= B[i];
                addMap('B',bSum[j]);
            }
        }

        for(int aKey : aMap.keySet()){
            if(bMap.containsKey(T-aKey)){
                answer += aMap.get(aKey)*bMap.get(T-aKey);
            }
        }

        System.out.println(answer);
    }

    static void addMap(char flag,int key){
        if(flag=='A'){
            if(aMap.containsKey(key)) aMap.replace(key,aMap.get(key) + 1);
            else aMap.put(key,1L);
        }
        else{
            if(bMap.containsKey(key)) bMap.replace(key,bMap.get(key) + 1);
            else bMap.put(key,1L);
        }
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
