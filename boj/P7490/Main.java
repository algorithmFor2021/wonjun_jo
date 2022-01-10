package boj.P7490;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static String[] op = {"+","-",""};
    public static void main(String[] args) throws Exception{
        int t = fs.nInt();
        while(t-->0){
            solve(fs.nInt());
        }
    }
    static void solve(int num) throws Exception{
        StringBuilder sb = new StringBuilder();
        ArrayList<String> ans = new ArrayList<>();
        sb.append("1");
        dfs(2,num,sb,ans);
        Collections.sort(ans);
        StringBuilder sbans = new StringBuilder();
        for(String a : ans) sbans.append(a).append('\n');
        System.out.println(sbans);
    }

    static void dfs(int cur,int num,StringBuilder sb,ArrayList<String> ans) throws Exception{
        if(cur > num){
            if(calculate(sb)) {
                StringBuilder temp = new StringBuilder();
                for(char s : sb.toString().toCharArray()){
                    if(temp.length()>0 && Character.isDigit(temp.charAt(temp.length()-1))
                            && Character.isDigit(s)){
                        temp.append(" ");
                    }
                    temp.append(s);
                }
                ans.add(temp.toString());
            }
        }else{
            // + , - , ''
            for(String o : op){
                int st = sb.length();
                sb.append(o).append(cur);
                dfs(cur+1,num,sb,ans);
                sb.delete(st,st+2);
            }
        }
    }
    static boolean calculate(StringBuilder sb) throws Exception {
        StringTokenizer st = new StringTokenizer(sb.toString(),"+-",true);
        int ret = 0;
        String op = "+";
        while(st.hasMoreTokens()){
            String s = st.nextToken();
            if(isNumeric(s)) {
                if(op.equals("+")) ret += Integer.parseInt(s);
                else if(op.equals("-")) ret -= Integer.parseInt(s);
            }else{
                op = s;
            }
        }
        return ret==0;
    }
    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
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
