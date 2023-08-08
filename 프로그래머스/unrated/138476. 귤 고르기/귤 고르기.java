import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int key : tangerine) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        List<Integer> sortedCount = new ArrayList<>(map.values());
        Collections.sort(sortedCount, Collections.reverseOrder());

        for(Integer count : sortedCount){
            k -= count;
            answer++;
            if(k<=0) break;
        }
        return answer;
    }
}