import java.util.HashMap;
import java.util.Map;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        
        int[] answer = new int[photo.length];
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < name.length; i++) {
			map.put(name[i], yearning[i]);
		}
        
        for (int i = 0; i < photo.length; i++) {
		    for (int j = 0; j < photo[i].length; j++) {
			    answer[i] += map.containsKey(photo[i][j]) ? map.get(photo[i][j]):0;
		    }	
		}
		
        return answer;
    }
}