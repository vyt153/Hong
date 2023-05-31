import java.util.Arrays;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        int amount = 0;
        for (int i : d) {
            if (amount < budget){
                amount += i;
                answer += 1;
                if (amount > budget){
                    answer -= 1;
                }
            }
        }
        
        return answer;
    }
}