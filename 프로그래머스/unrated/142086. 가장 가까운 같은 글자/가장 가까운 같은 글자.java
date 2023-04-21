import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        String[] str = s.split("");
        for (int i = 0; i < answer.length; i++) {
			for (int j = i+1; j < answer.length; j++) {
                if(str[i].equals(str[j])){
                    answer[j] = j - i; 
                    continue;
                } 
			}
            if (answer[i] == 0) answer[i] = -1;
		}
        return answer;
    }
}