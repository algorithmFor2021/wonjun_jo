package programers.kakao2021.거리두기확인하기;

import java.util.*;

class Solution {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0;i<5;i++) answer[i] = 1;
        char[][] map = new char[5][5];

        int now = 0;
        for(String[] p : places){
            for(int i=0;i<5;i++){
                char[] tmp = p[i].toCharArray();
                for(int j=0;j<5;j++){
                    map[i][j] = tmp[j];
                }
            }

            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(map[i][j]=='P'){
                        if(find(i,j,map)){
                            answer[now] = 0;
                        }
                    }
                }
            }
            now++;
        }
        return answer;
    }

    public boolean find(int x,int y,char[][] map){
        int[][] dist = new int[5][5];
        boolean[][] visited = new boolean[5][5];
        visited[x][y] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));

        while(!q.isEmpty()){
            Pair p = q.poll();

            for(int i=0;i<4;i++){
                int nx =p.x + dx[i];
                int ny =p.y + dy[i];

                if(checkRange(nx,ny,map) && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[p.x][p.y] + 1;
                    if(map[nx][ny]=='P'){
                        return true;
                    }
                    if(dist[nx][ny] < 2){
                        q.add(new Pair(nx,ny));
                    }
                }
            }
        }

        return false;
    }

    static class Pair{
        int x,y;

        public Pair(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public boolean checkRange(int x,int y,char[][] map){
        return x>=0 && x<5 && y>=0 && y<5 && map[x][y]!='X';
    }


}
