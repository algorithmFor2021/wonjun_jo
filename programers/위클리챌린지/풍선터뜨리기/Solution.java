package programers.위클리챌린지.풍선터뜨리기;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int[] leftDp = new int[a.length];
        int[] rightDp = new int[a.length];
        int lmin = leftDp[0] = a[0];
        int rmin = rightDp[a.length-1] = a[a.length-1];
        for(int i=1;i<a.length;i++) {
            leftDp[i] = lmin;
            rightDp[a.length-i-1] = rmin;
            lmin = Math.min(lmin,a[i]);
            rmin = Math.min(rmin,a[a.length-i-1]);
        }
        for(int i=0;i<a.length;i++) if(!(leftDp[i] < a[i] && rightDp[i] < a[i])) answer++;
        return answer;
    }
}}
