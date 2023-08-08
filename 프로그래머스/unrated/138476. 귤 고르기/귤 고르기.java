import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int key : tangerine) {
			map.put(key, map.getOrDefault(key, 0) + 1);
		}
        
        List<Integer> list = new ArrayList<>();
        
        for(Integer key : map.keySet()) list.add(map.get(key));
        Collections.sort(list, Collections.reverseOrder());
        
        for(Integer i : list){
            k -= i;
            answer++;
            if(k<=0) break;
        }
        return answer;
    }
}