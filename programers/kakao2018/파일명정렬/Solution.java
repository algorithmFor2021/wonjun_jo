package programers.kakao2018.파일명정렬;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        ArrayList<File> fileList = new ArrayList<>();
        for(int i=0;i<files.length;i++){
            fileList.add(new File(files[i]));
        }

        Collections.sort(fileList);

        for(int i=0;i<fileList.size();i++){
            answer[i] = fileList.get(i).original;
        }
        return answer;
    }

    static class File implements Comparable<File>{
        String original;
        String head;
        String number;
        String tail;

        public File(String f){
            original = f;
            head = "";
            number ="";
            tail = "";
            char[] temp = f.toCharArray();
            int i=0;
            for(;i<temp.length;i++){
                if(!Character.isDigit(temp[i])) head += temp[i];
                else break;
            }
            for(;i<temp.length;i++){
                if(Character.isDigit(temp[i])) number += temp[i];
                else break;
            }
            tail = f.substring(i);
        }

        @Override
        public String toString() {
            return original;
        }

        @Override
        public int compareTo(File o) {
            // head 비교
            String h1 = this.head.toLowerCase();
            String h2 = o.head.toLowerCase();

            if(h1.compareToIgnoreCase(h2) != 0) return h1.compareToIgnoreCase(h2);

            // number 비교
            Integer n1 = Integer.parseInt(this.number);
            Integer n2 = Integer.parseInt(o.number);

            if(n1.compareTo(n2) != 0) return n1.compareTo(n2);

            return 0;
        }
    }

    public static void main(String[] args) {
        for(String s : new Solution().solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})){
            System.out.println(s);
        }
    }
}
