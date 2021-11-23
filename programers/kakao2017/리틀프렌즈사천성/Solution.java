package programers.kakao2017.리틀프렌즈사천성;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static char[][] b = new char[101][101];
    static int m,n;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<Character> clist ;
    static HashMap<Character, ArrayList<Pos>> dotMap = new HashMap<>();
    static ArrayList<String> answer = new ArrayList<>();

    public String solution(int m, int n, String[] board) {
        HashSet<Character> set = new HashSet<>();
        answer = new ArrayList<>();
        dotMap = new HashMap<>();
        dx = new int[]{-1,1,0,0};
        dy = new int[]{0,0,-1,1};
        b = new char[101][101];
        this.m = m; this.n = n;
        for(int i=0;i<m;i++){
            char[] temp = board[i].toCharArray();
            for(int j=0;j<n;j++){
                b[i][j] = temp[j];
                if(b[i][j] != '*' && b[i][j] != '.'){
                    if(!dotMap.containsKey(b[i][j])) dotMap.put(b[i][j],new ArrayList<>());
                    dotMap.get(b[i][j]).add(new Pos(i,j));
                    set.add(b[i][j]);
                }
            }
        }

        clist = (ArrayList<Character>) new ArrayList<>(set).stream().sorted().collect(Collectors.toList());

        dfs(0,new boolean[clist.size()+1],new StringBuilder());

        return answer.size()==0?"IMPOSSIBLE":answer.get(0);
    }
    static void dfs(int idx,boolean[] ch,StringBuilder sb){
        if(idx >= clist.size()){
            answer.add(sb.toString());
            return;
        }

        for(int i=0;i<clist.size();i++){
            if(!ch[i]){
                Pos pos1 = dotMap.get(clist.get(i)).get(0);
                Pos pos2 = dotMap.get(clist.get(i)).get(1);
                if(pos1.islink(pos2)){
                    ch[i] = true;
                    char temp = b[pos1.x][pos1.y];
                    pos1.remove();
                    pos2.remove();
                    sb.append(temp);
                    if(answer.size()==0) dfs(idx+1,ch,sb);
                    pos1.update(temp);
                    pos2.update(temp);
                    sb.deleteCharAt(idx);
                }

            }

        }

    }


    static class Pos{
        int x,y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void remove(){
            b[x][y] = '.';
        }

        public void update(char c){
            b[x][y] = c;
        }

        public boolean islink(Pos p){
            Pos mid1 = new Pos(this.x,p.y);
            Pos mid2 = new Pos(p.x,this.y);

            boolean path1,path2;
            path1 = path2 = true;
            // x,y -> mid1(x,p.y) -> p
            for(int i=Math.min(y,p.y);i<=Math.max(y,p.y);i++){
                if(b[x][i] != b[x][y] && b[x][i] != '.') path1 = false;
            }
            for(int i=Math.min(x,p.x);i<=Math.max(x,p.x);i++){
                if(b[i][p.y] != b[x][y] && b[i][p.y] != '.') path1 = false;
            }
            // x,y-> mid2(p.x,y) -> p
            for(int i=Math.min(x,p.x);i<=Math.max(x,p.x);i++){
                if(b[i][y] != b[x][y] && b[i][y] != '.') path2 = false;
            }
            for(int i=Math.min(y,p.y);i<=Math.max(y,p.y);i++){
                if(b[p.x][i] != b[x][y] && b[p.x][i] != '.') path2 = false;
            }

            return path1 || path2;

        }

    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(10,10,new String[] {
                "M...M...DU",
                "....AR...R",
                "...E..OH.H",
                "...*..O...",
                ".E..A..Q..",
                "Q...*LL.*.",
                ".D.N.....U",
                "GT.T...F..",
                "....FKS...",
                "GN....K..S"
                 }));
    }
}
