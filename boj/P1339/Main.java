package boj.P1339;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static Fs fs = new Fs();
    static int n;
    static String[] str;
    static char[] chars;
    static int[] degree;
    static boolean[] visited = new boolean[11];
    static int charNum = 0;
    static int ans = 0;
    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        str = new String[n];
        for(int i=0;i<n;i++) str[i] = fs.next();
        Set<Character> set = new HashSet<>();
        for(int i=0;i<n;i++){
            for(char c : str[i].toCharArray()){
                set.add(c);
            }
        }
        ArrayList<Character> arr =  new ArrayList<>(set);
        chars = new char[arr.size()];
        degree = new int[arr.size()];
        for(char s : arr) chars[charNum++] = s;

        dfs(0);
        System.out.println(ans);


    }
    static void dfs(int idx){
        if(idx == charNum) ans = Math.max(getSum(),ans);
        else{
            for(int i=9;i>(9-charNum);i--){
                if(!visited[i]){
                    visited[i] = true;
                    degree[idx] = i;
                    dfs(idx+1);
                    degree[idx] = 0;
                    visited[i] = false;
                }
            }
        }
    }

    static int getSum(){
        int ret = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<charNum;i++){
            map.put(chars[i],degree[i]);
        }
        for(String s : str){
            int temp = 0;
            int mul = 1;
            char[] tmpChar = s.toCharArray();
            for(int j=tmpChar.length-1;j>=0;j--){
                temp += (map.get(tmpChar[j])*mul);
                mul *= 10;
            }
            ret += temp;
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

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
