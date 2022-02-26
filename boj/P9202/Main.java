package boj.P9202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int w;
    static char[][] board = new char[4][4];
    static int[] dx = {-1,1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,-1,1,1,-1,1,-1};
    static int n;
    static Trie trie = new Trie();
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception{
        w = fs.nInt();
        for(int i=0;i<w;i++) {
            trie.insert(fs.next());
        }
        n = fs.nInt();
        for(int i=0;i<n;i++) {
            for(int j=0;j<4;j++) {
                char[] temp = fs.next().toCharArray();
                for(int k=0;k<4;k++) {
                    board[j][k] = temp[k];
                }
            }

            Set<String> ret = new HashSet<>();
            for(int j=0;j<4;j++){
                for(int k=0;k<4;k++) {
                    boolean[][] ch = new boolean[4][4];
                    StringBuilder sb = new StringBuilder();
                    ch[j][k] = true;
                    sb.append(board[j][k]);
                    dfs(j,k,ch,sb,ret);
                }
            }
            answer.append(makeAns(ret));
        }

        System.out.println(answer);

    }

    static int getScore(String s) {
        if(s.length() < 3) return 0;
        else if(s.length() < 5) return 1;
        else if(s.length() < 6) return 2;
        else if(s.length() < 7) return 3;
        else if(s.length() < 8) return 5;
        else return 11;
    }

    static String makeAns(Set<String> set) {
        if(set.size() == 0) return "0 0";
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<>(set);
        Collections.sort(list, (o1,o2)-> {
            if(o1.length() != o2.length()) return o2.length() - o1.length();
            return o1.compareTo(o2);
        });
        int score = 0;
        for(String s : list) {
            score += getScore(s);
        }
        sb.append(score).append(" ").append(list.get(0)).append(" ").append(list.size()).append('\n');
        return sb.toString();
    }

    static void dfs(int x, int y,boolean[][] ch ,StringBuilder sb , Set<String> set) {

        for(int i=0;i<8;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(checkRange(nx,ny) && !ch[nx][ny]) {
                ch[nx][ny] = true;
                sb.append(board[nx][ny]);
                int flag = trie.find(sb.toString());
                if(flag == 1) set.add(sb.toString());
                if(flag != -1 ) dfs(nx,ny,ch,sb,set);
                sb.deleteCharAt(sb.length()-1);
                ch[nx][ny] = false;
            }
        }
    }

    static boolean checkRange(int x,int y) {
        return x>=0 && x<4 && y>=0 && y<4;
    }

    static class Node {
        boolean isEnd = false;
        Map<Character,Node> child = new HashMap<>();
    }

    static class Trie {
        Node root = new Node();

        void insert(String s) {
            Node curNode = root;

            for(char c : s.toCharArray()) {
                curNode = curNode.child.computeIfAbsent(c, k -> new Node());
            }

            curNode.isEnd = true;
        }

        int find(String s) {
            Node curNode = root;

            for(char c : s.toCharArray()) {
                if(!curNode.child.containsKey(c)) {
                    return -1;
                }
                curNode = curNode.child.get(c);
            }

            return curNode.isEnd?1:0;
        }
    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }

        public String next() throws Exception{
            while(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
