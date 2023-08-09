import java.util.*;
class Solution {
    public int[] solution(String s) {
        String[] arr = s.replaceAll("[{}]", "").split(",");
        
        Map<String, Integer> map = new HashMap<>();
        for(String str:arr)map.put(str, map.getOrDefault(str, 0)+1);
        
        int[] answer = new int[map.size()];
        for(String key : map.keySet())answer[answer.length-map.get(key)] = Integer.parseInt(key);
        return answer;
    }
}