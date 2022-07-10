package leetcode.longest_palindromic_substring;

public class Solution {
    public String longestPalindrome(String s) {
        String answer = "";
        int l = s.length();
        for(int i=l;i>=1;i--) {
            for(int j=0;j<l-i+1;j++) {
                String sub = s.substring(j, j + i);
                if (check(sub)){
                    return sub;
                }
            }
        }
        return answer;

    }

    public boolean check(String sb) {
        int ll = sb.length();
        for(int i=0;i<ll/2;i++) {
            if(sb.charAt(i) != sb.charAt(ll-(i+1))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("babab"));
    }

}
