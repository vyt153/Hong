import java.util.*;
class Solution {
    public int[] solution(String s) {
        
        HashSet<String> set = new HashSet<>();
        String[] arr = s.replaceAll("[{}]", "").split(",");
        
        for(String str : arr) set.add(str);
        int[] answer = new int[set.size()];
        
        for(String tuple : set){
            int index = set.size();
            for(String str : arr){
                if(tuple.equals(str)) index--;
            }
            answer[index] = Integer.parseInt(tuple);
        }   
        return answer;
    }
}