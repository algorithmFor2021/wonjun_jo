package programers.devmatching.다단계칫솔판매;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    static Node[] nodes;
    static HashMap<String,Integer> toNum;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = 1;
        toNum = new HashMap<>();
        nodes = new Node[enroll.length+1];
        for(int i=0;i<=enroll.length;i++) nodes[i] = new Node(i);
        for(String e : enroll) toNum.put(e,n++);
        for(int i=0;i<referral.length;i++){
            if(!referral[i].equals("-")) nodes[i+1].parent = nodes[toNum.get(referral[i])];
            else nodes[i+1].parent = nodes[0];
        }
        for(int i=0;i<seller.length;i++) {
            int cur = toNum.get(seller[i]);
            int money = 100 * amount[i];
            do {
                int remain = (int) (money * 0.1);
                int mine = money - remain;
                nodes[cur].amount += mine;
                money = remain;
                cur = nodes[cur].parent.id;
            }while(nodes[cur].id != 0);
        }

        return Arrays.stream(nodes)
                .filter(o->o.id!=0)
                .mapToInt(o->o.amount)
                .toArray();
    }

    static class Node{
        int id;
        Node parent;
        int amount;

        public Node(int id){
            this.parent = null;
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        for(int r : sol.solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}
                                ,new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}
                                ,new String[]{"young", "john", "tod", "emily", "mary"}
                                ,new int[]{12, 4, 2, 5, 10})){
            System.out.println(r);
        }
    }
}
