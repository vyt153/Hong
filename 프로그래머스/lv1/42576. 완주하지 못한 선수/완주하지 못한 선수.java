import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
       String answer = "";
		
		int listLength = participant.length + completion.length;
		String[] List = new String[listLength];
		
		System.arraycopy(participant, 0, List, 0, participant.length);
		System.arraycopy(completion, 0, List, participant.length, completion.length);
		
		Map<String, Integer> map = new HashMap();
		for (int i = 0; i < List.length; i++) {
			if (!map.containsKey(List[i])) {
				map.put(List[i], 1);
			} else {
				map.remove(List[i]);
			}
		}
		
		for (String key : map.keySet()) {
			System.out.println(map.get(key));
			if (map.get(key)%2 == 1) {
				answer = key;
				break;
			}
		}
		return answer;
	}
}