package programers.kakao2018.프렌즈4블록;

public class Solution {
    static int m,n;
    static char[][] arr = new char[31][31];

    public int solution(int m_, int n_, String[] board) {
        m = m_;
        n = n_;
        int answer = 0;
        for(int i=0;i<m;i++){
            char[] temp = board[i].toCharArray();
            System.arraycopy(temp, 0, arr[i], 0, n);
        }

        while(true){
            int r = remove();
            answer += r;
            if(r == 0) break;
            drop();
        }

        return answer;
    }

    public static void drop(){
        for(int i=0;i<n;i++){
            for(int j=m-1;j>=0;j--){
                if(arr[j][i] == '.'){
                    int swapIdx = -1;
                    for(int k=j-1;k>=0;k--){
                        if(arr[k][i] != '.'){
                            swapIdx = k;
                            break;
                        }
                    }

                    if(swapIdx == -1) break;
                    arr[j][i] = arr[swapIdx][i];
                    arr[swapIdx][i] = '.';

                }
            }
        }
    }

    public static int remove(){
        int removedCnt = 0;
        boolean[][] temp = new boolean[31][31];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]=='.') continue;
                boolean flag = true;
                char c = arr[i][j];
                aaa : for(int k=i;k<i+2;k++){
                    for(int l=j;l<j+2;l++){
                        if(!checkRange(k,l) || arr[k][l] != c) {flag = false; break aaa;}
                    }
                }
                if(flag){
                    for(int k=i;k<i+2;k++){
                        for(int l=j;l<j+2;l++){
                            temp[k][l] = true;
                        }
                    }
                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(temp[i][j]) {
                    removedCnt++;
                    arr[i][j] = '.';
                }
            }
        }
        return removedCnt;
    }

    public static boolean checkRange(int x,int y){
        return x>=0 && x<m && y>=0 && y<n;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().solution(6,6,new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
    }


}
