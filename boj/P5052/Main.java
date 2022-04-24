package boj.P5052;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();

    public static void main(String[] args) throws Exception{
        int testCase = fs.nInt();

        while(testCase-->0) {
            int n = fs.nInt();
            Trie trie = new Trie();

            boolean ans = true;
            ArrayList<String> input = new ArrayList<>();
            for(int i=0;i<n;i++) {
                input.add(fs.next());
            }
            input.sort(Comparator.comparingInt(String::length));

            for(String s : input) {
                if(!trie.insert(s)) {
                    ans = false;
                }
            }

            System.out.println(ans?"YES":"NO");

        }

    }

    static class Trie {
        Node root;

        public Trie() {
            root = new Node('#',false);
        }

        public boolean insert(String s) {
            Node cur = root;

            char[] temp = s.toCharArray();

            for(int i=0;i<temp.length;i++) {
                char c = temp[i];
                boolean isLast = i == temp.length - 1;

                Node next = cur.getChildIfContains(c);
                if(next == null) {
                    next = new Node(c,isLast);
                    cur.addChild(next);
                }else if(next.isEnd){
                    return false;
                }

                cur = next;
            }

            return true;

        }
    }


    static class Node {
        char num;
        boolean isEnd = false;
        ArrayList<Node> child = new ArrayList<>();

        public Node(char num,boolean isEnd) {
            this.num = num;
            this.isEnd = isEnd;
        }

        public void addChild(Node node) {
            this.child.add(node);
        }

        public Node getChildIfContains(char c) {
            for(Node n : this.child) {
                if(n.num == c) return n;
            }
            return null;
        }

    }

    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }

        public String next() throws Exception{
            while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
