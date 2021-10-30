package boj.P5373;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Fs fs = new Fs();
    static char[] cube = new char[55];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        solve();
        System.out.println(sb);
    }

    static void solve() throws Exception{
        int testCase = fs.nInt();
        while(testCase-- > 0){
            cubeInit();
            int cnt = fs.nInt();
            for(int i=0;i<cnt;i++) {
                String nextCmd = fs.next();
                if(nextCmd.charAt(1)=='-') translate(nextCmd.charAt(0)).execute();
                else for(int j=0;j<3;j++) translate(nextCmd.charAt(0)).execute();
            }
            setCubeUpSide();
        }
    }

    static void cubeInit(){
        String s = "owrygb";
        for(int i=1;i<=54;i++) cube[i] = s.charAt((i-1)/9);
    }

    static Command translate(char s){
        if(s == 'L') return Command.LL;
        else if(s == 'R') return Command.RL;
        else if(s == 'U') return Command.UL;
        else if(s == 'D') return Command.DL;
        else if(s == 'F') return Command.FL;
        else if(s == 'B') return Command.BL;
        // never return
        return null;
    }


    enum Command{
        UL(new int[]{7,8,9,37,38,39,19,20,21,46,47,48,10,11,12,13,14,15,16,17,18},
                new int[]{48,47,46,9,8,7,37,38,39,19,20,21,12,15,18,11,14,17,10,13,16}),
        DL(new int[]{1,2,3,43,44,45,25,26,27,52,53,54,28,29,30,31,32,33,34,35,36},
                new int[]{45,44,43,25,26,27,52,53,54,3,2,1,30,33,36,29,32,35,28,31,34}),
        FL(new int[]{16,17,18,46,49,52,28,29,30,39,42,45,19,20,21,22,23,24,25,26,27},
                new int[]{46,49,52,30,29,28,39,42,45,18,17,16,21,24,27,20,23,26,19,22,25}),
        BL(new int[]{10,11,12,48,51,54,34,35,36,37,40,43,1,2,3,4,5,6,7,8,9},
                new int[]{43,40,37,10,11,12,54,51,48,34,35,36,3,6,9,2,5,8,1,4,7}),
        LL(new int[]{1,4,7,10,13,16,19,22,25,28,31,34,37,38,39,40,41,42,43,44,45},
                new int[]{10,13,16,19,22,25,28,31,34,1,4,7,39,42,45,38,41,44,37,40,43}),
        RL(new int[]{18,15,12,9,6,3,36,33,30,27,24,21,46,47,48,49,50,51,52,53,54},
                new int[]{9,6,3,36,33,30,27,24,21,18,15,12,48,51,54,47,50,53,46,49,52});

        private int[] target;
        private int[] src;

        Command(int[] target,int[] src){
            this.target = target;
            this.src = src;
        }

        public void execute(){
            char[] srcTmp = new char[21];
            for(int i=0;i<21;i++) srcTmp[i] = cube[this.src[i]];
            for(int i=0;i<21;i++) cube[this.target[i]]=srcTmp[i];
        }

    }

    static void setCubeUpSide(){
        int i=10;
        int cnt = 1;
        while(i<=18){
            sb.append(cube[i]);
            if(cnt%3==0) sb.append('\n');
            cnt++;i++;
        }
    }
    static class Fs{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public int nInt() throws Exception{
            return Integer.parseInt(next());
        }

        public String next() throws Exception{
            if(!st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }

}
