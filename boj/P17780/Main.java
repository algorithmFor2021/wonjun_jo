package boj.P17780;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fs fs = new Fs();
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};
    static int[][] arr = new int[15][15];
    static Node[] nodes = new Node[15];
    static HashMap<String,Queue<Node>> map = new HashMap<>();
    static int n,k;

    public static void main(String[] args) throws Exception{
        n = fs.nInt();
        k = fs.nInt();

        for(int i=0;i<=k;i++) nodes[i] = new Node();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                map.put(i+"_"+j,new LinkedList<>());
            }
        }
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
            nodes[i].id = i;
            nodes[i].dir = dir;
            Queue<Node> tmp = map.get(x + "_" + y);
            tmp.add(nodes[i]);
        }

        int t = 1;
        while(t<=1000){
            start();
            if(check()){
                System.out.println(t);
                return;
            }
            t++;
        }
        System.out.println(-1);

    }
    static boolean check(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(map.get(i+"_"+j).size() >= 4){
                    return true;
                }
            }
        }
        return false;
    }

    static void start(){
        // 1번부터 이동
        for(int i=1;i<=k;i++){
            int[] loc = find(i);
            if(loc[0] == -1) continue;
            else{ // 맨밑이 i인것을 찾은 경우
                Node node = map.get(loc[0]+"_"+loc[1]).peek();
                int nx = loc[0] + dx[node.dir];
                int ny = loc[1] + dy[node.dir];

                move(node.dir,loc[0],loc[1],nx,ny);

            }
        }
    }
    static void move(int dir,int x,int y,int nx,int ny){
        // 흰색 or 빨강
        if(checkRange(nx,ny)){
            Queue<Node> nextQueue = map.get(nx + "_" + ny);
            Queue<Node> curQueue = map.get(x + "_" + y);
            // 흰
            if(arr[nx][ny] == 0){
                while(!curQueue.isEmpty()) nextQueue.add(curQueue.poll());
            }
            //빨강
            else{
                Stack<Node> stk = new Stack<>();
                while(!curQueue.isEmpty()) stk.add(curQueue.poll());
                while(!stk.isEmpty()) nextQueue.add(stk.pop());

            }
        }
        // 파랑
        else{
            // 현재말 이동방향 반대로
            if(dir%2==0){
                map.get(x+"_"+y).peek().dir -= 1;
            }else{
                map.get(x+"_"+y).peek().dir += 1;
            }

            // 이동가능하면 이동하기
            dir = map.get(x+"_"+y).peek().dir;
            nx = x + dx[dir];
            ny = y + dy[dir];

            if(checkRange(nx,ny)){
                move(dir,x,y,nx,ny);
            }
        }
    }

    // 파랭이 or 경계 벗어남
    static boolean checkRange(int x,int y){
        return x>=1 && x<=n && y>=1 && y<=n && arr[x][y]!=2;
    }

    static int[] find(int x){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(!map.get(i+"_"+j).isEmpty() &&
                    map.get(i+"_"+j).peek().id == x
                ){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }


    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(!map.get(i+"_"+j).isEmpty()){
                    System.out.println(i+","+j);
                    printQueue(map.get(i+"_"+j));
                }
            }
        }
    }
    static void printQueue(Queue<Node> q){
        Iterator<Node> it = q.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    static class Node{
        int id;
        int dir;

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
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
    }
}
