import java.util.*;
class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        int[] hallOfFame = new int[k];
        
        answer[0] = score[0];
        hallOfFame[0] = score[0];
        int min = score[0];
        
        for(int i=1; i<k; i++){
            if(i==score.length) break;
            else {hallOfFame[i] = score[i];
                if(min>score[i]) min = score[i];
                answer[i] = min;    
            }
        }
        Arrays.sort(hallOfFame);
        
        if(k<score.length){
            for(int i=k; i<score.length ;i++){
                if(score[i]>hallOfFame[0]){
                    hallOfFame[0] = score[i];
                    Arrays.sort(hallOfFame);
                    answer[i] = hallOfFame[0];
                } else answer[i] = hallOfFame[0];
            }    
        }
        return answer;
    }
}