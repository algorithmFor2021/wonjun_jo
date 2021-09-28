package boj.P17837;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs= new Fs();
    static int n,k;
    static int[][] arr;
    static Node[] nodes;
    static HashMap<String, List<Node>> map = new HashMap<>();
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};
    static boolean endGame = false;

    public static void main(String[] args) throws Exception {
        n = fs.nInt();
        k = fs.nInt();

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                map.put(i+"_"+j,new ArrayList<>());
            }
        }

        arr = new int[n+1][n+1];
        nodes = new Node[k+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                arr[i][j] = fs.nInt();
            }
        }

        int x,y,dir;
        for(int i=1;i<=k;i++){
            x = fs.nInt();
            y = fs.nInt();
            dir = fs.nInt();

            nodes[i] = new Node(i,x,y,dir);
            map.get(x+"_"+y).add(nodes[i]);
        }

        int time = 1;
        while(time<=1000){
            start();
            if(endGame){
                System.out.println(time);
                return;
            }
            time++;
        }

        System.out.println(-1);


    }


    static void start(){
        for(int i=1;i<=k;i++) {
            List<Node> f = find(i);
            move(f);
            if(endGame) return;
        }
    }

    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(!map.get(i+"_"+j).isEmpty()) {
                    System.out.println("("+i+" , "+j+")");
                    for (Node node : map.get(i + "_" + j)) {
                        System.out.println(node);
                    }
                }
            }
        }
    }

    static void move(List<Node> f){
        int x = f.get(0).x;
        int y = f.get(0).y;
        int dir = f.get(0).dir;
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(checkRange(nx,ny)){
            if(arr[nx][ny]==0){
                map.get(nx+"_"+ny).addAll(f);
                listUpdate(map.get(nx+"_"+ny),nx,ny);
            }
            else{
                Collections.reverse(f);
                map.get(nx+"_"+ny).addAll(f);
                listUpdate(map.get(nx+"_"+ny),nx,ny);
            }

            if(map.get(nx+"_"+ny).size()>=4) {
                endGame = true;
                return;
            }

        }
        else{
            if(dir%2==0) f.get(0).dir--;
            else f.get(0).dir++;

            int nx2 = x + dx[f.get(0).dir];
            int ny2 = y + dy[f.get(0).dir];

            if(checkRange(nx2,ny2)){
                move(f);
            }
            else{
                map.get(x+"_"+y).addAll(f);
            }
        }
    }
    static void listUpdate(List<Node> f , int nx,int ny){
        for(Node node : f){
            node.x = nx;
            node.y = ny;
        }
    }

    static boolean checkRange(int x,int y){
        return x>=1 && x<=n && y>=1 && y<=n && arr[x][y]!=2;
    }

    static List<Node> find(int num){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(!map.get(i+"_"+j).isEmpty()) {
                    for(int l=0;l<map.get(i+"_"+j).size();l++){
                        Node node = map.get(i+"_"+j).get(l);
                        if(node.id == num){
                            List<Node> r1 = map.get(i + "_" + j).subList(l, map.get(i + "_" + j).size());
                            List<Node> r2 = map.get(i + "_" + j).subList(0, l);
                            map.replace(i+"_"+j,r2);
                            return r1;
                        }
                    }
                }
            }
        }
        return null;
    }

    static class Node{
        int id;
        int x,y;
        int dir;

        public Node(int id, int x, int y, int dir) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    '}';
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
            if(st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
}
