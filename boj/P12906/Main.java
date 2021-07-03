package boj.P12906;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static HashMap<Integer,Character> map = new HashMap<>();
    static HashSet<String> set = new HashSet<>();
    static int ans = -1;

    public static void main(String[] args) throws Exception{
        map.put(0,'A');map.put(1,'B');map.put(2,'C');

        StringBuilder[] arr = new StringBuilder[3];

        for(int i=0;i<3;i++){
            int n = fs.nInt();
            arr[i] = new StringBuilder();
            if(n > 0) {
                for (char c : fs.next().toCharArray()) {
                    arr[i].append(c);
                }
            }
        }
        Queue<Snode> q = new LinkedList<>();
        q.add(new Snode(0,arr));
        checkHashSet(arr);

        while(!q.isEmpty()){
            Snode snode = q.poll();

            if(removeAndCheckAnswer(snode.arr)){
                ans = snode.cnt;
                break;
            }
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(i!=j){
                        // i 에서 j로
                        StringBuilder[] temp = copySb(snode.arr);
                        if(temp[i].length() > 0) {
                            char c = temp[i].charAt(temp[i].length()-1);
                            temp[i].delete(temp[i].length()-1,temp[i].length());
                            temp[j].append(c);
                            if(checkHashSet(temp))
                            {
                                q.add(new Snode(snode.cnt + 1, temp));
                            }
                        }
                    }
                }
            }
        }

        System.out.println(ans);

    }
    static boolean checkHashSet(StringBuilder[] sb){
        String s = sb[0]+"_"+sb[1]+"_"+sb[2];
        if(!set.contains(s)){
            set.add(s);
            return true;
        }
        return false;
    }
    static StringBuilder[] copySb(StringBuilder[] sb){
        StringBuilder[] ret = new StringBuilder[3];
        for(int i=0;i<3;i++) ret[i] = new StringBuilder();
        for(int i=0;i<3;i++){
            for(int j=0;j<sb[i].length();j++) {
                ret[i].append(sb[i].charAt(j));
            }
        }
        return ret;
    }

    static boolean removeAndCheckAnswer(StringBuilder[] sb){
        for(int i=0;i<3;i++){
            int idx = -1;
            for(int j=0;j<sb[i].length();j++){
                if(sb[i].charAt(j) == map.get(i)) idx = j;
                else break;
            }
            if(idx >= 0) {
                sb[i].delete(0,idx+1);
            }
        }
        for(int i=0;i<3;i++){
            if(sb[i].length()>0) return false;
        }
        return true;
    }

    static class Snode{
        int cnt;
        StringBuilder[] arr;

        public Snode(int cnt, StringBuilder[] arr) {
            this.cnt = cnt;
            this.arr = arr;
        }
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
